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
public class WsStockReceiveDto {

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
    private String receivedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date recdDate;
    private String driverName;
    private String authorisedBy;
    private Integer ou;
    private Integer fromKm;
    private Integer toKm;
    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creationDate;
    private String recImage1;
    private String recImage2;
    private String recImage3;
    private String recImage4;
    private String recImage5;
    private String recImage6;
    private String recImage7;
    private String recImage8;
    private String recImage9;
    private String recImage10;
    private String recImage11;
    private String recImage12;
    private String recImage13;
    private String recImage14;
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

    public WsStockReceiveDto() {

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
