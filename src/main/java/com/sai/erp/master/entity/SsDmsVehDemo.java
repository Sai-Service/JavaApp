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
@Table(name = "SS_DMS_VEH_DEMO")
public class SsDmsVehDemo implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "MODEL_DESC")
    private String modelDesc;
    @Column(name = "FUEL_DESC")
    private String fuelDesc;
    @Column(name = "VARIANT_DESC")
    private String variantDesc;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "LOC_ID")
    private Integer locId;
    @Column(name = "OU_ID")
    private Integer ouId;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "CUST_CONTACT_NO")
    private String custContactNo;
    @Column(name = "CUST_ADDRESS")
    private String custAddress;
    @Column(name = "REMARKS")
    private String remarks;
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
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATION_DATE")
    private Date updationDate;
    @Column(name = "OUT_KM")
    private Long outKm;
    @Column(name = "OUT_TIME")
    private Date outTime;
    @Column(name = "IN_KM")
    private Long inKm;
    @Column(name = "IN_TIME")
    private Date inTime;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "GATE_PASS_NO")
    private Long gatePassNo;
    @Column(name = "FUEL_QTY")
    private Long fuelQty;
    
    
    public SsDmsVehDemo(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getFuelDesc() {
        return fuelDesc;
    }

    public void setFuelDesc(String fuelDesc) {
        this.fuelDesc = fuelDesc;
    }

    public String getVariantDesc() {
        return variantDesc;
    }

    public void setVariantDesc(String variantDesc) {
        this.variantDesc = variantDesc;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustContactNo() {
        return custContactNo;
    }

    public void setCustContactNo(String custContactNo) {
        this.custContactNo = custContactNo;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Long getOutKm() {
        return outKm;
    }

    public void setOutKm(Long outKm) {
        this.outKm = outKm;
    }

    public Long getInKm() {
        return inKm;
    }

    public void setInKm(Long inKm) {
        this.inKm = inKm;
    }

   

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }


    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
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

    public Long getFuelQty() {
        return fuelQty;
    }

    public void setFuelQty(Long fuelQty) {
        this.fuelQty = fuelQty;
    }

 
    
    
    
}
