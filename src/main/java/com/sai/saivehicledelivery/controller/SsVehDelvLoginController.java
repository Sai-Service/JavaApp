/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.controller;

import com.sai.saivehicledelivery.SaiResponse;
import com.sai.saivehicledelivery.dao.SsVehDelvLoginDao;
import com.sai.saivehicledelivery.entity.SsVehDelvLogin;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Harsh Gawde
 */
@RestController
@RequestMapping("/login")
public class SsVehDelvLoginController {

    @Autowired
    private SsVehDelvLoginDao loginRepo;

    //code for login with validation
    @RequestMapping(value = "/vehDelvLogin", method = org.springframework.web.bind.annotation.RequestMethod.POST, produces = {"application/JSON"})
    public SaiResponse login(@RequestBody SsVehDelvLogin loginUser, HttpServletRequest request) throws javax.security.sasl.AuthenticationException {
        SaiResponse apiResponse;
        java.util.Date currentDate = Calendar.getInstance().getTime();

        if (loginUser == null) {
            apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);

        } else {
            if (loginUser.getLoginName() == null && loginUser.getPassword() == null) {
                apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);
            } else {

                try {
                    SsVehDelvLogin user = loginRepo.findByLoginNameAndPassword(loginUser.getLoginName(), loginUser.getPassword());
                    if (user != null) {

                        apiResponse = new SaiResponse(200, "login sucessful", user);
                    } else {
                        apiResponse = new SaiResponse(400, " Login Error", "Invalid Username / Password Error");

                    }
                } catch (Exception e) {
                    apiResponse = new SaiResponse(400, "Login Error", "Login Error");
                    return apiResponse;
                }
            }
        }
        return apiResponse;
    }

    //code for reset password by login name and old password
//    @PutMapping("/resetPassword")
//    SaiResponse resetPassword(@RequestBody ResetPasswordDto resetPass) throws Exception {
//        SaiResponse apiResponse;
//        try {
//
//            Optional<SsVehDelvLogin> userDetails = loginRepo.findDetailsByLoginNameAndPassword(resetPass.getLoginName(), resetPass.getPassword());
//            SsVehDelvLogin userDetails1 = userDetails.isPresent() ? userDetails.get() : null;
//            if (userDetails1 != null) {
//
//                if (resetPass.getLoginName().equals(userDetails1.getLoginName())) {
//                    if (resetPass.getPassword().equals(userDetails1.getPassword())) {
//
//                        userDetails1.setPassword(resetPass.getnPassword());
//                        loginRepo.save(userDetails1);
//                        apiResponse = new SaiResponse(200, "Password updated Successfully", userDetails1);
//                    } else {
//                        apiResponse = new SaiResponse(400, "Password Does Not Match With Old Password", null);
//                    }
//                } else {
//                    apiResponse = new SaiResponse(400, "Username Does not match", null);
//                }
//
//            } else {
//                apiResponse = new SaiResponse(400, "Employee Does not exist", null);
//            }
//            return apiResponse;
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
//        }
//        return apiResponse;
//    }
    
    //git commit
}
