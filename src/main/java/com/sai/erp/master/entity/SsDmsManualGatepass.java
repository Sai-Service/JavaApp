/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HarshG
 */
@Entity
@Table(name = "SS_DMS_MANUAL_GATEPASS")
public class SsDmsManualGatepass implements Serializable{
    
    
   @Column(name = "REG_NO")
    private String regNo;
   @Id
   @Column(name = "GATEPASS_NO")
    private Long gatePassNo;
   @Column(name = "GATEPASS_DATE")
    private Date gatePassDate;
   @Column(name = "CUSTOMER_NAME")
    private String customerName;
   @Column(name = "MADE_BY")
    private String madeBy;
   @Column(name = "FROM_LOCATION")
    private String fromLocation;
   @Column(name = "REMARKS")
    private String remarks;
   @Column(name = "JOBCARD_NUMBER")
    private String jobCardNo;
   @Column(name = "LOGIN_NAME")
    private String loginName;
   @Column(name = "OLDJOBNO")
    private String oldJobNo;
   
   public SsDmsManualGatepass(){
       
   }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Long getGatePassNo() {
        return gatePassNo;
    }

    public void setGatePassNo(Long gatePassNo) {
        this.gatePassNo = gatePassNo;
    }

   
    public Date getGatePassDate() {
        return gatePassDate;
    }

    public void setGatePassDate(Date gatePassDate) {
        this.gatePassDate = gatePassDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getOldJobNo() {
        return oldJobNo;
    }

    public void setOldJobNo(String oldJobNo) {
        this.oldJobNo = oldJobNo;
    }
   
   
   
}
