/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

/**
 *
 * @author HarshG
 */
public class TestDriveDeliverDto {
    
    private String regNo;
    private String testDriveNo;
    private Integer outKm;
    private String updatedBy;
  
    
    public TestDriveDeliverDto(){
        
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getTestDriveNo() {
        return testDriveNo;
    }

    public void setTestDriveNo(String testDriveNo) {
        this.testDriveNo = testDriveNo;
    }
    
    

    public Integer getOutKm() {
        return outKm;
    }

    public void setOutKm(Integer outKm) {
        this.outKm = outKm;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
    
}
