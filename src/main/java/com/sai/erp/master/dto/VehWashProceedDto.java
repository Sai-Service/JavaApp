/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dto;

import java.util.Date;

/**
 *
 * @author HarshG
 */
public class VehWashProceedDto {

    private String regNo;
    private String chassisNo;
    private String vehWashNo;
    private String model;
    private String serviceAdvisor;
    
    private String washingSupervisor;
    private String firstStage;
    private Date fsInTime;
    private String airBlowStn;
    private String underbodyStn;
    private String engineRoomStn;
    private Date fsOutTime;
    private String secondStage;
    private Date ssInTime;
    private String looseItemsStn;
    private String vehInteriorStn;
    private Date ssOutTime;
    private String dryWashStage;
    private Date dsInTime;
    private String vehExteriorStn;
    private String glassPolishStn;
    private Date dsOutTime;
    private Integer locId;
    private String location;
    private Integer ouId;
    private String createdBy;
    
    private String updatedBy;
    
    private String status;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    public VehWashProceedDto() {

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

   

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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
