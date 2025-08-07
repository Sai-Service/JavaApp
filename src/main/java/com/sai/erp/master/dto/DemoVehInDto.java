/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import java.util.Date;

/**
 *
 * @author HarshG
 */
public class DemoVehInDto {

    private String regNo;
    private String chassisNo;
    private String remarks;
    private String updatedBy;
    private Date updationDate;
    private Long inKm;
    private Date inTime;
    private String attribute1;
    private String attribute2;
    private Long fuelQty;

    public DemoVehInDto() {

    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    
    
    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    public Long getInKm() {
        return inKm;
    }

    public void setInKm(Long inKm) {
        this.inKm = inKm;
    }

   

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
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

    public Long getFuelQty() {
        return fuelQty;
    }

    public void setFuelQty(Long fuelQty) {
        this.fuelQty = fuelQty;
    }

    
    
}
