/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "SS_DMS_INV_STOCK")
public class SsDmsInvStockOriginal implements Serializable {

    @Id
    @Column(name = "purchase_price")
    private Integer purchasePrice;
    @Column(name = "chassis_no")
    private String chassisNo;
    private String model_desc;
    private String colour;
    @Column(name = "engine_no")
    private String engineNo;
    private String fuel_desc;
    private String vin;
    private String status;
    

    public String getEngineNo() {
        return engineNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public SsDmsInvStockOriginal() {
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getModel_desc() {
        return model_desc;
    }

    public void setModel_desc(String model_desc) {
        this.model_desc = model_desc;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFuel_desc() {
        return fuel_desc;
    }

    public void setFuel_desc(String fuel_desc) {
        this.fuel_desc = fuel_desc;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

}
