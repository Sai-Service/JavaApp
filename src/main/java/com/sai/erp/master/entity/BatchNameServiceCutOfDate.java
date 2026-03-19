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
 * @author HarshG
 */
@Entity
@Table(name = "SS_DMS_INV_STOCK_TAKING_SR")
public class BatchNameServiceCutOfDate implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "REGDATE")
    private String regDate;
    @Column(name = "CHASSISNO")
    private String chassisNo;
    @Column(name = "ENGINNO")
    private String engineNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "VARIANT")
    private String variant;
    @Column(name = "MODELDESC")
    private String modelDesc;
    @Column(name = "COLOR")
    private String color;

    @Column(name = "COLORDESC")
    private String colorDesc;
    @Column(name = "JOBCARDNO")
    private String jobCardNo;
    @Column(name = "JOBCARDDATE")
    private Date jobCardDate;
    @Column(name = "ERPACCTNO")
    private String erpAcctNo;
    @Column(name = "DMSCUSTID")
    private String dmsCustId;
    private String custName;
    private String contactNo;
    @Column(name = "SERVICEADVISOR")
    private String serviceAdvisor;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    @Column(name = "CREATIONDATE")
    private String creationDate;
    private String technician;
    @Column(name = "DMSLOCATION")
    private String dmsLocation;
    @Column(name = "ERPLOC")
    private String erpLoc;
    @Column(name = "OUID")
    private String ouId;
    @Column(name = "VEH_STATUS")
    private String vehStatus;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PHYSICALLOCATION")
    private String physicalLocation;
    @Column(name = "LASTUPDATEDBY")
    private String lastUpdatedBy;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    @Column(name = "FUEL_DESC")
    private String fuelDesc;
    @Column(name = "DEPT")
    private String dept;
    @Column(name = "CUT_OFF_DATE")
    private Date cutOffDate;

    public BatchNameServiceCutOfDate() {
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

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public Date getJobCardDate() {
        return jobCardDate;
    }

    public void setJobCardDate(Date jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    public String getErpAcctNo() {
        return erpAcctNo;
    }

    public void setErpAcctNo(String erpAcctNo) {
        this.erpAcctNo = erpAcctNo;
    }

    public String getDmsCustId() {
        return dmsCustId;
    }

    public void setDmsCustId(String dmsCustId) {
        this.dmsCustId = dmsCustId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getDmsLocation() {
        return dmsLocation;
    }

    public void setDmsLocation(String dmsLocation) {
        this.dmsLocation = dmsLocation;
    }

    public String getErpLoc() {
        return erpLoc;
    }

    public void setErpLoc(String erpLoc) {
        this.erpLoc = erpLoc;
    }

    public String getOuId() {
        return ouId;
    }

    public void setOuId(String ouId) {
        this.ouId = ouId;
    }

    public String getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(String vehStatus) {
        this.vehStatus = vehStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public String getFuelDesc() {
        return fuelDesc;
    }

    public void setFuelDesc(String fuelDesc) {
        this.fuelDesc = fuelDesc;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getCutOffDate() {
        return cutOffDate;
    }

    public void setCutOffDate(Date cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

}
