/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.SsDmsStockServiceDao;
import com.sai.erp.master.dao.SsDmsStockTrfServiceDao;
import com.sai.erp.master.dao.SsDmsStockTrfWsDao;
import com.sai.erp.master.dao.SsDmsWsTestDriveDao;
import java.util.Date;
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
@RequestMapping("/srAccounts")
public class ServiceReportsController {

    @Autowired
    private SsDmsStockServiceDao servRepo;
    
    @Autowired
    private SsDmsStockTrfWsDao srVehTransImgRepo;

    @Autowired
    private SsDmsStockTrfServiceDao servTrfRepo;
    
     @Autowired
    private SsDmsWsTestDriveDao testDriveRepo;

    @GetMapping("/srStockDetailsByOu")
    public SaiResponse srStockDetailsByOu(@RequestParam Integer ouId, @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {

//            List<Map> codeList = servRepo.getSrStockDetailsByOu(ou);
            List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = servRepo.getSrStockDetailsByOu(ouId);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = servRepo.getSrStockDetailsServByOu(ouId);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = servRepo.getSrStockDetailsDpByOu(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/srStkTrfDetailsByOu")
    public SaiResponse srStkTrfDetailsByOu(@RequestParam Integer ouId, @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = servTrfRepo.getSrStockTrfDetailsByOu(ou);

             List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = servTrfRepo.getSrStockTrfDetailsByOu(ouId);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = servTrfRepo.getSrStockTrfDetailsServByOu(ouId);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = servTrfRepo.getSrStockTrfDetailsDpByOu(ouId);
            }
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/srStockDelvDetailsByOu")
    public SaiResponse srStockDelvDetailsByOu(@RequestParam Integer ouId, @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = servRepo.getSrStockDelvDetailsByOu(ou);

             List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = servRepo.getSrStockDelvDetailsByOu(ouId);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = servRepo.getSrStockDelvDetailsServByOu(ouId);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = servRepo.getSrStockDelvDetailsDpByOu(ouId);
            }
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for reports //..fetches the overall SERVICE vehicle count based on  a particular city...
    @GetMapping("/srReportLocationCount")
    public SaiResponse srReportLocationCount(@RequestParam Integer ouId, @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = servRepo.getSrLocationCountAll(ouId);

            if (dept.equalsIgnoreCase("ALL")) {
                codeList = servRepo.getSrLocationCountAll(ouId);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = servRepo.getSrLocationCountServ(ouId);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = servRepo.getSrLocationCountDp(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for reports //..fetches the sum of overall SERVICE vehicle count of a particular city...
    @GetMapping("/srReportLocationCountSum")
    public SaiResponse srReportLocationCountSum(@RequestParam Integer ouId,  @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = servRepo.getSrLocationCountAllSum(ouId);

            if (dept.equalsIgnoreCase("ALL")) {
                codeList = servRepo.getSrLocationCountAllSum(ouId);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = servRepo.getSrLocationCountServSum(ouId);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = servRepo.getSrLocationCountDpSum(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //USED for reports
    //used for report for stock trans by ouid by from & to date
    //ss_dms_stock_trf_ws
      @GetMapping("/wsStkTransDetailsByOu")
    public SaiResponse wsStkTransDetailsByOu(@RequestParam Integer ou, @RequestParam String dept, @RequestParam Date fromDate, @RequestParam Date toDate)
            throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = servTrfRepo.getSrStockTrfDetailsByOu(ou);

             List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = srVehTransImgRepo.getWsStkTransDetailsByOu(ou, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = srVehTransImgRepo.getWsStkTransDetailsServByOu(ou, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = srVehTransImgRepo.getWsStkTransDetailsDpByOu(ou, fromDate, toDate );
            }
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
     //USED for reports
    //used for report for test drive workshop ouid by from & to date
    //SS_DMS_WS_TEST_DRIVE
      @GetMapping("/testDriveDetailsByOu")
    public SaiResponse testDriveDetailsByOu(@RequestParam Integer ou,@RequestParam Integer locId, @RequestParam String dept, @RequestParam Date fromDate, @RequestParam Date toDate)
            throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = servTrfRepo.getSrStockTrfDetailsByOu(ou);

             List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = testDriveRepo.getTestDriveDetailsByOu(ou,locId, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = testDriveRepo.getTestDriveDetailsServByOu(ou,locId, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = testDriveRepo.getTestDriveDetailsDpByOu(ou,locId, fromDate, toDate );
            }
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
    
    //for getting test drive details gate wise as per location and ou and between dates
    //report as per gates in diiferent locations
     @GetMapping("/tdGatewiseDetailsByOuAndLoc")
    public SaiResponse tdGatewiseDetailsByOuAndLoc(@RequestParam Integer ou,@RequestParam Integer locId, @RequestParam String dept, @RequestParam Date fromDate, @RequestParam Date toDate)
            throws Exception {
        SaiResponse apiResponse;
        try {
//            List<Map> codeList = servTrfRepo.getSrStockTrfDetailsByOu(ou);

             List<Map> codeList = null;
            if (dept.equalsIgnoreCase("ALL")) {
                codeList = testDriveRepo.getTdGatewiseByOuAndLocId(ou,locId, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("SERVICE")) {
                codeList = testDriveRepo.getTdGatewiseServByOuAndLocId(ou, locId, fromDate, toDate);
            } else if (dept.equalsIgnoreCase("DP")) {
                codeList = testDriveRepo.getTdGatewiseDpByOuAndLocId(ou,locId, fromDate, toDate );
            }
            
            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
            if(codeList.isEmpty()){
                apiResponse = new SaiResponse(400, "No Data Available", codeList);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Internal Server Error", e.getMessage());
        }
        return apiResponse;

    }
}
