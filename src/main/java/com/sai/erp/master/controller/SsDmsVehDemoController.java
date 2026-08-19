/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsDmsManualGpVhDao;
import com.sai.erp.master.dao.SsDmsVehDemoDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.dto.DemoVehInDto;
import com.sai.erp.master.dto.DemoVehOutDto;
import com.sai.erp.master.entity.SsDmsManualGpVh;
import com.sai.erp.master.entity.SsDmsVehDemo;
import com.sai.erp.master.entity.SsVehStockLogin;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HarshG
 */
@RestController
@RequestMapping("/demoVehicleTrans")
public class SsDmsVehDemoController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SsDmsVehDemoDao demoVehRepo;

    @Autowired
    private SsVehStockLoginDao loginRepo;

    @Autowired
    private SsDmsManualGpVhDao manualGatePassRepo;

    //used for getting demo veh details by chassisno for demo out 
    @GetMapping("/getDemoVehByChassisNo")
    public SaiResponse getDemoVehByChassisNo(@RequestParam String chassisNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = demoVehRepo.getDemoVehDetailsByChassisNo(chassisNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    //used for getting demo veh details by regNo for demo out 
//    @GetMapping("/getDemoVehByRegNo")
//    public SaiResponse getDemoVehByRegNo(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//
//            Optional<SsDmsVehDemo> existDemoVeh = demoVehRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
//            SsDmsVehDemo existDemoVeh1 = existDemoVeh.isPresent() ? existDemoVeh.get() : null;
//
//            if (existDemoVeh1 != null && existDemoVeh1.getOutKm() != null && existDemoVeh1.getInKm() == null && existDemoVeh1.getGatePassNo() != null) {
//
//                apiResponse = new SaiResponse(400, "Vehicle already out for demo with customer - " + existDemoVeh1.getCustName() + ", by executive - " + existDemoVeh1.getCreatedBy(), existDemoVeh1.getRegNo());
//                return apiResponse;
//            } else if (existDemoVeh1 != null && existDemoVeh1.getOutKm() != null && existDemoVeh1.getInKm() != null && existDemoVeh1.getGatePassNo() != null) {
//
//                List<Map> codeList = demoVehRepo.getDemoVehDetailsPresentByRegNo(regNo);
//
//                apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//            } else {
//
//                List<Map> codeList = demoVehRepo.getDemoVehDetailsByRegNo(regNo);
//
//                apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//            }
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
//        }
//        return apiResponse;
//
//    }
    //updated api with location passed
//    @GetMapping("/getDemoVehByRegNo")
//    public SaiResponse getDemoVehByRegNo(@RequestParam String regNo, @RequestParam String location) throws Exception {
//        SaiResponse apiResponse;
//        try {
//
//            Optional<SsDmsVehDemo> existDemoVeh = demoVehRepo.findFirstByRegNoAndLocationOrderByCreationDateDesc(regNo, location);
//            SsDmsVehDemo existDemoVeh1 = existDemoVeh.isPresent() ? existDemoVeh.get() : null;
//
//            if (existDemoVeh1 != null && existDemoVeh1.getOutKm() != null && existDemoVeh1.getInKm() == null && existDemoVeh1.getGatePassNo() != null) {
//
//                apiResponse = new SaiResponse(400, "Vehicle already out for demo with customer - " + existDemoVeh1.getCustName() + ", by executive - " + existDemoVeh1.getCreatedBy(), existDemoVeh1.getRegNo());
//                return apiResponse;
//            } else if (existDemoVeh1 != null && existDemoVeh1.getOutKm() != null && existDemoVeh1.getInKm() != null && existDemoVeh1.getGatePassNo() != null) {
//
//                List<Map> codeList = demoVehRepo.getDemoVehDetailsPresentByRegNo(regNo, location);
//
//                if (codeList != null && !codeList.isEmpty()) {
//                    apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//
//                } else {
//                    apiResponse = new SaiResponse(400, "Details not found", null);
//
//                }
//
//            } else {
//
//                List<Map> codeList = demoVehRepo.getDemoVehDetailsByRegNo(regNo, location);
//
//                if (codeList != null && !codeList.isEmpty()) {
//                    apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//
//                } else {
//                    apiResponse = new SaiResponse(400, "Details not found", null);
//
//                }
//
//            }
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
//        }
//        return apiResponse;
//
//    }
    //UPDATED API WITH LOCATION AND LOGIN PASSED FOR ARENA NEXA BIFUR
    @GetMapping("/getDemoVehByRegNo")
    public SaiResponse getDemoVehByRegNo(
            @RequestParam String regNo,
            @RequestParam String location,
            @RequestParam String loginName) throws Exception {
        SaiResponse apiResponse;

        try {

            Optional<SsVehStockLogin> loginOpt = loginRepo.findByLoginName(loginName);

            if (!loginOpt.isPresent()) {
                return new SaiResponse(
                        400,
                        "Login details not found",
                        null
                );
            }

            SsVehStockLogin loginUser = loginOpt.get();

            String salesType = loginUser.getAttribute2();

            if (salesType == null || salesType.trim().isEmpty()) {
                return new SaiResponse(
                        400,
                        "Arena/Nexa configuration not found for login",
                        null
                );
            }

            salesType = salesType.trim().toUpperCase();

            // Only ARENA / NEXA are allowed
            if (!salesType.equals("ARENA")
                    && !salesType.equals("NEXA")) {

                return new SaiResponse(
                        400,
                        "Invalid Arena/Nexa configuration for login",
                        null
                );
            }

            Optional<SsDmsVehDemo> existDemoVeh
                    = demoVehRepo.findFirstByRegNoAndLocationOrderByCreationDateDesc(
                            regNo,
                            location
                    );

            SsDmsVehDemo existDemoVeh1
                    = existDemoVeh.isPresent()
                    ? existDemoVeh.get()
                    : null;

            if (existDemoVeh1 != null
                    && existDemoVeh1.getOutKm() != null
                    && existDemoVeh1.getInKm() == null
                    && existDemoVeh1.getGatePassNo() != null) {

                List<Map> vehicleList
                        = demoVehRepo.getDemoVehDetailsByRegNo(
                                regNo,
                                location,
                                salesType
                        );

                if (vehicleList == null || vehicleList.isEmpty()) {

                    return new SaiResponse(
                            400,
                            "Vehicle not available for " + salesType + " login",
                            null
                    );
                }

                return new SaiResponse(
                        400,
                        "Vehicle already out for demo with customer - "
                        + existDemoVeh1.getCustName()
                        + ", by executive - "
                        + existDemoVeh1.getCreatedBy(),
                        existDemoVeh1.getRegNo()
                );
            } else if (existDemoVeh1 != null
                    && existDemoVeh1.getOutKm() != null
                    && existDemoVeh1.getInKm() != null
                    && existDemoVeh1.getGatePassNo() != null) {

                List<Map> codeList
                        = demoVehRepo.getDemoVehDetailsPresentByRegNo(
                                regNo,
                                location,
                                salesType
                        );

                if (codeList != null && !codeList.isEmpty()) {

                    return new SaiResponse(
                            200,
                            "Details Found Successfully",
                            codeList
                    );

                } else {

                    return new SaiResponse(
                            400,
                            "Vehicle not available for "
                            + salesType + " login",
                            null
                    );
                }
            } else {

                List<Map> codeList
                        = demoVehRepo.getDemoVehDetailsByRegNo(
                                regNo,
                                location,
                                salesType
                        );

                if (codeList != null && !codeList.isEmpty()) {

                    return new SaiResponse(
                            200,
                            "Details Found Successfully",
                            codeList
                    );

                } else {

                    return new SaiResponse(
                            400,
                            "Vehicle not available for "
                            + salesType + " login",
                            null
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            return new SaiResponse(
                    400,
                    "Details not found",
                    "Details not found"
            );
        }
    }

    @PostMapping("/demoVehOutProceed")
    public SaiResponse demoVehOutProceed(@RequestBody DemoVehOutDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsVehDemo> existDemoVeh = demoVehRepo.findFirstByRegNoAndLocationOrderByCreationDateDesc(input.getRegNo(), input.getLocation());
            SsDmsVehDemo existDemoVeh1 = existDemoVeh.isPresent() ? existDemoVeh.get() : null;

            if (existDemoVeh1 != null) {
                if (existDemoVeh1.getOutKm() != null && existDemoVeh1.getInKm() == null && existDemoVeh1.getGatePassNo() != null) {

                    apiResponse = new SaiResponse(400, "Vehicle Already Out For Demo", existDemoVeh1.getChassisNo());
                    return apiResponse;

                } else {

                    Long prevVehInKm = existDemoVeh1.getInKm();
                    if (input.getOutKm() < prevVehInKm) {
                        apiResponse = new SaiResponse(400, "Out km value is less than previous In km", input.getRegNo());
                        return apiResponse;
                    } else {

                        SsDmsVehDemo newDemo = new SsDmsVehDemo();

                        newDemo.setRegNo(input.getRegNo());
                        newDemo.setChassisNo(input.getChassisNo());
                        newDemo.setVin(input.getVin());
                        newDemo.setModelDesc(input.getModelDesc());
                        newDemo.setFuelDesc(input.getFuelDesc());
                        newDemo.setVariantDesc(input.getVariantDesc());
                        newDemo.setEngineNo(input.getEngineNo());
                        newDemo.setLocId(input.getLocId());
                        newDemo.setOuId(input.getOuId());
                        newDemo.setLocation(input.getLocation());
                        newDemo.setCustName(input.getCustName());
                        newDemo.setCustContactNo(input.getCustContactNo());
                        newDemo.setCustAddress(input.getCustAddress());
                        newDemo.setRemarks(input.getRemarks());
                        newDemo.setOutKm(input.getOutKm());
                        newDemo.setOutTime(dateTime);
                        newDemo.setCreatedBy(input.getCreatedBy());
                        newDemo.setCreationDate(dateTime);
                        newDemo.setUpdatedBy(input.getUpdatedBy());
                        newDemo.setUpdationDate(dateTime);
                        newDemo.setAttribute1(input.getAttribute1());
                        newDemo.setAttribute2(input.getAttribute2());
                        newDemo.setAttribute3(input.getAttribute3());
                        newDemo.setFuelQty(input.getFuelQty());
                        newDemo.setSobEnqDet(input.getSobEnqDet());

                        Long gatePassNo = ((Number) entityManager
                                .createNativeQuery("SELECT DMS_MANUAL_GP_VH_SEQ.NEXTVAL FROM dual")
                                .getSingleResult()).longValue();

                        newDemo.setGatePassNo(gatePassNo);

                        demoVehRepo.save(newDemo);

                        SsDmsManualGpVh newGatePass = new SsDmsManualGpVh();

                        newGatePass.setRegNo(input.getRegNo());
                        newGatePass.setGatePassNo(gatePassNo);
                        newGatePass.setGatePassDate(dateTime);
                        newGatePass.setCustomerName(input.getCustName());
                        newGatePass.setMadeBy(input.getCreatedBy());
                        newGatePass.setFromLocation(input.getFromLocation());
                        newGatePass.setRemarks(input.getRemarks());
                        newGatePass.setInvoiceNo(input.getInvoiceNo());
                        newGatePass.setLoginName(input.getLoginName());
                        newGatePass.setVin(input.getVin());
                        newGatePass.setGatePassType("TEST DRIVE");
                        newGatePass.setCurrentKms(input.getOutKm());
                        newGatePass.setAuthorisedBy(input.getAuthorisedBy());
                        newGatePass.setCustAddress(input.getCustAddress());
                        newGatePass.setCustContact(input.getCustContactNo());

                        newGatePass.setFuelQty(input.getFuelQty());
                        newGatePass.setExeName(input.getAttribute3());
                        newGatePass.setSob(input.getSobEnqDet());

                        manualGatePassRepo.save(newGatePass);

                        apiResponse = new SaiResponse(200, "Vehicle Successfully Out for demo", newDemo);
                        return apiResponse;
                    }

                }

            } else {

                SsDmsVehDemo newDemo = new SsDmsVehDemo();

                newDemo.setRegNo(input.getRegNo());
                newDemo.setChassisNo(input.getChassisNo());
                newDemo.setVin(input.getVin());
                newDemo.setModelDesc(input.getModelDesc());
                newDemo.setFuelDesc(input.getFuelDesc());
                newDemo.setVariantDesc(input.getVariantDesc());
                newDemo.setEngineNo(input.getEngineNo());
                newDemo.setLocId(input.getLocId());
                newDemo.setOuId(input.getOuId());
                newDemo.setLocation(input.getLocation());
                newDemo.setCustName(input.getCustName());
                newDemo.setCustContactNo(input.getCustContactNo());
                newDemo.setCustAddress(input.getCustAddress());
                newDemo.setRemarks(input.getRemarks());
                newDemo.setOutKm(input.getOutKm());
                newDemo.setOutTime(dateTime);
                newDemo.setCreatedBy(input.getCreatedBy());
                newDemo.setCreationDate(dateTime);
                newDemo.setUpdatedBy(input.getUpdatedBy());
                newDemo.setUpdationDate(dateTime);
                newDemo.setAttribute1(input.getAttribute1());
                newDemo.setAttribute2(input.getAttribute2());
                newDemo.setAttribute3(input.getAttribute3());
                newDemo.setFuelQty(input.getFuelQty());

                Long gatePassNo = ((Number) entityManager
                        .createNativeQuery("SELECT DMS_MANUAL_GP_VH_SEQ.NEXTVAL FROM dual")
                        .getSingleResult()).longValue();

                newDemo.setGatePassNo(gatePassNo);

                demoVehRepo.save(newDemo);

                SsDmsManualGpVh newGatePass = new SsDmsManualGpVh();

                newGatePass.setRegNo(input.getRegNo());
                newGatePass.setGatePassNo(gatePassNo);
                newGatePass.setGatePassDate(dateTime);
                newGatePass.setCustomerName(input.getCustName());
                newGatePass.setMadeBy(input.getCreatedBy());
                newGatePass.setFromLocation(input.getFromLocation());
                newGatePass.setRemarks(input.getRemarks());
                newGatePass.setInvoiceNo(input.getInvoiceNo());
                newGatePass.setLoginName(input.getLoginName());
                newGatePass.setVin(input.getVin());
                newGatePass.setGatePassType("TEST DRIVE");
                newGatePass.setCurrentKms(input.getOutKm());
                newGatePass.setAuthorisedBy(input.getAuthorisedBy());
                newGatePass.setCustAddress(input.getCustAddress());
                newGatePass.setCustContact(input.getCustContactNo());
                newGatePass.setFuelQty(input.getFuelQty());
                newGatePass.setExeName(input.getAttribute3());
                newGatePass.setSob(input.getSobEnqDet());

                manualGatePassRepo.save(newGatePass);

                apiResponse = new SaiResponse(200, "Vehicle Successfully Out for demo", newDemo);
                return apiResponse;
            }

        } catch (Exception e) {

            apiResponse = new SaiResponse(500, "Vehicle Out Failed", "Vehicle Out Failed");
            return apiResponse;
        }

    }

    //used for getting demo veh details by regNo for demo VEH IN
//    @GetMapping("/getDemoVehInDetailsByRegNo")
//    public SaiResponse getDemoVehInDetailsByRegNo(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            List<Map> codeList = demoVehRepo.getDemoVehInDetailsByRegNo(regNo);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
//        }
//        return apiResponse;
//
//    }
    //updated with location passed
//    @GetMapping("/getDemoVehInDetailsByRegNo")
//    public SaiResponse getDemoVehInDetailsByRegNo(@RequestParam String regNo, @RequestParam String location) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            List<Map> codeList = demoVehRepo.getDemoVehInDetailsByRegNo(regNo, location);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
//        }
//        return apiResponse;
//
//    }
    //updated with check of arena nexa bifur and location 
    @GetMapping("/getDemoVehInDetailsByRegNo")
    public SaiResponse getDemoVehInDetailsByRegNo(
            @RequestParam String regNo,
            @RequestParam String location,
            @RequestParam String loginName) throws Exception {

        SaiResponse apiResponse;

        try {

            Optional<SsVehStockLogin> loginOpt
                    = loginRepo.findByLoginName(loginName);

            if (!loginOpt.isPresent()) {

                return new SaiResponse(
                        400,
                        "Login details not found",
                        null
                );
            }

            SsVehStockLogin loginUser = loginOpt.get();

            String salesType = loginUser.getAttribute2();

            if (salesType == null || salesType.trim().isEmpty()) {

                return new SaiResponse(
                        400,
                        "Arena/Nexa configuration not found for login",
                        null
                );
            }

            salesType = salesType.trim().toUpperCase();

            if (!salesType.equals("ARENA")
                    && !salesType.equals("NEXA")) {

                return new SaiResponse(
                        400,
                        "Invalid Arena/Nexa configuration for login",
                        null
                );
            }

            List<Map> codeList
                    = demoVehRepo.getDemoVehInDetailsByRegNo(
                            regNo,
                            location,
                            salesType
                    );

            if (codeList != null && !codeList.isEmpty()) {

                apiResponse = new SaiResponse(
                        200,
                        "Details Found Successfully",
                        codeList
                );

            } else {

                apiResponse = new SaiResponse(
                        400,
                        "Vehicle not available for "
                        + salesType + " login",
                        null
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            apiResponse = new SaiResponse(
                    400,
                    "Details not found",
                    "Details not found"
            );
        }

        return apiResponse;
    }

    @PutMapping("/demoVehInUpdate")
    public SaiResponse demoVehInUpdate(@RequestBody DemoVehInDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsVehDemo> existDemoVeh = demoVehRepo.findFirstByRegNoAndLocationOrderByCreationDateDesc(input.getRegNo(), input.getLocation());
            SsDmsVehDemo existDemoVeh1 = existDemoVeh.isPresent() ? existDemoVeh.get() : null;

            if (existDemoVeh.isPresent()) {
                if (existDemoVeh1 != null && existDemoVeh1.getInKm() != null) {
                    apiResponse = new SaiResponse(400, "Demo Vehicle Already In", existDemoVeh1.getChassisNo());
                    return apiResponse;

                } else {

                    existDemoVeh1.setInKm(input.getInKm());
                    existDemoVeh1.setInTime(dateTime);
                    existDemoVeh1.setUpdatedBy(input.getUpdatedBy());
                    existDemoVeh1.setUpdationDate(dateTime);
//                    existDemoVeh1.setRemarks(input.getRemarks());
                    existDemoVeh1.setInRemarks(input.getRemarks());
                    existDemoVeh1.setAttribute1(input.getAttribute1());
                    existDemoVeh1.setAttribute2(input.getAttribute2());

                    if (input.getFuelQty() != null) {
                        existDemoVeh1.setFuelQtyIn(input.getFuelQty());
                    }

                    demoVehRepo.save(existDemoVeh1);

                    apiResponse = new SaiResponse(200, "Demo Vehicle In Successfully.", existDemoVeh1);
                    return apiResponse;

                }
            } else {
                apiResponse = new SaiResponse(400, "Demo Vehicle Not Found.", input.getChassisNo());
                return apiResponse;
            }

        } catch (Exception e) {

            apiResponse = new SaiResponse(400, "Vehicle In Failed", "Vehicle In Failed");
            return apiResponse;
        }

    }

    //report for demo vehicle s- sales demo car 
    //location wise and datewise
    @GetMapping("/demoVehReportByOuAndLoc")
    public SaiResponse demoVehReportByOuAndLoc(@RequestParam Integer ouId, @RequestParam Integer locId, @RequestParam Date fromDate, @RequestParam Date toDate)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = demoVehRepo.getDemoVehReportByOuIdAndLocId(ouId, locId, fromDate, toDate);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    //To get the location names from ss_dms_veh_demo table on ou id.
//     @GetMapping("/locationDetails")
//    public SaiResponse demoVehReportByOuAndLoc(@RequestParam Integer ouId)
//            throws Exception {
//        SaiResponse apiResponse;
//        try {
//
//            List<Map> codeList = demoVehRepo.getLocationDetailsByOu(ouId);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
//        }
//        return apiResponse;
//
//    }
    //UPDATED QUERY TO AS PER STOCK TABLE DEMO VEHICLES ON 27-06-2026 BY HARSH
    @GetMapping("/locationDetails")
    public SaiResponse demoVehReportByOuAndLoc(@RequestParam Integer ouId)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = demoVehRepo.getLocationDetailsByOu(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    //fetch the status of demo vehicle - available or out for demo  
    @GetMapping("/demoVehStatusList")
    public SaiResponse demoVehStatusList(@RequestParam Integer ouId, @RequestParam String location)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = demoVehRepo.getDemoVehStatusListByOu(ouId, location);

            if (!codeList.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
            } else {
                apiResponse = new SaiResponse(400, "No Vehicles In Stock", "No Vehicles at this location");

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

}
