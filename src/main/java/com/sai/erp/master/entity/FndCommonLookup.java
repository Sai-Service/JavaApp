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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jyoti K
 */
@Entity
@Table(name = "fnd_common_lookup")
public class FndCommonLookup implements Serializable{
    
     private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
     
     private Integer cmnId;
     private String cmnCode;
     private String cmnDesc;
     @Column(name = "cmnType")
     private String cmnType;
     private String status;
     private Date createdDate;
     private String updatedby;
     private String createdby;
     private Date updatedDate;
     private String attribute1;

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public Integer getCmnId() {
        return cmnId;
    }

    public void setCmnId(Integer cmnId) {
        this.cmnId = cmnId;
    }

    public String getCmnCode() {
        return cmnCode;
    }

    public void setCmnCode(String cmnCode) {
        this.cmnCode = cmnCode;
    }

    public String getCmnDesc() {
        return cmnDesc;
    }

    public void setCmnDesc(String cmnDesc) {
        this.cmnDesc = cmnDesc;
    }

    public String getCmnType() {
        return cmnType;
    }

    public void setCmnType(String cmnType) {
        this.cmnType = cmnType;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

  

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
  
}
