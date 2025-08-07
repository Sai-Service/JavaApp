/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

/**
 *
 * @author Lenovo
 */
public class updateInStkTrfDto {
    private String vin;
        private String stkTrfNo;
        private String receivedBy;
        private String status;
        private String vehStatus;
        private String location;
        private String toKm;

    public updateInStkTrfDto() {
    }

    public String getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(String vehStatus) {
        this.vehStatus = vehStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getStkTrfNo() {
        return stkTrfNo;
    }

    public void setStkTrfNo(String stkTrfNo) {
        this.stkTrfNo = stkTrfNo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getToKm() {
        return toKm;
    }

    public void setToKm(String toKm) {
        this.toKm = toKm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
    
        
        
        
        
}
