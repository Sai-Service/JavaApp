/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.FndCommonDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.entity.FndCommonLookup;
import com.sai.erp.master.entity.SsVehStockLogin;
import com.sai.erp.master.service.LoginAttemptService;
import java.util.Calendar;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/login")
public class SsVehStockLoginController {

    @Autowired
    private SsVehStockLoginDao ssVehLoginRepo;

    @Autowired
    private FndCommonDao commonRepo;

    @Autowired
    private LoginAttemptService loginAttemptService;

//    @RequestMapping(value = "/loginpage1", method = org.springframework.web.bind.annotation.RequestMethod.POST, produces = {"application/JSON"})
//    public SaiResponse login(@RequestBody SsVehStockLogin loginUser, HttpServletRequest request) throws javax.security.sasl.AuthenticationException {
//        SaiResponse apiResponse = null;
//        java.util.Date currentDate = Calendar.getInstance().getTime();
//
//        if (loginUser == null) {
//            apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);
//
//        } else {
//            if (loginUser.getLoginName() == null && loginUser.getPassword() == null) {
//                apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);
//            } else {
//
//                try {
//                    SsVehStockLogin user = ssVehLoginRepo.findByLoginNameAndPassword(loginUser.getLoginName(), loginUser.getPassword());
//                    if (user != null) {
//                        // if (user.getLoginPass().equals(loginUser.getLoginPass())) {
//                        Optional<FndCommonLookup> fndLoc = commonRepo.findByAttribute1(user.getOuId().toString());
//                        FndCommonLookup fndLoc1 = fndLoc.isPresent() ? fndLoc.get() : null;
////                        user.setAttribute1(fndLoc1.getCmnDesc());
//                        apiResponse = new SaiResponse(200, "login sucessful", user);
//                    } else {
//                        apiResponse = new SaiResponse(400, " Login Error", "Invalid Username / Password Error");
//
//                    }
//                } catch (Exception e) {
//                    apiResponse = new SaiResponse(400, "Login Error", "Login Error");
//                    throw e;
//                }
//            }
//        }
//        return apiResponse;
//    }
    @RequestMapping(value = "/loginpage2", method = org.springframework.web.bind.annotation.RequestMethod.POST, produces = {"application/JSON"})
    public SaiResponse login(@RequestBody SsVehStockLogin loginUser, HttpServletRequest request) throws javax.security.sasl.AuthenticationException {
        SaiResponse apiResponse = null;
        java.util.Date currentDate = Calendar.getInstance().getTime();

        if (loginUser == null) {
            apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);

        } else {
            if (loginUser.getLoginName() == null && loginUser.getPassword() == null) {
                apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);
            } else {

                Optional<SsVehStockLogin> userOpt = ssVehLoginRepo.findByLoginName(loginUser.getLoginName());
                SsVehStockLogin user1 = userOpt.isPresent() ? userOpt.get() : null;
                if (user1 == null) {
                    apiResponse = new SaiResponse(400, "Invalid Username or Password !", null);

                } else {

                    // 1. Check if user is locked
                    if (loginAttemptService.isLocked(user1)) {
                        long secondsLeft = loginAttemptService.getRemainingLockSeconds(user1);
                        long minutes = secondsLeft / 60;
                        long seconds = secondsLeft % 60;

                        String message = String.format("Account locked. Try again in %d minutes and %d seconds.", minutes, seconds);
                        return new SaiResponse(429, message, message);
                    }

                    try {
                        SsVehStockLogin user = ssVehLoginRepo.findByLoginNameAndPassword(loginUser.getLoginName(), loginUser.getPassword());
                        if (user != null) {

                            loginAttemptService.loginSucceeded(user1);
                            // if (user.getLoginPass().equals(loginUser.getLoginPass())) {
                            Optional<FndCommonLookup> fndLoc = commonRepo.findByAttribute1(user.getOuId().toString());
                            FndCommonLookup fndLoc1 = fndLoc.isPresent() ? fndLoc.get() : null;
//                        user.setAttribute1(fndLoc1.getCmnDesc());
                            apiResponse = new SaiResponse(200, "login successful", user);
                        } else {

                            loginAttemptService.loginFailed(user1);
                            int remainingAttempts = loginAttemptService.getRemainingAttempts(user1);

                            String msg = (remainingAttempts > 0)
                                    ? String.format("Invalid credentials. You have %d attempt(s) left before lock.", remainingAttempts)
                                    : "Too many failed attempts. Your account is now locked for 10 minutes.";

//                        apiResponse = new SaiResponse(400, " Login Error", "Invalid Username / Password Error");
                            apiResponse = new SaiResponse(400, " Login Error", msg);

                        }
                    } catch (Exception e) {
                        loginAttemptService.loginFailed(user1);

                        apiResponse = new SaiResponse(400, "Login Error", "Login Error");
                        throw e;
                    }
                }
            }
        }
        return apiResponse;
    }

//    @PutMapping("/resetpassword")
//    SaiResponse updateUserLogin(@RequestBody ResetPasswordDto resetPass) throws Exception {
//        SaiResponse apiResponse;
//        //    SsVehStockLogin empLoginDetails = ssVehLoginRepo.findByLoginName( resetPass.getLoginName());
//
//        Optional<SsVehStockLogin> empLoginDetails1 = ssVehLoginRepo.findByLoginName(resetPass.getLoginName());
//        SsVehStockLogin empLoginDetails = empLoginDetails1.isPresent() ? empLoginDetails1.get() : null;
//        if (empLoginDetails != null) {
//
//            if (empLoginDetails.getLoginName().equals(resetPass.getLoginName())) {
//                if (resetPass.getnPassword().equals(resetPass.getcPassword())) {
//
//                    empLoginDetails.setPassword(resetPass.getnPassword());
//                    ssVehLoginRepo.save(empLoginDetails);
//                    apiResponse = new SaiResponse(200, "Password updated Successfully", empLoginDetails);
//                } else {
//                    apiResponse = new SaiResponse(400, "New Password Does not match with confirm Password", null);
//                }
//            } else {
//                apiResponse = new SaiResponse(400, "Username Does not match", null);
//            }
//
//        } else {
//            apiResponse = new SaiResponse(400, "Employee Does not exist", null);
//        }
//        return apiResponse;
//    }
}
