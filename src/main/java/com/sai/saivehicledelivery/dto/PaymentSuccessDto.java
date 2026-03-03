/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dto;

import java.util.Date;

/**
 *
 * @author HarshG
 */
public class PaymentSuccessDto {

    private String mobileNo;
    private float amount;
    private String method;
    private String city;
    private String trxNumber;
    private String instanceNumber;
    private String partyName;
    private String attribute4;
    private String accountType;
    private String smsText;
    private Date targetDt;
    private Date sendDate;
    private String sendTime;
    private String status;
    private String orgId;
    private Date dataCreateDt;
    private String tid;
    
    private String secondMobileNo;
  
    
    

    public PaymentSuccessDto() {

    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

  

    public String getTrxNumber() {
        return trxNumber;
    }

    public void setTrxNumber(String trxNumber) {
        this.trxNumber = trxNumber;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public Date getTargetDt() {
        return targetDt;
    }

    public void setTargetDt(Date targetDt) {
        this.targetDt = targetDt;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getDataCreateDt() {
        return dataCreateDt;
    }

    public void setDataCreateDt(Date dataCreateDt) {
        this.dataCreateDt = dataCreateDt;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSecondMobileNo() {
        return secondMobileNo;
    }

    public void setSecondMobileNo(String secondMobileNo) {
        this.secondMobileNo = secondMobileNo;
    }
    
    

}
