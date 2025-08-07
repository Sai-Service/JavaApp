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
@Table(name = "SS_DMS_MANUAL_GP_VH")
public class SsDmsManualGpVh implements Serializable {

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
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNo;
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Column(name = "OLD_INV_NUMBER")
    private String oldInvNo;
    @Column(name = "SOB")
    private String sob;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "GATEPASS_TYPE")
    private String gatePassType;
    @Column(name = "CURRENT_KMS")
    private Long currentKms;
    @Column(name = "INKMS")
    private Long inKms;
    @Column(name = "AUTHORISED_BY")
    private String authorisedBy;
    @Column(name = "CUST_ADDRESS")
    private String custAddress;
    @Column(name = "CUST_CONTACT")
    private String custContact;
    
    public SsDmsManualGpVh(){
        
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getOldInvNo() {
        return oldInvNo;
    }

    public void setOldInvNo(String oldInvNo) {
        this.oldInvNo = oldInvNo;
    }

    public String getSob() {
        return sob;
    }

    public void setSob(String sob) {
        this.sob = sob;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getGatePassType() {
        return gatePassType;
    }

    public void setGatePassType(String gatePassType) {
        this.gatePassType = gatePassType;
    }

    public Long getCurrentKms() {
        return currentKms;
    }

    public void setCurrentKms(Long currentKms) {
        this.currentKms = currentKms;
    }

    public Long getInKms() {
        return inKms;
    }

    public void setInKms(Long inKms) {
        this.inKms = inKms;
    }

    public String getAuthorisedBy() {
        return authorisedBy;
    }

    public void setAuthorisedBy(String authorisedBy) {
        this.authorisedBy = authorisedBy;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustContact() {
        return custContact;
    }

    public void setCustContact(String custContact) {
        this.custContact = custContact;
    }
    
    

}
