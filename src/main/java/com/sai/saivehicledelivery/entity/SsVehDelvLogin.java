/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Harsh Gawde
 */
@Entity
@Table(name = "SS_VEH_DELV_LOGIN")
public class SsVehDelvLogin implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "LOGIN_ID")
    private Integer loginId;
    @Column(name = "LOGIN_NAME")
    private String loginName ;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    @Column(name = "ATTRIBUTE1")
    private String attribute1;
    @Column(name = "ATTRIBUTE2")
    private String attribute2;
    @Column(name = "ATTRIBUTE3")
    private String attribute3;
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Column(name = "ATTRIBUTE5")
    private String attribute5;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "EMAIL_ID")
    private String emailId;
    @Column(name = "LOC_ID")
    private Integer locId;
    @Column(name = "DMS_LOC")
    private String dmsLoc;
    @Column(name = "OPERATING_UNIT")
    private Integer ouId;
    @Column(name = "LOCATION_NAME")
    private String locationName;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "USER_CONTACT")
    private String userContact;

    public SsVehDelvLogin() {

    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public String getDmsLoc() {
        return dmsLoc;
    }

    public void setDmsLoc(String dmsLoc) {
        this.dmsLoc = dmsLoc;
    }

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

  
    
    
}
