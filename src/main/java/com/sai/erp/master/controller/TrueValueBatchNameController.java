/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.BatchNameTrueValueDao;
import com.sai.erp.master.dao.BatchNameTvCutOfDateDao;
import com.sai.erp.master.dao.SsDmsStockTruevalueDao;
import com.sai.erp.master.dto.TrueValueBatchNameDto;
import com.sai.erp.master.dto.tvBatchNameDto;
import com.sai.erp.master.dto.batchName1Dto;
import com.sai.erp.master.entity.BatchNameTrueValue;
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
@RequestMapping("/tvAccounts")
public class TrueValueBatchNameController {

    @Autowired
    private BatchNameTrueValueDao batchNameTvRepo;

    @Autowired
    private SsDmsStockTruevalueDao trueValRepo;
    
     @Autowired
    private BatchNameTvCutOfDateDao tvCutOfDateRepo;

    //used for true value stock taking 
    //..............to fetch veh details by reg no scan, uses this api...........
    @GetMapping("/tvAccDetailsByRegNo")
    public SaiResponse getByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for true value stock taking 
    //........fetches true value veh details by chassis no, uses this api...........
    @GetMapping("/tvAccDetailsByChassisNo")
    public SaiResponse getByChassisNo(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getByChassisNo(chassisNo, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for true value stock taking
    //...fetches the batchname of that particular location...
    @GetMapping("/tvBatchNameByLocation")
    public SaiResponse tvBatchNameByLocation(@RequestParam Integer locationId, @RequestParam String createdBy) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = batchNameTvRepo.getBatchByLocation(locationId, createdBy);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for true value stock taking 
    //....fetches the vehicles stocked against a particular batchname...
    @GetMapping("/tvVehDetailsByBatchName")
    public SaiResponse findByBatchName(@RequestParam String batchName) throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = batchNameTvRepo.getTvBatchDetails(batchName);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///////checking regNo first and then post batchname
    //used for true value stock taking
    //.....inserts the vehicle details into a batchname datewise.....
    @PostMapping("/tvBatchNameScan")
    SaiResponse tvBatchNameScan(@RequestBody TrueValueBatchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchNameTrueValue> existReg = batchNameTvRepo.findByBatchNameAndRegNo(input.getBatchName(), input.getRegNo());
            BatchNameTrueValue existReg1 = existReg.isPresent() ? existReg.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;
            Date tvBatchDate = batchNameTvRepo.getTvBatchDate(input.getBatchName());
            String tvBatchName1 = batchNameTvRepo.getTvBatchName(input.getBatchName());

            BatchNameTrueValue tvBatchName = new BatchNameTrueValue();
            if (existReg1 != null) {
                // REG NO already exists for this batchName
                apiResponse = new SaiResponse(400, "Registration No already exists for this batchName", null);
            } else {
                if (tvBatchName1 == null) {
                    // If batchName does not exist, create new
                    tvBatchName.setBatchName(input.getBatchName());
                    tvBatchName.setBatchCreationDate(input.getBatchCreationDate());
                    tvBatchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    tvBatchName.setBatchName(input.getBatchName());
                    tvBatchName.setBatchCreationDate(tvBatchDate);
                    tvBatchName.setBatchStatus(input.getBatchStatus());
                }

                tvBatchName.setRegNo(input.getRegNo());
                tvBatchName.setVin(input.getVin());
                tvBatchName.setChassisNo(input.getChassis_no());
                tvBatchName.setEngineNo(input.getEngin_no());
                tvBatchName.setModelDesc(input.getModel_desc());
                tvBatchName.setColour(input.getColour());
                tvBatchName.setFuelDesc(input.getFuel_desc());
                tvBatchName.setLocationId(input.getLocationId());
                tvBatchName.setLocationName(input.getLocationName());
                tvBatchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                tvBatchName.setScanCreationTime(currentDate);
                tvBatchName.setCreationDate(currentDate);
                tvBatchName.setCreatedBy(input.getCreatedBy());
                tvBatchName.setUpdationDate(currentDate);
                tvBatchName.setUpdatedBy(input.getUpdatedBy() + "-Mobile-Scan");

                batchNameTvRepo.save(tvBatchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", tvBatchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;
    }
    
    @PostMapping("/tvBatchNameManual")
    SaiResponse tvBatchNameManual(@RequestBody TrueValueBatchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchNameTrueValue> existReg = batchNameTvRepo.findByBatchNameAndRegNo(input.getBatchName(), input.getRegNo());
            BatchNameTrueValue existReg1 = existReg.isPresent() ? existReg.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;
            Date tvBatchDate = batchNameTvRepo.getTvBatchDate(input.getBatchName());
            String tvBatchName1 = batchNameTvRepo.getTvBatchName(input.getBatchName());

            BatchNameTrueValue tvBatchName = new BatchNameTrueValue();
            if (existReg1 != null) {
                // REG NO already exists for this batchName
                apiResponse = new SaiResponse(400, "Registration No already exists for this batchName", null);
            } else {
                if (tvBatchName1 == null) {
                    // If batchName does not exist, create new
                    tvBatchName.setBatchName(input.getBatchName());
                    tvBatchName.setBatchCreationDate(input.getBatchCreationDate());
                    tvBatchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    tvBatchName.setBatchName(input.getBatchName());
                    tvBatchName.setBatchCreationDate(tvBatchDate);
                    tvBatchName.setBatchStatus(input.getBatchStatus());
                }

                tvBatchName.setRegNo(input.getRegNo());
                tvBatchName.setVin(input.getVin());
                tvBatchName.setChassisNo(input.getChassis_no());
                tvBatchName.setEngineNo(input.getEngin_no());
                tvBatchName.setModelDesc(input.getModel_desc());
                tvBatchName.setColour(input.getColour());
                tvBatchName.setFuelDesc(input.getFuel_desc());
                tvBatchName.setLocationId(input.getLocationId());
                tvBatchName.setLocationName(input.getLocationName());
                tvBatchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                tvBatchName.setScanCreationTime(currentDate);
                tvBatchName.setCreationDate(currentDate);
                tvBatchName.setCreatedBy(input.getCreatedBy());
                tvBatchName.setUpdationDate(currentDate);
                tvBatchName.setUpdatedBy(input.getUpdatedBy() + "-Mobile-Manually");

                batchNameTvRepo.save(tvBatchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", tvBatchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;
    }
    
    

    @GetMapping("/findTvBatchNameStatus")
    public SaiResponse findTvBatchNameStatus(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = batchNameTvRepo.getEXBatchStatus(location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/tvBatchNameOpen")
    public SaiResponse tvBatchNameOpen(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> batchList = batchNameTvRepo.getTvBatchOpen(location);

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
            List<tvBatchNameDto> batch = batchNameTvRepo.getBatchStatus(batchName, location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batch);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
      ///CUT OF DATE LOGIC //
//     @GetMapping("/tvBatchCutofDate")
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
//
//    }
    
     @GetMapping("/tvBatchCutofDate")
    public SaiResponse tvBatchCutofDate(@RequestParam Integer ou) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = tvCutOfDateRepo.getTvBatchCutOfDate(ou);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

}
