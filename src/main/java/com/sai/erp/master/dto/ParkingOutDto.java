package com.sai.erp.master.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class ParkingOutDto {

    @JsonProperty("regNo")
    private String regNo;
    @JsonProperty("chassisNo")
    private String chassisNo;
    @JsonProperty("engineNo")
    private String engineNo;
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("dept")
    private String dept;
    @JsonProperty("driverOut")
    private String driverOut;
    @JsonProperty("outKm")
    private Integer outKm;
    @JsonProperty("outTime")
    private Date outTime;
    @JsonProperty("locId")
    private Integer locId;
    @JsonProperty("ouId")
    private Integer ouId;
    @JsonProperty("location")
    private String location;
    @JsonProperty("gateNo")
    private String gateNo;
    @JsonProperty("gateType")
    private String gateType;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("custName")
    private String custName;
    @JsonProperty("parkingReason")
    private String parkingReason;
    @JsonProperty("parkingDesc")
    private String parkingDesc;
    @JsonProperty("status")
    private String status;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("creationDate")
    private Date creationDate;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("updationDate")
    private Date updationDate;
     private String attribute1;
    private String attribute2;

    @JsonProperty("regNo")
    public String getRegNo() {
        return regNo;
    }

    @JsonProperty("regNo")
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @JsonProperty("chassisNo")
    public String getChassisNo() {
        return chassisNo;
    }

    @JsonProperty("chassisNo")
    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    @JsonProperty("engineNo")
    public String getEngineNo() {
        return engineNo;
    }

    @JsonProperty("engineNo")
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    @JsonProperty("vin")
    public String getVin() {
        return vin;
    }

    @JsonProperty("vin")
    public void setVin(String vin) {
        this.vin = vin;
    }

    @JsonProperty("dept")
    public String getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(String dept) {
        this.dept = dept;
    }

    @JsonProperty("driverOut")
    public String getDriverOut() {
        return driverOut;
    }

    @JsonProperty("driverOut")
    public void setDriverOut(String driverOut) {
        this.driverOut = driverOut;
    }

    @JsonProperty("outKm")
    public Integer getOutKm() {
        return outKm;
    }

    @JsonProperty("outKm")
    public void setOutKm(Integer outKm) {
        this.outKm = outKm;
    }

    @JsonProperty("locId")
    public Integer getLocId() {
        return locId;
    }

    @JsonProperty("locId")
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    @JsonProperty("ouId")
    public Integer getOuId() {
        return ouId;
    }

    @JsonProperty("ouId")
    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    

    @JsonProperty("gateNo")
    public String getGateNo() {
        return gateNo;
    }

    @JsonProperty("gateNo")
    public void setGateNo(String gateNo) {
        this.gateNo = gateNo;
    }

    @JsonProperty("gateType")
    public String getGateType() {
        return gateType;
    }

    @JsonProperty("gateType")
    public void setGateType(String gateType) {
        this.gateType = gateType;
    }

    @JsonProperty("remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("custName")
    public String getCustName() {
        return custName;
    }

    @JsonProperty("custName")
    public void setCustName(String custName) {
        this.custName = custName;
    }

    @JsonProperty("parkingReason")
    public String getParkingReason() {
        return parkingReason;
    }

    @JsonProperty("parkingReason")
    public void setParkingReason(String parkingReason) {
        this.parkingReason = parkingReason;
    }

    @JsonProperty("parkingDesc")
    public String getParkingDesc() {
        return parkingDesc;
    }

    @JsonProperty("parkingDesc")
    public void setParkingDesc(String parkingDesc) {
        this.parkingDesc = parkingDesc;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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
