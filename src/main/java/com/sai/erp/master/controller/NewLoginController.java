/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.NewLoginDao;
import com.sai.erp.master.dto.NewLoginDto;
import com.sai.erp.master.dto.UpdateLoginDto;
import com.sai.erp.master.entity.SsVehStockLogin;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
 * @author Harsh Gawde
 */
@RestController
@RequestMapping("/admin")
public class NewLoginController {

    @Autowired
    private NewLoginDao adminRepo;

    @PostMapping("/addUser")
    public SaiResponse addUser(@RequestBody NewLoginDto input) {
        SaiResponse apiResponse;
        try {

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<SsVehStockLogin> existUser = adminRepo.findByLoginName(input.getLoginName());
            SsVehStockLogin existUser1 = existUser.isPresent() ? existUser.get() : null;

            SsVehStockLogin newUser = new SsVehStockLogin();
            if (existUser1 != null) {
                // loginname already present 
                apiResponse = new SaiResponse(400, "Login Name Already Exists", null);
            } else {

                Long lastId = adminRepo.findLastInsertedId();
               Long newId = (lastId == null) ? 1 : lastId + 1;
                
                newUser.setId(newId);
                newUser.setLoginName(input.getLoginName());
                newUser.setPassword(input.getPassword());
                newUser.setDeptName(input.getDeptName());
                newUser.setLocation(input.getLocation());
                newUser.setCreationDate(currentDate);
                newUser.setLastUpdateDate(currentDate);
                newUser.setAttribute1(input.getAttribute1());
                newUser.setAttribute2(input.getAttribute2());
                newUser.setAttribute3(input.getAttribute3());
                newUser.setAttribute4(input.getAttribute4());
                newUser.setUserId(input.getUserId());
                newUser.setEmailId(input.getEmailId());
                newUser.setLocId(input.getLocId());
                newUser.setDmsLoc(input.getDmsLoc());
                newUser.setOuId(input.getOuId());
                newUser.setLocation_name(input.getLocation_name());
                newUser.setRole(input.getRole());

                adminRepo.save(newUser);
                apiResponse = new SaiResponse(200, "New User Added Successfully", "New User Added Successfully");
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Failed To Add User", "Failed To Add User");
        }
        return apiResponse;
    }

    
    @PutMapping("/updateUser")
    SaiResponse updateUser(@RequestBody UpdateLoginDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            Date currentDate = Calendar.getInstance().getTime();

            Optional<SsVehStockLogin> existUser = adminRepo.findByLoginName(input.getLoginName());
            SsVehStockLogin existUser1 = existUser.isPresent() ? existUser.get() : null;

            if (existUser1 != null) {

                existUser1.setLoginName(input.getLoginName());
                existUser1.setPassword(input.getPassword());
                existUser1.setLastUpdateDate(currentDate);
                existUser1.setOuId(input.getOuId());
                existUser1.setLocId(input.getLocId());
                existUser1.setLocation(input.getLocation());
                existUser1.setDeptName(input.getDeptName());
                existUser1.setLocation_name(input.getLocation_name());

                adminRepo.save(existUser1);

//                adminRepo.updateSrVehStatus("STOCK", input.getLocation(), input.getLoginName());

                apiResponse = new SaiResponse(200, "User details updated successfully", input.getLoginName());
            } else {
                apiResponse = new SaiResponse(400, "User details not found", null);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating User details", "Error while updating User details");
            e.printStackTrace();

        }
        return apiResponse;
    }
    
    
     @GetMapping("/detailsByLoginName")
    public SaiResponse detailsByLoginName(@RequestParam String loginName) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = adminRepo.getdetailsByLoginName(loginName);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    @GetMapping("/locationListByOu")
    public SaiResponse detailsByOuId(@RequestParam String ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = adminRepo.detailsByOuId(ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
}
