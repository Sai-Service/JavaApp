/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author HarshG
 */
public class VehWashProceedGaDto {

    @JsonProperty("regNo")
    private String regNo;
    @JsonProperty("chassisNo")
    private String chassisNo;
    @JsonProperty("vehWashNo")
    private String vehWashNo;
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("model")
    private String model;
    @JsonProperty("serviceAdvisor")
    private String serviceAdvisor;
    @JsonProperty("washingSupervisor")
    private String washingSupervisor;
    @JsonProperty("bodyWash")
    private String bodyWash;
    @JsonProperty("fullWash")
    private String fullWash;
    @JsonProperty("dryBodyWash")
    private String dryBodyWash;
    @JsonProperty("dryWashFull")
    private String dryWashFull;
    @JsonProperty("inTime")
    private Date inTime;
    @JsonProperty("outTime")
    private Date outTime;
    @JsonProperty("locId")
    private Integer locId;
    @JsonProperty("location")
    private String location;
    @JsonProperty("ouId")
    private Integer ouId;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("creationDate")
    private Date creationDate;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("updationDate")
    private Date updationDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("attribute1")
    private String attribute1;
    @JsonProperty("attribute2")
    private String attribute2;
    @JsonProperty("attribute3")
    private String attribute3;
    @JsonProperty("attribute4")
    private String attribute4;
    @JsonProperty("attribute5")
    private String attribute5;
    @JsonProperty("loginName")
    private String loginName;

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

    @JsonProperty("vehWashNo")
    public String getVehWashNo() {
        return vehWashNo;
    }

    @JsonProperty("vehWashNo")
    public void setVehWashNo(String vehWashNo) {
        this.vehWashNo = vehWashNo;
    }

    @JsonProperty("vin")
    public String getVin() {
        return vin;
    }

    @JsonProperty("vin")
    public void setVin(String vin) {
        this.vin = vin;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("serviceAdvisor")
    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    @JsonProperty("serviceAdvisor")
    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    @JsonProperty("washingSupervisor")
    public String getWashingSupervisor() {
        return washingSupervisor;
    }

    @JsonProperty("washingSupervisor")
    public void setWashingSupervisor(String washingSupervisor) {
        this.washingSupervisor = washingSupervisor;
    }

    @JsonProperty("bodyWash")
    public String getBodyWash() {
        return bodyWash;
    }

    @JsonProperty("bodyWash")
    public void setBodyWash(String bodyWash) {
        this.bodyWash = bodyWash;
    }

    @JsonProperty("fullWash")
    public String getFullWash() {
        return fullWash;
    }

    @JsonProperty("fullWash")
    public void setFullWash(String fullWash) {
        this.fullWash = fullWash;
    }

    @JsonProperty("dryBodyWash")
    public String getDryBodyWash() {
        return dryBodyWash;
    }

    @JsonProperty("dryBodyWash")
    public void setDryBodyWash(String dryBodyWash) {
        this.dryBodyWash = dryBodyWash;
    }

    @JsonProperty("dryWashFull")
    public String getDryWashFull() {
        return dryWashFull;
    }

    @JsonProperty("dryWashFull")
    public void setDryWashFull(String dryWashFull) {
        this.dryWashFull = dryWashFull;
    }

    @JsonProperty("inTime")
    public Date getInTime() {
        return inTime;
    }

    @JsonProperty("inTime")
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @JsonProperty("outTime")
    public Date getOutTime() {
        return outTime;
    }

    @JsonProperty("outTime")
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @JsonProperty("locId")
    public Integer getLocId() {
        return locId;
    }

    @JsonProperty("locId")
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("ouId")
    public Integer getOuId() {
        return ouId;
    }

    @JsonProperty("ouId")
    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("creationDate")
    public Date getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("updationDate")
    public Date getUpdationDate() {
        return updationDate;
    }

    @JsonProperty("updationDate")
    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("attribute1")
    public String getAttribute1() {
        return attribute1;
    }

    @JsonProperty("attribute1")
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @JsonProperty("attribute2")
    public String getAttribute2() {
        return attribute2;
    }

    @JsonProperty("attribute2")
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @JsonProperty("attribute3")
    public String getAttribute3() {
        return attribute3;
    }

    @JsonProperty("attribute3")
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @JsonProperty("attribute4")
    public String getAttribute4() {
        return attribute4;
    }

    @JsonProperty("attribute4")
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @JsonProperty("attribute5")
    public String getAttribute5() {
        return attribute5;
    }

    @JsonProperty("attribute5")
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    

}
