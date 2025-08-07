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
 * @author Harsh Gawde
 */
@Entity
@Table(name = "SS_DMS_STOCK_TRF_WS")
public class SsDmsStockTrfWs implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRF_ID")
    private Integer trfId;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "STOCK_TRF_NO")
    private String stockTrfNo;
    @Column(name = "STOCK_TRF_DATE")
    private Date stockTrfDate;
    @Column(name = "JOBCARDNO")
    private String jobCardNo;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "MADE_BY")
    private String madeBy;
    @Column(name = "FROM_LOCATION")
    private String fromLocation;
    @Column(name = "TO_LOCATION")
    private String toLocation;
    @Column(name = "RECEIVED_BY")
    private String receivedBy;
    @Column(name = "RECD_DATE")
    private Date recdDate;
    @Column(name = "DRIVER_NAME")
    private String driverName;
    @Column(name = "AUTHORISED_BY")
    private String authorisedBy;
    @Column(name = "OU")
    private Integer ou;
    @Column(name = "FROM_KM")
    private Integer fromKm;
    @Column(name = "TO_KM")
    private Integer toKm;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATION_DATE")
    private Date updationDate;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "TRF_IMAGE_1")
    private String trfImage1;
    @Column(name = "TRF_IMAGE_2")
    private String trfImage2;
    @Column(name = "TRF_IMAGE_3")
    private String trfImage3;
    @Column(name = "TRF_IMAGE_4")
    private String trfImage4;
    @Column(name = "TRF_IMAGE_5")
    private String trfImage5;
    @Column(name = "TRF_IMAGE_6")
    private String trfImage6;
    @Column(name = "TRF_IMAGE_7")
    private String trfImage7;
    @Column(name = "TRF_IMAGE_8")
    private String trfImage8;
    @Column(name = "TRF_IMAGE_9")
    private String trfImage9;
    @Column(name = "TRF_IMAGE_10")
    private String trfImage10;
    @Column(name = "TRF_IMAGE_11")
    private String trfImage11;
    @Column(name = "TRF_IMAGE_12")
    private String trfImage12;
    @Column(name = "TRF_IMAGE_13")
    private String trfImage13;
    @Column(name = "TRF_IMAGE_14")
    private String trfImage14;
    @Column(name = "LAST_UPLOADED_BY")
    private String lastUploadedBy;
    @Column(name = "LAST_UPLOAD_DATE")
    private Date lastUploadDate;
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
    @Column(name = "DEPT")
    private String dept;
    @Column(name = "FROM_LOC_CODE")
    private String fromLocCode;
    @Column(name = "TO_LOC_CODE")
    private String toLocCode;
    @Column(name = "REC_IMAGE_1")
    private String recImage1;
    @Column(name = "REC_IMAGE_2")
    private String recImage2;
    @Column(name = "REC_IMAGE_3")
    private String recImage3;
    @Column(name = "REC_IMAGE_4")
    private String recImage4;
    @Column(name = "REC_IMAGE_5")
    private String recImage5;
    @Column(name = "REC_IMAGE_6")
    private String recImage6;
    @Column(name = "REC_IMAGE_7")
    private String recImage7;
    @Column(name = "REC_IMAGE_8")
    private String recImage8;
    @Column(name = "REC_IMAGE_9")
    private String recImage9;
    @Column(name = "REC_IMAGE_10")
    private String recImage10;
    @Column(name = "REC_IMAGE_11")
    private String recImage11;
    @Column(name = "REC_IMAGE_12")
    private String recImage12;
    @Column(name = "REC_IMAGE_13")
    private String recImage13;
    @Column(name = "REC_IMAGE_14")
    private String recImage14;

    public SsDmsStockTrfWs() {

    }

    public Integer getTrfId() {
        return trfId;
    }

    public void setTrfId(Integer trfId) {
        this.trfId = trfId;
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

    public String getStockTrfNo() {
        return stockTrfNo;
    }

    public void setStockTrfNo(String stockTrfNo) {
        this.stockTrfNo = stockTrfNo;
    }

    public Date getStockTrfDate() {
        return stockTrfDate;
    }

    public void setStockTrfDate(Date stockTrfDate) {
        this.stockTrfDate = stockTrfDate;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
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

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Date getRecdDate() {
        return recdDate;
    }

    public void setRecdDate(Date recdDate) {
        this.recdDate = recdDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAuthorisedBy() {
        return authorisedBy;
    }

    public void setAuthorisedBy(String authorisedBy) {
        this.authorisedBy = authorisedBy;
    }

    public Integer getOu() {
        return ou;
    }

    public void setOu(Integer ou) {
        this.ou = ou;
    }

    public Integer getFromKm() {
        return fromKm;
    }

    public void setFromKm(Integer fromKm) {
        this.fromKm = fromKm;
    }

    public Integer getToKm() {
        return toKm;
    }

    public void setToKm(Integer toKm) {
        this.toKm = toKm;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTrfImage1() {
        return trfImage1;
    }

    public void setTrfImage1(String trfImage1) {
        this.trfImage1 = trfImage1;
    }

    public String getTrfImage2() {
        return trfImage2;
    }

    public void setTrfImage2(String trfImage2) {
        this.trfImage2 = trfImage2;
    }

    public String getTrfImage3() {
        return trfImage3;
    }

    public void setTrfImage3(String trfImage3) {
        this.trfImage3 = trfImage3;
    }

    public String getTrfImage4() {
        return trfImage4;
    }

    public void setTrfImage4(String trfImage4) {
        this.trfImage4 = trfImage4;
    }

    public String getTrfImage5() {
        return trfImage5;
    }

    public void setTrfImage5(String trfImage5) {
        this.trfImage5 = trfImage5;
    }

    public String getTrfImage6() {
        return trfImage6;
    }

    public void setTrfImage6(String trfImage6) {
        this.trfImage6 = trfImage6;
    }

    public String getTrfImage7() {
        return trfImage7;
    }

    public void setTrfImage7(String trfImage7) {
        this.trfImage7 = trfImage7;
    }

    public String getTrfImage8() {
        return trfImage8;
    }

    public void setTrfImage8(String trfImage8) {
        this.trfImage8 = trfImage8;
    }

    public String getTrfImage9() {
        return trfImage9;
    }

    public void setTrfImage9(String trfImage9) {
        this.trfImage9 = trfImage9;
    }

    public String getTrfImage10() {
        return trfImage10;
    }

    public void setTrfImage10(String trfImage10) {
        this.trfImage10 = trfImage10;
    }

    public String getTrfImage11() {
        return trfImage11;
    }

    public void setTrfImage11(String trfImage11) {
        this.trfImage11 = trfImage11;
    }

    public String getTrfImage12() {
        return trfImage12;
    }

    public void setTrfImage12(String trfImage12) {
        this.trfImage12 = trfImage12;
    }

    public String getTrfImage13() {
        return trfImage13;
    }

    public void setTrfImage13(String trfImage13) {
        this.trfImage13 = trfImage13;
    }

    public String getTrfImage14() {
        return trfImage14;
    }

    public void setTrfImage14(String trfImage14) {
        this.trfImage14 = trfImage14;
    }

    public String getLastUploadedBy() {
        return lastUploadedBy;
    }

    public void setLastUploadedBy(String lastUploadedBy) {
        this.lastUploadedBy = lastUploadedBy;
    }

    public Date getLastUploadDate() {
        return lastUploadDate;
    }

    public void setLastUploadDate(Date lastUploadDate) {
        this.lastUploadDate = lastUploadDate;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFromLocCode() {
        return fromLocCode;
    }

    public void setFromLocCode(String fromLocCode) {
        this.fromLocCode = fromLocCode;
    }

    public String getToLocCode() {
        return toLocCode;
    }

    public void setToLocCode(String toLocCode) {
        this.toLocCode = toLocCode;
    }

    public String getRecImage1() {
        return recImage1;
    }

    public void setRecImage1(String recImage1) {
        this.recImage1 = recImage1;
    }

    public String getRecImage2() {
        return recImage2;
    }

    public void setRecImage2(String recImage2) {
        this.recImage2 = recImage2;
    }

    public String getRecImage3() {
        return recImage3;
    }

    public void setRecImage3(String recImage3) {
        this.recImage3 = recImage3;
    }

    public String getRecImage4() {
        return recImage4;
    }

    public void setRecImage4(String recImage4) {
        this.recImage4 = recImage4;
    }

    public String getRecImage5() {
        return recImage5;
    }

    public void setRecImage5(String recImage5) {
        this.recImage5 = recImage5;
    }

    public String getRecImage6() {
        return recImage6;
    }

    public void setRecImage6(String recImage6) {
        this.recImage6 = recImage6;
    }

    public String getRecImage7() {
        return recImage7;
    }

    public void setRecImage7(String recImage7) {
        this.recImage7 = recImage7;
    }

    public String getRecImage8() {
        return recImage8;
    }

    public void setRecImage8(String recImage8) {
        this.recImage8 = recImage8;
    }

    public String getRecImage9() {
        return recImage9;
    }

    public void setRecImage9(String recImage9) {
        this.recImage9 = recImage9;
    }

    public String getRecImage10() {
        return recImage10;
    }

    public void setRecImage10(String recImage10) {
        this.recImage10 = recImage10;
    }

    public String getRecImage11() {
        return recImage11;
    }

    public void setRecImage11(String recImage11) {
        this.recImage11 = recImage11;
    }

    public String getRecImage12() {
        return recImage12;
    }

    public void setRecImage12(String recImage12) {
        this.recImage12 = recImage12;
    }

    public String getRecImage13() {
        return recImage13;
    }

    public void setRecImage13(String recImage13) {
        this.recImage13 = recImage13;
    }

    public String getRecImage14() {
        return recImage14;
    }

    public void setRecImage14(String recImage14) {
        this.recImage14 = recImage14;
    }
    
    

}
