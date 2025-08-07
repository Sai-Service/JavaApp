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
public class batchName1Dto {
    private String batchName;
    private String batchStatus;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

   

    public batchName1Dto(String batchName, String batchStatus) {
        this.batchName = batchName;
        this.batchStatus = batchStatus;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public batchName1Dto() {
    }
    
    
    
}
