///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sai.erp.master.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// *
// * @author HarshG
// */
//@Entity
//@Table(name = "ss_dms_demo_fuel_trans")
//public class SsDmsDemoFuelTrans implements Serializable{
//    
//    @Id
//    @Basic(optional = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "FT_ID")
//    private Integer ftId;
//    @Column(name = "REG_NO")
//    private String regNo;
//    @Column(name = "CHASSIS_NO")
//    private String chassisNo;
//    @Column(name = "FUEL_DESC")
//    private String fuelDesc;
//    @Column(name = "OUT_KM")
//    private Integer outKm;
//    @Column(name = "OUT_TIME")
//    private Date outTime;
//    @Column(name = "IN_KM")
//    private Integer inKm;
//    @Column(name = "IN_TIME")
//    private Date inTime;
//    @Column(name = "FUEL_TANK_PREV")
//    private Integer fuelTankPrev;
//    @Column(name = "FUEL_TANK_AFTER")
//    private Integer fuelTankAfter;
//    @Column(name = "LOC_ID")
//    private Integer locId;
//    @Column(name = "OU_ID")
//    private Integer ouId;
//    @Column(name = "LOCATION")
//    private String location;
//    @Column(name = "REMARKS")
//    private String remarks;
//    @Column(name = "EXECUTIVE_NAME")
//    private String executiveName;
//    @Column(name = "GATE_PASS_NO")
//    private Integer gatePassNo;
//    @Column(name = "CREATED_BY")
//    private String createdBy;
//    @Column(name = "CREATION_DATE")
//    private Date creationDate;
//    @Column(name = "UPDATED_BY")
//    private String updatedBy;
//    @Column(name = "UPDATION_DATE")
//    private Date updationDate;
//    @Column(name = "ATTRIBUTE1")
//    private String attribute1;
//    @Column(name = "ATTRIBUTE2")
//    private String attribute2;
//    @Column(name = "ATTRIBUTE3")
//    private String attribute3;
//    @Column(name = "ATTRIBUTE4")
//    private String attribute4;
//    @Column(name = "ATTRIBUTE5")
//    private String attribute5;
//    
//    
//    public SsDmsDemoFuelTrans(){
//        
//    }
//
//    public Integer getFtId() {
//        return ftId;
//    }
//
//    public void setFtId(Integer ftId) {
//        this.ftId = ftId;
//    }
//
//    public String getRegNo() {
//        return regNo;
//    }
//
//    public void setRegNo(String regNo) {
//        this.regNo = regNo;
//    }
//
//    public String getChassisNo() {
//        return chassisNo;
//    }
//
//    public void setChassisNo(String chassisNo) {
//        this.chassisNo = chassisNo;
//    }
//
//    public String getFuelDesc() {
//        return fuelDesc;
//    }
//
//    public void setFuelDesc(String fuelDesc) {
//        this.fuelDesc = fuelDesc;
//    }
//
//    public Integer getOutKm() {
//        return outKm;
//    }
//
//    public void setOutKm(Integer outKm) {
//        this.outKm = outKm;
//    }
//
//    public Date getOutTime() {
//        return outTime;
//    }
//
//    public void setOutTime(Date outTime) {
//        this.outTime = outTime;
//    }
//
//    public Integer getInKm() {
//        return inKm;
//    }
//
//    public void setInKm(Integer inKm) {
//        this.inKm = inKm;
//    }
//
//    public Date getInTime() {
//        return inTime;
//    }
//
//    public void setInTime(Date inTime) {
//        this.inTime = inTime;
//    }
//
//    public Integer getFuelTankPrev() {
//        return fuelTankPrev;
//    }
//
//    public void setFuelTankPrev(Integer fuelTankPrev) {
//        this.fuelTankPrev = fuelTankPrev;
//    }
//
//    public Integer getFuelTankAfter() {
//        return fuelTankAfter;
//    }
//
//    public void setFuelTankAfter(Integer fuelTankAfter) {
//        this.fuelTankAfter = fuelTankAfter;
//    }
//
//    public Integer getLocId() {
//        return locId;
//    }
//
//    public void setLocId(Integer locId) {
//        this.locId = locId;
//    }
//
//    public Integer getOuId() {
//        return ouId;
//    }
//
//    public void setOuId(Integer ouId) {
//        this.ouId = ouId;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
//
//    public String getExecutiveName() {
//        return executiveName;
//    }
//
//    public void setExecutiveName(String executiveName) {
//        this.executiveName = executiveName;
//    }
//
//    public Integer getGatePassNo() {
//        return gatePassNo;
//    }
//
//    public void setGatePassNo(Integer gatePassNo) {
//        this.gatePassNo = gatePassNo;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public Date getUpdationDate() {
//        return updationDate;
//    }
//
//    public void setUpdationDate(Date updationDate) {
//        this.updationDate = updationDate;
//    }
//
//    public String getAttribute1() {
//        return attribute1;
//    }
//
//    public void setAttribute1(String attribute1) {
//        this.attribute1 = attribute1;
//    }
//
//    public String getAttribute2() {
//        return attribute2;
//    }
//
//    public void setAttribute2(String attribute2) {
//        this.attribute2 = attribute2;
//    }
//
//    public String getAttribute3() {
//        return attribute3;
//    }
//
//    public void setAttribute3(String attribute3) {
//        this.attribute3 = attribute3;
//    }
//
//    public String getAttribute4() {
//        return attribute4;
//    }
//
//    public void setAttribute4(String attribute4) {
//        this.attribute4 = attribute4;
//    }
//
//    public String getAttribute5() {
//        return attribute5;
//    }
//
//    public void setAttribute5(String attribute5) {
//        this.attribute5 = attribute5;
//    }
//    
//    
//}
