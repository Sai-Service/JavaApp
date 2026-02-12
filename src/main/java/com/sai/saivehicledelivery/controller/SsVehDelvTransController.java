/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.controller;

import com.sai.saivehicledelivery.SaiResponse;
import com.sai.saivehicledelivery.dao.SsServiceGpInfoDmsDao;
import com.sai.saivehicledelivery.dao.SsSmsNewDataDao;
import com.sai.saivehicledelivery.dao.SsVehDelvTransDao;
import com.sai.saivehicledelivery.dto.PaymentSuccessDto;
import com.sai.saivehicledelivery.dto.VehDelvTransDto;
import com.sai.saivehicledelivery.entity.SsServiceGpInfoDms;
import com.sai.saivehicledelivery.entity.SsSmsNewData;
import com.sai.saivehicledelivery.entity.SsVehDelvTrans;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Harsh Gawde
 */
@RestController
@RequestMapping("/vehDelvTrans")
public class SsVehDelvTransController {

    @Autowired
    private SsVehDelvTransDao transRepo;

    @Autowired
    private SsServiceGpInfoDmsDao gpDmsRepo;

//    @Autowired
//    private SsSmsdataAllDao smsDataRepo;
    @Autowired
    private SsSmsNewDataDao smsDataNewRepo;
//    private final String UPLOAD_DIR = "D://delvPaymentUpload//";
//    @GetMapping("/getByInvoiceNo")
//    public SaiResponse getByInvoiceNo(@RequestParam String invoiceNo) throws Exception {
//        SaiResponse apiResponse;
//        try {
//            List<Map> codeList = transRepo.getDetailsByInvoiceNo(invoiceNo);
//
//            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
//        } catch (Exception e) {
//            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
//        }
//        return apiResponse;
//
//    }
    //used for fetching transaction data by gate pass id

    @GetMapping("/getByGatePassId")
    public SaiResponse getByGatePassId(@RequestParam String attribute1, @RequestParam String location) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehDelvTrans> delvTrans = transRepo.findFirstByAttribute1OrderByCreationDateDesc(attribute1);
            SsVehDelvTrans delvTrans1 = delvTrans.isPresent() ? delvTrans.get() : null;

            if (delvTrans1 != null) {

                Double amountPending = Math.floor(delvTrans1.getAmountPending());

                if (amountPending == 0) {

                    apiResponse = new SaiResponse(400, "Amount already paid for this vehicle", delvTrans1.getVehicleNo());
                    return apiResponse;
                } else if (delvTrans1.getAmountPending() > 0) {
                    List<Map> codeList = transRepo.getDetailsByAttribute1AndServiceLocation(attribute1, location);

                    apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
                    return apiResponse;
                }
            } else {

                List<Map> codeList = transRepo.getDetailsByGatePassIdAndServiceLocation(attribute1, location);

                apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
        return apiResponse;

    }

//used for fetching the mode of payment 
    @GetMapping("/getByPaymentType")
    public SaiResponse getByPaymentType(@RequestParam String paymentType) throws Exception {
        SaiResponse apiResponse;
        try {

            List<Map> codeList = transRepo.getDetailsByPaymentType(paymentType);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

    //used for fetching the receipt method by passing the payment mode
    @GetMapping("/getByReceiptMethod")
    public SaiResponse getByReceiptMethod(@RequestParam String department,
            @RequestParam String paymentType,
            @RequestParam String location,
            @RequestParam Integer ouId) throws Exception {
        SaiResponse apiResponse;
        try {
            List<Map> codeList = transRepo.getPaymentDetailsByDepartment(department, paymentType, location, ouId);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
        }
        return apiResponse;

    }

//to post the transaction values along with the otp to ss_dms_delv_trans table
// @RequestParam("image") MultipartFile file    
    @PostMapping("/delvPaymentComplete")
    public SaiResponse delvPaymentComplete(@RequestBody VehDelvTransDto input) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            LocalDateTime now = LocalDateTime.now();
            Timestamp dateTime = Timestamp.valueOf(now);

            Optional<SsVehDelvTrans> existInvoice = transRepo.findFirstByInvoiceNoOrderByCreationDateDesc(input.getInvoiceNo());
            SsVehDelvTrans existInvoice1 = existInvoice.isPresent() ? existInvoice.get() : null;

            if (existInvoice1 != null) {

                Double amountPending = Math.floor(existInvoice1.getAmountPending());

                if (amountPending == 0) {
                    // invoice payment details already present 
                    apiResponse = new SaiResponse(400, "Payment Already Done For This Invoice", existInvoice1.getInvoiceNo());
                    return apiResponse;

                } else if (existInvoice1.getAmountPending() > 0) {

                    //fetch details by gate pass id and location from SS_VEH_DELV_TRANS table
                    //used to insert data by backend for 2nd time
                    List<Map> secondTrans = transRepo.getDetailsByAttribute1AndServiceLocation(input.getAttribute1(), input.getLocation());

                    Integer lastTransId = transRepo.findLastInsertedTransId();
                    Integer newTransId = (lastTransId == null) ? 1 : lastTransId + 1;

                    SsVehDelvTrans newTrans = new SsVehDelvTrans();

                    for (Map<String, Object> map : secondTrans) {
                        newTrans.setTransId(newTransId);
                        newTrans.setVehicleNo((String) map.get("vehicle_no"));
                        newTrans.setCustName((String) map.get("customer_name"));
                        newTrans.setCustAddress((String) map.get("customer_address"));
                        newTrans.setCustContactNo((String) map.get("contact_no"));
                        newTrans.setInvoiceNo((String) map.get("invoice_no"));
                        newTrans.setTransactionNo((String) map.get("trans_ref_num"));
                        newTrans.setTransactionDate((Date) map.get("date_of_delivery"));

                        float amountDueRemaining = ((BigDecimal) map.get("amount_due_remaining")).floatValue();
                        newTrans.setAmountDueRemaining(amountDueRemaining);
                        newTrans.setAmountPaid(input.getAmountPaid());   //input from user

                        //for getting pending amount //amountdue - amount paid
                        float pendingAmount = amountDueRemaining - input.getAmountPaid();
                        newTrans.setAmountPending(pendingAmount);
                        newTrans.setPaymentType(input.getPaymentType());   //input from user
                        newTrans.setReceiptMethodId(input.getReceiptMethodId());    //input from user
                        newTrans.setPaymentTransactionNo(input.getPaymentTransactionNo()); //input from user
                        newTrans.setOtp(input.getOtp());    //input from user
                        newTrans.setCreationDate(dateTime);
                        newTrans.setUpdationDate(dateTime);
                        newTrans.setDepartment(input.getDepartment());  //input from user
                        newTrans.setDriverName(input.getDriverName());      //input from user
                        newTrans.setDriverContactNo(input.getDriverContactNo());    //input from user
                        newTrans.setDriverLocId(input.getDriverLocId());    //input from user
                        newTrans.setDriverLocationName(input.getDriverLocationName());  //input from user
                        newTrans.setAttribute2(input.getAttribute2());

                        Object gpi = map.get("gate_pass_id");
                        if (gpi instanceof BigDecimal) {
                            String gatePassId = ((BigDecimal) gpi).toString();
                            newTrans.setAttribute1(gatePassId);
                        } else if (gpi instanceof Number) { // Handle other numeric types
                            String gatePassId = ((Number) gpi).toString();
                            newTrans.setAttribute1(gatePassId);
                        } else {
                            newTrans.setAttribute1((String) map.get("gate_pass_id"));
                        }

                        Integer ouId = ((BigDecimal) map.get("org_id")).intValue();
                        newTrans.setOuId(ouId);
                        Integer partyId = ((BigDecimal) map.get("party_id")).intValue();
                        newTrans.setPartyId(partyId);

                    }

                    transRepo.save(newTrans);
                    apiResponse = new SaiResponse(200, "Payment Details Added Successfully", input.getAttribute1());
                    return apiResponse;
                }
            } else {

                List<Map> firstTrans = transRepo.getDetailsByGatePassIdAndServiceLocation(input.getAttribute1(), input.getLocation());

                SsVehDelvTrans newTrans = new SsVehDelvTrans();

                Integer lastTransId = transRepo.findLastInsertedTransId();
                Integer newTransId = (lastTransId == null) ? 1 : lastTransId + 1;

                for (Map<String, Object> map : firstTrans) {
                    newTrans.setTransId(newTransId);
                    newTrans.setVehicleNo((String) map.get("vehicle_no"));
                    newTrans.setCustName((String) map.get("customer_name"));
                    newTrans.setCustAddress((String) map.get("customer_address"));
                    newTrans.setCustContactNo((String) map.get("contact_no"));
                    newTrans.setInvoiceNo((String) map.get("invoice_no"));
                    newTrans.setTransactionNo((String) map.get("trans_ref_num"));
                    newTrans.setTransactionDate((Date) map.get("date_of_delivery"));
                    float amountDueRemaining = ((BigDecimal) map.get("amount_due_remaining")).floatValue();
                    newTrans.setAmountDueRemaining(amountDueRemaining);
                    newTrans.setAmountPaid(input.getAmountPaid());   //input from user

                    //for getting pending amount //amountdue - amount paid
                    float pendingAmount = amountDueRemaining - input.getAmountPaid();
                    newTrans.setAmountPending(pendingAmount);
                    newTrans.setPaymentType(input.getPaymentType());   //input from user
                    newTrans.setReceiptMethodId(input.getReceiptMethodId());    //input from user
                    newTrans.setPaymentTransactionNo(input.getPaymentTransactionNo()); //input from user
                    newTrans.setOtp(input.getOtp());    //input from user
                    newTrans.setCreationDate(dateTime);
                    newTrans.setUpdationDate(dateTime);
                    newTrans.setDepartment(input.getDepartment());  //input from user
                    newTrans.setDriverName(input.getDriverName());      //input from user
                    newTrans.setDriverContactNo(input.getDriverContactNo());    //input from user
                    newTrans.setDriverLocId(input.getDriverLocId());    //input from user
                    newTrans.setDriverLocationName(input.getDriverLocationName());  //input from user
                    newTrans.setAttribute2(input.getAttribute2());

                    Object gpi = map.get("gate_pass_id");
                    if (gpi instanceof BigDecimal) {
                        String gatePassId = ((BigDecimal) gpi).toString();
                        newTrans.setAttribute1(gatePassId);
                    } else if (gpi instanceof Number) { // Handle other numeric types
                        String gatePassId = ((Number) gpi).toString();
                        newTrans.setAttribute1(gatePassId);
                    } else {
                        newTrans.setAttribute1((String) map.get("gate_pass_id"));
                    }
                    Integer ouId = ((BigDecimal) map.get("org_id")).intValue();
                    newTrans.setOuId(ouId);
                    Integer partyId = ((BigDecimal) map.get("party_id")).intValue();
                    newTrans.setPartyId(partyId);

                }

//                newTrans.setPaymentImage(input.getPaymentImage());
//                if (file != null) {
//
//                    String fileName = "Inv_no_" + input.getInvoiceNo() + ".jpg";
//
//                    String fullPath = UPLOAD_DIR + fileName;
//
//                    // Create the necessary directories (if not exist)
//                    File destinationDir = new File(UPLOAD_DIR + "\\");
//                    if (!destinationDir.exists()) {
//                        boolean dirsCreated = destinationDir.mkdirs(); // Create the subdirectories if they don't exist
//                        if (!dirsCreated) {
//                            apiResponse = new SaiResponse(400, "Error creating directories", null);
//                            return apiResponse;
//                        }
//                    }
//
//                    // Create the destination file
//                    File destinationFile = new File(fullPath);
//
//                    String payImagePath = UPLOAD_DIR + fileName;
//                    // Save the uploaded image to the destination path
//                    try {
////                        file.transferTo(destinationFile);
//                        file.transferTo(destinationFile);
//
////                        String payImagePath = UPLOAD_DIR + fileName;
//                        newTrans.setPaymentImage(payImagePath);
//                        apiResponse = new SaiResponse(200, "File uploaded successfully", payImagePath);
//                    } catch (IOException e) {
//                        apiResponse = new SaiResponse(400, "Error saving the file", e.getMessage());
//                        return apiResponse;
//                    }
//
//                }
                transRepo.save(newTrans);
                apiResponse = new SaiResponse(200, "Payment Details Added Successfully", input.getAttribute1());
                return apiResponse;
            }

        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Failed To Add Payment Details", "Failed To Add Payment Details");
        }
        return apiResponse;
    }

    //used to fetch the report of deivery payments completed in between provided dates
    //make this as a report on front end side..
    @GetMapping("/getTransactionsByDays")
    public SaiResponse getTransactionsByDays(@RequestParam Integer driverLocId,
            @RequestParam Date fromDate,
            @RequestParam Date toDate) throws Exception {
        SaiResponse apiResponse;
        try {

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            List<Map> codeList = transRepo.getTransactionDetailsByLocation(driverLocId, fromDate, toDate);

            apiResponse = new SaiResponse(200, "Details Found Successfully", codeList);
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", e.getMessage());
        }
        return apiResponse;

    }

    //used for sending an sms to customer after payment is completed 
    @PostMapping("/sendPaymentSuccessSms")
    public SaiResponse sendPaymentSuccessSms(@RequestBody PaymentSuccessDto input) throws Exception {
        SaiResponse apiResponse;
        try {

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Integer orgId = Integer.parseInt(input.getOrgId());

            String mobileNo = input.getMobileNo();

            //testing purpose -- sms
//            String smsText = "For testing purpose only. Dear Customer, Recd Payment Of Rs. " + input.getAmount() + ", By " + input.getMethod() + " .Thank you Sai Service " + input.getCity();
            // Construct SMS text -- orig. sms
//            String smsText = "Dear Customer, Recd Payment Of Rs. " + input.getAmount() + ", By " + input.getMethod() + " .Thank you Sai Service " + input.getCity();
//new sms 21-06-2025 - WHATSAPP
//            String smsText = "Dear Customer, Received Payment of Rs. " + input.getAmount() + ", By " + input.getMethod() + " .Thank you Sai Service " + input.getCity();
            // Construct SMS text -- orig. sms
            String smsText = "Dear Customer, Recd Payment Of Rs. " + input.getAmount() + ", By " + input.getMethod() + " .Thank you Sai Service " + input.getCity();

            //api for whatsapp success msgs
            /*    String apiKey = null;
            String userid = null;
            String wabaNumber = null;

            if (orgId.equals(104)) {
                apiKey = "65c3169f60a7fba6dad2967252170980756045e5";
                userid = "saiservicemum";
                wabaNumber = "918451940468";
            } else if (orgId.equals(81)) {
                apiKey = "04bff7e71b813b0ee65e56a2be14097067cb08ac";
                userid = "saiservicepune";
                wabaNumber = "917028696111";
            } else if (orgId.equals(105)) {
                apiKey = "311e84da08fe86f980909ba1db3def33e716fd56";
                userid = "saiservicekol";
                wabaNumber = "919503272525";
            } else if (orgId.equals(108)) {
                apiKey = "a241cffa3a64d3deb39d56c367bcd99265fc092e";
                userid = "saiservicehyd";
                wabaNumber = "919000333735";
            } else if (orgId.equals(106)) {
                apiKey = "b3b4adccdf3be0bb88c00c545d7d08e50034386d";
                userid = "saiservicegoa";
                wabaNumber = "919699754341";
            } else if (orgId.equals(107)) {
                apiKey = "1d821b84bdee5483a56f3d4115816095c706003d";
                userid = "saiservicecochi";
                wabaNumber = "918089646683";
            }

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response;
            response = Unirest.post("https://theultimate.io/WAApi/send")
                    .header("apiKey", apiKey)
                    .header("Cookie", "SERVERID=webC1")
                    .field("userid", userid)
                    .field("msg", smsText)
                    .field("wabaNumber", wabaNumber)
                    .field("output", "json")
                    .field("mobile", "91" + String.valueOf(mobileNo))
                    .field("sendMethod", "quick")
                    .field("msgType", "text")
                    .field("templateName", "cust_xl_parse")
                    .asString();//'buttonsPayload="{\"button0\": \"{{1}}\"}"'

            StringBuffer res = new StringBuffer(response.getBody());// getAuthToken();
            Gson gson = new Gson();
            System.out.println("res...." + res.toString());

            SmsNewRespDto organisation = gson.fromJson(res.toString(), SmsNewRespDto.class);
            String tid = null;

            String status = null;

            if (organisation.getStatusCode().equalsIgnoreCase("200")) { // success
                status = "SEND";
                tid = organisation.getTransactionId();

            } else {
                status = "EROR";
                tid = "0";

            }*/
            // SMS API setup
            String baseUrl = "http://bulkpush.mytoday.com/BulkSms/SingleMsgApi";
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("feedid", "343652");
            requestParams.put("username", "9594952153");
            requestParams.put("password", "Sai@123");
            requestParams.put("to", input.getMobileNo());
            requestParams.put("Text", smsText);

            String smsUrl = requestParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + encodeValue(entry.getValue()))
                    .reduce((a, b) -> a + "&" + b)
                    .orElse("");

            String urlFinal = baseUrl + "?" + smsUrl;
            System.out.println("Sending SMS: " + urlFinal);

            HttpURLConnection conn = (HttpURLConnection) new URL(urlFinal).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);

            String input1 = "";
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String input2;
                    while ((input2 = br.readLine()) != null) {
                        input1 = input1 + input2;
                    }
                    br.close();
                    System.out.println("==========" + input1);
                }
            } else {
                return new SaiResponse(400, "Failed to send SMS", "Response code: " + conn.getResponseCode());
            }

            String tid = input1.substring(input1.indexOf("TID = '") + 7, input1.lastIndexOf("'"));

            // Check TID and save SMS data
            if (tid != null && !tid.equals("TID Not Found")) {

                SsSmsNewData newData = new SsSmsNewData();
//                newData.setReferenceNumber(input.getReferenceNumber());
                newData.setTrxNumber(input.getTrxNumber());
                newData.setInstanceNumber(input.getInstanceNumber());
                newData.setPartyName(input.getPartyName());
                newData.setAttribute4("DELV_RECEIPT");
                newData.setAccountType(input.getAccountType());

                //type casting the string  input mobile no
                long mobileNo1 = Long.valueOf(mobileNo);
                newData.setMobileNo(mobileNo1);
                newData.setSmsText(smsText);

                newData.setTargetDt(currentDate);
                newData.setSendDate(currentDate);
                newData.setSendTime(formattedTime);
                newData.setStatus("SEND");
                newData.setOrgId(input.getOrgId());
                newData.setDataCreateDt(currentDate);
                newData.setTid(tid);

                smsDataNewRepo.save(newData);

                apiResponse = new SaiResponse(200, "SMS Sent Successfully", smsText);
                return apiResponse;
            } else {
                apiResponse = new SaiResponse(400, "Failed to send SMS", "TID Not Found");
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(500, "Error sending SMS", "Error sending SMS");
            return apiResponse;

        }
    }
    // URL encoding helper method

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }

    @GetMapping("/getByVehicleNo")
    public SaiResponse getByVehicleNo(@RequestParam String vehicleNo, @RequestParam String location) throws Exception {
        SaiResponse apiResponse = null;
        try {

            Optional<SsVehDelvTrans> delvTrans = transRepo.findFirstByVehicleNoOrderByCreationDateDesc(vehicleNo);
            SsVehDelvTrans delvTrans1 = delvTrans.isPresent() ? delvTrans.get() : null;

            Optional<SsServiceGpInfoDms> gatePass = gpDmsRepo.findFirstByVehicleNoOrderByCreationDateDesc(vehicleNo);
            SsServiceGpInfoDms gatePass1 = gatePass.isPresent() ? gatePass.get() : null;

            if (delvTrans1 != null && gatePass1 != null) {
                Double amountPending = Math.floor(delvTrans1.getAmountPending());

                // Check if gatePassId equals attribute1
                if (gatePass1.getGatePassId().toString().equals(delvTrans1.getAttribute1())) {
                    if (amountPending == 0) {
                        return new SaiResponse(400, "Amount already paid for this vehicle", delvTrans1.getVehicleNo());
                    } else {
                        List<Map> codeList = transRepo.getDetailsByVehicleNoAndServiceLocation(vehicleNo, location);
                        return new SaiResponse(200, "Details Found Successfully", codeList);
                    }
                } else {
                    // Gate pass ID does not match, still check for pending amount
                    if (amountPending > 0) {
                        List<Map> codeList = transRepo.getDetailsByVehicleNoAndServiceLocation(vehicleNo, location);
                        return new SaiResponse(200, "Pending amount exists from earlier delivery", codeList);
                    } else {
                        List<Map> codeList = transRepo.getGpDetailsByVehicleNoAndServiceLocation(vehicleNo, location);
                        return new SaiResponse(200, "Details Found Successfully", codeList);
                    }
                }
            } else {
                // If no delvTrans or gatePass found, just return GP details
                List<Map> codeList = transRepo.getGpDetailsByVehicleNoAndServiceLocation(vehicleNo, location);
                return new SaiResponse(200, "Details Found Successfully", codeList);
            }
        } catch (Exception e) {
            apiResponse = new SaiResponse(400, "Details not found", "Details not found");
            return apiResponse;
        }
//        return apiResponse;

    }
}
