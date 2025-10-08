/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AndroidServiceVehImageDao;
import com.sai.erp.master.entity.AndroidServiceVehImage;
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
@RequestMapping("/servImage")
public class ServiceImageController {

    @Autowired
    private AndroidServiceVehImageDao servImageRepo;

    //-------for clone----------
//    private final String UPLOAD_DIR = "/oraarchives/Android_Data_Store/Service_Data_Store/";

    //------for production
    private final String UPLOAD_DIR = "/sai02_data/Android_Data_Store/Service_Data_Store/";
//    private final String UPLOAD_DIR = "D://srUpload//";
//    @PostMapping("/srUpload")
//    public SaiResponse srUpload(@RequestParam("file") MultipartFile file,
//            @RequestParam String regNo,
//            @RequestParam String lastUploadedBy) {
//        SaiResponse apiResponse;
//        try {
//
//            Calendar calendar = Calendar.getInstance();
//            java.util.Date currentDate = calendar.getTime();
//
//            Optional<AndroidServiceVehImage> existingRecord = servImageRepo.findByRegNo(regNo);
//
//            if (existingRecord.isPresent()) {
//                AndroidServiceVehImage existingEntity = existingRecord.get();
//
//                // Check if vehImage is null, then upload to vehImage2 or 3
//                if (existingEntity.getVehImage() == null) {
//                    String docPath = determineFilePath(existingEntity, regNo, 0);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    servImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage2() == null) { // Check vehImage2
//                    String docPath = determineFilePath(existingEntity, regNo, 1);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    servImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage3() == null) { // Check vehImage3
//                    String docPath = determineFilePath(existingEntity, regNo, 2);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    servImageRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image Uploaded Successfully", existingEntity);
//                    return apiResponse;
//                } else {
//                    apiResponse = new SaiResponse(400, "Three Images Already Uploaded For " + regNo, null);
//                    return apiResponse;
//                }
//            } else {
//                String docPath = UPLOAD_DIR + "RegNo_" + regNo + ".jpg";
//                AndroidServiceVehImage newEntity = new AndroidServiceVehImage();
//                newEntity.setRegNo(regNo);
//                saveFileAndEntity(file, newEntity, docPath);
//                newEntity.setLastUploadedBy(lastUploadedBy);
//                newEntity.setLastUploadDate(new Date());
//                servImageRepo.save(newEntity);
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
//    private String determineFilePath(AndroidServiceVehImage entity, String regNo, int index) {
//        return UPLOAD_DIR + "RegNo_" + regNo + "_" + index + ".jpg";
//    }
//
//    private void saveFileAndEntity(MultipartFile file, AndroidServiceVehImage entity, String docPath) throws Exception {
//        file.transferTo(new File(docPath));
//        if (entity.getVehImage() == null) {
//            entity.setVehImage(docPath);
//        } else if (entity.getVehImage2() == null) {
//            entity.setVehImage2(docPath);
//        } else if (entity.getVehImage3() == null) {
//            entity.setVehImage3(docPath);
//        }
//        servImageRepo.save(entity);
//    }
    
    
    // FOR UPLOADING 5 IMAGES -- SERVICE DEPT....
    @PostMapping("/srUpload")
    public SaiResponse srUpload(@RequestParam("file") MultipartFile file,
            @RequestParam String regNo,
            @RequestParam String lastUploadedBy) {
        SaiResponse apiResponse;
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<AndroidServiceVehImage> existingRecord = servImageRepo.findByRegNo(regNo);

            if (existingRecord.isPresent()) {
                AndroidServiceVehImage existingEntity = existingRecord.get();

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
                } else if (existingEntity.getVehImage4() == null) {
                    String docPath = determineFilePath(existingEntity, regNo, 3);
                    saveFileAndEntity(file, existingEntity, docPath, 3);
                } else if (existingEntity.getVehImage5() == null) {
                    String docPath = determineFilePath(existingEntity, regNo, 4);
                    saveFileAndEntity(file, existingEntity, docPath, 4);
                } else {
                    apiResponse = new SaiResponse(400, "Five images already uploaded for " + regNo, null);
                    return apiResponse;
                }

                // Update metadata after saving the image
                existingEntity.setLastUploadedBy(lastUploadedBy);
                existingEntity.setLastUploadDate(currentDate);
                servImageRepo.save(existingEntity);
                apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
                return apiResponse;

            } else {
                // If no record exists, create a new one
                String docPath = UPLOAD_DIR + "RegNo_" + regNo + ".jpg";
                AndroidServiceVehImage newEntity = new AndroidServiceVehImage();
                newEntity.setRegNo(regNo);
                saveFileAndEntity(file, newEntity, docPath, 0);
                newEntity.setLastUploadedBy(lastUploadedBy);
                newEntity.setLastUploadDate(new Date());
                servImageRepo.save(newEntity);

                apiResponse = new SaiResponse(200, "Image uploaded successfully", newEntity);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Image upload failed", e.getMessage());
            return apiResponse;
        }
    }

    private String determineFilePath(AndroidServiceVehImage entity, String regNo, int index) {
        return UPLOAD_DIR + "RegNo_" + regNo + "_" + index + ".jpg";
    }

    private void saveFileAndEntity(MultipartFile file, AndroidServiceVehImage entity, String docPath, int index) throws Exception {
        file.transferTo(new File(docPath));
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
                entity.setVehImage4(docPath);
                break;
            case 4:
                entity.setVehImage5(docPath);
                break;
        }
        servImageRepo.save(entity);
    }

//    @GetMapping("/srDownload")
//    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String regNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            // Check vehImage first
//            Optional<AndroidServiceVehImage> getImg = servImageRepo.findByRegNoAndVehImageNotNull(regNo);
//            if (!getImg.isPresent()) {
//                // If vehImage is null, check vehImage2
//                getImg = servImageRepo.findByRegNoAndVehImage2NotNull(regNo);
//                if (!getImg.isPresent()) {
//                    // If vehImage2 is also null, check vehImage3
//                    getImg = servImageRepo.findByRegNoAndVehImage3NotNull(regNo);
//                }
//            }
//
//            if (getImg.isPresent()) {
//                AndroidServiceVehImage stock = getImg.get();
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
    
    
    // FOR DISPLAYING 5 IMAGES AT FRONT END
    @GetMapping("/srDownload")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {

            // Retrieve the AndroidTvVehImage record based on regNo
            Optional<AndroidServiceVehImage> getImg = servImageRepo.findByRegNo(regNo);

            if (getImg.isPresent()) {
                AndroidServiceVehImage stock = getImg.get();
                String imagePath = null;

                // Select the last available image from the order of preference
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
