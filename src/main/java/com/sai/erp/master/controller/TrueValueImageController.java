/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AndroidTvVehImageDao;
import com.sai.erp.master.entity.AndroidTvVehImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author IT-HARSH
 */
@RestController
@RequestMapping("/tvImage")
public class TrueValueImageController {

    @Autowired
    private AndroidTvVehImageDao tvImageRepo;

    //-------for clone----------
//    private final String UPLOAD_DIR = "/oraarchives/Android_Data_Store/TV_Data_Store/";

    //------for production
    private final String UPLOAD_DIR = "/sai02_data/Android_Data_Store/TV_Data_Store/";
//    private final String UPLOAD_DIR = "D://tvUpload//";
    //for uploading image against a true value vehicle in table android_tv_veh_image
    
    
    
    // FOR UPLOADING 3 IMAGES -- TRUE VALUE DEPT
//    @PostMapping("/tvUpload")
//    public SaiResponse tvUpload(@RequestParam("file") MultipartFile file,
//            @RequestParam String regNo,
//            @RequestParam String lastUploadedBy) {
//        SaiResponse apiResponse;
//        try {
//
//            Calendar calendar = Calendar.getInstance();
//            java.util.Date currentDate = calendar.getTime();
//
//            Optional<AndroidTvVehImage> existingRecord = tvImageRepo.findByRegNo(regNo);
//
//            if (existingRecord.isPresent()) {
//                AndroidTvVehImage existingEntity = existingRecord.get();
//
//                // Check if vehImage is null, then upload to vehImage2 or 3
//                if (existingEntity.getVehImage() == null) {
//                    String docPath = determineFilePath(existingEntity, regNo, 0);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    tvImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage2() == null) { // Check vehImage2
//                    String docPath = determineFilePath(existingEntity, regNo, 1);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    tvImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage3() == null) { // Check vehImage3
//                    String docPath = determineFilePath(existingEntity, regNo, 2);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    tvImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image Uploaded Successfully", existingEntity);
//                    return apiResponse;
//                } else {
//                    apiResponse = new SaiResponse(400, "Three Images Already Uploaded For " + regNo, null);
//                    return apiResponse;
//                }
//            } else {
//                String docPath = UPLOAD_DIR + "RegNo_" + regNo + ".jpg";
//                AndroidTvVehImage newEntity = new AndroidTvVehImage();
//                newEntity.setRegNo(regNo);
//                saveFileAndEntity(file, newEntity, docPath);
//                newEntity.setLastUploadedBy(lastUploadedBy);
//                newEntity.setLastUploadDate(new Date());
//                tvImageRepo.save(newEntity);
//
//                apiResponse = new SaiResponse(200, "Image uploaded successfully", newEntity);
//                return apiResponse;
//            }
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(500, "Image upload failed", e.getMessage());
//
//            return apiResponse;
//        }
//    }
//
//    private String determineFilePath(AndroidTvVehImage entity, String regNo, int index) {
//        return UPLOAD_DIR + "RegNo_" + regNo + "_" + index + ".jpg";
//    }
//
//    private void saveFileAndEntity(MultipartFile file, AndroidTvVehImage entity, String docPath) throws Exception {
//        file.transferTo(new File(docPath));
//        if (entity.getVehImage() == null) {
//            entity.setVehImage(docPath);
//        } else if (entity.getVehImage2() == null) {
//            entity.setVehImage2(docPath);
//        } else if (entity.getVehImage3() == null) {
//            entity.setVehImage3(docPath);
//        }
//        tvImageRepo.save(entity);
//    }
    
    
    
    
    
    // FOR UPLOADING 5 IMAGES....TRUE VALUE DEPT.....
    @PostMapping("/tvUpload")
    public SaiResponse tvUpload(@RequestParam("file") MultipartFile file,
            @RequestParam String regNo,
            @RequestParam String lastUploadedBy) {
        SaiResponse apiResponse;
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<AndroidTvVehImage> existingRecord = tvImageRepo.findByRegNo(regNo);

            if (existingRecord.isPresent()) {
                AndroidTvVehImage existingEntity = existingRecord.get();

                // Check if vehImage is null, then upload to vehImage2, 3, 4, or 5
                if (existingEntity.getVehImage() == null) {
                    String docPath = determineFilePath(existingEntity, regNo, 0);
                    saveFileAndEntity(file, existingEntity, docPath, 0);
                } else if (existingEntity.getVehImage2() == null) {
                    String docPath = determineFilePath(existingEntity, regNo, 1);
                    saveFileAndEntity(file, existingEntity, docPath, 1);
                } else if (existingEntity.getVehImage3() == null) {
                    String docPath = determineFilePath(existingEntity, regNo, 2);
                    saveFileAndEntity(file, existingEntity, docPath, 2);
                } else if (existingEntity.getVehImage4() == null) { // Check vehImage4
                    String docPath = determineFilePath(existingEntity, regNo, 3);
                    saveFileAndEntity(file, existingEntity, docPath, 3);
                } else if (existingEntity.getVehImage5() == null) { // Check vehImage5
                    String docPath = determineFilePath(existingEntity, regNo, 4);
                    saveFileAndEntity(file, existingEntity, docPath, 4);
                } else {
                    apiResponse = new SaiResponse(400, "Five Images Already Uploaded For " + regNo, null);
                    return apiResponse;
                }

                // Update metadata after saving the image
                existingEntity.setLastUploadedBy(lastUploadedBy);
                existingEntity.setLastUploadDate(currentDate);
                tvImageRepo.save(existingEntity);
                apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
                return apiResponse;

            } else {
                // If no record exists, create a new one
                String docPath = UPLOAD_DIR + "RegNo_" + regNo + ".jpg";
                AndroidTvVehImage newEntity = new AndroidTvVehImage();
                newEntity.setRegNo(regNo);
                saveFileAndEntity(file, newEntity, docPath, 0);
                newEntity.setLastUploadedBy(lastUploadedBy);
                newEntity.setLastUploadDate(new Date());
                tvImageRepo.save(newEntity);

                apiResponse = new SaiResponse(200, "Image uploaded successfully", newEntity);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Image upload failed", e.getMessage());
            return apiResponse;
        }
    }

    private String determineFilePath(AndroidTvVehImage entity, String regNo, int index) {
        return UPLOAD_DIR + "RegNo_" + regNo + "_" + index + ".jpg";
    }

    private void saveFileAndEntity(MultipartFile file, AndroidTvVehImage entity, String docPath, int index) throws Exception {
        file.transferTo(new File(docPath));
        // Save to the appropriate vehImage field based on the index
        switch (index) {
            case 0:
                entity.setVehImage(docPath);
                break;
            case 1:
                entity.setVehImage2(docPath);
                break;
            case 2:
                entity.setVehImage3(docPath);
                break;
            case 3:
                entity.setVehImage4(docPath); // Save to vehImage4
                break;
            case 4:
                entity.setVehImage5(docPath); // Save to vehImage5
                break;
        }
        tvImageRepo.save(entity);
    }

//    @GetMapping("/tvDownload")
//    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            // Check vehImage first
//            Optional<AndroidTvVehImage> getImg = tvImageRepo.findByRegNoAndVehImageNotNull(regNo);
//            if (!getImg.isPresent()) {
//                // If vehImage is null, check vehImage2
//                getImg = tvImageRepo.findByRegNoAndVehImage2NotNull(regNo);
//                if (!getImg.isPresent()) {
//                    // If vehImage2 is also null, check vehImage3
//                    getImg = tvImageRepo.findByRegNoAndVehImage3NotNull(regNo);
//                }
//            }
//
//            if (getImg.isPresent()) {
//                AndroidTvVehImage stock = getImg.get();
//                String imagePath = null;
//
//                if (stock.getVehImage3() != null) {
//                    imagePath = stock.getVehImage3();
//                } else if (stock.getVehImage2() != null) {
//                    imagePath = stock.getVehImage2();
//                } else if (stock.getVehImage() != null) {
//                    imagePath = stock.getVehImage();
//                }
//
//                if (imagePath != null) {
//                    File file = new File(imagePath);
//                    if (file.exists()) {
//                        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//                        HttpHeaders headers = new HttpHeaders();
//                        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
//
//                        return ResponseEntity.ok()
//                                .headers(headers)
//                                .body(resource);
//                    } else {
//                        throw new FileNotFoundException("File not found: " + imagePath);
//                    }
//                } else {
//                    throw new Exception("No image found for regNo: " + regNo);
//                }
//            } else {
//                throw new Exception("No record found for regNo: " + regNo);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading file", e);
//            throw e;
//        }
//    }
    
    
    // FOR DISPLAYING 5 IMAGES AT FRONT END....
    @GetMapping("/tvDownload")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String regNo) throws Exception {
        try {
            // Retrieve the AndroidTvVehImage record based on regNo
            Optional<AndroidTvVehImage> getImg = tvImageRepo.findByRegNo(regNo);

            if (getImg.isPresent()) {
                AndroidTvVehImage stock = getImg.get();
                String imagePath = null;

                // Check for images from vehImage5 to vehImage
                if (stock.getVehImage5() != null) {
                    imagePath = stock.getVehImage5();
                } else if (stock.getVehImage4() != null) {
                    imagePath = stock.getVehImage4();
                } else if (stock.getVehImage3() != null) {
                    imagePath = stock.getVehImage3();
                } else if (stock.getVehImage2() != null) {
                    imagePath = stock.getVehImage2();
                } else if (stock.getVehImage() != null) {
                    imagePath = stock.getVehImage();
                }

                if (imagePath != null) {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Content-Disposition", "attachment; filename=" + file.getName());

                        return ResponseEntity.ok()
                                .headers(headers)
                                .body(resource);
                    } else {
                        throw new FileNotFoundException("File not found: " + imagePath);
                    }
                } else {
                    throw new Exception("No image found for regNo: " + regNo);
                }
            } else {
                throw new Exception("No record found for regNo: " + regNo);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading file", e);
            throw e;
        }
    }

}
