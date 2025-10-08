/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;

import com.sai.erp.master.entity.SsDmsInvStockDuplicate;
import java.io.File;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sai.erp.master.dao.SsDmsInvStockDuplicateDao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lenovo
 */
@RestController
@RequestMapping("/invstock")
public class SsDmsInvStockController {

    @Autowired
    private SsDmsInvStockDuplicateDao invStockRepo;

    @Autowired
    private SsDmsInvStockDuplicateDao ssinvrepo;

//    private final String UPLOAD_DIR = "//sai_data//Sai_ErpAndroid_Data//";
    
    
    //-------for clone----------
//    private final String UPLOAD_DIR = "/oraarchives/Android_Data_Store/Sai_ErpAndroid_Data/";
    //------for production
    private final String UPLOAD_DIR = "/sai02_data/Android_Data_Store/Sai_ErpAndroid_Data/";
//    private final String UPLOAD_DIR = "D://Upload//";
//    @PostMapping("/upload")
//    public SaiResponse imgUpload(@RequestParam("file") MultipartFile file,
//            @RequestParam String chassis_no,
//            @RequestParam String lastUploadedBy) {
//        SaiResponse apiResponse;
//        try {
//
//            Calendar calendar = Calendar.getInstance();
//            java.util.Date currentDate = calendar.getTime();
//
//            Optional<SsDmsInvStockDuplicate> existingRecord = invStockRepo.findByChassisNo(chassis_no);
//
//            if (existingRecord.isPresent()) {
//                SsDmsInvStockDuplicate existingEntity = existingRecord.get();
//
//                // Check if vehImage is null, then upload to vehImage2 or 3
//                if (existingEntity.getVehImage() == null) {
//                    String docPath = determineFilePath(existingEntity, chassis_no, 0);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    invStockRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage2() == null) { // Check vehImage2
//                    String docPath = determineFilePath(existingEntity, chassis_no, 1);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    invStockRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
//                    return apiResponse;
//                } else if (existingEntity.getVehImage3() == null) { // Check vehImage3
//                    String docPath = determineFilePath(existingEntity, chassis_no, 2);
//                    saveFileAndEntity(file, existingEntity, docPath);
//                    existingEntity.setLastUploadedBy(lastUploadedBy);
//                    existingEntity.setLastUploadDate(currentDate);
//                    invStockRepo.save(existingEntity);
//                    apiResponse = new SaiResponse(200, "Image Uploaded Successfully", existingEntity);
//                    return apiResponse;
//                } else {
//                    apiResponse = new SaiResponse(400, "Three Images Already Uploaded For " + chassis_no, null);
//                    return apiResponse;
//                }
//            } else {
//                String docPath = UPLOAD_DIR + "ChassisNo_" + chassis_no + ".jpg";
//                SsDmsInvStockDuplicate newEntity = new SsDmsInvStockDuplicate();
//                newEntity.setChassis_no(chassis_no);
//                saveFileAndEntity(file, newEntity, docPath);
//                newEntity.setLastUploadedBy(lastUploadedBy);
//                newEntity.setLastUploadDate(new Date());
//                invStockRepo.save(newEntity);
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
//    private String determineFilePath(SsDmsInvStockDuplicate entity, String chassis_no, int index) {
//        return UPLOAD_DIR + "ChassisNo_" + chassis_no + "_" + index + ".jpg";
//    }
//
//    private void saveFileAndEntity(MultipartFile file, SsDmsInvStockDuplicate entity, String docPath) throws Exception {
//        file.transferTo(new File(docPath));
//        if (entity.getVehImage() == null) {
//            entity.setVehImage(docPath);
//        } else if (entity.getVehImage2() == null) {
//            entity.setVehImage2(docPath);
//        } else if (entity.getVehImage3() == null) {
//            entity.setVehImage3(docPath);
//        }
//        invStockRepo.save(entity);
//    }

    @PostMapping("/upload")
    public SaiResponse imgUpload(@RequestParam("file") MultipartFile file,
            @RequestParam String vin,
            @RequestParam String chassisNo,
            @RequestParam String lastUploadedBy) {
        SaiResponse apiResponse;
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Optional<SsDmsInvStockDuplicate> existingRecord = invStockRepo.findByVin(vin);

            if (existingRecord.isPresent()) {
                SsDmsInvStockDuplicate existingEntity = existingRecord.get();

                // Check if vehImage is null, then upload to vehImage2, 3, 4, or 5
                if (existingEntity.getVehImage() == null) {
                    String docPath = determineFilePath(existingEntity, vin, 0);
                    saveFileAndEntity(file, existingEntity, docPath);
                } else if (existingEntity.getVehImage2() == null) {
                    String docPath = determineFilePath(existingEntity, vin, 1);
                    saveFileAndEntity(file, existingEntity, docPath);
                } else if (existingEntity.getVehImage3() == null) {
                    String docPath = determineFilePath(existingEntity, vin, 2);
                    saveFileAndEntity(file, existingEntity, docPath);
                } else if (existingEntity.getVehImage4() == null) { // Check vehImage4
                    String docPath = determineFilePath(existingEntity, vin, 3);
                    saveFileAndEntity(file, existingEntity, docPath);
                } else if (existingEntity.getVehImage5() == null) { // Check vehImage5
                    String docPath = determineFilePath(existingEntity, vin, 4);
                    saveFileAndEntity(file, existingEntity, docPath);
                } else {
                    apiResponse = new SaiResponse(400, "Five Images Already Uploaded For " + vin, null);
                    return apiResponse;
                }

                existingEntity.setLastUploadedBy(lastUploadedBy);
                existingEntity.setLastUploadDate(currentDate);
                invStockRepo.save(existingEntity);
                apiResponse = new SaiResponse(200, "Image uploaded successfully", existingEntity);
                return apiResponse;
            } else {
                String docPath = UPLOAD_DIR + "Vin_" + vin + ".jpg";
                SsDmsInvStockDuplicate newEntity = new SsDmsInvStockDuplicate();
                newEntity.setVin(vin);
                newEntity.setChassisNo(chassisNo);
                saveFileAndEntity(file, newEntity, docPath);
                newEntity.setLastUploadedBy(lastUploadedBy);
                newEntity.setLastUploadDate(new Date());
                invStockRepo.save(newEntity);

                apiResponse = new SaiResponse(200, "Image uploaded successfully", newEntity);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Image upload failed", e.getMessage());
            return apiResponse;
        }
    }

    private String determineFilePath(SsDmsInvStockDuplicate entity, String vin, int index) {
        return UPLOAD_DIR + "Vin_" + vin + "_" + index + ".jpg";
    }

    private void saveFileAndEntity(MultipartFile file, SsDmsInvStockDuplicate entity, String docPath) throws Exception {
        file.transferTo(new File(docPath));
        if (entity.getVehImage() == null) {
            entity.setVehImage(docPath);
        } else if (entity.getVehImage2() == null) {
            entity.setVehImage2(docPath);
        } else if (entity.getVehImage3() == null) {
            entity.setVehImage3(docPath);
        } else if (entity.getVehImage4() == null) { // Save to vehImage4
            entity.setVehImage4(docPath);
        } else if (entity.getVehImage5() == null) { // Save to vehImage5
            entity.setVehImage5(docPath);
        }
        invStockRepo.save(entity);
    }

//    @GetMapping("/Download")
//    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String chassisNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            // Check vehImage first
//            Optional<SsDmsInvStockDuplicate> getImg = invStockRepo.findByChassisNoAndVehImageNotNull(chassisNo);
//            if (!getImg.isPresent()) {
//                // If vehImage is null, check vehImage2
//                getImg = invStockRepo.findByChassisNoAndVehImage2NotNull(chassisNo);
//                if (!getImg.isPresent()) {
//                    // If vehImage2 is also null, check vehImage3
//                    getImg = invStockRepo.findByChassisNoAndVehImage3NotNull(chassisNo);
//                }
//            }
//
//            if (getImg.isPresent()) {
//                SsDmsInvStockDuplicate stock = getImg.get();
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
//                    throw new Exception("No image found for chassisNo: " + chassisNo);
//                }
//            } else {
//                throw new Exception("No record found for chassisNo: " + chassisNo);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading file", e);
//            throw e;
//        }
//    }
    
    // FOR DISPLAYING ONLY 1 IMAGE OUT OF 5 IMAGE AT FRONT END
    // USED FOR CHASSIS ENQUIRY ...SALES DEPT..
    @GetMapping("/Download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String vin) throws Exception {
        try {
            // Check for images starting from vehImage to vehImage5
            Optional<SsDmsInvStockDuplicate> getImg = invStockRepo.findByVin(vin);

            if (getImg.isPresent()) {
                SsDmsInvStockDuplicate stock = getImg.get();
                String imagePath = null;

                // Check for images in order from 5 to 1
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
                    throw new Exception("No image found for vin: " + vin);
                }
            } else {
                throw new Exception("No record found for vin: " + vin);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading file", e);
            throw e;
        }
    }
    
    
    //for displaying 5 images at once at front end
//    @GetMapping("/Download")
//    public ResponseEntity<List<String>> downloadImages(@RequestParam String chassisNo) throws Exception {
//        try {
//            // Fetch the stock record
//            Optional<SsDmsInvStockDuplicate> getImg = invStockRepo.findByChassisNo(chassisNo);
//
//            if (getImg.isPresent()) {
//                SsDmsInvStockDuplicate stock = getImg.get();
//                List<String> imagePaths = new ArrayList<>();
//
//                // Collect image paths
//                if (stock.getVehImage5() != null) imagePaths.add(stock.getVehImage5());
//                if (stock.getVehImage4() != null) imagePaths.add(stock.getVehImage4());
//                if (stock.getVehImage3() != null) imagePaths.add(stock.getVehImage3());
//                if (stock.getVehImage2() != null) imagePaths.add(stock.getVehImage2());
//                if (stock.getVehImage() != null) imagePaths.add(stock.getVehImage());
//
//                if (!imagePaths.isEmpty()) {
//                    return ResponseEntity.ok(imagePaths);
//                } else {
//                    throw new Exception("No images found for chassisNo: " + chassisNo);
//                }
//            } else {
//                throw new Exception("No record found for chassisNo: " + chassisNo);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error fetching image paths", e);
//            throw e;
//        }
//    }
    
    
    // for displaying 5 images at once /...........SALES DEPT 
    
//     @GetMapping("/downloadImage")
//    public ResponseEntity<InputStreamResource> downloadImage(@RequestParam String imagePath) throws Exception {
//        try {
//            File file = new File(imagePath);
//            if (file.exists()) {
//                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.add("Content-Disposition", "attachment; filename=" + file.getName());
//
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .body(resource);
//            } else {
//                throw new FileNotFoundException("File not found: " + imagePath);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading image", e);
//            throw e;
//        }
//    }
}
