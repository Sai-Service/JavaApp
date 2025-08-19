/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author HarshG
 */
public class VehWashOutGaDto {

    @JsonProperty("regNo")
    private String regNo;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("vin")
    private String vin;

    @JsonProperty("regNo")
    public String getRegNo() {
        return regNo;
    }

    @JsonProperty("regNo")
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("vin")
    public String getVin() {
        return vin;
    }

    @JsonProperty("vin")
    public void setVin(String vin) {
        this.vin = vin;
    }
}
