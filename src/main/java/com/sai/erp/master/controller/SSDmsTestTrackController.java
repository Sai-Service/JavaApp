/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import com.sai.erp.master.dao.SsDmsWsTestTrackDao;
import com.sai.erp.master.dto.TestTrackDto;
import com.sai.erp.master.entity.CsiItemInstances;
import com.sai.erp.master.entity.SSDmsWsTestTrack;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SAGAR PAWAR
 */
@RestController
@RequestMapping("/VehicleTrack")
public class SSDmsTestTrackController {

    @Autowired
    private SsDmsWsTestTrackDao testTrackRepo;

    @Autowired
    private CsiItemInstancesDao csiRepo;

    @PostMapping("/VehicleIn")
    public SaiResponse wsVehTdIn(@RequestBody TestTrackDto input) throws Exception {
        SaiResponse apiResponse;
        try {

            Optional<SSDmsWsTestTrack> testTrackIn = testTrackRepo.findByRegNoAndStatusOrderByCreationDateDesc(input.getRegNo(), "IN");

            if (testTrackIn.isPresent()) {
                apiResponse = new SaiResponse(400, "Vehicle is already In at Floor.", input.getRegNo());
                return apiResponse;
            }

            SSDmsWsTestTrack st = new SSDmsWsTestTrack();
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            st.setRegNo(input.getRegNo());
            st.setChassisNo(input.getChassisNo());
            st.setJobCardNo(input.getJobCardNo());
            st.setEngineNo(input.getEngineNo());
            st.setVin(input.getVin());
            st.setTechnicianName(input.getTechnicianName());
            st.setAuthorisedBy(input.getAuthorisedBy());
            st.setDept(input.getDept());
            st.setLocation(input.getLocation());
            st.setOu(input.getOu());
            st.setCreatedBy(input.getCreatedBy());
            st.setUpdatedBy(input.getUpdatedBy());
            st.setUpdationDate(input.getUpdationDate());
            st.setAttribute1(input.getAttribute1());
            st.setRemarks(input.getRemarks());
//        st.setOutTime(currentDate);
            st.setInTime(currentDate);
            st.setAttribute2(input.getAttribute2());
            st.setStatus("IN");
            st.setLocCode(input.getLocCode());
            st.setCreationDate(currentDate);

            testTrackRepo.save(st);
            apiResponse = new SaiResponse(200, "New Vehicle Received In Premises.", st);
            return apiResponse;

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while receiving vehicle", "Error while receiving vehicle");
            return apiResponse;
        }

    }

    @GetMapping("/wsVehDetForTestDriveIn")
    public SaiResponse wsVehDetForTestDriveIn(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {

            Optional<SSDmsWsTestTrack> vehRec = testTrackRepo.findByRegNoAndStatus(regNo, "IN");
            SSDmsWsTestTrack vehRec1 = vehRec.isPresent() ? vehRec.get() : null;

            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;

            if (vehRec.isPresent()) {
                List<SSDmsWsTestTrack> codeList = testTrackRepo.getByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully In Test Drive Table", codeList);
                return apiResponse;

            } else if (masterVeh1 != null) {

                List<Map> codeList = csiRepo.getNewVehDetailsCsiByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully in master table", codeList);
                return apiResponse;
            } else {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("REGNO", regNo);  // Add the regNo to indicate the new vehicle

                List<JSONObject> newVehicleList = new ArrayList<>();
                newVehicleList.add(newVehicleJson);  // Add the JSONObject to the list

                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
                return apiResponse;

            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
    }

    @GetMapping("/VehicleInAndOut")
    public SaiResponse VehicleInAndOut(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {

            Optional<SSDmsWsTestTrack> vehRec = testTrackRepo.findByRegNoAndStatusOrderByCreationDateDesc(regNo, "IN");
            SSDmsWsTestTrack vehRec1 = vehRec.isPresent() ? vehRec.get() : null;

            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;

            if (vehRec.isPresent()) {
//                List<SSDmsWsTestTrack> codeList = testTrackRepo.getByRegNo(regNo);
                Optional<SSDmsWsTestTrack> codeList = testTrackRepo.findByRegNoAndStatusOrderByCreationDateDesc(regNo, "IN");

                apiResponse = new SaiResponse(200, "Details Found Successfully In Test Track Table", codeList);
                return apiResponse;

            } else {

                Optional<SSDmsWsTestTrack> vehOut = testTrackRepo.findByRegNoAndStatus(regNo, "OUT");
                SSDmsWsTestTrack vehOut1 = vehRec.isPresent() ? vehRec.get() : null;

                apiResponse = new SaiResponse(400, "Vehicle Is Not IN", vehOut1);
                return apiResponse;
            }
//            else {
//                JSONObject newVehicleJson = new JSONObject();
//                newVehicleJson.put("REGNO", regNo);  // Add the regNo to indicate the new vehicle
//
//                List<JSONObject> newVehicleList = new ArrayList<>();
//                newVehicleList.add(newVehicleJson);  // Add the JSONObject to the list
//
//                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
//                return apiResponse;
//
//            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
    }

//       @PutMapping("/VehicleOUT/{id}")
//    public SaiResponse VehicleIn(@PathVariable("trfId") Integer trfId,@RequestBody TestTrackDto input) throws Exception {
//        SaiResponse apiResponse = null;
//        try {
//            Calendar calendar = Calendar.getInstance();
//            java.util.Date currentDate = calendar.getTime();
//
//            LocalDateTime now = LocalDateTime.now();
//            Timestamp dateTime = Timestamp.valueOf(now);
//            
//            Optional<SSDmsWsTestTrack> cdmMst = testTrackRepo.findByTrfId(trfId);
//            SSDmsWsTestTrack docMst = cdmMst.isPresent() ? cdmMst.get() : null;
//            
//            Optional<SSDmsWsTestTrack> testTrackIn = testTrackRepo.findByRegNoAndStatusOrderByCreationDateDesc(input.getRegNo(),"OUT");
//             
//             if (testTrackIn.isPresent() ) {
//                    apiResponse = new SaiResponse(400, "Test Drive Vehicle Already OUT", input.getRegNo());
//                    return apiResponse;
//                }
//             
//                SSDmsWsTestTrack st=new SSDmsWsTestTrack();
//        st.setRegNo(input.getRegNo());
//        st.setChassisNo(input.getChassisNo());
//        st.setTestDriveNo(input.getTestDriveNo());
//        st.setJobCardNo(input.getJobCardNo());
//        st.setEngineNo(input.getEngineNo());
//        st.setVin(input.getVin());
//        st.setTechnicianName(input.getTechnicianName());
//        st.setAuthorisedBy(input.getAuthorisedBy());
//        st.setDept(input.getDept());
//        st.setLocation(input.getLocation());
//        st.setOu(input.getOu());
//        st.setCreatedBy(input.getCreatedBy());
//        st.setUpdatedBy(input.getUpdatedBy());
//        st.setUpdationDate(input.getUpdationDate());
//        st.setRemarks(input.getRemarks());
//
//        st.setOutTime(currentDate);
//        st.setInTime(currentDate);
//        st.setFinalTime(input.getFinalTime());
//        st.setRoadtestTime(input.getRoadtestTime());
//        st.setFromdelTime(input.getFromdelTime());
//        st.setLocCode(input.getLocCode());
//        st.setStatus("OUT");
//        st.setUpdatedBy(input.getUpdatedBy());
//        st.setUpdationDate(currentDate);
//                
//                testTrackRepo.save(st);
//            
//            apiResponse = new SaiResponse(200, "Vehicle Successfully Out For Test Drive", st);
//                return apiResponse;
//            
//        } catch (Exception e) {
//
//            apiResponse = new SaiResponse(400, "Vehicle Out Failed", e.getMessage());
//            return apiResponse;
//}
//    
//    }
    @PutMapping("/VehicleOUT/{trfId}")
    public SaiResponse vehicleOut(@PathVariable("trfId") Integer trfId, @RequestBody TestTrackDto input) throws Exception {
        try {
            java.util.Date currentDate = new java.util.Date();

            // Check for already OUT
            Optional<SSDmsWsTestTrack> existingOut = testTrackRepo.findByRegNoAndStatusOrderByCreationDateDesc(input.getRegNo(), "OUT");
            if (existingOut.isPresent()) {
                return new SaiResponse(400, "Test Drive Vehicle Already OUT", input.getRegNo());
            }

            // Find the existing record by trfId
            SSDmsWsTestTrack st = testTrackRepo.findByTrfId(trfId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found with trfId: " + trfId));

            // Update fields
            st.setOutTime(currentDate);
            st.setStatus("OUT");
            st.setUpdatedBy(input.getUpdatedBy());
            st.setUpdationDate(currentDate);
            st.setAttribute2(input.getAttribute2());

            // Save updated record
            testTrackRepo.save(st);

            return new SaiResponse(200, "Vehicle Successfully Out For Test Drive", st);

        } catch (Exception e) {
            return new SaiResponse(400, "Vehicle Out Failed", e.getMessage());
        }
    }

    @GetMapping("/VehicleHistory")
    public SaiResponse VehicleHistory(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {

            List<SSDmsWsTestTrack> codeList = testTrackRepo.getByRegNoOrderByTrfIdDesc(regNo);

            apiResponse = new SaiResponse(400, "Vehicle History Found Successfully", codeList);
            return apiResponse;

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
    }

    @GetMapping("/vehTrackReportByOu")
    public SaiResponse getReportByOu(
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate,
            @RequestParam(required = false) String loc_code, // String here to capture empty input
            @RequestParam(required = false) String ou) {

        try {
            // Convert empty strings to null so NVL in query works properly
            Integer locCodeParam = (loc_code == null || loc_code.trim().isEmpty()) ? null : Integer.valueOf(loc_code);
            Integer ouParam = (ou == null || ou.trim().isEmpty()) ? null : Integer.valueOf(ou);

            List<Map<String, Object>> results = testTrackRepo.getTestTrackDatewise(fromDate, toDate, locCodeParam, ouParam);
            return new SaiResponse(200, "Details Found Successfully", results);
        } catch (Exception e) {
            return new SaiResponse(400, "Details not found", "Error: " + e.getMessage());
        }
    }

}
