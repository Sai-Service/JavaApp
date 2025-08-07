/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsDmsStockTrfTruevalueDao;
import com.sai.erp.master.dao.SsDmsStockTruevalueDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-HARSH
 */
@RestController
@RequestMapping("/tvReports")
public class TrueValueReportsController {

    @Autowired
    private SsDmsStockTruevalueDao trueValRepo;

    @Autowired
    private SsDmsStockTrfTruevalueDao trueValTrfRepo;

    
     @GetMapping("/tvStockDetailsByOu")
    public SaiResponse tvStockDetailsByOu(@RequestParam Integer ou, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = trueValRepo.getTvStockDetailsByOu(ou);
            List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = trueValRepo.getTvStockDetailsByOu(ou);
            } else if (vehType.equalsIgnoreCase("MARUTI")) {
                codeList = trueValRepo.getTvStockDetailsMarutiByOu(ou);
            } else if (vehType.equalsIgnoreCase("NON-MARUTI")) {
                codeList = trueValRepo.getTvStockDetailsNonMarutiByOu(ou);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    @GetMapping("/tvStkTrfDetailsByOu")
    public SaiResponse tvStkTrfDetailsByOu(@RequestParam Integer ou, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = trueValRepo.getTvStockTrfDetailsByOu(ou);
            
             List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = trueValRepo.getTvStockTrfDetailsByOu(ou);
            } else if (vehType.equalsIgnoreCase("MARUTI")) {
                codeList = trueValRepo.getTvStockTrfMarutiDetailsByOu(ou);
            } else if (vehType.equalsIgnoreCase("NON-MARUTI")) {
                codeList = trueValRepo.getTvStockTrfNonMarutiDetailsByOu(ou);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
      @GetMapping("/tvStockDelvDetailsByOu")
    public SaiResponse tvStockDeliveredDetailsByOu(@RequestParam Integer ou, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = trueValRepo.getTvStockTrfDetailsByOu(ou);
            
           List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = trueValRepo.getTvStockDelvDetailsByOu(ou);
            } else if (vehType.equalsIgnoreCase("MARUTI")) {
                codeList = trueValRepo.getTvStockDelvMarutiDetailsByOu(ou);
            } else if (vehType.equalsIgnoreCase("NON-MARUTI")) {
                codeList = trueValRepo.getTvStockDelvNonMarutiDetailsByOu(ou);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
    //used for reports //..fetches the overall true value vehicle count based on  a particular city...
    @GetMapping("/tvReportLocationCount")
    public SaiResponse tvReportLocationCount(@RequestParam Integer ouId,@RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);

              List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = trueValRepo.getTvLocationCountAll(ouId);
            } else if (vehType.equalsIgnoreCase("MARUTI")) {
                codeList = trueValRepo.getTvLocationCountMaruti(ouId);
            } else if (vehType.equalsIgnoreCase("NON-MARUTI")) {
                codeList = trueValRepo.getTvLocationCountNonMaruti(ouId);
            }
            
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
      

        //used for reports //..fetches the sum of overall true value vehicle count of a particular city...
    @GetMapping("/tvReportLocationCountSum")
    public SaiResponse tvReportLocationCountSum(@RequestParam Integer ouId, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
              List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = trueValRepo.getTvLocationCountAllSum(ouId);
            } else if (vehType.equalsIgnoreCase("MARUTI")) {
                codeList = trueValRepo.getTvLocationCountMarutiSum(ouId);
            } else if (vehType.equalsIgnoreCase("NON-MARUTI")) {
                codeList = trueValRepo.getTvLocationCountNonMarutiSum(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
}
