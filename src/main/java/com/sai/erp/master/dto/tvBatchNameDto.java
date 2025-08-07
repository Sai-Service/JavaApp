/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

/**
 *
 * @author IT-HARSH
 */
public class tvBatchNameDto {

    private String batchName;
    private String batchStatus;

    public tvBatchNameDto() {
    }

    public String getBatchName() {
        return batchName;
    }

    public tvBatchNameDto(String batchName, String batchStatus) {
        this.batchName = batchName;
        this.batchStatus = batchStatus;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

}
