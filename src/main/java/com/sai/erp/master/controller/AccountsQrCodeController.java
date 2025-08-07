/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AndroidVehQrRepository;
import com.sai.erp.master.dao.BatchNameCutOfDateDao;
import com.sai.erp.master.dao.BatchNameDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dto.batchName1Dto;
import com.sai.erp.master.dto.batchNameDto;
import com.sai.erp.master.entity.BatchName;
import com.sai.erp.master.entity.SsDmsInvStockNew;
import java.text.SimpleDateFormat;
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
 * @author Lenovo
 */
@RestController
@RequestMapping("/accounts")
public class AccountsQrCodeController {

    @Autowired
    private SsDmsInvStockOriginalDao vehStockOriginal;

    @Autowired
    private AndroidVehQrRepository vehQrRepository;

    @Autowired
    private BatchNameDao batchNameRepo;
    
    @Autowired
    private BatchNameCutOfDateDao cutOfDateRepo;

    @GetMapping("/vehDetailsByVin")
    public SaiResponse vehDetailsByVin(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            Optional<SsDmsInvStockNew> codeList1 = vehStockOriginal.findByVin(vin);
            SsDmsInvStockNew codeList = codeList1.isPresent() ? codeList1.get() : null;

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///////checking vin first and then post batchname
    //used for stock taking -accounts
    //.....inserts the vehicle details into a batchname datewise.....
    @PostMapping("/batchNameScan")
    SaiResponse batchNameScan(@RequestBody batchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchName> existVin = batchNameRepo.findByBatchNameAndVin(input.getBatchName(), input.getVin());
            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;

            Date batchDate = batchNameRepo.getBatchDate(input.getBatchName());
            String batchName1 = batchNameRepo.getBatchName(input.getBatchName());

            BatchName batchName = new BatchName();
            if (existVin1 != null) {
                // VIN already exists for this batchName
                apiResponse = new SaiResponse(400, "VIN already exists for this batchName", null);
            } else {
                if (batchName1 == null) {
                    // If batchName does not exist, create new
                    batchName.setBatchName(input.getBatchName());
                    batchName.setBatchCreationDate(input.getBatchCreationDate());
                    batchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    batchName.setBatchName(input.getBatchName());
                    batchName.setBatchCreationDate(batchDate);
                    batchName.setBatchStatus(input.getBatchStatus());
                }

                // Check if VIN already exists for this batchName
                // BatchName existingVinBatch = batchNameRepo.findByBatchNameAndVin(input.getBatchName(), input.getVin());
                // VIN does not exist, proceed with saving
                batchName.setVin(input.getVin());
                batchName.setChassis_no(input.getChassis_no());
                batchName.setEngin_no(input.getEngin_no());
                batchName.setModel_desc(input.getModel_desc());
                batchName.setColour(input.getColour());
                batchName.setFuel_desc(input.getFuel_desc());
                batchName.setLocationId(input.getLocationId());
                batchName.setLocationName(input.getLocationName());
                batchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                batchName.setScanCreationTime(currentDate);
                batchName.setCreationDate(currentDate);
                batchName.setCreatedBy(input.getCreatedBy());
                batchName.setUpdationDate(currentDate);
                batchName.setUpdatedBy(input.getUpdatedBy()+ "-Mobile-Scan");

                batchNameRepo.save(batchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", batchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;
    }
    
    @PostMapping("/batchNameManual")
    SaiResponse batchNameManual(@RequestBody batchNameDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<BatchName> existVin = batchNameRepo.findByBatchNameAndVin(input.getBatchName(), input.getVin());
            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;

//            Optional<BatchName> existVin = batchNameRepo.findByBatchCreationDateAndVinAndBatchStatus(input.getBatchCreationDate(), input.getVin(),"open");
//            BatchName existVin1 = existVin.isPresent() ? existVin.get() : null;

            Date batchDate = batchNameRepo.getBatchDate(input.getBatchName());
            String batchName1 = batchNameRepo.getBatchName(input.getBatchName());

            BatchName batchName = new BatchName();
            if (existVin1 != null) {
                // VIN already exists for this batchName
                apiResponse = new SaiResponse(400, "VIN already exists for this batchName", null);
            } else {
                if (batchName1 == null) {
                    // If batchName does not exist, create new
                    batchName.setBatchName(input.getBatchName());
                    batchName.setBatchCreationDate(input.getBatchCreationDate());
                    batchName.setBatchStatus(input.getBatchStatus());
                } else {
                    // If batchName exists, update existing batch
                    batchName.setBatchName(input.getBatchName());
                    batchName.setBatchCreationDate(batchDate);
                    batchName.setBatchStatus(input.getBatchStatus());
                }

                // Check if VIN already exists for this batchName
                // BatchName existingVinBatch = batchNameRepo.findByBatchNameAndVin(input.getBatchName(), input.getVin());
                // VIN does not exist, proceed with saving
                batchName.setVin(input.getVin());
                batchName.setChassis_no(input.getChassis_no());
                batchName.setEngin_no(input.getEngin_no());
                batchName.setModel_desc(input.getModel_desc());
                batchName.setColour(input.getColour());
                batchName.setFuel_desc(input.getFuel_desc());
                batchName.setLocationId(input.getLocationId());
                batchName.setLocationName(input.getLocationName());
                batchName.setBatchCodeEndDate(input.getBatchCodeEndDate());
                batchName.setScanCreationTime(currentDate);
                batchName.setCreationDate(currentDate);
                batchName.setCreatedBy(input.getCreatedBy());
                batchName.setUpdationDate(currentDate);
                batchName.setUpdatedBy(input.getUpdatedBy()+ "-Mobile-Manually");

                batchNameRepo.save(batchName);
                apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", batchName);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;
    }

    
    //used for stock taking -accounts
    //....fetches the vehicles noted against a particular batchname...
    @GetMapping("/vehDetailsByBatchName")
    public SaiResponse findByBatchName(@RequestParam String batchName) throws Exception {
        SaiResponse apiResponse;
        try {
            
             List<Map> codeList= batchNameRepo.getBatchDetails(batchName);
//            List<BatchName> codeList = batchNameRepo.findByBatchName(batchName);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
   
    @GetMapping("/findBatchNameStatus")
    public SaiResponse findBatchName(@RequestParam String batchName,@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
             List<batchName1Dto> batch = batchNameRepo.getBatchStatus(batchName,location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batch);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    @GetMapping("/batchNameOpen")
    public SaiResponse batchNameOpen(@RequestParam String locationName) throws Exception {
         SaiResponse apiResponse;
        try {
            List<Map> batchList= batchNameRepo.getBatchOpen(locationName);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batchList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //used for stock taking-accounts
    //...fetches the batchname of that particular location...
    @GetMapping("/batchNameByLocation")
    public SaiResponse batchNameByLocation(@RequestParam Integer locationId, @RequestParam String createdBy) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = batchNameRepo.getBatchByFromLocation(locationId, createdBy);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    
    ///CUT OF DATE LOGIC //
//     @GetMapping("/batchCutofDate")
//    public SaiResponse batchCutofDate(@RequestParam Integer ou, @RequestParam Date cutOfDate) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            List<Map> codeList = cutOfDateRepo.getBatchCutOfDate(ou, cutOfDate);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
//        }
//        return apiResponse;
//
//    }
    
     ///CUT OF DATE LOGIC //
    @GetMapping("/batchCutofDate")
    public SaiResponse batchCutofDate(@RequestParam Integer ou) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = cutOfDateRepo.getBatchCutOfDate(ou);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    

    /////all reports start here////////
    
    //used for reports //..fetches the overall vehicle count based on model of a particular city...
    @GetMapping("/reportModelCount")
    public SaiResponse reportModelCount(@RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = vehQrRepository.getByModelCount(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
        //used for reports //..fetches the overall vehicle count based on location of a particular city...
     @GetMapping("/reportLocCount")
    public SaiResponse reportLocCount(@RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = vehQrRepository.getByLocCount(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

        //used for reports //..fetches the sum of overall vehicle  particular city...
    @GetMapping("/reportAllLocationCount")
    public SaiResponse reportAllModelCount(@RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = vehQrRepository.getByAllModelCount(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

        //used for reports //..fetches the uninvoiced vehicles of a particular city...
    @GetMapping("/reportUnInvoice")
    public SaiResponse reportUnInvoice(@RequestParam Integer ouId, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = vehQrRepository.getByUnInvoiceVehAll(ouId);
            } else if (vehType.equalsIgnoreCase("NEXA")) {
                codeList = vehQrRepository.getByUnInvoiceVehNexa(ouId);
            } else if (vehType.equalsIgnoreCase("ARENA")) {
                codeList = vehQrRepository.getByUnInvoiceVehArena(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for reports //..fetches the physically in vehicles of a particular city...
    @GetMapping("/reportPhysicallyIn")
    public SaiResponse reportPhysicallyIn(@RequestParam Integer ouId, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = vehQrRepository.getByPhyInAll(ouId);
            } else if (vehType.equalsIgnoreCase("NEXA")) {
                codeList = vehQrRepository.getByPhyInNexa(ouId);
            } else if (vehType.equalsIgnoreCase("ARENA")) {
                codeList = vehQrRepository.getByPhyInArena(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
        

//used for reports //..fetches the invoice but not delivered vehicles of a particular city...
    @GetMapping("/reportIND")
    public SaiResponse reportIND(@RequestParam Integer ouId, @RequestParam String toDate, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);

            List<Map> codeList = null;

            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = vehQrRepository.getByINDAll(ouId, toDate1);
            } else if (vehType.equalsIgnoreCase("NEXA")) {
                codeList = vehQrRepository.getByINDNexa(ouId, toDate1);
            } else if (vehType.equalsIgnoreCase("ARENA")) {
                codeList = vehQrRepository.getByINDArena(ouId, toDate1);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for reports //..fetches the intransit vehicles of a particular city...
    
    @GetMapping("/reportIntransit")
    public SaiResponse reportIntransit(@RequestParam Integer ouId, @RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = null;
            if (vehType.equalsIgnoreCase("ALL")) {
                codeList = vehQrRepository.getByIntransitAll(ouId);
            } else if (vehType.equalsIgnoreCase("NEXA")) {
                codeList = vehQrRepository.getByIntransitNexa(ouId);
            } else if (vehType.equalsIgnoreCase("ARENA")) {
                codeList = vehQrRepository.getByIntransitArena(ouId);
            }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/reportSummaryUninvoice")
    public SaiResponse reportSummaryUninvoice(@RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = vehQrRepository.getBySummaryUninvoice(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/reportSummaryIntransit")
    public SaiResponse reportSummaryIntransit(@RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {

//               Date fromDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fromDate);
//            Date toDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse(toDate);
            List<Map> codeList = vehQrRepository.getBySummaryIntransit(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

     //used for reports //..fetches the stk trf intransit vehicles of a particular city...

    @GetMapping("/reportStkTrfIntransit")
    public SaiResponse reportStkTrfIntransit(@RequestParam Integer ouId,@RequestParam String vehType) throws Exception {
        SaiResponse apiResponse;
        try {

             List<Map> codeList =null;
             
             if(vehType.equalsIgnoreCase("ALL"))
             {
              codeList =  vehQrRepository.getByStkTrfIntransitAll(ouId);
             }
             else if (vehType.equalsIgnoreCase("NEXA"))
                   {
              codeList =  vehQrRepository.getByStkTrfIntransitNexa(ouId);
             }
              else if (vehType.equalsIgnoreCase("ARENA"))
                   {
              codeList =  vehQrRepository.getByStkTrfIntransitArena(ouId);
             }

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

     @GetMapping("/findExBatchNameStatus")
    public SaiResponse findExBatchNameStatus(@RequestParam String location) throws Exception {
        SaiResponse apiResponse;
        try {
             List<Map> batch = batchNameRepo.getEXBatchStatus(location);

            apiResponse = new SaiResponse(200, "Details Found Successfully", batch);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //used for reports //..fetches the alloted vehicles of a particular city...
    
    @GetMapping("/reportStkAlloted")

    public SaiResponse reportStkAllted(@RequestParam Integer ouId,@RequestParam String vehType) throws Exception {

        SaiResponse apiResponse;

        try {

 

             List<Map> codeList =null;

            

             if(vehType.equalsIgnoreCase("ALL"))

             {

              codeList =  vehQrRepository.getByAllotedAll(ouId);

             }

             else if (vehType.equalsIgnoreCase("NEXA"))

                   {

              codeList =  vehQrRepository.getByAllotedNexa(ouId);

             }

              else if (vehType.equalsIgnoreCase("ARENA"))

                   {

              codeList =  vehQrRepository.getByAllotedArena(ouId);

             }

 

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);

        } catch (Exception e) {

            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());

        }

        return apiResponse;

 

    }
   
   
}
