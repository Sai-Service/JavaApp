/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.FndCommonDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
//import com.Main.dao.whatsupcontactlistDao;
//import com.Main.dto.FndCommonDto;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jyoti K
 */
@RestController
@RequestMapping("/fndcom")
public class FndCommonController {

    @Autowired
    private FndCommonDao commonRepo;

    @Autowired
    private SsVehStockLoginDao ssVehLoginRepo;

//..fetches the city code and name..    
    @GetMapping("/cmnType")
    public SaiResponse getBycodesubType(@RequestParam String cmnType) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getLocByCmnType(cmnType);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    @GetMapping("/testDriveType")
    public SaiResponse testDriveType(@RequestParam String cmnType) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getTestDriveDetailsByCmnType(cmnType);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

//used for reports..fetches the total vehicle stock of a particular city...
    @GetMapping("/stockDetails")
    public SaiResponse getByStockDetailsLocation(@RequestParam Integer ouId, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {
//
//            Optional<SsVehStockLogin> empLoginDetails1 = ssVehLoginRepo.findByLoginName(loginName);
//            SsVehStockLogin empLoginDetails = empLoginDetails1.isPresent() ? empLoginDetails1.get() : null;

            List<Map> codeList = null;

            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = commonRepo.getStockDetailsAll(ouId);
            } else if (vehType.equalsIgnoreCase("NEXA")) {
                codeList = commonRepo.getStockDetailsNexa(ouId);
            } else if (vehType.equalsIgnoreCase("ARENA")) {
                codeList = commonRepo.getStockDetailsArena(ouId);
            }
//            if (empLoginDetails.getRole().equalsIgnoreCase("SUPERADMIN")) {
//                   codeList = commonRepo.getStockDetailsAllOU();
//            } else {
//               
//            }
            apiResponse = new SaiResponse(200, "Stock Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Stock Details Not Found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/stockDetailsByOuChassis")
    public SaiResponse getByStockDetails(@RequestParam Integer ouId, String chassis_no) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getStockDetailsByOuChassis(ouId, chassis_no);

            apiResponse = new SaiResponse(200, "Stock Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Stock Details Not Found", e.getMessage());
        }
        return apiResponse;

    }

    //used for chassis enquiry form..fetches vehicle details against a particular chassis...
    @GetMapping("/stockDetailsByChassis")
    public SaiResponse getByStockDetails(@RequestParam String chassis_no) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getStockDetailsByChassis(chassis_no);

            apiResponse = new SaiResponse(200, "Stock Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Stock Details Not Found", e.getMessage());
        }
        return apiResponse;

    }
    
     //used for chassis enquiry form..fetches vehicle details against a particular VIN...
    @GetMapping("/stockDetailsByVin")
    public SaiResponse getStockDetailsByVin(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getStockDetailsByVin(vin);

            apiResponse = new SaiResponse(200, "Stock Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Stock Details Not Found", e.getMessage());
        }
        return apiResponse;

    }

    //used to find multiple vin no by chassis no
    //used for chassis enquiry form -- SALES DEPT.
    @GetMapping("/vinNoByChassis")
    public SaiResponse vinNoByChassis(@RequestParam String chassis_no) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getVinNoByChassis(chassis_no);

            apiResponse = new SaiResponse(200, "Vin Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vin Details Not Found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/vehDeliver")
    public SaiResponse getByCmnType(@RequestParam String cmnType) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = commonRepo.getByCmnType(cmnType);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }


}
