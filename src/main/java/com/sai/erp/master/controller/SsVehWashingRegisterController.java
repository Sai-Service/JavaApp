/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dao.SsVehWashingRegisterDao;
import com.sai.erp.master.dto.VehWashOutDto;
import com.sai.erp.master.dto.VehWashProceedDto;
import com.sai.erp.master.entity.CsiItemInstances;
import com.sai.erp.master.entity.SsDmsInvStockNew;
import com.sai.erp.master.entity.SsVehWashingRegister;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HarshG
 */
@RestController
@RequestMapping("/washingRegister")
public class SsVehWashingRegisterController {

    @Autowired
    private SsVehWashingRegisterDao washingRepo;

    @Autowired
    private CsiItemInstancesDao csiRepo;

    @Autowired
    private SsDmsInvStockOriginalDao invStockRepo;

    //USED for getting the stage no of wash stages from fndcommonlookup
    @GetMapping("/washStageByCmnType")
    public SaiResponse washStageByCmnType(@RequestParam String cmnType) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = washingRepo.getWashStageByCmnType(cmnType);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    //used for getting wash stage type from fndcommonlookup by stage no
    @GetMapping("/washStageTypeByCmnCode")
    public SaiResponse washStageTypeByCmnCode(@RequestParam String cmnCode) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = washingRepo.getWashStageTypeByCmnCode(cmnCode);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    @GetMapping("/vehDetailsForWash")
    public SaiResponse vehDetailsForWash(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehWashingRegister> vehWash = washingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsVehWashingRegister vehWash1 = vehWash.isPresent() ? vehWash.get() : null;

            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;

            if (masterVeh1 != null) {

                List<Map> codeList = washingRepo.getVehDetailsCsiByRegNo(regNo);

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

    @GetMapping("/vehDetailsForWashOut")
    public SaiResponse vehDetailsForWashOut(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehWashingRegister> vehWash = washingRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsVehWashingRegister vehWash1 = vehWash.isPresent() ? vehWash.get() : null;

            if (vehWash1 != null) {

                List<Map> codeList = washingRepo.getVehWashOutDetailsByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details for vehicle found in washing table", codeList);
                return apiResponse;
            } else {
                apiResponse = new SaiResponse(400, "Details not found", regNo);
                return apiResponse;
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }

    }

    @PostMapping("/vehWashProceed")
    public SaiResponse vehWashProceed(@RequestBody VehWashProceedDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

//            Optional<SsVehWashingRegister> vehWashExist = washingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            Optional<SsVehWashingRegister> vehWashExist = Optional.empty();

            if (input.getAttribute4() != null && !input.getAttribute4().isEmpty()) {
                vehWashExist = washingRepo.findFirstByAttribute4OrderByCreationDateDesc(input.getAttribute4());
            } else if (input.getRegNo() != null && !input.getRegNo().isEmpty()) {
                vehWashExist = washingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            } else {
                return new SaiResponse(400, "Please Provide Vehicle No Or Vin No.", null);
            }

            SsVehWashingRegister vehWash = vehWashExist.orElse(null);

            if (vehWash != null) {
                // Check if all stages are OUT
                if (isAllStageOut(vehWash)) {
                    // Create a new entry
                    vehWash = new SsVehWashingRegister();
                    initializeVehWashDetails(vehWash, input, dateTime);
                    vehWash.setAttribute1(dateTime.toString());
                } else {
                    // Check if any stage is currently IN
                    if (isAnyStageInProgress(vehWash)) {
                        apiResponse = new SaiResponse(400, "A stage is already in progress. Please complete the current stage before starting a new one.", vehWash.getRegNo());
                        return apiResponse;
                    }

                    // Check if selected stages are already OUT
                    if ((input.getAttribute1() != null && isSelectedStationAlreadyOut(vehWash, input.getAttribute1()))
                            || (input.getAttribute2() != null && isSelectedStationAlreadyOut(vehWash, input.getAttribute2()))
                            || (input.getAttribute3() != null && isSelectedStationAlreadyOut(vehWash, input.getAttribute3()))) {
                        apiResponse = new SaiResponse(400, "One or more selected stages are already marked as OUT. Cannot mark them IN again in the same row.", vehWash.getRegNo());
                        return apiResponse;
                    }

                }
            } else {
                // First ever entry
                vehWash = new SsVehWashingRegister();
                initializeVehWashDetails(vehWash, input, dateTime);
                vehWash.setAttribute1(dateTime.toString());
            }

            vehWash.setUpdatedBy(input.getUpdatedBy());
            vehWash.setUpdationDate(dateTime);

            if (input.getAttribute1() != null) {
                updateStageDetails(vehWash, input.getAttribute1(), dateTime, true);
            }
            if (input.getAttribute2() != null) {
                updateStageDetails(vehWash, input.getAttribute2(), dateTime, true);
            }
            if (input.getAttribute3() != null) {
                updateStageDetails(vehWash, input.getAttribute3(), dateTime, true);
            }

            washingRepo.save(vehWash);
            apiResponse = new SaiResponse(200, "Vehicle received for wash in selected stages", vehWash);
            return apiResponse;

        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Vehicle Wash IN Failed", "Vehicle Wash IN Failed");
            return apiResponse;
        }
    }

    private boolean isAllStageOut(SsVehWashingRegister vehWash) {
        return vehWash.getFsOutTime() != null && vehWash.getSsOutTime() != null && vehWash.getDsOutTime() != null;
    }

    private void initializeVehWashDetails(SsVehWashingRegister vehWash, VehWashProceedDto input, Timestamp dateTime) {
        vehWash.setRegNo(input.getRegNo());
        vehWash.setChassisNo(input.getChassisNo());
        vehWash.setVehWashNo(generateVehWashNo(input.getLocId(), input.getOuId()));
        vehWash.setModel(input.getModel());
        vehWash.setServiceAdvisor(input.getServiceAdvisor());
        vehWash.setWashingSupervisor(input.getWashingSupervisor());
        vehWash.setLocId(input.getLocId());
        vehWash.setLocation(input.getLocation());
        vehWash.setOuId(input.getOuId());
        vehWash.setCreatedBy(input.getCreatedBy());
        vehWash.setCreationDate(dateTime);
        vehWash.setAttribute4(input.getAttribute4());
    }

    private void updateStageDetails(SsVehWashingRegister wash, String status, Timestamp time, boolean isInTime) {
        switch (status.toUpperCase()) {
            case "AIR BLOW STATION":
                wash.setAirBlowStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setFsInTime(time);
                    wash.setFirstStage("Y");
                } else {
                    wash.setFsOutTime(time);
                }
                break;
            case "UNDERBODY STATION":
                wash.setUnderbodyStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setFsInTime(time);
                    wash.setFirstStage("Y");
                } else {
                    wash.setFsOutTime(time);
                }
                break;
            case "ENGINE ROOM STATION":
                wash.setEngineRoomStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setFsInTime(time);
                    wash.setFirstStage("Y");
                } else {
                    wash.setFsOutTime(time);
                }
                break;
            case "LOOSE ITEMS STATION":
                wash.setLooseItemsStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setSsInTime(time);
                    wash.setSecondStage("Y");
                } else {
                    wash.setSsOutTime(time);
                }
                break;
            case "VEHICLE INTERIOR STATION":
                wash.setVehInteriorStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setSsInTime(time);
                    wash.setSecondStage("Y");
                } else {
                    wash.setSsOutTime(time);
                }
                break;
            case "VEHICLE EXTERIOR STATION":
                wash.setVehExteriorStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setDsInTime(time);
                    wash.setDryWashStage("Y");
                } else {
                    wash.setDsOutTime(time);
                }
                break;
            case "GLASS AND POLISH STATION":
                wash.setGlassPolishStn(isInTime ? "IN" : "OUT");
                if (isInTime) {
                    wash.setDsInTime(time);
                    wash.setDryWashStage("Y");
                } else {
                    wash.setDsOutTime(time);
                }
                break;
        }
        wash.setStatus(status);
    }

    private boolean isSelectedStationAlreadyOut(SsVehWashingRegister vehWash, String stageName) {
        switch (stageName.toUpperCase()) {
            case "AIR BLOW STATION":
                return "OUT".equalsIgnoreCase(vehWash.getAirBlowStn());
            case "UNDERBODY STATION":
                return "OUT".equalsIgnoreCase(vehWash.getUnderbodyStn());
            case "ENGINE ROOM STATION":
                return "OUT".equalsIgnoreCase(vehWash.getEngineRoomStn());
            case "LOOSE ITEMS STATION":
                return "OUT".equalsIgnoreCase(vehWash.getLooseItemsStn());
            case "VEHICLE INTERIOR STATION":
                return "OUT".equalsIgnoreCase(vehWash.getVehInteriorStn());
            case "VEHICLE EXTERIOR STATION":
                return "OUT".equalsIgnoreCase(vehWash.getVehExteriorStn());
            case "GLASS AND POLISH STATION":
                return "OUT".equalsIgnoreCase(vehWash.getGlassPolishStn());
            default:
                return false;
        }
    }

    private String generateVehWashNo(Integer locId, Integer ouId) {
        String vehWashNo = washingRepo.getMaxVehWashNoByLocId(locId, ouId);
        if (vehWashNo == null) {
            return "WASH-" + locId + "-1";
        } else {
            String[] parts = vehWashNo.split("-");
            int srlNo = Integer.parseInt(parts[2]) + 1;
            return "WASH-" + locId + "-" + srlNo;
        }
    }

    private boolean isAnyStageInProgress(SsVehWashingRegister vehWash) {
        return "IN".equalsIgnoreCase(vehWash.getAirBlowStn())
                || "IN".equalsIgnoreCase(vehWash.getUnderbodyStn())
                || "IN".equalsIgnoreCase(vehWash.getEngineRoomStn())
                || "IN".equalsIgnoreCase(vehWash.getLooseItemsStn())
                || "IN".equalsIgnoreCase(vehWash.getVehInteriorStn())
                || "IN".equalsIgnoreCase(vehWash.getVehExteriorStn())
                || "IN".equalsIgnoreCase(vehWash.getGlassPolishStn());
    }

    @PutMapping("/vehWashOut")
    public SaiResponse vehWashOut(@RequestBody VehWashOutDto input) {
        SaiResponse apiResponse;
        try {
//            Optional<SsVehWashingRegister> vehWashExist = washingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            Optional<SsVehWashingRegister> vehWashExist = Optional.empty();

            if (input.getAttribute4() != null && !input.getAttribute4().isEmpty()) {
                vehWashExist = washingRepo.findFirstByAttribute4OrderByCreationDateDesc(input.getAttribute4());
            } else if (input.getRegNo() != null && !input.getRegNo().isEmpty()) {
                vehWashExist = washingRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            } else {
                return new SaiResponse(400, "Please Provide Vehicle No Or Vin No.", null);
            }

            SsVehWashingRegister vehWash = vehWashExist.isPresent() ? vehWashExist.get() : null;

            if (vehWashExist.isPresent()) {

                Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
                boolean anyStageIn = false;

                if ("IN".equalsIgnoreCase(vehWash.getAirBlowStn())) {
                    vehWash.setAirBlowStn("OUT");
                    vehWash.setFsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getUnderbodyStn())) {
                    vehWash.setUnderbodyStn("OUT");
                    vehWash.setFsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getEngineRoomStn())) {
                    vehWash.setEngineRoomStn("OUT");
                    vehWash.setFsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getLooseItemsStn())) {
                    vehWash.setLooseItemsStn("OUT");
                    vehWash.setSsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getVehInteriorStn())) {
                    vehWash.setVehInteriorStn("OUT");
                    vehWash.setSsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getVehExteriorStn())) {
                    vehWash.setVehExteriorStn("OUT");
                    vehWash.setDsOutTime(currentTime);
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getGlassPolishStn())) {
                    vehWash.setGlassPolishStn("OUT");
                    vehWash.setDsOutTime(currentTime);
                    anyStageIn = true;
                }

                if (!anyStageIn) {
                    apiResponse = new SaiResponse(400, "No stage is marked as IN, cannot process OUT request", vehWash.getRegNo());
                    return apiResponse;
                }

                vehWash.setAttribute2(currentTime.toString()); // Store the last OUT time
                vehWash.setUpdatedBy(input.getUpdatedBy());
                vehWash.setUpdationDate(currentTime);
                washingRepo.save(vehWash);

                // Unlock the vehicle for new entries after all OUTs
                if (isWashCycleCompleted(vehWash)) {
                    apiResponse = new SaiResponse(200, "Vehicle wash completed. A new wash cycle can be started.", vehWash.getRegNo() != null ? vehWash.getRegNo() : vehWash.getAttribute4());
                    return apiResponse;
                }

                apiResponse = new SaiResponse(200, "Vehicle updated for wash OUT in selected stages", vehWash);
                return apiResponse;
            } else {

                apiResponse = new SaiResponse(400, "Vehicle wash record not found", input.getRegNo() != null ? input.getRegNo() : input.getAttribute4());
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Vehicle Wash Out Failed", e.getMessage());
            return apiResponse;
        }
    }

    private boolean isWashCycleCompleted(SsVehWashingRegister vehWash) {
        return vehWash.getFsOutTime() != null && vehWash.getSsOutTime() != null && vehWash.getDsOutTime() != null;
    }

//-----------------   SALES VEHICLE WASHING -----------------------------// 
    //get veh details by - vin 
    //used for sales vehicle washing in...
    @GetMapping("/vehDetailsForWashSales")
    public SaiResponse vehDetailsForWashSales(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsDmsInvStockNew> stock = invStockRepo.findByVin(vin);
            SsDmsInvStockNew stock1 = stock.isPresent() ? stock.get() : null;

//            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
//            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;
            if (stock1 != null) {

                List<Map> codeList = invStockRepo.getVehDetailsByVin(vin);

                apiResponse = new SaiResponse(200, "Details Found Successfully in stock table", codeList);
                return apiResponse;
            } else {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("VIN", vin);  // Add the regNo to indicate the new vehicle

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

    //used for sales veh washing out details
    @GetMapping("/vehDetailsForWashOutSales")
    public SaiResponse vehDetailsForWashOutSales(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehWashingRegister> vehWash = washingRepo.findFirstByChassisNoAndOuIdOrderByCreationDateDesc(chassisNo, ouId);
            SsVehWashingRegister vehWash1 = vehWash.isPresent() ? vehWash.get() : null;

            if (vehWash1 != null) {

                List<Map> codeList = washingRepo.getVehWashOutSalesDetailsByChassisNoAndOuId(chassisNo, ouId);

                apiResponse = new SaiResponse(200, "Details for vehicle found in washing table", codeList);
                return apiResponse;
            } else {
                apiResponse = new SaiResponse(400, "Details not found", chassisNo);
                return apiResponse;
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }

    }
}
