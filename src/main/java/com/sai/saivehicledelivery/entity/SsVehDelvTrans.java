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
 * @author Harsh Gawde
 */
@Entity
@Table(name = "SS_VEH_DELV_TRANS")
public class SsVehDelvTrans implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "TRANS_ID")
    private Integer transId;
    @Column(name = "VEHICLE_NO")
    private String vehicleNo;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "CUST_ADDRESS")
    private String custAddress;
    @Column(name = "CUST_CONTACT_NO")
    private String custContactNo;
    @Column(name = "INVOICE_NO")
    private String invoiceNo;
    @Column(name = "TRANSACTION_NO")
    private String transactionNo;
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;
    @Column(name = "AMOUNT_DUE_REMAINING")
    private Float amountDueRemaining;
    @Column(name = "AMOUNT_PAID")
    private Float amountPaid;
    @Column(name = "AMOUNT_PENDING")
    private Float amountPending;
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    @Column(name = "RECEIPT_METHOD_ID")
    private String receiptMethodId;
    @Column(name = "PAYMENT_TRANS_NO")
    private String paymentTransactionNo;
    @Column(name = "PAYMENT_IMAGE")
    private String paymentImage;
    @Column(name = "OTP")
    private String otp;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "UPDATION_DATE")
    private Date updationDate;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "DRIVER_NAME")
    private String driverName;
    @Column(name = "DRIVER_CONTACT_NO")
    private String driverContactNo;
    @Column(name = "DRIVER_LOC_ID")
    private Integer driverLocId;
    @Column(name = "DRIVER_LOCATION_NAME")
    private String driverLocationName;
    @Column(name = "ATTRIBUTE1")
    private String attribute1;
    @Column(name = "ATTRIBUTE2")
    private String attribute2;
    @Column(name = "ATTRIBUTE3")
    private String attribute3;
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Column(name = "ATTRIBUTE5")
    private String attribute5;
    @Column (name = "OU_ID")
    private Integer ouId;
    @Column (name = "PARTYID")
    private Integer partyId;

    public SsVehDelvTrans() {

    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

   

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptMethodId() {
        return receiptMethodId;
    }

    public void setReceiptMethodId(String receiptMethodId) {
        this.receiptMethodId = receiptMethodId;
    }

  

    public String getPaymentTransactionNo() {
        return paymentTransactionNo;
    }

    public void setPaymentTransactionNo(String paymentTransactionNo) {
        this.paymentTransactionNo = paymentTransactionNo;
    }

    public String getPaymentImage() {
        return paymentImage;
    }

    public void setPaymentImage(String paymentImage) {
        this.paymentImage = paymentImage;
    }

    public Float getAmountDueRemaining() {
        return amountDueRemaining;
    }

    public void setAmountDueRemaining(Float amountDueRemaining) {
        this.amountDueRemaining = amountDueRemaining;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getAmountPending() {
        return amountPending;
    }

    public void setAmountPending(Float amountPending) {
        this.amountPending = amountPending;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

   
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

 
    public Integer getDriverLocId() {
        return driverLocId;
    }

    public void setDriverLocId(Integer driverLocId) {
        this.driverLocId = driverLocId;
    }

    public String getDriverLocationName() {
        return driverLocationName;
    }

    public void setDriverLocationName(String driverLocationName) {
        this.driverLocationName = driverLocationName;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getCustContactNo() {
        return custContactNo;
    }

    public void setCustContactNo(String custContactNo) {
        this.custContactNo = custContactNo;
    }

    public String getDriverContactNo() {
        return driverContactNo;
    }

    public void setDriverContactNo(String driverContactNo) {
        this.driverContactNo = driverContactNo;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    
}
