/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author IT-HARSH
 */
@Entity
@Table(name = "SS_BATCHCODE_ANDROID_TV")
public class BatchNameTrueValue implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "MODEL_DESC")
    private String modelDesc;
    @Column(name = "COLOUR")
    private String colour;
    @Column(name = "FUEL_DESC")
    private String fuelDesc;
    @Column(name = "LOCATIONID")
    private Integer locationId;
    @Column(name = "LOCATIONNAME")
    private String locationName;
    @Column(name = "BATCHNAME")
    private String batchName;
    @Column(name = "BATCHCREATIONDATE")
    private Date batchCreationDate;
    @Column(name = "BATCHSTATUS")
    private String batchStatus;
    @Column(name = "BATCHCODEENDDATE")
    private Date batchCodeEndDate;
    @Column(name = "SCANCREATIONTIME")
    private Date scanCreationTime;
    @Column(name = "CREATIONDATE")
    private Date creationDate;
    @Column(name = "CREATEDBY")
    private String createdBy;
    @Column(name = "UPDATIONDATE")
    private Date updationDate;
    @Column(name = "UPDATEDBY")
    private String updatedBy;

    public BatchNameTrueValue(){
        
    }
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFuelDesc() {
        return fuelDesc;
    }

    public void setFuelDesc(String fuelDesc) {
        this.fuelDesc = fuelDesc;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Date getBatchCreationDate() {
        return batchCreationDate;
    }

    public void setBatchCreationDate(Date batchCreationDate) {
        this.batchCreationDate = batchCreationDate;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public Date getBatchCodeEndDate() {
        return batchCodeEndDate;
    }

    public void setBatchCodeEndDate(Date batchCodeEndDate) {
        this.batchCodeEndDate = batchCodeEndDate;
    }

    public Date getScanCreationTime() {
        return scanCreationTime;
    }

    public void setScanCreationTime(Date scanCreationTime) {
        this.scanCreationTime = scanCreationTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
    

}
