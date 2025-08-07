/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.CsiItemInstancesDao;
import com.sai.erp.master.dao.OrgDefinitionDao;
import com.sai.erp.master.dao.SsDmsStockServiceDao;
import com.sai.erp.master.dao.SsDmsStockTrfServiceDao;
import com.sai.erp.master.dao.SsDmsStockTrfWsDao;
import com.sai.erp.master.dao.SsDmsWsTestDriveDao;
import com.sai.erp.master.dao.SsGateTypeMasterDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.dto.TestDriveDeliverDto;
import com.sai.erp.master.dto.TestDriveInDto;
import com.sai.erp.master.dto.TestDriveOutDto;
import com.sai.erp.master.dto.WsStockReceiveDto;
import com.sai.erp.master.dto.WsStockTrfDto;
import com.sai.erp.master.entity.CsiItemInstances;
import com.sai.erp.master.entity.SsDmsStockService;
import com.sai.erp.master.entity.SsDmsStockTrfWs;
import com.sai.erp.master.entity.SsDmsWsTestDrive;
import com.sai.erp.master.entity.SsVehStockLogin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.*;
import org.springframework.http.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author IT-HARSH
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private SsDmsStockServiceDao servRepo;

    @Autowired
    private SsDmsStockTrfServiceDao servTrfRepo;

    @Autowired
    private SsDmsStockTrfWsDao srVehTransImgRepo;

    @Autowired
    private SsVehStockLoginDao ssVehLoginRepo;

    @Autowired
    private OrgDefinitionDao orgDefRepo;

    @Autowired
    private SsDmsWsTestDriveDao testDriveRepo;

    @Autowired
    private CsiItemInstancesDao csiRepo;

    @Autowired
    private SsGateTypeMasterDao gateTypeRepo;

//    private final String UPLOAD_DIR = "D://wsUpload//";
//for clone 
    private final String UPLOAD_DIR = "/sai14_data/Service_Veh_Data/Veh_Img_Store/";

    ///for getting vehicle overall details by registration no-- SERVICE
    ///from ss_dms_inv_stock_service table
    //Vehicle enquiry form  
    @GetMapping("/srDetailsByRegNo")
    public SaiResponse srDetailsByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetailsByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///for getting vehicle overall details by chassis no-- SERVICE 
    ///from ss_dms_inv_stock_service table
    //vehicle enquiry form
    @GetMapping("/srDetailsByChassis")
    public SaiResponse srDetailsByChassis(@RequestParam String chassisNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetailsByChassis(chassisNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    ///for getting vehicle overall details by job card no-- SERVICE 
    ///from ss_dms_inv_stock_service table
    //vehicle enquiry form
    @GetMapping("/srDetailsByJobcardNo")
    public SaiResponse srDetailsByJobcardNo(@RequestParam String jobCardNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetailsByJobCardNo(jobCardNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //for getting vehicle details by reg no for VEHICLE IN process--SERVICE
    @GetMapping("/srDetInByRegNo")
    public SaiResponse srDetInByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetStockInByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //for getting vehicle details by chassis no for VEHICLE IN process---SERVICE
    @GetMapping("/srDetInByChassis")
    public SaiResponse srDetInByChassis(@RequestParam String chassisNo, @RequestParam Integer ou) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetStockInByChassis(chassisNo, ou);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //for getting vehicle details by job card no for VEHICLE IN process---SERVICE
    @GetMapping("/srDetInByJobCardNo")
    public SaiResponse srDetInByJobCardNo(@RequestParam String jobCardNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servRepo.getSrDetStockInByJobCardNo(jobCardNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //vehicle track -- SERVICE
    //for updating vehicle  status after stock in process 
    //in table ss_dms_inv_stock_service and ss_dms_stock_trf_service
//    @PutMapping("/srUpdateStockIn")
//    SaiResponse srUpdateStockIn(@RequestBody updateStkTrfServiceDto input) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            Date currentDate = Calendar.getInstance().getTime();
//
//            Optional<SsDmsStockTrfService> optionalEmpId = servTrfRepo.findByStockTrfNo(input.getStockTrfNo());
//            SsDmsStockTrfService emplMstId = optionalEmpId.isPresent() ? optionalEmpId.get() : null;
//
//            if (emplMstId != null) {
//
//                emplMstId.setReceivedBy(input.getReceivedBy());
//                emplMstId.setRecdDate(currentDate);
//                emplMstId.setToKm(input.getToKm());
//                emplMstId.setToLocation(input.getToLocation());
//                emplMstId.setUpdatedBy(input.getUpdatedBy());
//                emplMstId.setUpdationDate(currentDate);
//
//                servTrfRepo.save(emplMstId);
//
//                servRepo.updateSrVehStatus("STOCK", input.getLocation(), input.getRegNo());
//
//                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", input.getRegNo());
//            } else {
//                apiResponse = new SaiResponse(400, "Vehicle details not found", null);
//            }
//
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
//            e.printStackTrace();
//
//        }
//        return apiResponse;
//    }
    //used for vehicle track --SERVICE
    //for fetching stock transfer vehicles LIST as per location
    @GetMapping("/srTransferList")
    public SaiResponse srTransferList(@RequestParam String to_location, @RequestParam String dept) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servTrfRepo.getBySrTransList(to_location, dept);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    //------------ for stock transfer starts here ------------------//
    // for fetching service vehicle details from service stock table to make stock transfer
    // used for stock transfer process - workshop
    @GetMapping("/wsVehTransDetByJobCardNo")
    public SaiResponse wsVehTransDet(@RequestParam String jobCardNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servTrfRepo.getWsVehTransDetByJobCardNo(jobCardNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    // for fetching service vehicle details from service stock table to make stock transfer
    // used for stock transfer process - workshop
    @GetMapping("/wsVehTransDetByRegNo")
    public SaiResponse wsVehTransDetByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = servTrfRepo.getWsVehTransDetByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
        return apiResponse;

    }

    //for saving the service vehicle transfer details in table ss_dms_stock_trf_ws
    // used for stock transfer process
    @PostMapping("/wsVehTransMake")
    public SaiResponse wsVehTransMake(@ModelAttribute WsStockTrfDto input, @RequestParam(value = "images", required = false) MultipartFile[] images)
            throws Exception {
        SaiResponse apiResponse;
        try {
            Optional<SsVehStockLogin> empLoginDetails1 = ssVehLoginRepo.findByLoginName(input.getMadeBy());
            SsVehStockLogin empLoginDetails = empLoginDetails1.isPresent() ? empLoginDetails1.get() : null;

            Optional<SsDmsStockService> servStock = servRepo.findByJobCardNo(input.getJobCardNo());
            SsDmsStockService servStock1 = servStock.isPresent() ? servStock.get() : null;

            if (servStock1 == null) {
                // If the job card number is not found, throw an exception or handle the error
                apiResponse = new SaiResponse(400, "Job Card No Is Invalid Or Not Found.", "Job Card No Is Invalid Or Not Found.");
                return apiResponse;
//                throw new IllegalArgumentException("Job Card No Is Invalid Or Not Found.");
            } else {

                Calendar calendar = Calendar.getInstance();
                java.util.Date currentDate = calendar.getTime();

                LocalDateTime now = LocalDateTime.now();
                Timestamp dateTime = Timestamp.valueOf(now);

                String stkTrfNo = null;
//                String stkTrfNo1 = null;
                Integer srlNo = 0;

                stkTrfNo = srVehTransImgRepo.getMaxSrlNo(input.getOu());

                if (stkTrfNo == null) {
                    stkTrfNo = "STAN" + input.getFromLocCode() + "-" + input.getToLocCode() + "-" + 1;

                } else {
                    //     stkNo = stockTransRepo.getMaxSrlNo1(organization_id);
//                    String stkNo2[] = stkTrfNo.split("-");
//                    srlNo = Integer.parseInt(stkNo2[2]) + 1;
                    srlNo = Integer.parseInt(stkTrfNo) + 1;

                    stkTrfNo = "STAN" + input.getFromLocCode() + "-" + input.getToLocCode() + "-" + srlNo;
                }

                SsDmsStockTrfWs stkTrfNew = new SsDmsStockTrfWs();

                stkTrfNew.setRegNo(input.getRegNo());
                stkTrfNew.setChassisNo(input.getChassisNo());
                stkTrfNew.setStockTrfNo(stkTrfNo);
                stkTrfNew.setStockTrfDate(dateTime);
                stkTrfNew.setJobCardNo(input.getJobCardNo());
                stkTrfNew.setEngineNo(input.getEngineNo());
                stkTrfNew.setVin(input.getVin());
                stkTrfNew.setMadeBy(input.getMadeBy());
                stkTrfNew.setFromLocation(input.getFromLocation());
                stkTrfNew.setToLocation(input.getToLocation());
                stkTrfNew.setDriverName(input.getDriverName());
                stkTrfNew.setAuthorisedBy(input.getAuthorisedBy());
                stkTrfNew.setOu(input.getOu());
                stkTrfNew.setFromKm(input.getFromKm());
                stkTrfNew.setUpdatedBy(input.getUpdatedBy() + " - Mobile App Transfer");
                stkTrfNew.setUpdationDate(currentDate);
                stkTrfNew.setCreationDate(currentDate);
                stkTrfNew.setCreatedBy(input.getCreatedBy() + " - Mobile App Transfer");
                stkTrfNew.setDept(input.getDept());
                stkTrfNew.setFromLocCode(input.getFromLocCode());
                stkTrfNew.setToLocCode(input.getToLocCode());

//                // Handle multiple images
                if (images != null) {
                    for (int i = 0; i < images.length && i < 14; i++) {

                        String fileName = input.getJobCardNo() + "-" + stkTrfNo + "_trf_" + (i + 1) + ".jpg";

                        File destinationFile = new File(UPLOAD_DIR + fileName);

                        // Save the uploaded image to the destination path
                        images[i].transferTo(destinationFile);

                        // Assign image path to the appropriate column
                        String imagePath = UPLOAD_DIR + fileName;
                        switch (i) {
                            case 0:
                                stkTrfNew.setTrfImage1(imagePath);
                                break;
                            case 1:
                                stkTrfNew.setTrfImage2(imagePath);
                                break;
                            case 2:
                                stkTrfNew.setTrfImage3(imagePath);
                                break;
                            case 3:
                                stkTrfNew.setTrfImage4(imagePath);
                                break;
                            case 4:
                                stkTrfNew.setTrfImage5(imagePath);
                                break;
                            case 5:
                                stkTrfNew.setTrfImage6(imagePath);
                                break;
                            case 6:
                                stkTrfNew.setTrfImage7(imagePath);
                                break;
                            case 7:
                                stkTrfNew.setTrfImage8(imagePath);
                                break;
                            case 8:
                                stkTrfNew.setTrfImage9(imagePath);
                                break;
                            case 9:
                                stkTrfNew.setTrfImage10(imagePath);
                                break;
                            case 10:
                                stkTrfNew.setTrfImage11(imagePath);
                                break;
                            case 11:
                                stkTrfNew.setTrfImage12(imagePath);
                                break;
                            case 12:
                                stkTrfNew.setTrfImage13(imagePath);
                                break;
                            case 13:
                                stkTrfNew.setTrfImage14(imagePath);
                                break;
                        }
                    }
                }
                stkTrfNew.setLastUploadedBy(input.getLastUploadedBy());
                stkTrfNew.setLastUploadDate(currentDate);

                srVehTransImgRepo.save(stkTrfNew);

                servRepo.updateStkTrfMakeVehStatus("Stock Transfer In-Transit", input.getJobCardNo());

                apiResponse = new SaiResponse(200, "Vehicle Transfer Successfully", stkTrfNew);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Vehicle Transfer Failed", "Vehicle Transfer Failed");
            
        }
        return apiResponse;

    }

    //for displaying 14 images at once at front end
    @GetMapping("/wsTrfImage")
    public ResponseEntity<List<String>> wsTrfImage(@RequestParam String stockTrfNo) throws Exception {
        try {
            // Fetch the stock record
            Optional<SsDmsStockTrfWs> getImg = srVehTransImgRepo.findByStockTrfNo(stockTrfNo);

            if (getImg.isPresent()) {
                SsDmsStockTrfWs stkTrfWs = getImg.get();
                List<String> imagePaths = new ArrayList<>();

                // Collect image paths
                if (stkTrfWs.getTrfImage14() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage14());
                }
                if (stkTrfWs.getTrfImage13() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage13());
                }
                if (stkTrfWs.getTrfImage12() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage12());
                }
                if (stkTrfWs.getTrfImage11() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage11());
                }
                if (stkTrfWs.getTrfImage10() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage10());
                }
                if (stkTrfWs.getTrfImage9() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage9());
                }
                if (stkTrfWs.getTrfImage8() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage8());
                }
                if (stkTrfWs.getTrfImage7() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage7());
                }
                if (stkTrfWs.getTrfImage6() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage6());
                }
                if (stkTrfWs.getTrfImage5() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage5());
                }
                if (stkTrfWs.getTrfImage4() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage4());
                }
                if (stkTrfWs.getTrfImage3() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage3());
                }
                if (stkTrfWs.getTrfImage2() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage2());
                }
                if (stkTrfWs.getTrfImage1() != null) {
                    imagePaths.add(stkTrfWs.getTrfImage1());
                }

                if (!imagePaths.isEmpty()) {
                    return ResponseEntity.ok(imagePaths);
                } else {
                    throw new Exception("No images found for chassisNo: " + stockTrfNo);
                }
            } else {
                throw new Exception("No record found for chassisNo: " + stockTrfNo);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error fetching image paths", e);
            throw e;
        }
    }

    // for displaying all images at once /...........ws DEPT 
    @GetMapping("/wsTrfImageDownload")
    public ResponseEntity<InputStreamResource> wsTrfImageDownload(@RequestParam String imagePath) throws Exception {
        try {
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
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading image", e);
            throw e;
        }
    }

    //--------------------for stock receiving starts here ----------------------
    // for fetching service vehicle details from service stock transfer table ssdmsstocktrfws to receive vehicle
    // used for stock transfer receive process - workshop
    @GetMapping("/wsVehReceiveDetByJobCardNo")
    public SaiResponse wsVehReceiveDetByJobCardNo(@RequestParam String jobCardNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = srVehTransImgRepo.getWsVehReceiveDetByJobCardNo(jobCardNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    // for fetching service vehicle details from service stock transfer table ssdmsstocktrfws to receive vehicle
    // used for stock transfer receive process - workshop
    @GetMapping("/wsVehReceiveDetByRegNo")
    public SaiResponse wsVehReceiveDetByRegNo(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = srVehTransImgRepo.getWsVehReceiveDetByRegNo(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    //for updating vehicle  status after stock receive process  workshop
    //in table ss_dms_stock_trf_ws and ss_dms_inv_stock_service
    @PutMapping("/wsVehTransReceive")
    public SaiResponse wsVehTransReceive(@ModelAttribute WsStockReceiveDto input, @RequestParam(value = "images", required = false) MultipartFile[] images) throws Exception {
        SaiResponse apiResponse;
        try {
            Date currentDate = Calendar.getInstance().getTime();

            Optional<SsDmsStockTrfWs> stkTrf = srVehTransImgRepo.findByStockTrfNo(input.getStockTrfNo());
            SsDmsStockTrfWs stkTrf1 = stkTrf.isPresent() ? stkTrf.get() : null;

            if (stkTrf1 != null) {

                stkTrf1.setReceivedBy(input.getReceivedBy());
                stkTrf1.setRecdDate(currentDate);
                stkTrf1.setToKm(input.getToKm());

                stkTrf1.setUpdatedBy(input.getUpdatedBy() + " - Mobile App Receive");
                stkTrf1.setUpdationDate(currentDate);

                // Handle multiple images
                if (images != null) {
                    for (int i = 0; i < images.length && i < 14; i++) {

                        String fileName = input.getJobCardNo() + "-" + input.getStockTrfNo() + "_recd_" + (i + 1) + ".jpg";

                        File destinationFile = new File(UPLOAD_DIR + fileName);

                        // Save the uploaded image to the destination path
                        images[i].transferTo(destinationFile);

                        // Assign image path to the appropriate column
                        String imagePath = UPLOAD_DIR + fileName;
                        switch (i) {
                            case 0:
                                stkTrf1.setRecImage1(imagePath);
                                break;
                            case 1:
                                stkTrf1.setRecImage2(imagePath);
                                break;
                            case 2:
                                stkTrf1.setRecImage3(imagePath);
                                break;
                            case 3:
                                stkTrf1.setRecImage4(imagePath);
                                break;
                            case 4:
                                stkTrf1.setRecImage5(imagePath);
                                break;
                            case 5:
                                stkTrf1.setRecImage6(imagePath);
                                break;
                            case 6:
                                stkTrf1.setRecImage7(imagePath);
                                break;
                            case 7:
                                stkTrf1.setRecImage8(imagePath);
                                break;
                            case 8:
                                stkTrf1.setRecImage9(imagePath);
                                break;
                            case 9:
                                stkTrf1.setRecImage10(imagePath);
                                break;
                            case 10:
                                stkTrf1.setRecImage11(imagePath);
                                break;
                            case 11:
                                stkTrf1.setRecImage12(imagePath);
                                break;
                            case 12:
                                stkTrf1.setRecImage13(imagePath);
                                break;
                            case 13:
                                stkTrf1.setRecImage14(imagePath);
                                break;
                        }
                    }
                }

                stkTrf1.setLastUploadedBy(input.getLastUploadedBy());
                stkTrf1.setLastUploadDate(currentDate);

                srVehTransImgRepo.save(stkTrf1);

                servRepo.updateWsStkTrfRecVehStatus("STOCK", input.getToLocation(), input.getJobCardNo());

                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", input.getJobCardNo());
            } else {
                apiResponse = new SaiResponse(400, "Stock Transfer No. Not Found", null);
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", "Error while updating vehicle");
//            e.printStackTrace();

        }
        return apiResponse;
    }

    //for displaying 14 images at once at front end
    @GetMapping("/wsRecImage")
    public ResponseEntity<List<String>> wsRecImage(@RequestParam String stockTrfNo) throws Exception {
        try {
            // Fetch the stock record
            Optional<SsDmsStockTrfWs> getImg = srVehTransImgRepo.findByStockTrfNo(stockTrfNo);

            if (getImg.isPresent()) {
                SsDmsStockTrfWs stkTrfWs = getImg.get();
                List<String> imagePaths = new ArrayList<>();

                // Collect image paths
                if (stkTrfWs.getRecImage14() != null) {
                    imagePaths.add(stkTrfWs.getRecImage14());
                }
                if (stkTrfWs.getRecImage13() != null) {
                    imagePaths.add(stkTrfWs.getRecImage13());
                }
                if (stkTrfWs.getRecImage12() != null) {
                    imagePaths.add(stkTrfWs.getRecImage12());
                }
                if (stkTrfWs.getRecImage11() != null) {
                    imagePaths.add(stkTrfWs.getRecImage11());
                }
                if (stkTrfWs.getRecImage10() != null) {
                    imagePaths.add(stkTrfWs.getRecImage10());
                }
                if (stkTrfWs.getRecImage9() != null) {
                    imagePaths.add(stkTrfWs.getRecImage9());
                }
                if (stkTrfWs.getRecImage8() != null) {
                    imagePaths.add(stkTrfWs.getRecImage8());
                }
                if (stkTrfWs.getRecImage7() != null) {
                    imagePaths.add(stkTrfWs.getRecImage7());
                }
                if (stkTrfWs.getRecImage6() != null) {
                    imagePaths.add(stkTrfWs.getRecImage6());
                }
                if (stkTrfWs.getRecImage5() != null) {
                    imagePaths.add(stkTrfWs.getRecImage5());
                }
                if (stkTrfWs.getRecImage4() != null) {
                    imagePaths.add(stkTrfWs.getRecImage4());
                }
                if (stkTrfWs.getRecImage3() != null) {
                    imagePaths.add(stkTrfWs.getRecImage3());
                }
                if (stkTrfWs.getRecImage2() != null) {
                    imagePaths.add(stkTrfWs.getRecImage2());
                }
                if (stkTrfWs.getRecImage1() != null) {
                    imagePaths.add(stkTrfWs.getRecImage1());
                }

                if (!imagePaths.isEmpty()) {
                    return ResponseEntity.ok(imagePaths);
                } else {
                    throw new Exception("No images found for chassisNo: " + stockTrfNo);
                }
            } else {
                throw new Exception("No record found for chassisNo: " + stockTrfNo);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error fetching image paths", e);
            throw e;
        }
    }

    // for displaying all images at once /...........ws DEPT 
    @GetMapping("/wsRecImageDownload")
    public ResponseEntity<InputStreamResource> wsRecImageDownload(@RequestParam String imagePath) throws Exception {
        try {
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
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error downloading image", e);
            throw e;
        }
    }

    //for fetching vehicle transfer history by regNo
    @GetMapping("/wsVehTransHist")
    public SaiResponse wsVehTransHist(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = srVehTransImgRepo.getWsVehTransHist(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    //-----------------------------------------------------------------------------------------------//
    //---------------TEST DRIVE ------------------------------------//
    //----------FOR NEW VEHICLE & OLD VEH TEST DRIVE OUT  AND IN---------------//
    //for fetching  vehicle details  by regNo
    // previous : /wsNewVehicle
    @GetMapping("/wsVehDetForTestDriveOut")
    public SaiResponse wsVehDetForTestDriveOut(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsDmsWsTestDrive> vehOut = testDriveRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsDmsWsTestDrive vehOut1 = vehOut.isPresent() ? vehOut.get() : null;

            Optional<SsDmsStockService> vehExist = servRepo.findByRegNo(regNo);
            SsDmsStockService vehExist1 = vehExist.isPresent() ? vehExist.get() : null;

            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;

            if (vehOut1 != null && vehOut1.getOutKm() == null && vehOut1.getStatus().equalsIgnoreCase("NEW")) {

                if (vehExist1 != null && vehOut1.getRegNo().equalsIgnoreCase(vehExist1.getRegNo())) {
                    List<Map> codeList = testDriveRepo.getNewVehDetailsServByRegNo(regNo);

                    apiResponse = new SaiResponse(200, "Details Found Successfully In Service Table", codeList);
                    return apiResponse;
                } else {

                    List<Map> codeList = testDriveRepo.getNewVehDetailsTestByRegNo(regNo);

                    apiResponse = new SaiResponse(200, "Details Found Successfully In Test Drive Table", codeList);
                    return apiResponse;
                }

            } else if (vehOut1 != null && vehOut1.getOutKm() != null && vehOut1.getInKm() != null && vehOut1.getStatus().equalsIgnoreCase("STOCK")) {
                List<Map> codeList = testDriveRepo.getTestDriveOutNewDetByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully In Test Drive Table", codeList);
                return apiResponse;

            } else if (masterVeh1 != null) {

                List<Map> codeList = csiRepo.getNewVehDetailsCsiByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully in master table", codeList);
                return apiResponse;
            } else {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("REGNO", regNo);  // Add the regNo to indicate the new vehicle

                List<JSONObject> newVehicleList = new ArrayList<>();
                newVehicleList.add(newVehicleJson);  // Add the JSONObject to the list

                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
                return apiResponse;

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }

    }

    //for saving the vehicle data into table SsDmsWsTestDrive
    //for test drive ou process
    // prevoius : /wsNewVehicleIn
    @PostMapping("/wsVehTdOut")
    public SaiResponse wsVehTdOut(@RequestBody TestDriveOutDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsDmsWsTestDrive> existingVehOpt = testDriveRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());
            SsDmsWsTestDrive existingVeh = existingVehOpt.isPresent() ? existingVehOpt.get() : null;

            if (existingVehOpt.isPresent() && !existingVeh.getStatus().equalsIgnoreCase("PHYSICALLY DELIVERED")) {

                if (existingVeh.getOutKm() == null && existingVeh.getInKm() != null && existingVeh.getStatus().equalsIgnoreCase("NEW")) {

                    SsDmsWsTestDrive tdNew = new SsDmsWsTestDrive();

                    tdNew.setRegNo(input.getRegNo());
                    tdNew.setChassisNo(input.getChassisNo());

                    String testDriveNo = null;

                    Integer srlNo = 0;

                    testDriveNo = testDriveRepo.getMaxTestDriveNoNewByLocCodeAndOuAndDept(input.getLocCode(), input.getOu(), input.getDept());

                    if (testDriveNo == null) {
                        testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + 1;

                    } else {

                        String[] testDriveParts = testDriveNo.split("-");

                        if (testDriveParts.length == 4) {
                            try {
                                srlNo = Integer.parseInt(testDriveParts[3]) + 1;
                            } catch (NumberFormatException e) {

                                apiResponse = new SaiResponse(400, "Invalid serial number format in testDriveNo.", "Invalid serail number");
                                return apiResponse;
                            }
                        } else {
                            apiResponse = new SaiResponse(400, "Invalid testDriveNo format.", "Invalid testDriveNo format.");
                            return apiResponse;

                        }

                        testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + srlNo;
                    }
                    tdNew.setTestDriveNo(testDriveNo);
                    tdNew.setJobCardNo(input.getJobCardNo());
                    tdNew.setEngineNo(input.getEngineNo());
                    tdNew.setVin(input.getVin());
                    tdNew.setDriverName(input.getDriverName());
                    tdNew.setAuthorisedBy(input.getAuthorisedBy());
                    tdNew.setDept(input.getDept());
                    tdNew.setOutKm(input.getOutKm());
                    tdNew.setOutTime(dateTime);
                    tdNew.setLocation(input.getLocation());
                    tdNew.setLocCode(input.getLocCode());
                    tdNew.setOu(input.getOu());
                    tdNew.setCreatedBy(input.getCreatedBy());
                    tdNew.setCreationDate(dateTime);
                    tdNew.setUpdatedBy(input.getUpdatedBy());
                    tdNew.setUpdationDate(dateTime);
                    tdNew.setRemarks(input.getRemarks());
                    tdNew.setAttribute1(input.getAttribute1());
                    tdNew.setAttribute2(input.getAttribute2());
                    tdNew.setAttribute3(input.getAttribute3());
                    tdNew.setAttribute4(input.getAttribute4());
                    tdNew.setAttribute5(input.getAttribute5());
                    tdNew.setStatus("INTRANSIT");

                    testDriveRepo.save(tdNew);

                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out For Test Drive.", tdNew);
                    return apiResponse;

                } else if (existingVeh.getStatus().equalsIgnoreCase("STOCK") && existingVeh.getOutKm() != null && existingVeh.getInKm() != null) {

                    SsDmsWsTestDrive tdNew = new SsDmsWsTestDrive();

                    tdNew.setRegNo(input.getRegNo());
                    tdNew.setChassisNo(input.getChassisNo());

                    String testDriveNo = null;

                    Integer srlNo = 0;

                    testDriveNo = testDriveRepo.getMaxTestDriveNoNewByLocCodeAndOuAndDept(input.getLocCode(), input.getOu(), input.getDept());

                    if (testDriveNo == null) {
                        testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + 1;

                    } else {

                        String[] testDriveParts = testDriveNo.split("-");

                        if (testDriveParts.length == 4) {
                            try {
                                srlNo = Integer.parseInt(testDriveParts[3]) + 1;
                            } catch (NumberFormatException e) {

                                apiResponse = new SaiResponse(400, "Invalid serial number format in testDriveNo.", e.getMessage());
                                throw e;
                            }
                        } else {
                            throw new IllegalArgumentException("Invalid testDriveNo format.");
                        }

                        testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + srlNo;
                    }
                    tdNew.setTestDriveNo(testDriveNo);
                    tdNew.setJobCardNo(input.getJobCardNo());
                    tdNew.setEngineNo(input.getEngineNo());
                    tdNew.setVin(input.getVin());
                    tdNew.setDriverName(input.getDriverName());
                    tdNew.setAuthorisedBy(input.getAuthorisedBy());
                    tdNew.setDept(input.getDept());
                    tdNew.setOutKm(input.getOutKm());
                    tdNew.setOutTime(dateTime);
                    tdNew.setLocation(input.getLocation());
                    tdNew.setLocCode(input.getLocCode());
                    tdNew.setOu(input.getOu());
                    tdNew.setCreatedBy(input.getCreatedBy());
                    tdNew.setCreationDate(dateTime);
                    tdNew.setUpdatedBy(input.getUpdatedBy());
                    tdNew.setUpdationDate(dateTime);
                    tdNew.setRemarks(input.getRemarks());
                    tdNew.setAttribute1(input.getAttribute1());
                    tdNew.setAttribute2(input.getAttribute2());
                    tdNew.setAttribute3(input.getAttribute3());
                    tdNew.setAttribute4(input.getAttribute4());
                    tdNew.setAttribute5(input.getAttribute5());
                    tdNew.setStatus("INTRANSIT");

                    testDriveRepo.save(tdNew);

                    testDriveRepo.updateOldTestDriveOutStatus("STOCK-IN", existingVeh.getLocation(), existingVeh.getTestDriveNo(), existingVeh.getRegNo());

                    apiResponse = new SaiResponse(200, "Vehicle Successfully Out For Test Drive.", tdNew);
                    return apiResponse;
                } else {

                    apiResponse = new SaiResponse(400, "Vehicle is already out, cannot move out for test drive. Receive the vehicle first.", input.getRegNo());
                    return apiResponse;
                }

            } else {

                SsDmsWsTestDrive tdNew = new SsDmsWsTestDrive();

                tdNew.setRegNo(input.getRegNo());
                tdNew.setChassisNo(input.getChassisNo());

                String testDriveNo = null;

                Integer srlNo = 0;

                testDriveNo = testDriveRepo.getMaxTestDriveNoNewByLocCodeAndOuAndDept(input.getLocCode(), input.getOu(), input.getDept());

                if (testDriveNo == null) {
                    testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + 1;

                } else {

                    String[] testDriveParts = testDriveNo.split("-");

                    if (testDriveParts.length == 4) {
                        try {
                            srlNo = Integer.parseInt(testDriveParts[3]) + 1;
                        } catch (NumberFormatException e) {

                            apiResponse = new SaiResponse(400, "Invalid serial number format in testDriveNo.", e.getMessage());
                            throw e;
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid testDriveNo format.");
                    }

                    testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + srlNo;
                }
                tdNew.setTestDriveNo(testDriveNo);
                tdNew.setJobCardNo(input.getJobCardNo());
                tdNew.setEngineNo(input.getEngineNo());
                tdNew.setVin(input.getVin());
                tdNew.setDriverName(input.getDriverName());
                tdNew.setAuthorisedBy(input.getAuthorisedBy());
                tdNew.setDept(input.getDept());
                tdNew.setOutKm(input.getOutKm());
                tdNew.setOutTime(dateTime);
                tdNew.setLocation(input.getLocation());
                tdNew.setLocCode(input.getLocCode());
                tdNew.setOu(input.getOu());
                tdNew.setCreatedBy(input.getCreatedBy());
                tdNew.setCreationDate(dateTime);
                tdNew.setUpdatedBy(input.getUpdatedBy());
                tdNew.setUpdationDate(dateTime);
                tdNew.setRemarks(input.getRemarks());
                tdNew.setAttribute1(input.getAttribute1());
                tdNew.setAttribute2(input.getAttribute2());
                tdNew.setAttribute3(input.getAttribute3());
                tdNew.setAttribute4(input.getAttribute4());
                tdNew.setAttribute5(input.getAttribute5());
                tdNew.setStatus("INTRANSIT");

                testDriveRepo.save(tdNew);

                apiResponse = new SaiResponse(200, "Vehicle Successfully Out For Test Drive", tdNew);
                return apiResponse;
            }
        } catch (Exception e) {

            apiResponse = new SaiResponse(400, "Vehicle Out Failed", e.getMessage());
            return apiResponse;
        }

    }

//    
// for fetching service vehicle details from service TEST DRIVE table SS_DMS_WS_TEST_DRIVE
//service stock table and csi master table
// to receive test drive vehicle and new vehicle 
//  used for vehicle test drive IN process & new veh IN process
// prevoius /testDriveInDetByRegNo
    @GetMapping("/wsVehDetForTestDriveIn")
    public SaiResponse wsVehDetForTestDriveIn(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {

            Optional<SsDmsWsTestDrive> vehRec = testDriveRepo.findFirstByRegNoOrderByCreationDateDesc(regNo);
            SsDmsWsTestDrive vehRec1 = vehRec.isPresent() ? vehRec.get() : null;

            Optional<CsiItemInstances> masterVeh = csiRepo.findByInstanceNumber(regNo);
            CsiItemInstances masterVeh1 = masterVeh.isPresent() ? masterVeh.get() : null;

            if (vehRec1 != null && vehRec1.getInKm() == null && vehRec1.getOutKm() != null && vehRec1.getStatus().equalsIgnoreCase("INTRANSIT")) {
                List<Map> codeList = testDriveRepo.getTestDriveInDetByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully In Test Drive Table", codeList);
                return apiResponse;

            } else if (masterVeh1 != null) {

                List<Map> codeList = csiRepo.getNewVehDetailsCsiByRegNo(regNo);

                apiResponse = new SaiResponse(200, "Details Found Successfully in master table", codeList);
                return apiResponse;
            } else {
                JSONObject newVehicleJson = new JSONObject();
                newVehicleJson.put("REGNO", regNo);  // Add the regNo to indicate the new vehicle

                List<JSONObject> newVehicleList = new ArrayList<>();
                newVehicleList.add(newVehicleJson);  // Add the JSONObject to the list

                apiResponse = new SaiResponse(200, "New Vehicle", newVehicleList);
                return apiResponse;

            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found",  "Details not found");
            return apiResponse;
        }

    }

    //for updating TEST DRIVE -  IN KMS after test drive in  ss_dms_ws_test_drive table
    //ALSO adding new vehicle in premises and insert the in kms during arrival
    //vehicle IN after test drive
    // PREVIOUS : testDriveInMake
    @PostMapping("/wsVehTdIn")
    public SaiResponse wsVehTdIn(@RequestBody TestDriveInDto input) throws Exception {
        SaiResponse apiResponse;
        try {

            Optional<SsDmsWsTestDrive> testDriveIn = testDriveRepo.findFirstByRegNoOrderByCreationDateDesc(input.getRegNo());

            SsDmsWsTestDrive testDriveIn1 = testDriveIn.isPresent() ? testDriveIn.get() : null;

            if (testDriveIn.isPresent() && !testDriveIn1.getStatus().equalsIgnoreCase("PHYSICALLY DELIVERED")) {

                if (testDriveIn1.getInKm() != null && testDriveIn1.getOutKm() == null && testDriveIn1.getStatus().equalsIgnoreCase("STOCK")) {

                    apiResponse = new SaiResponse(400, "Test Drive Vehicle Already Received", input.getRegNo());
                    return apiResponse;
                } else if (testDriveIn1.getInKm() == null && testDriveIn1.getOutKm() != null && testDriveIn1.getStatus().equalsIgnoreCase("INTRANSIT")) {
                    Calendar calendar = Calendar.getInstance();
                    java.util.Date currentDate = calendar.getTime();

                    LocalDateTime now = LocalDateTime.now();
                    Timestamp dateTime = Timestamp.valueOf(now);

                    testDriveIn1.setInKm(input.getInKm());
                    testDriveIn1.setInTime(dateTime);
                    testDriveIn1.setUpdatedBy(input.getUpdatedBy());
                    testDriveIn1.setUpdationDate(dateTime);
                    testDriveIn1.setRemarks(input.getRemarks());
                    testDriveIn1.setStatus("STOCK");

                    testDriveRepo.save(testDriveIn1);

                    apiResponse = new SaiResponse(200, "Test Drive Vehicle Received", testDriveIn);
                    return apiResponse;
                } else {
                    apiResponse = new SaiResponse(400, "Cannot receive vehicle", "Vehicle Already In");
                    return apiResponse;
                }

            } else {

                LocalDateTime now = LocalDateTime.now();
                Timestamp dateTime = Timestamp.valueOf(now);

                SsDmsWsTestDrive tdNew = new SsDmsWsTestDrive();

                tdNew.setRegNo(input.getRegNo());
                tdNew.setChassisNo(input.getChassisNo());

                String testDriveNo = null;

                Integer srlNo = 0;

                testDriveNo = testDriveRepo.getMaxTestDriveNoNewByLocCodeAndOuAndDept(input.getLocCode(), input.getOu(), input.getDept());

                if (testDriveNo == null) {
                    testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + 1;

                } else {

                    String[] testDriveParts = testDriveNo.split("-");

                    if (testDriveParts.length == 4) {
                        try {
                            srlNo = Integer.parseInt(testDriveParts[3]) + 1;
                        } catch (NumberFormatException e) {

                            apiResponse = new SaiResponse(400, "Invalid serial number format in testDriveNo.", e.getMessage());
                            throw e;
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid testDriveNo format.");
                    }

                    testDriveNo = "TD" + "-" + input.getLocCode() + "-" + input.getDept() + "-" + srlNo;
                }
                tdNew.setTestDriveNo(testDriveNo);
                tdNew.setJobCardNo(input.getJobCardNo());
                tdNew.setEngineNo(input.getEngineNo());
                tdNew.setVin(input.getVin());
                tdNew.setDriverName(input.getDriverName());
                tdNew.setAuthorisedBy(input.getAuthorisedBy());
                tdNew.setDept(input.getDept());

                tdNew.setInKm(input.getInKm());
                tdNew.setInTime(dateTime);
                tdNew.setLocation(input.getLocation());
                tdNew.setLocCode(input.getLocCode());
                tdNew.setOu(input.getOu());
                tdNew.setCreatedBy(input.getCreatedBy());
                tdNew.setCreationDate(dateTime);
                tdNew.setUpdatedBy(input.getUpdatedBy());
                tdNew.setUpdationDate(dateTime);
                tdNew.setRemarks(input.getRemarks());
                tdNew.setAttribute1(input.getAttribute1());
                tdNew.setAttribute2(input.getAttribute2());
                tdNew.setAttribute3(input.getAttribute3());
                tdNew.setAttribute4(input.getAttribute4());
                tdNew.setAttribute5(input.getAttribute5());
                tdNew.setStatus("NEW");

                testDriveRepo.save(tdNew);

                apiResponse = new SaiResponse(200, "New Vehicle Received In Premises.", tdNew);
                return apiResponse;

            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while receiving vehicle", "Error while receiving vehicle");
            return apiResponse;
        }

    }

    //for fetching vehicle test drive history by regNo
    @GetMapping("/wsVehTestDriveHistory")
    public SaiResponse wsVehTestDriveHistory(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = testDriveRepo.getWsVehTestDriveHistory(regNo);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found","Details not found");
            return apiResponse;
        }
        return apiResponse;

    }

    //for fetching test drive vehs which are delivered and with gate pass no
    @GetMapping("/wsVehDetTestDriveDelivered")
    public SaiResponse wsVehDetTestDriveDelivered(@RequestParam String regNo) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = testDriveRepo.getWsVehDetTestDriveDeliver(regNo);

            if (!codeList.isEmpty()) {
                apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
                return apiResponse;
            } else {

                apiResponse = new SaiResponse(400, "Details Not Found", regNo);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Failed to Find Details", "Failed to Find Details");
            return apiResponse;
        }

    }

    //
    @PutMapping("/wsVehTdDeliver")
    public SaiResponse wsVehTdDeliver(@RequestBody TestDriveDeliverDto input) throws Exception {
        SaiResponse apiResponse;
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);
            Integer lastKm = 0;
            Integer tdId = testDriveRepo.getMaxTdIdSingle(input.getRegNo());
            Integer tdIdMultiple = testDriveRepo.getMaxTdIdMultiple(input.getRegNo());

            if (tdIdMultiple > 0) {
                lastKm = testDriveRepo.getMaxTdIdKm(tdIdMultiple);
            } else {
                lastKm = testDriveRepo.getMaxTdIdKm(tdId);
            }

            if (input.getOutKm() < lastKm) {

                apiResponse = new SaiResponse(400, "Out Km Must Be Greater Than Last In Km", input.getRegNo());
                return apiResponse;
            } else {
                Optional<SsDmsWsTestDrive> vehDelv = testDriveRepo.findByTestDriveNo(input.getTestDriveNo());
                SsDmsWsTestDrive vehDelv1 = vehDelv.isPresent() ? vehDelv.get() : null;

                if (vehDelv1 != null) {

                    vehDelv1.setOutKm(input.getOutKm());
                    vehDelv1.setOutTime(dateTime);
                    vehDelv1.setStatus("PHYSICALLY DELIVERED");
                    vehDelv1.setUpdatedBy(input.getUpdatedBy());
                    vehDelv1.setUpdationDate(dateTime);

                    testDriveRepo.save(vehDelv1);
                    
                    testDriveRepo.updateTdStatusPhyDeliver("PHYSICALLY DELIVERED", input.getRegNo());


                    apiResponse = new SaiResponse(200, "Vehicle Physically Delivered", vehDelv1.getRegNo());
                    return apiResponse;

                } else {
                    apiResponse = new SaiResponse(400, "Vehicle Not Found", vehDelv1.getRegNo());
                    return apiResponse;
                }

            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Details not found", "Details not found");
            return apiResponse;
        }
    }

//   
    //end of file//
}
