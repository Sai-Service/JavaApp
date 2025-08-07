/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsGateTypeMasterDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HarshG
 */
@RestController
@RequestMapping("/gateTypeMaster")
public class SsGateTypeMasterController {
    
    @Autowired
    private SsGateTypeMasterDao gateTypeRepo;
    
    
    //USED FOR FETCHING GATE NO AND GATE TYPE BASED ON LOC ID
    // FROM SS_GATE_TYPE_MASTER
     @GetMapping("/gateDetailsByLocId")
    public SaiResponse gateDetailsByLocId(@RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = gateTypeRepo.getGateDetailsByLocId(locId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //USED FOR FETCHING GATE NO BASED ON LOC ID
    // FROM SS_GATE_TYPE_MASTER
     @GetMapping("/gateNoByLocId")
    public SaiResponse gateNoByLocId(@RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = gateTypeRepo.getGateNoByLocId(locId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //USED FOR FETCHING GATE TYPE BASED ON LOC ID
    // FROM SS_GATE_TYPE_MASTER
     @GetMapping("/gateTypeByLocId")
    public SaiResponse gateTypeByLocId(@RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = gateTypeRepo.getGateTypeByLocId(locId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
     //USED FOR FETCHING GATE TYPE BASED ON LOC ID and GATE NO
    // FROM SS_GATE_TYPE_MASTER
     @GetMapping("/gateTypeByLocIdAndGateNo")
    public SaiResponse gateTypeByLocIdAndGateNo(@RequestParam Integer locId, @RequestParam Integer gateNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = gateTypeRepo.getGateTypeByLocIdAndGateNo(locId , gateNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //USED FOR FETCHING GATE TYPE BY LOC AND TICKET NO
      @GetMapping("/gateTypeForParking")
    public SaiResponse gateTypeForParking(@RequestParam Integer locId, @RequestParam String attribute1) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = gateTypeRepo.getGateTypeForParking(locId , attribute1);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
}
