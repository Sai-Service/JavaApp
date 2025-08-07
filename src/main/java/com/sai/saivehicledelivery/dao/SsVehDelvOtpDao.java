/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dao;

import com.sai.saivehicledelivery.entity.SsVehDelvOtp;
import com.sai.saivehicledelivery.entity.SsVehDelvTrans;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Harsh Gawde
 */
public interface SsVehDelvOtpDao  extends CrudRepository<SsVehDelvOtp, Integer> {
    
    @Query(value = "SELECT MAX(otp_id) FROM SS_VEH_DELV_OTP", nativeQuery = true)
    Integer findLastInsertedOtpId();
    
      public Optional<SsVehDelvOtp>findByOtpAndInvoiceNoAndStatusAndExpiryTimeGreaterThan(String otp, String invoiceNo, String status,  Date expiryTime);
}
