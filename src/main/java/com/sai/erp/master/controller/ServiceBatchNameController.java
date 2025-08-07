/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.BatchNameServiceDao;
import com.sai.erp.master.dao.SsDmsStockServiceDao;
import com.sai.erp.master.dto.ServiceBatchNameDto;
import com.sai.erp.master.dto.srBatchNameDto;
import com.sai.erp.master.entity.BatchNameService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-HARSH
 */
@RestController
@RequestMapping("/srAccounts")
public class ServiceBatchNameController {

    @Autowired
    private BatchNameServiceDao batchNameSrRepo;

    @Autowired
    private SsDmsStockServiceDao servRepo;

//    used for SERVICE stock taking 
//    ..............to fetch veh details by reg no scan, uses this api...........
    @GetMapping("/srAccDetailsByRegNo")
    public SaiResponse srAccDetailsByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for service stock taking 
    //........fetches service veh details by chassis no, uses this api...........
    @GetMapping("/srAccDetailsByChassisNo")
    public SaiResponse srAccDetailsByChassisNo(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getByChassisNo(chassisNo, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
     //used for service stock taking 
    //........fetches service veh details by job card no, uses this api...........
    @GetMapping("/srAccDetailsByJobCardNo")
    public SaiResponse srAccDetailsByJobCardNo(@RequestParam String jobCardNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getByJobCardNo(jobCardNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for service stock taking
    //...fetches the OPEN batchname of that particular location...
    @GetMapping("/srBatchNameByLocation")
    public SaiResponse srBatchNameByLocation(@RequestParam Integer locationId, @RequestParam String createdBy) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = batchNameSrRepo.getBatchByLocation(locationId, createdBy);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for SERVICE stock taking 
    //....fetches the vehicles stocked against a particular batchname...
    @GetMapping("/srVehDetailsByBatchName")
    public SaiResponse srVehDetailsByBatchName(@RequestParam String batchName) throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = batchNameSrRepo.getSrBatchDetails(batchName);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///////checking regNo first and then post batchname
    //used for SERVICE stock taking
    //.....inserts the vehicle details into a batchname datewise.....
    @PostMapping("/srBatchNameScan")
    SaiResponse srBatchNameScan(@RequestBody ServiceBatchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchNameService> existReg = batchNameSrRepo.findByBatchNameAndRegNo(input.getBatchName(), input.getRegNo());
            BatchNameService existReg1 = existReg.isPresent() ? existReg.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;
            Date srBatchDate = batchNameSrRepo.getSrBatchDate(input.getBatchName());
            String srBatchName1 = batchNameSrRepo.getSrBatchName(input.getBatchName());

            BatchNameService srBatchName = new BatchNameService();
            if (existReg1 != null) {
                // REG NO already exists for this batchName
                apiResponse = new SaiResponse(400, "Registration No already exists for this batchName", null);
            } else {
                if (srBatchName1 == null) {
                    // If batchName does not exist, create new
                    srBatchName.setBatchName(input.getBatchName());
                    srBatchName.setBatchCreationDate(input.getBatchCreationDate());
                    srBatchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    srBatchName.setBatchName(input.getBatchName());
                    srBatchName.setBatchCreationDate(srBatchDate);
                    srBatchName.setBatchStatus(input.getBatchStatus());
                }

                srBatchName.setRegNo(input.getRegNo());
                srBatchName.setVin(input.getVin());
                srBatchName.setChassisNo(input.getChassisNo());
                srBatchName.setJobCardNo(input.getJobCardNo());
                srBatchName.setEngineNo(input.getEngineNo());
                srBatchName.setModelDesc(input.getModelDesc());
                srBatchName.setColour(input.getColour());
                srBatchName.setFuelDesc(input.getFuelDesc());
                srBatchName.setLocationId(input.getLocationId());
                srBatchName.setLocationName(input.getLocationName());
                srBatchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                srBatchName.setScanCreationTime(currentDate);
                srBatchName.setCreationDate(currentDate);
                srBatchName.setCreatedBy(input.getCreatedBy());
                srBatchName.setUpdationDate(currentDate);
                srBatchName.setUpdatedBy(input.getUpdatedBy() + "-Mobile-Scan");

                batchNameSrRepo.save(srBatchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", srBatchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;
    }

    
     @PostMapping("/srBatchNameManual")
    SaiResponse srBatchNameManual(@RequestBody ServiceBatchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchNameService> existReg = batchNameSrRepo.findByBatchNameAndRegNo(input.getBatchName(), input.getRegNo());
            BatchNameService existReg1 = existReg.isPresent() ? existReg.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;
            Date srBatchDate = batchNameSrRepo.getSrBatchDate(input.getBatchName());
            String srBatchName1 = batchNameSrRepo.getSrBatchName(input.getBatchName());

            BatchNameService srBatchName = new BatchNameService();
            if (existReg1 != null) {
                // REG NO already exists for this batchName
                apiResponse = new SaiResponse(400, "Registration No already exists for this batchName", null);
            } else {
                if (srBatchName1 == null) {
                    // If batchName does not exist, create new
                    srBatchName.setBatchName(input.getBatchName());
                    srBatchName.setBatchCreationDate(input.getBatchCreationDate());
                    srBatchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    srBatchName.setBatchName(input.getBatchName());
                    srBatchName.setBatchCreationDate(srBatchDate);
                    srBatchName.setBatchStatus(input.getBatchStatus());
                }

                srBatchName.setRegNo(input.getRegNo());
                srBatchName.setVin(input.getVin());
                srBatchName.setChassisNo(input.getChassisNo());
                srBatchName.setJobCardNo(input.getJobCardNo());
                srBatchName.setEngineNo(input.getEngineNo());
                srBatchName.setModelDesc(input.getModelDesc());
                srBatchName.setColour(input.getColour());
                srBatchName.setFuelDesc(input.getFuelDesc());
                srBatchName.setLocationId(input.getLocationId());
                srBatchName.setLocationName(input.getLocationName());
                srBatchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                srBatchName.setScanCreationTime(currentDate);
                srBatchName.setCreationDate(currentDate);
                srBatchName.setCreatedBy(input.getCreatedBy());
                srBatchName.setUpdationDate(currentDate);
                srBatchName.setUpdatedBy(input.getUpdatedBy() + "-Mobile-Manually");

                batchNameSrRepo.save(srBatchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", srBatchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;
    }
    

    @GetMapping("/findSrBatchNameStatus")
    public SaiResponse findSrBatchNameStatus(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = batchNameSrRepo.getEXBatchStatus(location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/srBatchNameOpen")
    public SaiResponse srBatchNameOpen(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> batchList = batchNameSrRepo.getSrBatchOpen(location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batchList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/findBatchNameStatus")
    public SaiResponse findBatchNameStatus(@RequestParam String batchName, @RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<srBatchNameDto> batch = batchNameSrRepo.getBatchStatus(batchName, location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batch);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
//        ///CUT OF DATE LOGIC //
//     @GetMapping("/srBatchCutofDate")
//    public SaiResponse tvBatchCutofDate(@RequestParam Integer ou, @RequestParam Date cutOffDate) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            List<Map> codeList = tvCutOfDateRepo.getTvBatchCutOfDate(ou, cutOffDate);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
//        }
//        return apiResponse;

//    }

    
    

}
