/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class SsDmsStockTrfDto {

    private String chassisNo;
    private String stockTrfNo;
    private Date stockTrfDate;
    private String engineNo;
    private String vin;
    private String madeBy;
    private String fromLocation;
    private String toLocation;

    private String driverName;
    private String authorisedBy;
    private Integer ou;
    private String fromLocCode;
    private String toLocCode;
    private String remarks;
    
    
    public SsDmsStockTrfDto(){
        
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
