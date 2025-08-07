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
public class vehStkTrfDto {

        private String vin;
        private String reasonCode;
        private String location;
       // private String vehStatus;
        private Integer fromLocation;
        private Integer toLocation;
        private Integer organizationId;
        private String transferredBy;
        private String remarks;
        private String createdBy;
        private String frmKm;

    public vehStkTrfDto() {
    }

        
        
    public String getVin() {
        return vin;
    }
    
        public void setVin(String vin) {
        this.vin = vin;
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


    public String getFrmKm() {
        return frmKm;
    }

    public void setFrmKm(String frmKm) {
        this.frmKm = frmKm;
    }
        
        

    }
