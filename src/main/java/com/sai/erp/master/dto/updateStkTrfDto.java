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
public class updateStkTrfDto {
    private String vin;
        private String stkTrfNo;
        private String receivedBy;
      //  private String status;
        private String toKm;

    public updateStkTrfDto() {
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

    
        
        
        
        
}
