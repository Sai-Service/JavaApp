/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.master.dao.SsDmsInvStockDuplicateDao;
import com.sai.erp.master.dao.SsDmsStockTrfDao;
import com.sai.erp.master.dto.SsDmsStockTrfDto;
import com.sai.erp.master.dto.batchNameDto;
import com.sai.erp.master.entity.AndroidVehQr;
import com.sai.erp.master.entity.SsDmsInvStockOriginal;
import com.sai.erp.master.entity.SsDmsStockTrf;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/stockTransfer")
public class SsDmsStockTrfController {
    @Autowired
    private SsDmsStockTrfDao stockTransRepo;
    
    
     
     
//     @PostMapping("/vehTrackingByVin")
//    public ResponseEntity<String> storeReasonAndLocation(@RequestBody SsDmsStockTrfDto input) {
// 
//        Optional<SsDmsStockTrf> ssStockTrf1 = stockTransRepo.findByVin(vin);
//        SsDmsStockTrf ssStockTrf = ssStockTrf1.isPresent() ? ssStockTrf1.get() : null;
// 
//        
//        
//
// 
//        Calendar calendar = Calendar.getInstance();
//        java.util.Date currentDate = calendar.getTime();
// 
//        if (ssStockTrf == null) {
//            ssStockTrf = new SsDmsStockTrf();
//            ssStockTrf.setVin(vin);
//        }
//        androidVehQr.setChassisNo(origStock.getChassisNo());
//        androidVehQr.setColour(origStock.getColour());
//        androidVehQr.setFuelDesc(origStock.getFuel_desc());
//        androidVehQr.setModelDesc(origStock.getModel_desc());
//        
//        
//        
//      androidVehQr.setReasonCode(reasonCode);
//        androidVehQr.setLocation(location);
//        if ("Vehicle Delivered".equals(reasonCode)) {
//            androidVehQr.setVehStatus("DELIVERED");
//        } else {
//            androidVehQr.setVehStatus("INTRANSIT");
//        }
//        androidVehQr.setFromLocation(from_location);
//        androidVehQr.setToLocation(to_location);
//        androidVehQr.setOutTime(currentDate);
//        androidVehQr.setOrganizationId(organization_id);
//        androidVehQr.setTransferredBy(transferred_by);
//        androidVehQr.setRemarks(remarks);
//        androidVehQr.setCreatedBy(created_by);
//        androidVehQr.setCreationDate(currentDate);;
//        androidVehQr.setLastUpdateDate(currentDate);
// 
//        vehQrRepository.save(androidVehQr);
//        
//         origStockRepo.updateDelvStatus("STK-TRF",origStock.getVin());
//       
// 
//        return ResponseEntity.status(HttpStatus.CREATED).body("Values against vin stored successfully");
//    }
}
