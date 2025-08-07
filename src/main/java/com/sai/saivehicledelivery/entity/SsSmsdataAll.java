/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HarshG
 */
@Entity
@Table(name = "SS_SMSDATA_ALL")
public class SsSmsdataAll implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFERENCE_NUMBER")
    private Integer referenceNumber;
    @Column(name = "TRX_NUMBER")
    private String trxNumber;
    @Column(name = "INSTANCE_NUMBER")
    private String instanceNumber;
    @Column(name = "PARTY_NAME")
    private String partyName;
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "MOBILE_NO")
    private Long mobileNo;
    @Column(name = "SMS_TEXT")
    private String smsText;
    @Column(name = "TARGET_DT")
    private Date targetDt;
    @Column(name = "SEND_DATE")
    private Date sendDate;
    @Column(name = "SENDTIME")
    private String sendTime;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ORG_ID")
    private String orgId;
    @Column(name = "DATA_CREATE_DT")
    private Date dataCreateDt;
    @Column(name = "TID")
    private String tid;
    
    public SsSmsdataAll(){
        
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
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

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
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
    
    

}
