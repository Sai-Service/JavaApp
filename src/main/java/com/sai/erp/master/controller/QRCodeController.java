package com.sai.erp.master.controller;

import com.sai.erp.SaiResponse;
import com.sai.erp.master.dao.AndroidVehQrRepository;
import com.sai.erp.master.dao.DetailsByVinDao;
import com.sai.erp.master.dao.OrgDefinitionDao;
import com.sai.erp.master.dao.SsDmsInvStockOriginalDao;
import com.sai.erp.master.dao.SsDmsStockTrfDao;
import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.dto.updateInStkTrfDto;
import com.sai.erp.master.dto.updateStkTrfDto;
import com.sai.erp.master.entity.AndroidVehQr;
import com.sai.erp.master.entity.SsDmsInvStockNew;
import com.sai.erp.master.entity.SsDmsStockTrf;
import com.sai.erp.master.entity.SsVehStockLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private AndroidVehQrRepository vehQrRepository;

    @Autowired
    private DetailsByVinDao detByVinRepo;

    @Autowired
    private SsDmsInvStockOriginalDao origStockRepo;//

    @Autowired
    private OrgDefinitionDao orgDefRepo;

    @Autowired
    private SsDmsStockTrfDao stockTransRepo;

    @Autowired
    private SsVehStockLoginDao ssVehLoginRepo;

//    private final String QR_UPLOAD_DIR = "D://QRUPLOAD//";
//    private final String QR_UPLOAD_DIR = "//sai_data//Sai_ErpAndroid_Data//Vehicle_QR_Store//";
    
    //-------for clone---------
//    private final String QR_UPLOAD_DIR = "/oraarchives/Android_Data_Store/Vehicle_QR_Store/";
    
    //------for production---------
//    private final String QR_UPLOAD_DIR = "/sai02_data/Android_Data_Store/Vehicle_QR_Store/";


//    @PostMapping("/generate")
//    public ResponseEntity<String> generateAndStoreQRCode(
//            @RequestParam String chassisNo,
//            @RequestParam String modelDesc,
//            @RequestParam String colour,
//            @RequestParam String vin,
//            @RequestParam String fuelDesc) {
//        try {
//            String text = "Chassis No: " + chassisNo + ",\n" + "Model Description: " + modelDesc + ",\n" + "Colour: " + colour + ",\n" + "VIN: " + vin + ",\n" + "Fuel Description: " + fuelDesc;
//            BufferedImage qrCodeImage = generateQRCodeImage(text);
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(qrCodeImage, "PNG", outputStream);
//            byte[] qrCodeBytes = outputStream.toByteArray();
//
//            String fileExtension = "PNG";
//            String docPath = QR_UPLOAD_DIR + "ChassisNo_QR_" + chassisNo + "." + fileExtension;
//
//            File outputFile = new File(docPath);
//            ImageIO.write(qrCodeImage, fileExtension, outputFile);
//
//            AndroidVehQr vehQr = new AndroidVehQr();
//            vehQr.setChassisNo(chassisNo);
//            vehQr.setModelDesc(modelDesc);
//            vehQr.setColour(colour);
//            vehQr.setVin(vin);
//            vehQr.setFuelDesc(fuelDesc);
//            vehQr.setVehQr(qrCodeBytes);
//            vehQr.setVehQrImage(docPath);
//            vehQrRepository.save(vehQr);
//
//            return new ResponseEntity<>("QR code generated and stored successfully", HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error generating QR code", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(value = "/decode/{chassisNo}")
//    public ResponseEntity<String> decodeQRCode(@PathVariable String chassisNo) {
//        try {
//            AndroidVehQr vehQr = vehQrRepository.findByChassisNo(chassisNo);
//            if (vehQr == null) {
//                return new ResponseEntity<>("QR code not found", HttpStatus.NOT_FOUND);
//            }
//
//            String decodedContent = decodeQRCode(vehQr.getVehQr());
//            vehQr.setVehQrDecoded(decodedContent);
//            vehQrRepository.save(vehQr);
//
//            return new ResponseEntity<>(decodedContent, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Error decoding QR code", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private BufferedImage generateQRCodeImage(String text) {
//        try {
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
//            return MatrixToImageWriter.toBufferedImage(bitMatrix);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String decodeQRCode(byte[] qrCodeBytes) throws NotFoundException, ChecksumException, FormatException, IOException {
//        BufferedImage image = ImageIO.read(new ByteArrayInputStream(qrCodeBytes));
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
//        Result qrCodeResult = new QRCodeReader().decode(binaryBitmap);
//        return qrCodeResult.getText();
//    }
//
//    private String decodeQRCode(Serializable vehQr) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @GetMapping(value = "/image/{chassisNo}", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getQRCodeImage(@PathVariable String chassisNo) {
//        try {
//            AndroidVehQr vehQr = vehQrRepository.findByChassisNo(chassisNo);
//            if (vehQr == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            File imgPath = new File(vehQr.getVehQrImage());
//            byte[] imageBytes = Files.readAllBytes(imgPath.toPath());
//
//            return ResponseEntity
//                    .ok()
//                    .contentType(MediaType.IMAGE_PNG)
//                    .body(imageBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    
    //used for vehicle track - sales, stock in
    //.....to fetch veh details by qr/barcode, uses this api ........
    @GetMapping("/qrDetailsByVin")
    public SaiResponse getByVinNumber(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = detByVinRepo.getByVinNumber(vin);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/qrDetailsByVinDelv")
    public SaiResponse getByVinNumberDelv(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = detByVinRepo.getByVinNumberDelv(vin);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    
    //used for vehicle track - sales, stock in
    //.....to fetch veh details by chassis no, uses this api ........
    @GetMapping("/qrDetailsByChassis")
    public SaiResponse getByChassis(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = detByVinRepo.getByChassisNo(chassisNo, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    @GetMapping("/qrDetailsByChassisDelv")
    public SaiResponse getByChassisDelv(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = detByVinRepo.getByChassisNoForDelv(chassisNo, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    
    //used for stock taking -accounts
    //........fetches veh details by chassis no, uses this api...........
    @GetMapping("/qrDetailsByChassisBatch")
    public SaiResponse qrDetailsByChassisBatch(@RequestParam String chassisNo, @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = detByVinRepo.getByChassisNoBatch(chassisNo, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }
    
    //used for stock taking -accounts
    //..............to fetch veh details by qr barcode scan, uses this api...........
    @GetMapping("/detailsByVin")
    public SaiResponse getByVin(@RequestParam String vin) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = vehQrRepository.getByVinNumber(vin);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

//

    
    
    //used for vehicle track -> pending list-> stktrf intransit
    //.....Grid Update for Stock transfer intransit vehicle.......
     @PutMapping("/updateVehicle")
    SaiResponse updEmplId(@RequestBody updateStkTrfDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Date currentDate = Calendar.getInstance().getTime();

            Optional<SsDmsStockTrf> optionalEmpId = stockTransRepo.findByStockTrfNo(input.getStkTrfNo());
            SsDmsStockTrf emplMstId = optionalEmpId.isPresent() ? optionalEmpId.get() : null;

            if (emplMstId != null) {
                //       BeanUtils.copyProperties(objEmpMst, emplMstId);
                //To-DO set create & Update Info     
                emplMstId.setVin(input.getVin());
                emplMstId.setReceivedBy(input.getReceivedBy());
                emplMstId.setRecdDate(currentDate);
                emplMstId.setToKm(input.getToKm());
                  emplMstId.setCreatedFrom("MobileApp");
                stockTransRepo.save(emplMstId);

                // String locName = orgDefRepo.getByLocId(emplMstId.getToLocation().toString());
                origStockRepo.updateDelvStatusAndLoc("STOCK", emplMstId.getToLocation(), input.getVin());

                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", emplMstId);
            } else {
                apiResponse = new SaiResponse(400, "Vehicle details not found", null);
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
            e.printStackTrace();

        }
        return apiResponse;
    }

    
    //used for vehicle track ->pending list ->intransit
      ////.........Grid Update for Intransit vehicle..........
    @PutMapping("/updateVehicleIntransit")
    SaiResponse updateVehicleIntransit(@RequestParam String vin,@RequestParam String location) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Date currentDate = Calendar.getInstance().getTime();

             origStockRepo.updateStkTrfStatus("STOCK",  location,vin);

                apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", vin);
           
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
            e.printStackTrace();

        }
        return apiResponse;
    }

    //used for vehicle track -sales stock in
      ////Page Update for Stock transfer intransit & Intransit single chassis/QR wise vehicle
    @PutMapping("/updateVehicleStockIN")
    SaiResponse updateVehicleStockIN(@RequestBody updateInStkTrfDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {
            Date currentDate = Calendar.getInstance().getTime();
            SsDmsStockTrf emplMstId = null;
            if (!input.getStkTrfNo().equalsIgnoreCase("-")) {
                Optional<SsDmsStockTrf> optionalEmpId = stockTransRepo.findByStockTrfNo(input.getStkTrfNo());
                emplMstId = optionalEmpId.isPresent() ? optionalEmpId.get() : null;
            }
            if (input.getVehStatus().equalsIgnoreCase("In-Transit")) {
                origStockRepo.updateStkTrfStatus("STOCK", input.getLocation(), input.getVin());

            } else if (input.getVehStatus().equalsIgnoreCase("Stock Transfer In-Transit")) {

                if (emplMstId != null) {
                    //       BeanUtils.copyProperties(objEmpMst, emplMstId);
                    //To-DO set create & Update Info     
                    emplMstId.setVin(input.getVin());
                    emplMstId.setReceivedBy(input.getReceivedBy());
                    emplMstId.setRecdDate(currentDate);
                    emplMstId.setToKm(input.getToKm());
                    emplMstId.setCreatedFrom("MobileApp");

                    stockTransRepo.save(emplMstId);

                    // String locName = orgDefRepo.getByLocId(emplMstId.getToLocation().toString());
                    origStockRepo.updateStkTrfStatus("STOCK", emplMstId.getToLocation(), input.getVin());

                    apiResponse = new SaiResponse(200, "Vehicle Updated Successfully", emplMstId);
                }
            } else {
                apiResponse = new SaiResponse(400, "Vehicle details not found", null);
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Error while updating vehicle", e.getMessage());
            e.printStackTrace();

        }
        return apiResponse;
    }

//    
    
    //used for sales stock in - -vehicle track ->pending list-> stk trf intransit
    //....fetches the list of stktrf intransit vehicles at that location.......
    @GetMapping("/transferList")
    public SaiResponse getByToLocationAndVehicleStatus(@RequestParam String to_location, @RequestParam String veh_status) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = vehQrRepository.getByTransferList(to_location, veh_status);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }
    
    
     //used for sales stock in - -vehicle track ->pending list->intransit
    //....fetches the list of intransit vehicles at that location.......
     @GetMapping("/transferListIntransit")
    public SaiResponse transferListIntransit(@RequestParam String to_location) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = vehQrRepository.getByTransferListIntransit(to_location, "In-Transit");

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

    @GetMapping("/vehStatusByVin")
    public ResponseEntity<String> findVehStatusByVin(@RequestParam String vin) {
        try {
            Optional<AndroidVehQr> vehicle1 = vehQrRepository.findByVin(vin);
            AndroidVehQr vehicle = vehicle1.isPresent() ? vehicle1.get() : null;

            if (vehicle != null) {
                String status = vehicle.getVehStatus();
                return ResponseEntity.ok(status);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception for troubleshooting purposes
            e.printStackTrace();
            // Return a generic error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/vehTrackingByVin")
    public SaiResponse vehTrackingByVin(
            @RequestParam("vin") String vin,
            @RequestParam("reasonCode") String reasonCode,
            @RequestParam("location") String location,
            @RequestParam("veh_status") String veh_status,
            @RequestParam("from_location") Integer from_location,
            @RequestParam("to_location") Integer to_location,
            @RequestParam("organization_id") Integer organization_id,
            @RequestParam("transferred_by") String transferred_by,
            @RequestParam("remarks") String remarks,
            @RequestParam("created_by") String created_by,
            @RequestParam("frmKm") String frmKm) throws Exception {
        SaiResponse apiResponse;
        try {
            Optional<SsVehStockLogin> empLoginDetails1 = ssVehLoginRepo.findByLoginName(created_by);
            SsVehStockLogin empLoginDetails = empLoginDetails1.isPresent() ? empLoginDetails1.get() : null;

            Optional<AndroidVehQr> androidVehQr1 = vehQrRepository.findByVin(vin);
            AndroidVehQr androidVehQr = androidVehQr1.isPresent() ? androidVehQr1.get() : null;

            Optional<SsDmsInvStockNew> origStock1 = origStockRepo.findByVin(vin);
            SsDmsInvStockNew origStock = origStock1.isPresent() ? origStock1.get() : null;

            String toLoc = orgDefRepo.getByLocId(to_location.toString());

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            String stkNo, stkNo1 = null;
            Integer srlNo = 0;
            // String stkNo2[] =new ArrayList<>; 
//ST654-118-34215
            //'STAN207201-01'
            Optional<SsDmsStockTrf> ssStockTrf1 = stockTransRepo.findByVin(vin);
            SsDmsStockTrf ssStockTrf = ssStockTrf1.isPresent() ? ssStockTrf1.get() : null;
            stkNo = stockTransRepo.getMaxSrlNo(organization_id);

            if (stkNo.equalsIgnoreCase("1")) {
                stkNo = "STAN" + from_location + to_location + "-" + stkNo;

            } else {
                //     stkNo = stockTransRepo.getMaxSrlNo1(organization_id);
                String stkNo2[] = stkNo.split("-");
                srlNo = Integer.parseInt(stkNo2[1]) + 1;
                stkNo = "STAN" + from_location + to_location + "-" + srlNo;
            }

            SsDmsStockTrf stkNew = new SsDmsStockTrf();

            stkNew.setStockTrfNo(stkNo);
            stkNew.setVin(vin);
            stkNew.setChassisNo(origStock.getChassisNo());
            stkNew.setStockTrfDate(currentDate);
            stkNew.setEngineNo(origStock.getEngineNo());
            stkNew.setMadeBy(created_by);
            stkNew.setFromLocation(empLoginDetails.getLocation_name());
            stkNew.setToLocation(toLoc);
//        ssStockTrf.setDriverName(remarks);

            stkNew.setFromLocCode(empLoginDetails.getLocId().toString());
            stkNew.setToLocCode(to_location.toString());

            stkNew.setOu(organization_id);
            stkNew.setRemarks(remarks);
            stkNew.setFrmKm(frmKm);
            stkNew.setCreatedFrom("Mobile App");

            stockTransRepo.save(stkNew);

            origStockRepo.updateVehStatus("Stock Transfer In-Transit", origStock.getVin());
//        if (androidVehQr == null) {
//            androidVehQr = new AndroidVehQr();
//            androidVehQr.setVin(vin);
//        }
//        androidVehQr.setChassisNo(origStock.getChassisNo());
//        androidVehQr.setColour(origStock.getColour());
//        androidVehQr.setFuelDesc(origStock.getFuelDesc());
//        androidVehQr.setModelDesc(origStock.getModelDesc());
//
//        androidVehQr.setReasonCode(reasonCode);
//        androidVehQr.setLocation(location);
//        if ("Vehicle Delivered".equals(reasonCode)) {
//            androidVehQr.setVehStatus("DELIVERED");
//        } else {
//            androidVehQr.setVehStatus("INTRANSIT");
//        }
//        androidVehQr.setFromLocation(from_location);
//        androidVehQr.setToLocation(to_location);
//        androidVehQr.setOutTime(currentDate);
//        androidVehQr.setOrganizationId(organization_id);
//        androidVehQr.setTransferredBy(transferred_by);
//        androidVehQr.setRemarks(remarks);
//        androidVehQr.setCreatedBy(created_by);
//        androidVehQr.setCreationDate(currentDate);;
//        androidVehQr.setLastUpdateDate(currentDate);
//
//        vehQrRepository.save(androidVehQr);
//
//        origStockRepo.updateDelvStatus("STK-TRF", origStock.getVin());
            //////inserting data to SsDmsStockTrf table 01-07-2024
//        Optional<SsDmsStockTrf> ssStockTrf1 = stockTransRepo.findByVin(vin);
//        SsDmsStockTrf ssStockTrf = ssStockTrf1.isPresent() ? ssStockTrf1.get() : null;
//
//        if (ssStockTrf == null) {
//            ssStockTrf = new SsDmsStockTrf();
//            ssStockTrf.setVin(vin);
//        }
//
//        //  stockTrfNo
//        String stkNo, stkNo1 = null;
//        Integer srlNo = 0;
//        //String stkNo2[] =new ArrayList<>; 
//
//        //'STAN207201-01'
//        stkNo = stockTransRepo.getMaxSrlNo(organization_id);
//        if (stkNo.equalsIgnoreCase("1")) {
//            stkNo = "STAN" + from_location  + to_location + "-" + stkNo;
//
//        } else {
//               stkNo = stockTransRepo.getMaxSrlNo1(organization_id);
//            //stkNo2[] a= stkNo.split("-");
//            srlNo = Integer.parseInt(stkNo) + 1;
//            stkNo = "STAN" + from_location+ to_location + "-" + srlNo;
//        }
//        ssStockTrf.setStockTrfNo(stkNo);
//        ssStockTrf.setChassisNo(origStock.getChassisNo());
//        ssStockTrf.setStockTrfDate(currentDate);
//        ssStockTrf.setEngineNo(origStock.getEngineNo());
//        ssStockTrf.setMadeBy(created_by);
//        ssStockTrf.setFromLocation(empLoginDetails.getLocation_name());
//        ssStockTrf.setToLocation(to_location.toString());
////        ssStockTrf.setDriverName(remarks);
//
//        ssStockTrf.setFromLocCode(empLoginDetails.getLocId().toString());
//
//        ssStockTrf.setOu(organization_id);
//        ssStockTrf.setRemarks(remarks);
//
//        stockTransRepo.save(ssStockTrf);

            apiResponse = new SaiResponse(200, "Details Found Successfully", androidVehQr);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
            throw e;
        }
        return apiResponse;

    }

}
