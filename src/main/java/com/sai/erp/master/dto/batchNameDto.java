/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class batchNameDto {

    private String vin;
    private String chassis_no;
    private String engin_no;
    private String model_desc;
    private String colour;
    private String fuel_desc;
    private Integer locationId;
    private String locationName;
    private String batchName;
    private Date batchCreationDate;
    private String batchStatus;
    private Date batchCodeEndDate;
     private Date scanCreationTime;
    private Date creationDate;
    private String createdBy;
    private Date updationDate;
    private String updatedBy;
    

    public batchNameDto() {
    }


   
 

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getChassis_no() {
        return chassis_no;
    }

    public void setChassis_no(String chassis_no) {
        this.chassis_no = chassis_no;
    }

    public String getEngin_no() {
        return engin_no;
    }

    public void setEngin_no(String engin_no) {
        this.engin_no = engin_no;
    }

    public String getModel_desc() {
        return model_desc;
    }

    public void setModel_desc(String model_desc) {
        this.model_desc = model_desc;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFuel_desc() {
        return fuel_desc;
    }

    public void setFuel_desc(String fuel_desc) {
        this.fuel_desc = fuel_desc;
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
