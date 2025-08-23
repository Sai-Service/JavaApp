/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.dao.SsVehWashingRegisterGaDao;
import com.sai.erp.master.dto.VehWashOutGaDto;
import com.sai.erp.master.dto.VehWashProceedGaDto;
import com.sai.erp.master.entity.SsVehStockLogin;
import com.sai.erp.master.entity.SsVehWashingRegisterGa;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/washingRegisterGa")
public class WashingGoaController {

    @Autowired
    private SsVehWashingRegisterGaDao washingGaRepo;

    @Autowired
    private CsiItemInstancesDao csiRepo;

    @Autowired
    private SsDmsInvStockOriginalDao invStockRepo;

    @Autowired
    private SsVehStockLoginDao userRepo;

    //USED for getting the stage type of wash stages -goa from fndcommonlookup
    @GetMapping("/washStagesGoa")
    public SaiResponse washStagesGoa() throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> washStg = washingGaRepo.getWashStageByCmnType();

            if (washStg != null && !washStg.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", washStg);
            } else {
                apiResponse = new SaiResponse(400, "Details Not Found", "No washing stages found");
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Error occurred while fetching washing stages", e.getMessage());
        }
        return apiResponse;

    }

    //--------for fetching veh wash in details , use the same api from ssvehwashingregister controller---------
    //used for fetching veh wash out details by regno
    @GetMapping("/vehDetailsForWashOutGa")
    public SaiResponse vehDetailsForWashOutGa(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehWashingRegisterGa> vehWash = washingGaRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsVehWashingRegisterGa vehWash1 = vehWash.isPresent() ? vehWash.get() : null;

            if (vehWash1 != null) {

                List<Map> vehInReg = washingGaRepo.getVehWashOutGaDetailsByRegNo(regNo);

                if (vehInReg != null && !vehInReg.isEmpty()) {
                    apiResponse = new SaiResponse(200, "Details for vehicle found in washing table", vehInReg);
                } else {
                    apiResponse = new SaiResponse(400, "Details not found", "Vehicle details not present in washing table");
                }

            } else {
                apiResponse = new SaiResponse(400, "Details not found", regNo);

            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Error occurred while fetching vehicle details", e.getMessage());

        }
        return apiResponse;
    }

    //for making veh in process
    @PostMapping("/vehWashProceedGa")
    public SaiResponse vehWashProceedGa(@RequestBody VehWashProceedGaDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            String stage = input.getStatus();
            System.out.println("You selected stage: " + stage);

            Optional<SsVehWashingRegisterGa> vehExist = Optional.empty();

            if (input.getVin() != null && !input.getVin().isEmpty()) {
                vehExist = washingGaRepo.findFirstByVinOrderByCreationDateDesc(input.getVin());
            } else if (input.getRegNo() != null && !input.getRegNo().isEmpty()) {
                vehExist = washingGaRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            } else {
                return new SaiResponse(400, "Please Provide Vehicle No Or Vin No.", null);
            }

            SsVehWashingRegisterGa vehExist1 = vehExist.orElse(null);

            Optional<SsVehStockLogin> userExist = userRepo.findByLoginName(input.getLoginName());
            SsVehStockLogin user = userExist.isPresent() ? userExist.get() : null;

            if (vehExist1 != null) {

                if (isAnyStageInProgress(vehExist1)) {
                    apiResponse = new SaiResponse(400, "A stage is already in progress. Please complete the current stage before starting a new one.", vehExist1.getRegNo());
                    return apiResponse;
                } else {

                    SsVehWashingRegisterGa vehWash = new SsVehWashingRegisterGa();
                    BeanUtils.copyProperties(input, vehWash);

                    vehWash.setRegNo(input.getRegNo());
                    vehWash.setChassisNo(input.getChassisNo());
                    vehWash.setVehWashNo(generateVehWashNo(user.getLocId(), user.getOuId()));
                    vehWash.setVin(input.getVin());
                    vehWash.setModel(input.getModel());
                    vehWash.setServiceAdvisor(input.getServiceAdvisor());
                    vehWash.setWashingSupervisor(input.getLoginName());
                    switch (stage.toUpperCase()) {
                        case "BODY WASH":
                            vehWash.setBodyWash("IN");
                            vehWash.setInTime(dateTime);
                            break;
                        case "FULL WASH":
                            vehWash.setFullWash("IN");
                            vehWash.setInTime(dateTime);
                            break;
                        case "DRY BODY WASH":
                            vehWash.setDryBodyWash("IN");
                            vehWash.setInTime(dateTime);
                            break;
                        case "DRY WASH FULL":
                            vehWash.setDryWashFull("IN");
                            vehWash.setInTime(dateTime);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid stage name: " + stage);
                    }
                    vehWash.setLocId(user.getLocId());
                    vehWash.setLocation(user.getLocation_name());
                    vehWash.setOuId(user.getOuId());
                    vehWash.setCreatedBy(user.getLoginName());
                    vehWash.setCreationDate(dateTime);
                    vehWash.setUpdatedBy(user.getLoginName());
                    vehWash.setUpdationDate(dateTime);
                    vehWash.setStatus(stage + "-IN");
                    
                    washingGaRepo.save(vehWash);

                    apiResponse = new SaiResponse(200, "Vehicle received for wash", input);
                    return apiResponse;
                }
            } else {

                SsVehWashingRegisterGa vehWash = new SsVehWashingRegisterGa();
                BeanUtils.copyProperties(input, vehWash);

                vehWash.setRegNo(input.getRegNo());
                vehWash.setChassisNo(input.getChassisNo());
                vehWash.setVehWashNo(generateVehWashNo(user.getLocId(), user.getOuId()));
                vehWash.setVin(input.getVin());
                vehWash.setModel(input.getModel());
                vehWash.setServiceAdvisor(input.getServiceAdvisor());
                vehWash.setWashingSupervisor(input.getLoginName());
                switch (stage.toUpperCase()) {
                    case "BODY WASH":
                        vehWash.setBodyWash("IN");
                        vehWash.setInTime(dateTime);
                        break;
                    case "FULL WASH":
                        vehWash.setFullWash("IN");
                        vehWash.setInTime(dateTime);
                        break;
                    case "DRY BODY WASH":
                        vehWash.setDryBodyWash("IN");
                        vehWash.setInTime(dateTime);
                        break;
                    case "DRY WASH FULL":
                        vehWash.setDryWashFull("IN");
                        vehWash.setInTime(dateTime);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid stage name: " + stage);
                }
                vehWash.setLocId(user.getLocId());
                vehWash.setLocation(user.getLocation_name());
                vehWash.setOuId(user.getOuId());
                vehWash.setCreatedBy(user.getLoginName());
                vehWash.setCreationDate(dateTime);
                vehWash.setUpdatedBy(user.getLoginName());
                vehWash.setUpdationDate(dateTime);
                vehWash.setStatus(stage + "-IN");

                washingGaRepo.save(vehWash);

                apiResponse = new SaiResponse(200, "Vehicle received for wash", input);
                return apiResponse;
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Vehicle Wash IN Failed", "Vehicle Wash IN Failed");
            return apiResponse;
        }
    }

    private boolean isAnyStageInProgress(SsVehWashingRegisterGa vehWash) {
        return "IN".equalsIgnoreCase(vehWash.getBodyWash())
                || "IN".equalsIgnoreCase(vehWash.getFullWash())
                || "IN".equalsIgnoreCase(vehWash.getDryBodyWash())
                || "IN".equalsIgnoreCase(vehWash.getDryWashFull());
    }

    private String generateVehWashNo(Integer locId, Integer ouId) {
        String vehWashNo = washingGaRepo.getMaxVehWashNoByLocId(locId, ouId);
        if (vehWashNo == null) {
            return "WASH-" + locId + "-1";
        } else {
            String[] parts = vehWashNo.split("-");
            int srlNo = Integer.parseInt(parts[2]) + 1;
            return "WASH-" + locId + "-" + srlNo;
        }
    }

    //for making veh out process
    @PutMapping("/vehWashOutGa")
    public SaiResponse vehWashOutGa(@RequestBody VehWashOutGaDto input) {
        SaiResponse apiResponse;
        try {

            Optional<SsVehWashingRegisterGa> vehWashExist = Optional.empty();

            if (input.getVin() != null && !input.getVin().isEmpty()) {
                vehWashExist = washingGaRepo.findFirstByVinOrderByCreationDateDesc(input.getVin());
            } else if (input.getRegNo() != null && !input.getRegNo().isEmpty()) {
                vehWashExist = washingGaRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            } else {
                return new SaiResponse(400, "Please Provide Vehicle No Or Vin No.", null);
            }

            SsVehWashingRegisterGa vehWash = vehWashExist.isPresent() ? vehWashExist.get() : null;

            if (vehWashExist.isPresent()) {

                Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
                boolean anyStageIn = false;

                if ("IN".equalsIgnoreCase(vehWash.getBodyWash())) {
                    vehWash.setBodyWash("OUT");
                    vehWash.setOutTime(currentTime);
                    vehWash.setStatus("BODY WASH-OUT");
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getFullWash())) {
                    vehWash.setFullWash("OUT");
                    vehWash.setOutTime(currentTime);
                    vehWash.setStatus("FULL WASH-OUT");
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getDryBodyWash())) {
                    vehWash.setDryBodyWash("OUT");
                    vehWash.setOutTime(currentTime);
                    vehWash.setStatus("DRY BODY WASH-OUT");
                    anyStageIn = true;
                }
                if ("IN".equalsIgnoreCase(vehWash.getDryWashFull())) {
                    vehWash.setDryWashFull("OUT");
                    vehWash.setOutTime(currentTime);
                    vehWash.setStatus("DRY WASH FULL-OUT");
                    anyStageIn = true;
                }

                if (!anyStageIn) {
                    apiResponse = new SaiResponse(400, "No stage is marked as IN, cannot process OUT request", vehWash.getRegNo());
                    return apiResponse;
                }

                vehWash.setUpdatedBy(input.getUpdatedBy());
                vehWash.setUpdationDate(currentTime);
                washingGaRepo.save(vehWash);

                apiResponse = new SaiResponse(200, "Vehicle wash out successful", vehWash);
                return apiResponse;
            } else {

                apiResponse = new SaiResponse(400, "Vehicle wash record not found", input.getRegNo() != null ? input.getRegNo() : input.getVin());
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Vehicle Wash Out Failed", e.getMessage());
            return apiResponse;
        }
    }
    
    
    //----------SALES VEHICLE WASHING------------
    
    //For sales vehicle wash in details , use the same api as in veh washing register
    
    //for fetching sales veh details for wash out - goa
    @GetMapping("/vehDetailsForWashOutSales")
    public SaiResponse vehDetailsForWashOutSales(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehWashingRegisterGa> vehWash = washingGaRepo.findFirstByChassisNoAndOuIdOrderByCreationDateDesc(chassisNo, ouId);
            SsVehWashingRegisterGa vehWash1 = vehWash.isPresent() ? vehWash.get() : null;

            if (vehWash1 != null) {

                List<Map> washOut = washingGaRepo.getVehWashOutSalesGaDetailsByChassisNoAndOuId(chassisNo, ouId);

                apiResponse = new SaiResponse(200, "Details for vehicle found in washing table", washOut);
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
    
    @GetMapping("/vehWashHistoryByRegNoGa")
    public SaiResponse vehWashHistoryByRegNoGa(@RequestParam String regNo)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = washingGaRepo.getVehWashHistoryGaByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }
    
    @GetMapping("/vehWashHistoryByChassisNoGa")
    public SaiResponse vehWashHistoryByChassisNoGa(@RequestParam String chassisNo)
            throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = washingGaRepo.getVehWashHistoryGaByChassisNo(chassisNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }
    
}
