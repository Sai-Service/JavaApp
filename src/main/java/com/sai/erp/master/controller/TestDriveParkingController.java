/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sai.erp.master.dao.ParkingMasterDao;
import com.sai.erp.master.dao.SsDmsWsParkingDao;
import com.sai.erp.master.dto.ParkingInDto;
import com.sai.erp.master.dto.ParkingOutDto;
import com.sai.erp.master.dto.VehWashingReportMailDto;
import com.sai.erp.master.entity.CsiItemInstances;
import com.sai.erp.master.entity.SsDmsWsParking;
import com.sai.erp.master.service.VehParkingReportService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import net.minidev.json.JSONObject;
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
    @GetMapping("/vehParkInDet")
    public SaiResponse vehParkInDet(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;

        try {
            Optional<SsDmsWsParking> parkTable = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsDmsWsParking parkTable1 = parkTable.orElse(null);

            Optional<CsiItemInstances> vehMst = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances vehMst1 = vehMst.orElse(null);

            if (parkTable1 != null) {

                if (parkTable1.getInTime() == null && parkTable1.getOutTime() != null) {
                    List<Map> vehInPark = parkingRepo.getVehInParkingDetails(regNo);
                    apiResponse = new SaiResponse(200, "Details Found Successfully In Parking Table", vehInPark);

                } else if (parkTable1.getInTime() != null && parkTable1.getOutTime() != null) {
                    if (vehMst1 != null) {
                        List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);

                        if (!masterVeh.isEmpty()) {
                            apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
                        } else {
                            System.out.println("Vehicle present in master but no details found in query.");
                            parkTable1 = null;
                        }
                    } else {
                        System.out.println("Vehicle not found in master.");
                        parkTable1 = null;
                    }

                } else {
                    apiResponse = new SaiResponse(400, "Vehicle Already In", regNo);
                }

            }
            if (apiResponse == null && vehMst1 != null) {
                List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);

                if (!masterVeh.isEmpty()) {
                    apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
                } else {
                    System.out.println("Vehicle present in master but no details found in query.");
                    vehMst1 = null;
                }
            }
            if (apiResponse == null) {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("REGNO", regNo);

                List<JSONObject> newVehicleList = new ArrayList<>();
                newVehicleList.add(newVehicleJson);

                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Internal Server Error", e.getMessage());
        }

        return apiResponse;
    }

    //used to fetch veh details for parking out
    @GetMapping("/vehParkOutDet")
    public SaiResponse vehParkOutDet(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;

        try {
            Optional<SsDmsWsParking> parkTable = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsDmsWsParking parkTable1 = parkTable.orElse(null);

            Optional<CsiItemInstances> vehMst = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances vehMst1 = vehMst.orElse(null);

            if (parkTable1 != null) {

                if (parkTable1.getInTime() != null && parkTable1.getOutTime() == null) {
                    List<Map> vehOutPark = parkingRepo.getVehOutParkingDetails(regNo);
                    apiResponse = new SaiResponse(200, "Details Found Successfully In Parking Table", vehOutPark);

                } else if (parkTable1.getInTime() != null && parkTable1.getOutTime() != null) {
                    if (vehMst1 != null) {
                        List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);

                        if (!masterVeh.isEmpty()) {
                            apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
                        } else {
                            System.out.println("Vehicle present in master but no details found in query.");
                            parkTable1 = null; // allow fallback
                        }
                    } else {
                        System.out.println("Vehicle not found in master.");
                        parkTable1 = null; // allow fallback
                    }

                } else {
                    apiResponse = new SaiResponse(400, "Vehicle Already Out", regNo);
                }

            }

            if (apiResponse == null && vehMst1 != null) {
                List<Map> masterVeh = csiRepo.getVehDetailsCsiByRegNo(regNo);

                if (!masterVeh.isEmpty()) {
                    apiResponse = new SaiResponse(200, "Details Found Successfully In Master Table", masterVeh);
                } else {
                    System.out.println("Vehicle present in master but no details found in query.");
                    vehMst1 = null;
                }
            }
            if (apiResponse == null) {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("REGNO", regNo);

                List<JSONObject> newVehicleList = new ArrayList<>();
                newVehicleList.add(newVehicleJson);

                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Internal Server Error", e.getMessage());
        }

        return apiResponse;
    }

    //post for veh parking IN
    @PostMapping("/vehParkIn")
    public SaiResponse vehParkIn(@RequestBody ParkingInDto input) throws Exception {
        SaiResponse apiResponse;

        try {

            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            SsDmsWsParking parking;

            if (existing.isPresent()) {

                parking = existing.get();

                if (parking.getInTime() == null && parking.getOutTime() != null) {
//                    ParkingInUpdateDto update = new ParkingInUpdateDto();
//                    
//                    BeanUtils.copyProperties(update, parking);
                    parking.setInTime(dateTime);
                    if (input.getInKm() != null) {
                        parking.setInKm(input.getInKm());
                    }
                    parking.setDriverIn(input.getDriverIn());
                    parking.setGateNo(input.getGateNo());
                    parking.setGateType(input.getGateType());
                    parking.setRemarks(input.getRemarks());
                    parking.setStatus("IN");
                    parking.setUpdatedBy(input.getUpdatedBy());
                    parking.setUpdationDate(dateTime);

                    parkingRepo.save(parking);
                    apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);

                } else if (parking.getInTime() != null && parking.getOutTime() != null) {
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

                    parkingRepo.save(parkAgain);
                    apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);
                } else {
                    apiResponse = new SaiResponse(400, "Vehicle Already In", input.getRegNo());

                }
            } else {

                parking = new SsDmsWsParking();
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

                parkingRepo.save(parking);
                apiResponse = new SaiResponse(200, "Vehicle Successfully In", input);

            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vehicle In Failed", e.getMessage());
        }

        return apiResponse;
    }

    //post for veh parking out
    @PostMapping("/vehParkOut")
    public SaiResponse vehParkOut(@RequestBody ParkingOutDto input) throws Exception {
        SaiResponse apiResponse;

        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsWsParking> existing = parkingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            SsDmsWsParking parking;

            if (existing.isPresent()) {
                parking = existing.get();

                if (parking.getOutTime() == null && parking.getInTime() != null) {

//                    BeanUtils.copyProperties(input, parking);
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

                    SsDmsWsParking parkAgain = new SsDmsWsParking();
                    BeanUtils.copyProperties(input, parkAgain);
                    parkAgain.setRegNo(input.getRegNo());
                    parkAgain.setChassisNo(input.getChassisNo());
                    parkAgain.setEngineNo(input.getEngineNo());
                    parkAgain.setVin(input.getVin());
                    parkAgain.setDept(input.getDept());
                    parkAgain.setDriverOut(input.getDriverOut());
                    if (input.getOutKm() != null) {
                        parkAgain.setOutKm(input.getOutKm());
                    }
                    parkAgain.setOutTime(dateTime);
                    parkAgain.setLocId(input.getLocId());
                    parkAgain.setOuId(input.getOuId());
                    parkAgain.setLocation(input.getLocation());
                    parkAgain.setGateNo(input.getGateNo());
                    parkAgain.setGateType(input.getGateType());
                    parkAgain.setRemarks(input.getRemarks());
                    parkAgain.setCustName(input.getCustName());
                    parkAgain.setStatus("OUT");
                    parkAgain.setCreatedBy(input.getCreatedBy());
                    parkAgain.setCreationDate(dateTime);
                    parkAgain.setUpdatedBy(input.getUpdatedBy());
                    parkAgain.setUpdationDate(dateTime);
                    parkAgain.setParkingReason(input.getParkingReason());
                    parkAgain.setParkingDesc(input.getParkingDesc());
                    parkAgain.setAttribute1(input.getAttribute1());
                    parkAgain.setAttribute2(input.getAttribute2());
                    parkAgain.setAttribute3(input.getAttribute3());

                    parkingRepo.save(parkAgain);
                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);

                } else {
                    apiResponse = new SaiResponse(400, "Vehicle Already Out", input.getRegNo());

                }

            } else {

                parking = new SsDmsWsParking();
                BeanUtils.copyProperties(input, parking);
                parking.setRegNo(input.getRegNo());
                parking.setChassisNo(input.getChassisNo());
                parking.setEngineNo(input.getEngineNo());
                parking.setVin(input.getVin());
                parking.setDept(input.getDept());
                parking.setDriverOut(input.getDriverOut());
                if (input.getOutKm() != null) {
                    parking.setOutKm(input.getOutKm());
                }
                parking.setOutTime(dateTime);
                parking.setLocId(input.getLocId());
                parking.setOuId(input.getOuId());
                parking.setLocation(input.getLocation());
                parking.setGateNo(input.getGateNo());
                parking.setGateType(input.getGateType());
                parking.setRemarks(input.getRemarks());
                parking.setCustName(input.getCustName());
                parking.setStatus("OUT");
                parking.setCreatedBy(input.getCreatedBy());
                parking.setCreationDate(dateTime);
                parking.setUpdatedBy(input.getUpdatedBy());
                parking.setUpdationDate(dateTime);
                parking.setParkingDesc(input.getParkingDesc());
                parking.setParkingReason(input.getParkingReason());
                parking.setAttribute3(input.getAttribute3());
                
                parkingRepo.save(parking);
                apiResponse = new SaiResponse(200, "Vehicle Successfully Out", input);
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
