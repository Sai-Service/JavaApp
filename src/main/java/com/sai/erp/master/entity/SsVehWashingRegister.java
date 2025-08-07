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
 * @author HarshG
 */
@Entity
@Table(name = "SS_VEH_WASHING_REGISTER")
public class SsVehWashingRegister implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WASH_ID")
    private Integer washId;
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "VEH_WASH_NO")
    private String vehWashNo;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "SERVICE_ADVISOR")
    private String serviceAdvisor;
    @Column(name = "PROMISED_TIME")
    private Date promisedTime;
    @Column(name = "WASHING_SUPERVISOR")
    private String washingSupervisor;
    @Column(name = "FIRST_STAGE")
    private String firstStage;
    @Column(name = "FS_IN_TIME")
    private Date fsInTime;
    @Column(name = "AIR_BLOW_STN")
    private String airBlowStn;
    @Column(name = "UNDERBODY_STN")
    private String underbodyStn;
    @Column(name = "ENGINE_ROOM_STN")
    private String engineRoomStn;
    @Column(name = "FS_OUT_TIME")
    private Date fsOutTime;
    @Column(name = "SECOND_STAGE")
    private String secondStage;
    @Column(name = "SS_IN_TIME")
    private Date ssInTime;
    @Column(name = "LOOSE_ITEMS_STN")
    private String looseItemsStn;
    @Column(name = "VEH_INTERIOR_STN")
    private String vehInteriorStn;
    @Column(name = "SS_OUT_TIME")
    private Date ssOutTime;
    @Column(name = "DRY_WASH_STAGE")
    private String dryWashStage;
    @Column(name = "DS_IN_TIME")
    private Date dsInTime;
    @Column(name = "VEH_EXTERIOR_STN")
    private String vehExteriorStn;
    @Column(name = "GLASS_POLISH_STN")
    private String glassPolishStn;
    @Column(name = "DS_OUT_TIME")
    private Date dsOutTime;
    @Column(name = "LOC_ID")
    private Integer locId;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "OU_ID")
    private Integer ouId;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATION_DATE")
    private Date updationDate;
    @Column(name = "STATUS")
    private String status;
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

    public SsVehWashingRegister() {

    }

    public Integer getWashId() {
        return washId;
    }

    public void setWashId(Integer washId) {
        this.washId = washId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }


    public String getVehWashNo() {
        return vehWashNo;
    }

    public void setVehWashNo(String vehWashNo) {
        this.vehWashNo = vehWashNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

   

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public Date getPromisedTime() {
        return promisedTime;
    }

    public void setPromisedTime(Date promisedTime) {
        this.promisedTime = promisedTime;
    }

    public String getWashingSupervisor() {
        return washingSupervisor;
    }

    public void setWashingSupervisor(String washingSupervisor) {
        this.washingSupervisor = washingSupervisor;
    }

    public String getFirstStage() {
        return firstStage;
    }

    public void setFirstStage(String firstStage) {
        this.firstStage = firstStage;
    }

    public Date getFsInTime() {
        return fsInTime;
    }

    public void setFsInTime(Date fsInTime) {
        this.fsInTime = fsInTime;
    }

    public String getAirBlowStn() {
        return airBlowStn;
    }

    public void setAirBlowStn(String airBlowStn) {
        this.airBlowStn = airBlowStn;
    }

    public String getUnderbodyStn() {
        return underbodyStn;
    }

    public void setUnderbodyStn(String underbodyStn) {
        this.underbodyStn = underbodyStn;
    }

    public String getEngineRoomStn() {
        return engineRoomStn;
    }

    public void setEngineRoomStn(String engineRoomStn) {
        this.engineRoomStn = engineRoomStn;
    }

    public Date getFsOutTime() {
        return fsOutTime;
    }

    public void setFsOutTime(Date fsOutTime) {
        this.fsOutTime = fsOutTime;
    }

    public String getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(String secondStage) {
        this.secondStage = secondStage;
    }

    public Date getSsInTime() {
        return ssInTime;
    }

    public void setSsInTime(Date ssInTime) {
        this.ssInTime = ssInTime;
    }

    public String getLooseItemsStn() {
        return looseItemsStn;
    }

    public void setLooseItemsStn(String looseItemsStn) {
        this.looseItemsStn = looseItemsStn;
    }

    public String getVehInteriorStn() {
        return vehInteriorStn;
    }

    public void setVehInteriorStn(String vehInteriorStn) {
        this.vehInteriorStn = vehInteriorStn;
    }

    public Date getSsOutTime() {
        return ssOutTime;
    }

    public void setSsOutTime(Date ssOutTime) {
        this.ssOutTime = ssOutTime;
    }

    public String getDryWashStage() {
        return dryWashStage;
    }

    public void setDryWashStage(String dryWashStage) {
        this.dryWashStage = dryWashStage;
    }

    public Date getDsInTime() {
        return dsInTime;
    }

    public void setDsInTime(Date dsInTime) {
        this.dsInTime = dsInTime;
    }

    public String getVehExteriorStn() {
        return vehExteriorStn;
    }

    public void setVehExteriorStn(String vehExteriorStn) {
        this.vehExteriorStn = vehExteriorStn;
    }

    public String getGlassPolishStn() {
        return glassPolishStn;
    }

    public void setGlassPolishStn(String glassPolishStn) {
        this.glassPolishStn = glassPolishStn;
    }

    public Date getDsOutTime() {
        return dsOutTime;
    }

    public void setDsOutTime(Date dsOutTime) {
        this.dsOutTime = dsOutTime;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOuId() {
        return ouId;
    }

    public void setOuId(Integer ouId) {
        this.ouId = ouId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
