/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.service;

import com.sai.erp.master.dao.SsDmsWsTestDriveDao;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author HarshG
 */
@Component
public class AutoPhysicalDeliveryTask {

    @Autowired
    private SsDmsWsTestDriveDao testDriveRepo;

//    @Scheduled(cron = "0 0 1 * * ?") // runs every day at 1 AM
//    public void autoUpdateStatus() {
//        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(8);
//        testDriveRepo.updateStatusToPhysicallyDelivered(cutoffDate);
//    }
}
