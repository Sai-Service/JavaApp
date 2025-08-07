/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Harsh Gawde
 */
public class WsStockTrfDto {

    private String regNo;
    private String chassisNo;
    private String stockTrfNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date stockTrfDate;
    private String jobCardNo;
    private String engineNo;
    private String vin;
    private String madeBy;
    private String fromLocation;
    private String toLocation;
    private String driverName;
    private String authorisedBy;
    private Integer ou;
    private Integer fromKm;
    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creationDate;
    private String trfImage1;
    private String trfImage2;
    private String trfImage3;
    private String trfImage4;
    private String trfImage5;
    private String trfImage6;
    private String trfImage7;
    private String trfImage8;
    private String trfImage9;
    private String trfImage10;
    private String trfImage11;
    private String trfImage12;
    private String trfImage13;
    private String trfImage14;
    private String lastUploadedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastUploadDate;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String createdBy;
    private String dept;
    private String fromLocCode;
    private String toLocCode;

    public WsStockTrfDto() {

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

}
