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

/**
 *
 * @author IT-HARSH
 */
@Entity
@Table(name = "SS_DMS_STOCK_TRF_TV")
public class SsDmsStockTrfTruevalue implements Serializable {

    
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Id
    @Basic(optional = false)
    @Column(name = "STOCK_TRF_NO")
    private String stockTrfNo;
    @Column(name = "STOCK_TRF_DATE")
    private Date stockTrfDate;
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
    @Column(name = "FROMKM")
    private Integer frmKm;
    @Column(name = "TOKM")
    private Integer toKm;
    @Column(name = "UPDATEDBY")
    private String updatedBy;
    @Column(name = "UPDATIONDATE")
    private Date updationDate;
    @Column(name = "CREATIONDATE")
    private Date creationDate;

    public SsDmsStockTrfTruevalue() {
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

    public Integer getFrmKm() {
        return frmKm;
    }

    public void setFrmKm(Integer frmKm) {
        this.frmKm = frmKm;
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

}
