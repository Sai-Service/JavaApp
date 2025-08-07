/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AppVersionDao;
import com.sai.erp.master.dto.AppVersionDto;
import com.sai.erp.master.entity.SsAppVersionChsEnq;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HarshG
 */
@RestController
@RequestMapping("/appVer")
public class AppVersionController {

    @Autowired
    private AppVersionDao versionRepo;

    //uploading the apk details in table
    @PostMapping("/upload")
    public SaiResponse upload(@RequestBody AppVersionDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsAppVersionChsEnq> ver = versionRepo.findByVersionCode(input.getVersionCode());
            SsAppVersionChsEnq version = ver.isPresent() ? ver.get() : null;

            if (version != null) {
                apiResponse = new SaiResponse(400, "This Version Apk Already Uploaded", input.getVersionCode());
                return apiResponse;
            } else {
                SsAppVersionChsEnq appVersion = new SsAppVersionChsEnq();
                BeanUtils.copyProperties(input, appVersion);

                appVersion.setCreationDate(dateTime);
                appVersion.setUpdationDate(dateTime);

                versionRepo.save(appVersion);
                apiResponse = new SaiResponse(200, "New Version Apk Uploaded Successfully", input);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Internal Server Error", "Internal Server Error");

        }
        return apiResponse;

    }

    //get latest app version for comparison
    @GetMapping("/latest")
    public SaiResponse getLatestVersion() {
        SaiResponse apiResponse;
        try {
            Optional<SsAppVersionChsEnq> latestVersion = versionRepo.findTopByOrderByReleaseDateDesc();

            if (latestVersion.isPresent()) {
                apiResponse = new SaiResponse(200, "Latest Version Fetched Successfully", latestVersion.get());
            } else {
                apiResponse = new SaiResponse(404, "No Version Found", "No version data available");
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Internal Server Error", e.getMessage());
        }

        return apiResponse;
    }

}
