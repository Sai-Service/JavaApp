/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsDmsStockTrfTruevalueDao;
import com.sai.erp.master.dao.SsDmsStockTruevalueDao;
import com.sai.erp.master.dto.updateStkTrfTruevalueDto;
import com.sai.erp.master.entity.SsDmsStockTrfTruevalue;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-HARSH
 */
@RestController
@RequestMapping("/trueValue")
public class TrueValueController {

    @Autowired
    private SsDmsStockTruevalueDao trueValRepo;

    @Autowired
    private SsDmsStockTrfTruevalueDao trueValTrfRepo;

    ///for getting vehicle overall details by registration no-- TRUE VALUE
    ///from ss_dms_stock_tv table
    //Vehicle enquiry form
    @GetMapping("/tvDetailsByRegNo")
    public SaiResponse tvDetailsByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvDetailsByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///for getting vehicle overall details by chassis no-- TRUE VALUE 
    ///from ss_dms_stock_tv table
    //vehicle enquiry form
    @GetMapping("/tvDetailsByChassis")
    public SaiResponse tvDetailsByChassis(@RequestParam String chassisNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvDetailsByChassis(chassisNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
     ///for getting vehicle overall details by VIN no-- TRUE VALUE 
    ///from ss_dms_stock_tv table
    //vehicle enquiry form
    @GetMapping("/tvDetailsByVin")
    public SaiResponse tvDetailsByVin(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvDetailsByVin(vin);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
       //used to find multiple vin no by chassis no
    //used for chassis enquiry form -- TRUE VALUE DEPT.
    @GetMapping("/tvVinNoByChassis")
    public SaiResponse tvVinNoByChassis(@RequestParam String chassis_no) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvVinNoByChassis(chassis_no);

            apiResponse = new SaiResponse(200, "Vin Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vin Details Not Found", e.getMessage());
        }
        return apiResponse;

    }
    

    //for getting vehicle details by reg no for STOCK IN process--TRUE VALUE
    @GetMapping("/tvDetInByRegNo")
    public SaiResponse tvDetInByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvDetStockInByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //for getting vehicle details by chassis no for STOCK IN process---TRUE VALUE
    @GetMapping("/tvDetInByChassis")
    public SaiResponse tvDetInByChassis(@RequestParam String chassisNo, @RequestParam Integer ou) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValRepo.getTvDetStockInByChassis(chassisNo, ou);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //vehicle track -- TRUE VALUE
    //for updating vehicle phy status after stock in process 
    //in table ss_dms_stock_tv and ss_dms_stock_trf_tv
    @PutMapping("/tvUpdateStockIn")
    SaiResponse updateVehicleIntransit(@RequestBody updateStkTrfTruevalueDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Date currentDate = Calendar.getInstance().getTime();

            Optional<SsDmsStockTrfTruevalue> optionalEmpId = trueValTrfRepo.findByStockTrfNo(input.getStockTrfNo());
            SsDmsStockTrfTruevalue emplMstId = optionalEmpId.isPresent() ? optionalEmpId.get() : null;

            if (emplMstId != null) {

                emplMstId.setReceivedBy(input.getReceivedBy());
                emplMstId.setRecdDate(currentDate);
                emplMstId.setToKm(input.getToKm());
                emplMstId.setToLocation(input.getToLocation());

                trueValTrfRepo.save(emplMstId);

                trueValRepo.updateTvVehStatus("STOCK",input.getLocation(), input.getRegNo());

                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", input.getRegNo());
            } else {
                apiResponse = new SaiResponse(400, "Vehicle details not found", null);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
            e.printStackTrace();

        }
        return apiResponse;
    }

    
    //used for vehicle track --TRUE VALUE
    //for fetchinig stock transfer vehicles LIST as per location
    @GetMapping("/tvTransferList")
    public SaiResponse tvTransferList(@RequestParam String to_location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = trueValTrfRepo.getByTvTransList(to_location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

}
