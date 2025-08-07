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
 * @author HarshG
 */
@Entity
@Table(name = "SS_SERVICE_GATE_PASS_INFO_DMS")
public class SsServiceGpInfoDms implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "GATE_PASS_ID")
    private Integer gatePassId;

    @Column(name = "DATE_OF_DELIVERY")
    private Date dateOfDelivery;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "VEHICLE_NO")
    private String vehicleNo;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "SERVICE_REQUEST_NUMBER")
    private String serviceRequestNumber;

    @Column(name = "REF_PERSON")
    private String refPerson;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "SERVICE_ADVISOR")
    private String serviceAdvisor;

    @Column(name = "P_DEL_TYPE")
    private String pDelType;

    @Column(name = "DRIVER_NAME")
    private String driverName;

    @Column(name = "AUTO_CARD_NUM")
    private String autoCardNum;

    @Column(name = "LOYALTY_POINTS")
    private String loyaltyPoints;

    @Column(name = "AUTOCARD_AMOUNT")
    private Double autocardAmount;

    @Column(name = "PHYSICAL_LOCATION")
    private String physicalLocation;

    @Column(name = "PHY_LOCATION_NAME")
    private String phyLocationName;

    @Column(name = "FINAL_DEL")
    private String finalDel;

    @Column(name = "FINAL_DEL_DATE")
    private Date finalDelDate;

    @Column(name = "FINAL_DEL_PERSON")
    private String finalDelPerson;

    @Column(name = "PHYGATEPASS")
    private String phygatepass;

    @Column(name = "DELIVERY_BY")
    private String deliveryBy;

//    @Column(name = "CCP_AMT")
//    private Double ccpAmt;
//
//    @Column(name = "EM_AMT")
//    private Double emAmt;
//
//    @Column(name = "MCP_AMT")
//    private Double mcpAmt;
//
//    @Column(name = "INS_AMT")
//    private Double insAmt;
//
//    @Column(name = "OS_AMT")
//    private Double osAmt;
//
//    @Column(name = "ADVANCE_AMT")
//    private Double advanceAmt;
//
//    @Column(name = "TOT_AMT")
//    private Double totAmt;

    public SsServiceGpInfoDms() {
    }

    public Integer getGatePassId() {
        return gatePassId;
    }

    public void setGatePassId(Integer gatePassId) {
        this.gatePassId = gatePassId;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public String getRefPerson() {
        return refPerson;
    }

    public void setRefPerson(String refPerson) {
        this.refPerson = refPerson;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public String getpDelType() {
        return pDelType;
    }

    public void setpDelType(String pDelType) {
        this.pDelType = pDelType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAutoCardNum() {
        return autoCardNum;
    }

    public void setAutoCardNum(String autoCardNum) {
        this.autoCardNum = autoCardNum;
    }

    public String getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(String loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Double getAutocardAmount() {
        return autocardAmount;
    }

    public void setAutocardAmount(Double autocardAmount) {
        this.autocardAmount = autocardAmount;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getPhyLocationName() {
        return phyLocationName;
    }

    public void setPhyLocationName(String phyLocationName) {
        this.phyLocationName = phyLocationName;
    }

    public String getFinalDel() {
        return finalDel;
    }

    public void setFinalDel(String finalDel) {
        this.finalDel = finalDel;
    }

    public Date getFinalDelDate() {
        return finalDelDate;
    }

    public void setFinalDelDate(Date finalDelDate) {
        this.finalDelDate = finalDelDate;
    }

    public String getFinalDelPerson() {
        return finalDelPerson;
    }

    public void setFinalDelPerson(String finalDelPerson) {
        this.finalDelPerson = finalDelPerson;
    }

    public String getPhygatepass() {
        return phygatepass;
    }

    public void setPhygatepass(String phygatepass) {
        this.phygatepass = phygatepass;
    }

    public String getDeliveryBy() {
        return deliveryBy;
    }

    public void setDeliveryBy(String deliveryBy) {
        this.deliveryBy = deliveryBy;
    }

//    public Double getCcpAmt() {
//        return ccpAmt;
//    }
//
//    public void setCcpAmt(Double ccpAmt) {
//        this.ccpAmt = ccpAmt;
//    }
//
//    public Double getEmAmt() {
//        return emAmt;
//    }
//
//    public void setEmAmt(Double emAmt) {
//        this.emAmt = emAmt;
//    }
//
//    public Double getMcpAmt() {
//        return mcpAmt;
//    }
//
//    public void setMcpAmt(Double mcpAmt) {
//        this.mcpAmt = mcpAmt;
//    }
//
//    public Double getInsAmt() {
//        return insAmt;
//    }
//
//    public void setInsAmt(Double insAmt) {
//        this.insAmt = insAmt;
//    }
//
//    public Double getOsAmt() {
//        return osAmt;
//    }
//
//    public void setOsAmt(Double osAmt) {
//        this.osAmt = osAmt;
//    }
//
//    public Double getAdvanceAmt() {
//        return advanceAmt;
//    }
//
//    public void setAdvanceAmt(Double advanceAmt) {
//        this.advanceAmt = advanceAmt;
//    }
//
//    public Double getTotAmt() {
//        return totAmt;
//    }
//
//    public void setTotAmt(Double totAmt) {
//        this.totAmt = totAmt;
//    }
    
    

}
