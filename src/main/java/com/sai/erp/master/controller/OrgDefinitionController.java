/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.FndCommonDao;
import com.sai.erp.master.dao.OrgDefinitionDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/orgDef")
public class OrgDefinitionController {
    
     @Autowired
    private OrgDefinitionDao orgDefRepo;
    
     @GetMapping("/getLocation") 
    public SaiResponse getByCmnType(@RequestParam Integer operating_unit) throws Exception {
        SaiResponse apiResponse;
        try {
             List<Map> codeList=orgDefRepo.getByOrgOu(operating_unit);
            
        apiResponse = new SaiResponse(200, "Details Found Successfully",codeList );
        }
        catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;
        
    }
    
    
    @GetMapping("/getLocationPark") 
    public SaiResponse getByCmnTypePark(@RequestParam Integer operating_unit) throws Exception {
        SaiResponse apiResponse;
        try {
             List<Map> codeList=orgDefRepo.getByOrgOuPark(operating_unit);
            
        apiResponse = new SaiResponse(200, "Details Found Successfully",codeList );
        }
        catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;
        
    }
}
