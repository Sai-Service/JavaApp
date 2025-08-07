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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "SS_DMS_STOCK_TRF")
public class SsDmsStockTrf implements Serializable {

    @Column(name = "chassis_no")
    private String chassisNo;
    @Id
//    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_trf_no")
    private String stockTrfNo;
    @Column(name = "stock_trf_date")
    private Date stockTrfDate;
    @Column(name = "engine_no")
    private String engineNo;
    
    private String vin;
    @Column(name = "made_by")
    private String madeBy;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;
    @Column(name = "received_by")
    private String receivedBy;
    @Column(name = "recd_date")
    private Date recdDate;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "authorised_by")
    private String authorisedBy;
   
    private Integer ou;
     @Column(name = "from_loc_code")
    private String fromLocCode;
    @Column(name = "to_loc_code")
    private String toLocCode;
   
    private String remarks;
    private String frmKm;
    private String toKm;
    private String createdFrom;

    
    public String getFrmKm() {
        return frmKm;
    }

    public void setFrmKm(String frmKm) {
        this.frmKm = frmKm;
    }

    public String getToKm() {
        return toKm;
    }

    public void setToKm(String toKm) {
        this.toKm = toKm;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
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

    public SsDmsStockTrf() {

    }

}
