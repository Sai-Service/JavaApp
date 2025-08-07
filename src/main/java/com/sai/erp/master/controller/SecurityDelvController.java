/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AndroidVehQrRepository;
import com.sai.erp.master.dao.DetailsByVinDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dao.SsDmsStockTrfDao;
import com.sai.erp.master.dto.updateStkTrfDto;
import com.sai.erp.master.entity.SsDmsStockTrf;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/SecDelv")
public class SecurityDelvController {

    @Autowired
    private AndroidVehQrRepository vehQrRepository;

    @Autowired
    private SsDmsStockTrfDao stockTransRepo;
    
    @Autowired
    private DetailsByVinDao detByVinRepo;

    @Autowired
    private SsDmsInvStockOriginalDao origStockRepo;//SsDmsInvStockOriginalDao

//    @PostMapping("/vehTrackingBySecVin")
//    public SaiResponse vehTrackingBySecVin(
//            @RequestParam("vin") String vin,
//            @RequestParam("reasonCode") String reasonCode,
//            @RequestParam("location") String location,
//            @RequestParam("veh_status") String veh_status,
//            @RequestParam("from_location") Integer from_location,
//            @RequestParam("to_location") Integer to_location,
//            @RequestParam("organization_id") Integer organization_id,
//            @RequestParam("transferred_by") String transferred_by,
//            @RequestParam("remarks") String remarks,
//            @RequestParam("created_by") String created_by) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            Optional<AndroidVehQr> androidVehQr1 = vehQrRepository.findByVin(vin);
//            AndroidVehQr androidVehQr = androidVehQr1.isPresent() ? androidVehQr1.get() : null;
//
//            Optional<SsDmsInvStockNew> origStock1 = origStockRepo.findByVin(vin);
//            SsDmsInvStockNew origStock = origStock1.isPresent() ? origStock1.get() : null;
//
//            Calendar calendar = Calendar.getInstance();
//            java.util.Date currentDate = calendar.getTime();
//
//            if (androidVehQr == null) {
//                androidVehQr = new AndroidVehQr();
//                androidVehQr.setVin(vin);
//            }
//
//            androidVehQr.setChassisNo(origStock.getChassisNo());
//            androidVehQr.setColour(origStock.getColour());
//            androidVehQr.setFuelDesc(origStock.getFuelDesc());
//            androidVehQr.setModelDesc(origStock.getModelDesc());
//            androidVehQr.setVin(origStock.getVin());
//
//            androidVehQr.setReasonCode(reasonCode);
//            androidVehQr.setLocation(location);
//            if (origStock.getVehStatus().equalsIgnoreCase("DELIVERED") && origStock.getStatus().equalsIgnoreCase("Ready For Delivery")) {
//                androidVehQr.setVehStatus("PHYSICALLY DELIVERED");
//                origStockRepo.updateDelvStatus("PHYSICALLY DELIVERED", origStock.getVin());
//
//            } else {
//                androidVehQr.setVehStatus("STKTRF-DELIVERED");
//                origStockRepo.updateDelvStatus("STKTRF-DELIVERED", origStock.getVin());
//
//            }
////        androidVehQr.setVeh_status("INTRANSIT");
//
//            androidVehQr.setFromLocation(from_location);
//            androidVehQr.setToLocation(to_location);
////        androidVehQr.setIn_time(currentDate);
//            androidVehQr.setOutTime(currentDate);
//            androidVehQr.setOrganizationId(organization_id);
//            androidVehQr.setTransferredBy(transferred_by);
////        androidVehQr.setReceived_by(received_by);
//            androidVehQr.setRemarks(remarks);
//            androidVehQr.setCreatedBy(created_by);
//            androidVehQr.setCreationDate(currentDate);;
//            androidVehQr.setLastUpdateDate(currentDate);
//
//            vehQrRepository.save(androidVehQr);
//
//            apiResponse = new SaiResponse(200, "Vehicle Batch Updated Successfully", origStock.getVin());
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
//        }
//        return apiResponse;
//
//    }
//    
    
 @PutMapping("/vehTrackingBySecVin")
    SaiResponse updateVehicleDelv(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Date currentDate = Calendar.getInstance().getTime();

             origStockRepo.updateDelvStatus("PHYSICALLY DELIVERED",vin);

                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", vin);
           
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
            e.printStackTrace();

        }
        return apiResponse;
    }
  
    
}
