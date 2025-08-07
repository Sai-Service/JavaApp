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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "ANDROID_VEH_QR")
public class AndroidVehQr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QR_ID")
    private BigDecimal qrId;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "MODEL_DESC")
    private String modelDesc;
    @Column(name = "COLOUR")
    private String colour;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "FUEL_DESC")
    private String fuelDesc;
    @Lob
    @Column(name = "VEH_QR")
    private byte[] vehQr;
    @Column(name = "VEH_QR_DECODED")
    private String vehQrDecoded;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "VEH_QR_IMAGE")
    private String vehQrImage;
    @Column(name = "REASONCODE")
    private String reasonCode;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "VEHSTATUS")
    private String vehStatus;
    @Column(name = "FROM_LOCATION")
    private Integer fromLocation;
    @Column(name = "TO_LOCATION")
    private Integer toLocation;
    @Column(name = "IN_TIME")
    private Date inTime;
    @Column(name = "OUT_TIME")
    private Date outTime;
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    @Column(name = "TRANSFERRED_BY")
    private String transferredBy;
    @Column(name = "RECEIVED_BY")
    private String receivedBy;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
//    private Integer kms;

    public AndroidVehQr() {
    }

//    public Integer getKms() {
//        return kms;
//    }
//
//    public void setKms(Integer kms) {
//        this.kms = kms;
//    }

    public String getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(String vehStatus) {
        this.vehStatus = vehStatus;
    }

    public Integer getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Integer fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Integer getToLocation() {
        return toLocation;
    }

    public void setToLocation(Integer toLocation) {
        this.toLocation = toLocation;
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

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getTransferredBy() {
        return transferredBy;
    }

    public void setTransferredBy(String transferredBy) {
        this.transferredBy = transferredBy;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehQrImage() {
        return vehQrImage;
    }

    public void setVehQrImage(String vehQrImage) {
        this.vehQrImage = vehQrImage;
    }

    public AndroidVehQr(BigDecimal qrId) {
        this.qrId = qrId;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuelDesc() {
        return fuelDesc;
    }

    public void setFuelDesc(String fuelDesc) {
        this.fuelDesc = fuelDesc;
    }

    public byte[] getVehQr() {
        return vehQr;
    }

    public void setVehQr(byte[] vehQr) {
        this.vehQr = vehQr;
    }

    public String getVehQrDecoded() {
        return vehQrDecoded;
    }

    public void setVehQrDecoded(String vehQrDecoded) {
        this.vehQrDecoded = vehQrDecoded;
    }

    public BigDecimal getQrId() {
        return qrId;
    }

    public void setQrId(BigDecimal qrId) {
        this.qrId = qrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrId != null ? qrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AndroidVehQr)) {
            return false;
        }
        AndroidVehQr other = (AndroidVehQr) object;
        if ((this.qrId == null && other.qrId != null) || (this.qrId != null && !this.qrId.equals(other.qrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sai.erp.master.entity.AndroidVehQr[ qrId=" + qrId + " ]";
    }

}
