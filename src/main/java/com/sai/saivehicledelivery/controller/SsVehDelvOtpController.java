/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.saivehicledelivery.SaiResponse;
import com.sai.saivehicledelivery.dao.SsVehDelvOtpDao;
import com.sai.saivehicledelivery.dao.SsVehDelvTransDao;
import com.sai.saivehicledelivery.dto.VehDelvOtpDto;
import com.sai.saivehicledelivery.entity.SsVehDelvOtp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Harsh Gawde
 */
@RestController
@RequestMapping("/vehDelvOtp")
public class SsVehDelvOtpController {

    @Autowired
    private SsVehDelvOtpDao otpRepo;

    @Autowired
    private SsVehDelvTransDao transRepo;  // vehicle no repo

    private static final int OTP_LENGTH = 4;

    //expiry set to 3 minutes by harsh as per discussion on 02 mar 2026, meeting with ceo's
    private static final int OTP_EXPIRY_MINUTES = 3;

    // Generate OTP logic
    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));  // Appending random digits (0-9)
        }
        return otp.toString();
    }

    @PostMapping("/generateDelvOtp")
    public SaiResponse generateDelvOtp(@RequestParam String invoiceNo, @RequestParam String mobileNo) {
        SaiResponse apiResponse;
        try {
            // Generate OTP and define expiry time
            String otpCode = generateOtp();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expiryTime = now.plusMinutes(OTP_EXPIRY_MINUTES);

            // Convert LocalDateTime to Timestamp
            Timestamp creationTimestamp = Timestamp.valueOf(now);
            Timestamp expiryTimestamp = Timestamp.valueOf(expiryTime);

            // Try to fetch the invoice using invoiceNo
            // VehDelvOtpDto dto = null;
            Map list = null;
            String invNo = transRepo.findByCt_Reference(invoiceNo);
            String invNoNew = null;
            String mobileno;
            Integer orgId;
            String city;
            if (invNo == null) {

                apiResponse = new SaiResponse(400, "Invoice not found for invoice number: " + invoiceNo, "Invoice not found.");
                return apiResponse;
            } else {

                invNoNew = invNo;
                list = transRepo.getDetailsByInvoiceNoNew(invNoNew);

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                VehDelvOtpDto dto = mapper.convertValue(list, VehDelvOtpDto.class);
                //  dto.setTransactionDate(dto.getTransactionDate());
                // dto.setContactNo(dto.getContactNo());

                mobileno = dto.getContactNo();
                orgId = dto.getOrgId();

                String ouId = orgId.toString();

                city = otpRepo.getCityByOu(ouId);
            }

            // Create a new OTP record
            SsVehDelvOtp newOtp = new SsVehDelvOtp();

            // Generate new OTP ID (increment from last inserted)
            Integer lastOtpId = otpRepo.findLastInsertedOtpId();
            Integer newOtpId = (lastOtpId == null) ? 1 : lastOtpId + 1;

            // Set OTP details
            newOtp.setOtpId(newOtpId);
            newOtp.setOtp(otpCode);
            newOtp.setInvoiceNo(invNoNew); // Set fetched invoice number
            newOtp.setCreationTime(creationTimestamp);
            newOtp.setExpiryTime(expiryTimestamp);
            newOtp.setStatus("ACTIVE");

            // Save OTP record in the database
            newOtp = otpRepo.save(newOtp);

            mobileno = mobileNo;

            String minutes = "3";

//--------------code for whatsapp otp-------------------------
            //for testing purpose - otp sms
//            String smstext = "For testing purpose only. "+otpCode+" is your Sai Service OTP, to pay amount for car delivery, valid for 2 minutes only. Please do not share your OTP with anyone";
            //original otp sms
//            String smstext = otpCode + " is your Sai Service OTP, to pay amount for car delivery, valid for 2 minutes only. Please do not share your OTP with anyone";
//new otp sms 21-06-2025
            /*String smstext = "*" + otpCode + "* is your verification code. For your security, do not share this code.";

            String apiKey = null;
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

//            Unirest.setTimeouts(0, 0);
//            HttpResponse<String> response;
////            response = Unirest.post("https://theultimate.io/WAApi/send")
//            response = Unirest.post("https://smsnotify.one/WAApi/send")
//                    .header("apiKey", apiKey)
//                    .header("Cookie", "SERVERID=webC1")
//                    .field("userid", userid)
//                    .field("msg", smstext)
//                    .field("wabaNumber", wabaNumber)
//                    .field("output", "json")
//                    .field("mobile", "91" + String.valueOf(mobileno))
//                    .field("sendMethod", "quick")
//                    .field("msgType", "text")
//                    .field("templateName", "delvtwominotp2")
//                    //                    .field("buttonsPayload", "{\"button0\": \"" + otpCode + "\"}")
//                    //                     .field("buttonsPayload", "{\"button0\": \"+otpCode+\"}")    
//                    .field("buttonsPayload", "{\"button0\": \"otpCode\"}")
//                    .asString();//'buttonsPayload="{\"button0\": \"{{1}}\"}"'

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("https://theultimate.io/WAApi/send")
                    .header("apikey", apiKey)
                    .header("Cookie", "SERVERID=webC1")
                    .field("userid", userid)
                    .field("msg", smstext)
                    .field("wabaNumber", wabaNumber)
                    .field("output", "json")
                    .field("mobile", "91" + String.valueOf(mobileno))
                    .field("sendMethod", "quick")
                    .field("msgType", "text")
                    .field("templateName", "delvtwominotp2")
                    .field("buttonUrlPath", "{\"button1\": \"otpCode\"}")
                    .asString();

            StringBuffer res = new StringBuffer(response.getBody());// getAuthToken();
            Gson gson = new Gson();
            System.out.println("res...." + res.toString());

            SmsNewRespDto organisation = gson.fromJson(res.toString(), SmsNewRespDto.class);
            String tid = organisation.getTransactionId();


            if (organisation.getStatusCode().equalsIgnoreCase("200")) { // success
                newOtp.setStatus("SEND");
                newOtp.setAttribute1(tid);
            } else {
                newOtp.setStatus("EROR");

            }*/
            //code for sms otp-------------------------------          
//            String smstext = otpCode + " is your Sai Service OTP, to pay amount for car delivery, valid for 2 minutes only. Please do not share your OTP with anyone";
//            String smstext = otpCode + " is your Sai Service OTP, to pay amount for car delivery, valid for "+minutes+" minutes only. Please do not share your OTP with anyone";
            String smstext = "Dear Customer, your Sai Service Authorisation Code is " + otpCode + ". Please share this code with the driver only after payment and at the time of vehicle handover. This code is valid for " + minutes + " minutes. Sai Service " + city;

            String baseUrl = "http://bulkpush.mytoday.com/BulkSms/SingleMsgApi";
            String smsUrl = "";
            Map<String, String> requestParams = new HashMap<>();
            if (orgId == 104 || orgId == 81 || orgId == 105 || orgId == 106 || orgId == 107 || orgId == 108) {
                requestParams.put("feedid", "343652");
                requestParams.put("username", "9594952153");
                requestParams.put("password", "Sai@123");
                //  smsUrl = "?feedid=343652&username=9594952153&password=Sai@123&To=" + mobileno + "&Text=" + smstext + "";// 'mumbai--working
            }
//                if (sms.getOrgId() == 22) {
//                    requestParams.put("feedid", "343652");
//                    requestParams.put("username", "9594952153");
//                    requestParams.put("password", "Sai@123");
//                    //smsUrl = "http://bulkpush.mytoday.com/BulkSms/SingleMsgApi?feedid=124671&username=9923208022&password=sai@123&To=" + mobileno + "&Text=" + smstext + "";// 'pune--working
//                }
            requestParams.put("to", mobileno);
            requestParams.put("Text", smstext);
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                //conn.setRequestProperty(entry.getKey(), encodeValue(entry.getValue()));
                smsUrl = smsUrl + entry.getKey() + "=" + encodeValue(entry.getValue()) + '&';

            }
            smsUrl = smsUrl.substring(0, smsUrl.length() - 1);
            System.out.println(smsUrl);
            // String urlFinal = URLEncoder.encode(smsUrl, StandardCharsets.UTF_8.toString());
            String urlFinal = baseUrl + "?" + smsUrl;
            final URL url = new URL(urlFinal);
            System.out.println(urlFinal);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            // conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();
            String input1 = "";
            if (conn != null) {
                try {
                    final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String input2;
                    while ((input2 = br.readLine()) != null) {
                        input1 = input1 + input2;
                    }
                    br.close();
                    System.out.println("==========" + input1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            String tid = input1.substring(input1.indexOf("TID = '") + 7, input1.lastIndexOf("'"));

            String start = "TID='";

            String tid = null;

            int startIndex = input1.indexOf(start);
            if (startIndex != -1) {
                startIndex += start.length();

                int endIndex = input1.indexOf("'", startIndex);

                tid = input1.substring(startIndex, endIndex);

                System.out.println("TID :: " + tid);
            }

            System.out.println(conn.getResponseMessage() + "GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                if (input1.contains("<ERROR>")) {
                    newOtp.setStatus("ERRO");
                } else {
                    newOtp.setStatus("SEND");
                    newOtp.setAttribute1(tid);
                    newOtp.setAttribute2(mobileno);
                }

            } else {
                newOtp.setStatus("ERRO");
            }

            newOtp = otpRepo.save(newOtp);

            System.out.println(conn.getResponseMessage() + "GET Response Code :: " + responseCode);
            // Optionally, you could send OTP to the user's phone/email here
            // Send success response with generated OTP
            apiResponse = new SaiResponse(200, "OTP Generated Successfully", otpCode);

        } catch (Exception e) {
            // In case of error, return a response with error details
            apiResponse = new SaiResponse(400, "Failed to generate OTP", "Failed to generate OTP");
        }
        return apiResponse;
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

    //
    // Endpoint to validate OTP
    @PostMapping("/validateDelvOtp")
    public SaiResponse validateDelvOtp(@RequestParam String otp, @RequestParam String invoiceNo, String status) {
        SaiResponse apiResponse;
        try {
            // Validate OTP logic
            LocalDateTime currentTime = LocalDateTime.now();

            Timestamp creationTimestamp = Timestamp.valueOf(currentTime);

            Optional<SsVehDelvOtp> otpOptional = otpRepo.findByOtpAndInvoiceNoAndStatusAndExpiryTimeGreaterThan(
                    otp, invoiceNo, status, creationTimestamp);

            boolean isValid = otpOptional.isPresent();

            if (isValid) {
                // OTP is valid
                apiResponse = new SaiResponse(200, "OTP is valid!", null);  // Success response
            } else {
                // OTP is invalid or expired
                apiResponse = new SaiResponse(400, "Invalid or expired OTP!", null);  // Failure response
            }
        } catch (Exception e) {
            // Internal server error in case of exceptions
            apiResponse = new SaiResponse(500, "Internal Server Error", "Internal Server Error");  // Error response
        }

        return apiResponse;
    }

    // Clean expired OTPs
//    private void cleanExpiredOtps() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        otpRepository.deleteByExpiryTimeBeforeAndStatus(OtpStatus.ACTIVE, currentTime);
//    }
//
//    // Optional: Endpoint to manually clean expired OTPs (can also be automated via a scheduled task)
//    @PostMapping("/cleanExpired")
//    public String cleanExpired() {
//        cleanExpiredOtps();
//        return "Expired OTPs cleaned!";
//    }
}
