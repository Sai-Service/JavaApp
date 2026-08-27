/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import com.sai.erp.master.dao.JobCardDetailsForAppDao;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sai.erp.master.dao.ParkingMasterDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dao.SsDmsStockTruevalueDao;
import com.sai.erp.master.dao.SsDmsWsParkingDao;
import com.sai.erp.master.dto.ParkingInDto;
import com.sai.erp.master.dto.ParkingOutDto;
import com.sai.erp.master.dto.VehWashingReportMailDto;
import com.sai.erp.master.entity.SsDmsWsParking;
import com.sai.erp.master.service.VehParkingReportService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author HarshG
 */
@RestController
@RequestMapping("/tdParking")
public class TestDriveParkingController {

    @Autowired
    private ParkingMasterDao parkMstRepo;

    @Autowired
    private SsDmsWsParkingDao parkingRepo;

    @Autowired
    private CsiItemInstancesDao csiRepo;

    @Autowired
    private VehParkingReportService vehParkServ;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JobCardDetailsForAppDao jobCardRepo;

    @Autowired
    private SsDmsInvStockOriginalDao invStockRepo;

    @Autowired
    private SsDmsStockTruevalueDao tvStockRepo;

    private static final String DASH = "-";

    private String str(Object o) {
        return (o == null || o.toString().trim().isEmpty()) ? DASH : o.toString().trim();
    }

    /**
     * Returns null when the vehicle has no job card at all.
     */
    private Map<String, String> fetchLatestJobCardInfo(String regNo) {
        try {
            List<Map> rows = jobCardRepo.getLatestJobCardByRegNo(regNo);
            if (rows == null || rows.isEmpty()) {
                return null;
            }
            Map row = rows.get(0);
            Map<String, String> info = new HashMap<>();
            info.put("SERVICE_ADVISOR", str(row.get("SERVICE_ADVISOR")));
            info.put("TRANSSEGMENT", str(row.get("TRANSSEGMENT")));
            info.put("DEPTALLOTED", str(row.get("DEPTALLOTED")));
            info.put("JOB_CARD_NO", str(row.get("JOB_CARD_NO")));
            return info;
        } catch (Exception e) {
            System.out.println("Job card lookup failed for " + regNo + " : " + e.getMessage());
            return null;   // never break the parking screen because of job card data
        }
    }

    private List<Map> applyJobCardInfo(List<Map> rows, String regNo) {
        Map<String, String> jc = fetchLatestJobCardInfo(regNo);
        List<Map> result = new ArrayList<>();
        for (Map row : rows) {
            Map<String, Object> m = new LinkedHashMap<>(row);   // native-query maps can be read-only
            if (jc != null) {
                m.put("SERVICE_ADVISOR", jc.get("SERVICE_ADVISOR"));
                m.put("TRANSSEGMENT", jc.get("TRANSSEGMENT"));
                m.put("DEPTALLOTED", jc.get("DEPTALLOTED"));
                m.put("JOB_CARD_NO", jc.get("JOB_CARD_NO"));
            } else {
                m.putIfAbsent("SERVICE_ADVISOR", DASH);
                m.putIfAbsent("TRANSSEGMENT", DASH);
                m.putIfAbsent("DEPTALLOTED", DASH);
                m.putIfAbsent("JOB_CARD_NO", DASH);
            }
            result.add(m);
        }
        return result;
    }

    private boolean usable(String v) {
        if (v == null) {
            return false;
        }
        String s = v.trim();
        return !s.isEmpty() && !DASH.equals(s);
    }

    /**
     * Both chassis AND engine must be present and must both match the same row.
     * Precedence: SS_DMS_INV_STOCK (DEMO CAR / SALES) -> SS_DMS_STOCK_TV
     * (TRUEVALUE) -> "-".
     */
    private String resolveVehType(String chassisNo, String engineNo) {

        if (!usable(chassisNo) || !usable(engineNo)) {
            return DASH;                       // new vehicle / incomplete master data
        }

        String c = chassisNo.trim();
        String e = engineNo.trim();

        try {
            List<Map> inv = invStockRepo.findStockByChassisAndEngine(c, e);
            if (inv != null && !inv.isEmpty()) {
                return str(inv.get(0).get("VEH_TYPE"));      // DEMO CAR or SALES
            }

            List<Map> tv = tvStockRepo.findTvStockByChassisAndEngine(c, e);
            if (tv != null && !tv.isEmpty()) {
                return "TRUEVALUE";
            }
        } catch (Exception ex) {
            System.out.println("Stock lookup failed for chassis " + c + " : " + ex.getMessage());
        }
        return DASH;
    }

    private List<Map> enrichRows(List<Map> rows, String regNo) {
        Map<String, String> jc = fetchLatestJobCardInfo(regNo);
        List<Map> out = new ArrayList<>();

        for (Map row : rows) {
            Map<String, Object> m = new LinkedHashMap<>(row);

            if (jc != null) {
                m.put("SERVICE_ADVISOR", jc.get("SERVICE_ADVISOR"));
                m.put("TRANSSEGMENT", jc.get("TRANSSEGMENT"));
                m.put("DEPTALLOTED", jc.get("DEPTALLOTED"));
                m.put("JOB_CARD_NO", jc.get("JOB_CARD_NO"));
            } else {
                m.putIfAbsent("SERVICE_ADVISOR", DASH);
                m.putIfAbsent("TRANSSEGMENT", DASH);
                m.putIfAbsent("DEPTALLOTED", DASH);
                m.putIfAbsent("JOB_CARD_NO", DASH);
            }

            m.put("VEH_TYPE", resolveVehType(str(m.get("CHASSIS_NO")),
                    str(m.get("ENGINE_NO"))));
            out.add(m);
        }
        return out;
    }

    @GetMapping("/getDepartment")
    public SaiResponse getDepartment(@RequestParam Integer ouId, @RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> deptList = parkMstRepo.getDepartmentByOuIdAndLocId(ouId, locId);

            if (!deptList.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", deptList);
            } else {
                apiResponse = new SaiResponse(400, "Details not found", "Details not found");

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/getDriverName")
    public SaiResponse getDriverName(@RequestParam Integer ouId, @RequestParam Integer locId, @RequestParam String department) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> drivers = parkMstRepo.getDriverNameByOuIdAndLocIdAndDepartment(ouId, locId, department);

            if (!drivers.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", drivers);
            } else {
                apiResponse = new SaiResponse(400, "Details not found", "Details not found");

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/getBayName")
    public SaiResponse getBayName(@RequestParam Integer ouId, @RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> drivers = parkMstRepo.getBayByOuIdAndLocIdAndDepartment(ouId, locId);

            if (!drivers.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", drivers);
            } else {
                apiResponse = new SaiResponse(400, "Details not found", "Details not found");

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //--------Parking module-------------//
    //used to fetch veh details for park in
//    @GetMapping("/vehParkInDet")
//    public SaiResponse vehParkInDet(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse = null;
//
//        try {
//            Optional<SsDmsWsParking> parkTable = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
//            SsDmsWsParking parkTable1 = parkTable.orElse(null);
//
//            Optional<CsiItemInstances> vehMst = csiRepo.findByInstanceNumber(regNo);
//            CsiItemInstances vehMst1 = vehMst.orElse(null);
//
//            if (parkTable1 != null) {
//
//                if (parkTable1.getInTime() == null && parkTable1.getOutTime() != null) {
//                    List<Map> vehInPark = parkingRepo.getVehInParkingDetails(regNo);
//                    apiResponse = new SaiResponse(200, "Details Found Successfully In Parking Table", vehInPark);
//
//                } else if (parkTable1.getInTime() != null && parkTable1.getOutTime() != null) {
//                    if (vehMst1 != null) {
//                        List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);
//
//                        if (!masterVeh.isEmpty()) {
//                            apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
//                        } else {
//                            System.out.println("Vehicle present in master but no details found in query.");
//                            parkTable1 = null;
//                        }
//                    } else {
//                        System.out.println("Vehicle not found in master.");
//                        parkTable1 = null;
//                    }
//
//                } else {
//                    apiResponse = new SaiResponse(400, "Vehicle Already In", regNo);
//                }
//
//            }
//            if (apiResponse == null && vehMst1 != null) {
//                List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);
//
//                if (!masterVeh.isEmpty()) {
//                    apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
//                } else {
//                    System.out.println("Vehicle present in master but no details found in query.");
//                    vehMst1 = null;
//                }
//            }
//            if (apiResponse == null) {
//                JSONObject newVehicleJson = new JSONObject();
//                newVehicleJson.put("REGNO", regNo);
//
//                List<JSONObject> newVehicleList = new ArrayList<>();
//                newVehicleList.add(newVehicleJson);
//
//                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
//            }
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Internal Server Error", e.getMessage());
//        }
//
//        return apiResponse;
//    }
    @GetMapping("/vehParkInDet")
    public SaiResponse vehParkInDet(@RequestParam String regNo) {
        try {
            Optional<SsDmsWsParking> lastRec = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);

            if (lastRec.isPresent()) {
                SsDmsWsParking p = lastRec.get();

                if (p.getInTime() != null && p.getOutTime() == null) {
                    return new SaiResponse(400, "Vehicle Already In", regNo);
                }
                if (p.getInTime() == null) {
                    return new SaiResponse(400, "Invalid Parking State - Contact Admin", regNo);
                }
                // cycle closed -> fresh entry, fall through to master
            }

            List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);
            if (masterVeh != null && !masterVeh.isEmpty()) {
                return new SaiResponse(200, "Details Found Successfully In Master Table",
                        enrichRows(masterVeh, regNo));
            }

            return new SaiResponse(200, "New Vehicle", buildNewVehicleResponse(regNo));

        } catch (Exception e) {
            return new SaiResponse(400, "Internal Server Error", e.getMessage());
        }
    }

    private List<Map> buildNewVehicleResponse(String regNo) {
        Map<String, Object> newVeh = new LinkedHashMap<>();
        newVeh.put("REG_NO", regNo);
        newVeh.put("CHASSIS_NO", DASH);
        newVeh.put("ENGINE_NO", DASH);
        newVeh.put("VIN", DASH);
        newVeh.put("CUST_NAME", DASH);
        newVeh.put("VEHICLE_DESC", DASH);
        newVeh.put("MODEL_DESC", DASH);
        newVeh.put("REGISTRATION_DATE", DASH);
        newVeh.put("ACCOUNT_NUMBER", DASH);
        newVeh.put("SERVICE_ADVISOR", DASH);
        newVeh.put("TRANSSEGMENT", DASH);
        newVeh.put("DEPTALLOTED", DASH);
        newVeh.put("VEH_TYPE", DASH);

        List<Map> list = new ArrayList<>();
        list.add(newVeh);
        return enrichRows(list, regNo);
    }

    //used to fetch veh details for parking out
//    @GetMapping("/vehParkOutDet")
//    public SaiResponse vehParkOutDet(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse = null;
//
//        try {
//            Optional<SsDmsWsParking> parkTable = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
//            SsDmsWsParking parkTable1 = parkTable.orElse(null);
//
//            Optional<CsiItemInstances> vehMst = csiRepo.findByInstanceNumber(regNo);
//            CsiItemInstances vehMst1 = vehMst.orElse(null);
//
//            if (parkTable1 != null) {
//
//                if (parkTable1.getInTime() != null && parkTable1.getOutTime() == null) {
//                    List<Map> vehOutPark = parkingRepo.getVehOutParkingDetails(regNo);
//                    apiResponse = new SaiResponse(200, "Details Found Successfully In Parking Table", vehOutPark);
//
//                } else if (parkTable1.getInTime() != null && parkTable1.getOutTime() != null) {
//                    if (vehMst1 != null) {
//                        List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);
//
//                        if (!masterVeh.isEmpty()) {
//                            apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
//                        } else {
//                            System.out.println("Vehicle present in master but no details found in query.");
//                            parkTable1 = null; // allow fallback
//                        }
//                    } else {
//                        System.out.println("Vehicle not found in master.");
//                        parkTable1 = null; // allow fallback
//                    }
//
//                } else {
//                    apiResponse = new SaiResponse(400, "Vehicle Already Out", regNo);
//                }
//
//            }
//
//            if (apiResponse == null && vehMst1 != null) {
//                List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);
//
//                if (!masterVeh.isEmpty()) {
//                    apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
//                } else {
//                    System.out.println("Vehicle present in master but no details found in query.");
//                    vehMst1 = null;
//                }
//            }
//            if (apiResponse == null) {
//                JSONObject newVehicleJson = new JSONObject();
//                newVehicleJson.put("REGNO", regNo);
//
//                List<JSONObject> newVehicleList = new ArrayList<>();
//                newVehicleList.add(newVehicleJson);
//
//                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
//            }
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Internal Server Error", e.getMessage());
//        }
//
//        return apiResponse;
//    }
    @GetMapping("/vehParkOutDet")
    public SaiResponse vehParkOutDet(@RequestParam String regNo) {
        try {
            Optional<SsDmsWsParking> lastRec = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);

            if (!lastRec.isPresent()) {
                return new SaiResponse(400, "Vehicle Not Checked In - IN Required Before OUT", regNo);
            }

            SsDmsWsParking p = lastRec.get();

            if (p.getInTime() != null && p.getOutTime() == null) {
                List<Map> vehOutPark = parkingRepo.getVehOutParkingDetails(regNo);
                if (vehOutPark == null || vehOutPark.isEmpty()) {
                    return new SaiResponse(400, "Invalid Parking State - Contact Admin", regNo);
                }
                return new SaiResponse(200, "Details Found Successfully In Parking Table",
                        applyJobCardInfo(vehOutPark, regNo));
            }

            if (p.getInTime() != null && p.getOutTime() != null) {
                return new SaiResponse(400, "Vehicle Already Out - IN Required Before Next OUT", regNo);
            }

            return new SaiResponse(400, "Invalid Parking State - Contact Admin", regNo);

        } catch (Exception e) {
            return new SaiResponse(400, "Internal Server Error", e.getMessage());
        }
    }

    //post for veh parking IN
//    @PostMapping("/vehParkIn")
//    public SaiResponse vehParkIn(@RequestBody ParkingInDto input) throws Exception {
//        SaiResponse apiResponse;
//
//        try {
//
//            LocalDateTime now = LocalDateTime.now();
//            Timestamp dateTime = Timestamp.valueOf(now);
//
//            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
//
//            SsDmsWsParking parking;
//
//            if (existing.isPresent()) {
//
//                parking = existing.get();
//
//                if (parking.getInTime() == null && parking.getOutTime() != null) {
//
//                    parking.setInTime(dateTime);
//                    if (input.getInKm() != null) {
//                        parking.setInKm(input.getInKm());
//                    }
//                    parking.setDriverIn(input.getDriverIn());
//                    parking.setGateNo(input.getGateNo());
//                    parking.setGateType(input.getGateType());
//                    parking.setRemarks(input.getRemarks());
//                    parking.setStatus("IN");
//                    parking.setUpdatedBy(input.getUpdatedBy());
//                    parking.setUpdationDate(dateTime);
//
//                    parkingRepo.save(parking);
//                    apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);
//
//                } else if (parking.getInTime() != null && parking.getOutTime() != null) {
//                    SsDmsWsParking parkAgain = new SsDmsWsParking();
//                    BeanUtils.copyProperties(input, parkAgain);
//                    parkAgain.setRegNo(input.getRegNo());
//                    parkAgain.setChassisNo(input.getChassisNo());
//                    parkAgain.setEngineNo(input.getEngineNo());
//                    parkAgain.setVin(input.getVin());
//                    parkAgain.setDept(input.getDept());
//                    parkAgain.setDriverIn(input.getDriverIn());
//                    if (input.getInKm() != null) {
//                        parkAgain.setInKm(input.getInKm());
//                    }
//                    parkAgain.setInTime(dateTime);
//                    parkAgain.setLocId(input.getLocId());
//                    parkAgain.setOuId(input.getOuId());
//                    parkAgain.setLocation(input.getLocation());
//                    parkAgain.setGateNo(input.getGateNo());
//                    parkAgain.setGateType(input.getGateType());
//                    parkAgain.setRemarks(input.getRemarks());
//                    parkAgain.setCustName(input.getCustName());
//                    parkAgain.setStatus("IN");
//                    parkAgain.setCreatedBy(input.getCreatedBy());
//                    parkAgain.setCreationDate(dateTime);
//                    parkAgain.setUpdatedBy(input.getUpdatedBy());
//                    parkAgain.setUpdationDate(dateTime);
//                    parkAgain.setAttribute1(input.getAttribute1());
//                    parkAgain.setAttribute2(input.getAttribute2());
//                    parkAgain.setAttribute3(input.getAttribute3());
//
//                    parkingRepo.save(parkAgain);
//                    apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);
//                } else {
//                    apiResponse = new SaiResponse(400, "Vehicle Already In", input.getRegNo());
//
//                }
//            } else {
//
//                parking = new SsDmsWsParking();
//                BeanUtils.copyProperties(input, parking);
//                parking.setRegNo(input.getRegNo());
//                parking.setChassisNo(input.getChassisNo());
//                parking.setEngineNo(input.getEngineNo());
//                parking.setVin(input.getVin());
//                parking.setDept(input.getDept());
//                parking.setDriverIn(input.getDriverIn());
//                if (input.getInKm() != null) {
//                    parking.setInKm(input.getInKm());
//                }
//                parking.setInTime(dateTime);
//                parking.setLocId(input.getLocId());
//                parking.setOuId(input.getOuId());
//                parking.setLocation(input.getLocation());
//                parking.setGateNo(input.getGateNo());
//                parking.setGateType(input.getGateType());
//                parking.setRemarks(input.getRemarks());
//                parking.setCustName(input.getCustName());
//                parking.setStatus("IN");
//                parking.setCreatedBy(input.getCreatedBy());
//                parking.setCreationDate(dateTime);
//                parking.setUpdatedBy(input.getUpdatedBy());
//                parking.setUpdationDate(dateTime);
//                parking.setAttribute1(input.getAttribute1());
//                parking.setAttribute2(input.getAttribute2());
//                parking.setAttribute3(input.getAttribute3());
//
//                parkingRepo.save(parking);
//                apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);
//
//            }
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Vehicle In Failed", e.getMessage());
//        }
//
//        return apiResponse;
//    }
    @PostMapping("/vehParkIn")
    public SaiResponse vehParkIn(@RequestBody ParkingInDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            if (existing.isPresent()) {
                SsDmsWsParking parking = existing.get();

                if (parking.getInTime() != null && parking.getOutTime() == null) {
                    // currently IN — block
                    apiResponse = new SaiResponse(400, "Vehicle Already In", input.getRegNo());

                } else if (parking.getInTime() != null && parking.getOutTime() != null) {
                    // cycle closed — new IN record
                    SsDmsWsParking parkAgain = new SsDmsWsParking();
                    BeanUtils.copyProperties(input, parkAgain);
                    parkAgain.setRegNo(input.getRegNo());
                    parkAgain.setChassisNo(input.getChassisNo());
                    parkAgain.setEngineNo(input.getEngineNo());
                    parkAgain.setVin(input.getVin());
                    parkAgain.setDept(input.getDept());
                    parkAgain.setDriverIn(input.getDriverIn());
                    if (input.getInKm() != null) {
                        parkAgain.setInKm(input.getInKm());
                    }
                    parkAgain.setInTime(dateTime);
                    parkAgain.setLocId(input.getLocId());
                    parkAgain.setOuId(input.getOuId());
                    parkAgain.setLocation(input.getLocation());
                    parkAgain.setGateNo(input.getGateNo());
                    parkAgain.setGateType(input.getGateType());
                    parkAgain.setRemarks(input.getRemarks());
                    parkAgain.setCustName(input.getCustName());
                    parkAgain.setStatus("IN");
                    parkAgain.setCreatedBy(input.getCreatedBy());
                    parkAgain.setCreationDate(dateTime);
                    parkAgain.setUpdatedBy(input.getUpdatedBy());
                    parkAgain.setUpdationDate(dateTime);
                    parkAgain.setAttribute1(input.getAttribute1());
                    parkAgain.setAttribute2(input.getAttribute2());
                    parkAgain.setAttribute3(input.getAttribute3());
                    parkAgain.setAttribute4(input.getAttribute4());

                    parkAgain.setParkingDesc(input.getParkingDesc());

                    parkingRepo.save(parkAgain);
                    apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);

                } else {
                    // corrupt/unexpected state (e.g. inTime null but outTime set) — reject, don't silently patch
                    apiResponse = new SaiResponse(400, "Invalid Parking State - Contact Admin", input.getRegNo());
                }

            } else {
                // no record at all — new IN
                SsDmsWsParking parking = new SsDmsWsParking();
                BeanUtils.copyProperties(input, parking);
                parking.setRegNo(input.getRegNo());
                parking.setChassisNo(input.getChassisNo());
                parking.setEngineNo(input.getEngineNo());
                parking.setVin(input.getVin());
                parking.setDept(input.getDept());
                parking.setDriverIn(input.getDriverIn());
                if (input.getInKm() != null) {
                    parking.setInKm(input.getInKm());
                }
                parking.setInTime(dateTime);
                parking.setLocId(input.getLocId());
                parking.setOuId(input.getOuId());
                parking.setLocation(input.getLocation());
                parking.setGateNo(input.getGateNo());
                parking.setGateType(input.getGateType());
                parking.setRemarks(input.getRemarks());
                parking.setCustName(input.getCustName());
                parking.setStatus("IN");
                parking.setCreatedBy(input.getCreatedBy());
                parking.setCreationDate(dateTime);
                parking.setUpdatedBy(input.getUpdatedBy());
                parking.setUpdationDate(dateTime);
                parking.setAttribute1(input.getAttribute1());
                parking.setAttribute2(input.getAttribute2());
                parking.setAttribute3(input.getAttribute3());
                
                parking.setAttribute4(input.getAttribute4());
                parking.setParkingDesc(input.getParkingDesc());

                parkingRepo.save(parking);
                apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vehicle In Failed", e.getMessage());
        }
        return apiResponse;
    }

    //post for veh parking out
//    @PostMapping("/vehParkOut")
//    public SaiResponse vehParkOut(@RequestBody ParkingOutDto input) throws Exception {
//        SaiResponse apiResponse;
//
//        try {
//            LocalDateTime now = LocalDateTime.now();
//            Timestamp dateTime = Timestamp.valueOf(now);
//
//            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
//
//            SsDmsWsParking parking;
//
//            if (existing.isPresent()) {
//                parking = existing.get();
//
//                if (parking.getOutTime() == null && parking.getInTime() != null) {
//
////                    BeanUtils.copyProperties(input, parking);
//                    parking.setOutTime(dateTime);
//                    if (input.getOutKm() != null) {
//                        parking.setOutKm(input.getOutKm());
//                    }
//                    parking.setDriverOut(input.getDriverOut());
//                    parking.setGateNo(input.getGateNo());
//                    parking.setGateType(input.getGateType());
//                    parking.setRemarks(input.getRemarks());
//                    parking.setStatus("OUT");
//                    parking.setUpdatedBy(input.getUpdatedBy());
//                    parking.setUpdationDate(dateTime);
//                    parking.setParkingReason(input.getParkingReason());
//                    parking.setParkingDesc(input.getParkingDesc());
//
//                    parkingRepo.save(parking);
//                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);
//                } else if (parking.getInTime() != null && parking.getOutTime() != null) {
//
//                    SsDmsWsParking parkAgain = new SsDmsWsParking();
//                    BeanUtils.copyProperties(input, parkAgain);
//                    parkAgain.setRegNo(input.getRegNo());
//                    parkAgain.setChassisNo(input.getChassisNo());
//                    parkAgain.setEngineNo(input.getEngineNo());
//                    parkAgain.setVin(input.getVin());
//                    parkAgain.setDept(input.getDept());
//                    parkAgain.setDriverOut(input.getDriverOut());
//                    if (input.getOutKm() != null) {
//                        parkAgain.setOutKm(input.getOutKm());
//                    }
//                    parkAgain.setOutTime(dateTime);
//                    parkAgain.setLocId(input.getLocId());
//                    parkAgain.setOuId(input.getOuId());
//                    parkAgain.setLocation(input.getLocation());
//                    parkAgain.setGateNo(input.getGateNo());
//                    parkAgain.setGateType(input.getGateType());
//                    parkAgain.setRemarks(input.getRemarks());
//                    parkAgain.setCustName(input.getCustName());
//                    parkAgain.setStatus("OUT");
//                    parkAgain.setCreatedBy(input.getCreatedBy());
//                    parkAgain.setCreationDate(dateTime);
//                    parkAgain.setUpdatedBy(input.getUpdatedBy());
//                    parkAgain.setUpdationDate(dateTime);
//                    parkAgain.setParkingReason(input.getParkingReason());
//                    parkAgain.setParkingDesc(input.getParkingDesc());
//                    parkAgain.setAttribute1(input.getAttribute1());
//                    parkAgain.setAttribute2(input.getAttribute2());
//                    parkAgain.setAttribute3(input.getAttribute3());
//
//                    parkingRepo.save(parkAgain);
//                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);
//
//                } else {
//                    apiResponse = new SaiResponse(400, "Vehicle Already Out", input.getRegNo());
//
//                }
//
//            } else {
//
//                parking = new SsDmsWsParking();
//                BeanUtils.copyProperties(input, parking);
//                parking.setRegNo(input.getRegNo());
//                parking.setChassisNo(input.getChassisNo());
//                parking.setEngineNo(input.getEngineNo());
//                parking.setVin(input.getVin());
//                parking.setDept(input.getDept());
//                parking.setDriverOut(input.getDriverOut());
//                if (input.getOutKm() != null) {
//                    parking.setOutKm(input.getOutKm());
//                }
//                parking.setOutTime(dateTime);
//                parking.setLocId(input.getLocId());
//                parking.setOuId(input.getOuId());
//                parking.setLocation(input.getLocation());
//                parking.setGateNo(input.getGateNo());
//                parking.setGateType(input.getGateType());
//                parking.setRemarks(input.getRemarks());
//                parking.setCustName(input.getCustName());
//                parking.setStatus("OUT");
//                parking.setCreatedBy(input.getCreatedBy());
//                parking.setCreationDate(dateTime);
//                parking.setUpdatedBy(input.getUpdatedBy());
//                parking.setUpdationDate(dateTime);
//                parking.setParkingDesc(input.getParkingDesc());
//                parking.setParkingReason(input.getParkingReason());
//                parking.setAttribute3(input.getAttribute3());
//
//                parkingRepo.save(parking);
//                apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);
//            }
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Vehicle Out Failed", e.getMessage());
//        }
//
//        return apiResponse;
//    }
    @PostMapping("/vehParkOut")
    public SaiResponse vehParkOut(@RequestBody ParkingOutDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            if (existing.isPresent()) {
                SsDmsWsParking parking = existing.get();

                if (parking.getInTime() != null && parking.getOutTime() == null) {
                    // vehicle currently IN — OUT allowed
                    parking.setOutTime(dateTime);
                    if (input.getOutKm() != null) {
                        parking.setOutKm(input.getOutKm());
                    }
                    parking.setDriverOut(input.getDriverOut());
                    parking.setGateNo(input.getGateNo());
                    parking.setGateType(input.getGateType());
                    parking.setRemarks(input.getRemarks());
                    parking.setStatus("OUT");
                    parking.setUpdatedBy(input.getUpdatedBy());
                    parking.setUpdationDate(dateTime);
                    parking.setParkingReason(input.getParkingReason());
                    parking.setParkingDesc(input.getParkingDesc());

                    parkingRepo.save(parking);
                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);

                } else if (parking.getInTime() != null && parking.getOutTime() != null) {
                    // already closed — must IN again before OUT
                    apiResponse = new SaiResponse(400, "Vehicle Already Out - IN Required Before Next OUT", input.getRegNo());

                } else {
                    // inTime null — vehicle was never checked in, OUT not allowed
                    apiResponse = new SaiResponse(400, "Vehicle Not Checked In - IN Required Before OUT", input.getRegNo());
                }

            } else {
                // no record at all — OUT not allowed without IN first
                apiResponse = new SaiResponse(400, "Vehicle Not Checked In - IN Required Before OUT", input.getRegNo());
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vehicle Out Failed", e.getMessage());
        }
        return apiResponse;
    }

    //report for parking
    @GetMapping("/parkingDetailsByOu")
    public SaiResponse parkingDetailsByOu(@RequestParam Integer ouId, @RequestParam Integer locId, @RequestParam Date fromDate, @RequestParam Date toDate)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> parkDetails = parkingRepo.getParkingDetails(ouId, locId, fromDate, toDate);

            apiResponse = new SaiResponse(200, "Details Found Successfully", parkDetails);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //parking history by regno
    @GetMapping("/parkingHistory")
    public SaiResponse parkingHistory(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> parkHist = parkingRepo.getParkingHistory(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", parkHist);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //list of already park in vehicles
    @GetMapping("/vehInList")
    public SaiResponse vehInList(@RequestParam String dept, @RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> vehList = parkingRepo.getVehInList(dept, locId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", vehList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/getParkLoc")
    public SaiResponse getParkLoc(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> vehList = parkingRepo.getParkLocations(location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", vehList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used to mail the veh PARKING report as per jasper to multiple users
    @PostMapping("/sendParkingReportMail")
    public ResponseEntity<SaiResponse> sendParkingReportMail(@RequestBody VehWashingReportMailDto washMail) throws Exception {

        try {
            // Prepare report parameters
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("ouId", washMail.getOuId());
            parameter.put("locId", washMail.getLocId());
            parameter.put("fromDate", washMail.getFromDate());
            parameter.put("toDate", washMail.getToDate());

            String fileName = "VehParkingReport.xls";

            // Generate the report
//            ByteArrayInputStream reportStream = vehWashReportService.getVehWashMainReport(parameter, fileName);
            // Convert InputStream to byte[] for attachment
            byte[] attachmentBytes = null;

            attachmentBytes = vehParkServ.getParkingReport(parameter, fileName);

            // Send email with attachment
            sendReportByEmail(washMail.getRecipients(), attachmentBytes, fileName);

            SaiResponse apiResponse = new SaiResponse(200, "Report generated and emailed successfully.", fileName);
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            e.printStackTrace();
            SaiResponse apiResponse = new SaiResponse(500, "Error while sending email: ", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    private void sendReportByEmail(List<String> recipients, byte[] reportBytes, String fileName) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String senderEmail = "edpborivali@saiservice.com";

        helper.setFrom(senderEmail);
        helper.setTo(recipients.toArray(new String[0]));
        helper.setSubject("Vehicle Parking Report");
        helper.setText("Please Find Attached Vehicle Parking Report.");

        // Attach the Excel report
        helper.addAttachment(fileName, new ByteArrayResource(reportBytes));

        mailSender.send(message);
    }
}
