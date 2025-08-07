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
@Table(name = "SS_SMSNEW_DATA")
public class SsSmsNewData  implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer referenceNumber;
   @Column(name = "TRXNUMBER")
    private String trxNumber;
    @Column(name = "INSTANCENUMBER")
    private String instanceNumber;
    @Column(name = "PARTYNAME")
    private String partyName;
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Column(name = "ACCOUNTTYPE")
    private String accountType;
    @Column(name = "MOBILENO")
    private long mobileNo;
   private String smsText;
    @Column(name = "TARGETDT")
    private Date targetDt;
    @Column(name = "SENDDATE")
    private Date sendDate;
    @Column(name = "SENDTIME")
    private String sendTime;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ORGID")
    private String orgId;
    @Column(name = "DATACREATEDT")
    private Date dataCreateDt;
    @Column(name = "TID")
    private String tid;

    public SsSmsNewData() {
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

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
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