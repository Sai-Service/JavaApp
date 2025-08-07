/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.dto;

/**
 *
 * @author Jyoti T
 */
public class SmsNewRespDto {
    
     private String status;
    private String mobile;
    private String invalidMobile;
    private String transactionId;
    private String statusCode;
    private String reason;
    private String msgId;

    public SmsNewRespDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvalidMobile() {
        return invalidMobile;
    }

    public void setInvalidMobile(String invalidMobile) {
        this.invalidMobile = invalidMobile;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    
    
    
}
