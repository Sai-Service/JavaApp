/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Harsh Gawde
 */
public class VehDelvTransDto {

    private String location;
    private String custContactNo;
    private String attribute1;
    private String invoiceNo;
    private Float amountPaid;
    private String paymentType;
    private String receiptMethodId;
    private String paymentTransactionNo;
//    private String paymentImage;
    private String otp;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updationDate;
    private String department;
    private String driverName;
    private String driverContactNo;
    private Integer driverLocId;
    private String driverLocationName;
    private String attribute2;

    public VehDelvTransDto() {

    }

    public String getCustContactNo() {
        return custContactNo;
    }

    public void setCustContactNo(String custContactNo) {
        this.custContactNo = custContactNo;
    }

 
    
    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
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

    public String getDriverContactNo() {
        return driverContactNo;
    }

    public void setDriverContactNo(String driverContactNo) {
        this.driverContactNo = driverContactNo;
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

    
}
