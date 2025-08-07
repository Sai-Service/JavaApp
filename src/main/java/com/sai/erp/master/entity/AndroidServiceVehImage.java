/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

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
@Table(name = "ANDROID_SERVICE_VEH_IMAGE")
public class AndroidServiceVehImage {

    @Column(name = "REGNO")
    private String regNo;
    @Column(name = "REGDATE")
    private Date regDate;
    private String vin;
    @Column(name = "CHASSISNO")
    private String chassisNo;
    @Column(name = "ENGINNO")
    private String engineNo;
    private String variant;
    @Column(name = "MODELDESC")
    private String modelDesc;
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
    @Column(name = "VEHIMAGE")
    private String vehImage;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IMAGE_ID")
    private Integer image_id;
    @Column(name = "LASTUPLOADDATE")
    private Date lastUploadDate;
    @Column(name = "VEHIMAGE2")
    private String vehImage2;
    @Column(name = "VEHIMAGE3")
    private String vehImage3;
    @Column(name = "LASTUPLOADEDBY")
    private String lastUploadedBy;
    private String vehImage4;
    private String vehImage5;

    public AndroidServiceVehImage() {

    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
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

    public String getVehImage() {
        return vehImage;
    }

    public void setVehImage(String vehImage) {
        this.vehImage = vehImage;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Date getLastUploadDate() {
        return lastUploadDate;
    }

    public void setLastUploadDate(Date lastUploadDate) {
        this.lastUploadDate = lastUploadDate;
    }

    public String getVehImage2() {
        return vehImage2;
    }

    public void setVehImage2(String vehImage2) {
        this.vehImage2 = vehImage2;
    }

    public String getVehImage3() {
        return vehImage3;
    }

    public void setVehImage3(String vehImage3) {
        this.vehImage3 = vehImage3;
    }

    public String getLastUploadedBy() {
        return lastUploadedBy;
    }

    public void setLastUploadedBy(String lastUploadedBy) {
        this.lastUploadedBy = lastUploadedBy;
    }

    public String getVehImage4() {
        return vehImage4;
    }

    public void setVehImage4(String vehImage4) {
        this.vehImage4 = vehImage4;
    }

    public String getVehImage5() {
        return vehImage5;
    }

    public void setVehImage5(String vehImage5) {
        this.vehImage5 = vehImage5;
    }

}
