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
@Table(name = "SS_DMS_WS_PARKING")
public class SsDmsWsParking implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARKING_ID")
    private Integer parkingId;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "DEPT")
    private String dept;
    @Column(name = "DRIVER_IN")
    private String driverIn;
    @Column(name = "DRIVER_OUT")
    private String driverOut;
    @Column(name = "IN_KM")
    private Integer inKm;
    @Column(name = "IN_TIME")
    private Date inTime;
    @Column(name = "OUT_KM")
    private Integer outKm;
    @Column(name = "OUT_TIME")
    private Date outTime;
    @Column(name = "LOC_ID")
    private Integer locId;
    @Column(name = "OU_ID")
    private Integer ouId;
    @Column(name = "GATE_NO")
    private String gateNo;
    @Column(name = "GATE_TYPE")
    private String gateType;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "PARKING_REASON")
    private String parkingReason;
    @Column(name = "PARKING_DESC")
    private String parkingDesc;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATION_DATE")
    private Date updationDate;
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
     @Column(name = "LOCATION")
    private String location;

    public SsDmsWsParking() {
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDriverIn() {
        return driverIn;
    }

    public void setDriverIn(String driverIn) {
        this.driverIn = driverIn;
    }

    public String getDriverOut() {
        return driverOut;
    }

    public void setDriverOut(String driverOut) {
        this.driverOut = driverOut;
    }

    public Integer getInKm() {
        return inKm;
    }

    public void setInKm(Integer inKm) {
        this.inKm = inKm;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getOutKm() {
        return outKm;
    }

    public void setOutKm(Integer outKm) {
        this.outKm = outKm;
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

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getGateNo() {
        return gateNo;
    }

    public void setGateNo(String gateNo) {
        this.gateNo = gateNo;
    }

    public String getGateType() {
        return gateType;
    }

    public void setGateType(String gateType) {
        this.gateType = gateType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getParkingReason() {
        return parkingReason;
    }

    public void setParkingReason(String parkingReason) {
        this.parkingReason = parkingReason;
    }

    public String getParkingDesc() {
        return parkingDesc;
    }

    public void setParkingDesc(String parkingDesc) {
        this.parkingDesc = parkingDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
}
