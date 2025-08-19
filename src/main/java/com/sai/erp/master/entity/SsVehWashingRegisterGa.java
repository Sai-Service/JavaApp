/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

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
@Table(name = "SS_VEH_WASHING_REGISTER_GA")
public class SsVehWashingRegisterGa implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WASH_ID")
    private Integer washId;
    @Column(name = "REG_NO")
    private String regNo;

    @Column(name = "CHASSIS_NO")
    private String chassisNo;

    @Column(name = "VEH_WASH_NO")
    private String vehWashNo;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "SERVICE_ADVISOR")
    private String serviceAdvisor;

    @Column(name = "WASHING_SUPERVISOR")
    private String washingSupervisor;

    @Column(name = "BODY_WASH")
    private String bodyWash;

    @Column(name = "FULL_WASH")
    private String fullWash;

    @Column(name = "DRY_BODY_WASH")
    private String dryBodyWash;

    @Column(name = "DRY_WASH_FULL")
    private String dryWashFull;

    @Column(name = "IN_TIME")
    private Date inTime;

    @Column(name = "OUT_TIME")
    private Date outTime;

    @Column(name = "LOC_ID")
    private Integer locId;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "OU_ID")
    private Integer ouId;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATION_DATE")
    private Date updationDate;

    @Column(name = "STATUS")
    private String status;

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

    public SsVehWashingRegisterGa() {
    }

    public Integer getWashId() {
        return washId;
    }

    public void setWashId(Integer washId) {
        this.washId = washId;
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

    public String getVehWashNo() {
        return vehWashNo;
    }

    public void setVehWashNo(String vehWashNo) {
        this.vehWashNo = vehWashNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public String getWashingSupervisor() {
        return washingSupervisor;
    }

    public void setWashingSupervisor(String washingSupervisor) {
        this.washingSupervisor = washingSupervisor;
    }

    public String getBodyWash() {
        return bodyWash;
    }

    public void setBodyWash(String bodyWash) {
        this.bodyWash = bodyWash;
    }

    public String getFullWash() {
        return fullWash;
    }

    public void setFullWash(String fullWash) {
        this.fullWash = fullWash;
    }

    public String getDryBodyWash() {
        return dryBodyWash;
    }

    public void setDryBodyWash(String dryBodyWash) {
        this.dryBodyWash = dryBodyWash;
    }

    public String getDryWashFull() {
        return dryWashFull;
    }

    public void setDryWashFull(String dryWashFull) {
        this.dryWashFull = dryWashFull;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    
   
}
