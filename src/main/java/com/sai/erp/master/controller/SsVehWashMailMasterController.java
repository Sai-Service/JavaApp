/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsVehWashMailMasterDao;
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
@RequestMapping("/washMailMaster")
public class SsVehWashMailMasterController {
    
    @Autowired
    private SsVehWashMailMasterDao washMailMasterRepo;
    
    @GetMapping("/washMailByLocId")
    public SaiResponse washMailByLocId(@RequestParam Integer ouId, @RequestParam Integer locId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = washMailMasterRepo.getWashMailByOuAndLocId(ouId, locId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }
    
}
