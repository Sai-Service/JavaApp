/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.master.service.AutoPhysicalDeliveryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HarshG
 */
@RestController
public class AutoPhyDelvController {

    @Autowired
    private AutoPhysicalDeliveryTask task;

//    @GetMapping("/testAutoDelivery")
//    public String testAutoDelivery() {
//        task.autoUpdateStatus();
//        return "Triggered manually!";
//    }
}
