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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IT-HARSH
 */
@Entity
@Table(name = "SS_DMS_STOCK_TV")
public class SsDmsStockTruevalue implements Serializable {

    @Column(name = "DEALER_LOCATION")
    private String dealerLocation;
    @Column(name = "BUYING_LOCATION")
    private String buyingLocation;
    @Column(name = "MODEL_DESC")
    private String modelDesc;
    @Column(name = "FUEL_DESC")
    private String fuelDesc;
    @Column(name = "VARIANT_DESC")
    private String variantDesc;
    @Column(name = "VARIANT_CODE")
    private String variantCode;
    @Column(name = "COLOUR")
    private String colour;
    @Column(name = "COLOUR_CODE")
    private String colourCode;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Id
    @Basic(optional = false)
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "REG_DATE")
    private Date regDate;
    @Column(name = "MFG_YEAR")
    private Integer mfgYear;
    @Column(name = "KMS")
    private Integer kms;
    @Column(name = "OWNERS")
    private String owners;
    @Column(name = "INSURANCE_DETAILS")
    private String insuranceDetails;
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Column(name = "VEH_STATUS")
    private String vehStatus;
    @Column(name = "MUL_INV_NO")
    private String mulInvNo;
    @Column(name = "MUL_INV_DT")
    private Date mulInvDt;
    @Column(name = "GRN_NO")
    private String grnNo;
    @Column(name = "GRN_DATE")
    private Date grnDate;
    @Column(name = "RF_DATE")
    private Date rfDate;
    @Column(name = "AGEING")
    private String ageing;
    @Column(name = "PURCHASE_PRICE")
    private Integer purchasePrice;
    @Column(name = "RF_AMOUNT")
    private Integer rfAmount;
    @Column(name = "INTEREST_AMOUNT_ON_INVTRY")
    private Integer interestAmountOnInvtry;
    @Column(name = "SPOT_DISCOUNT")
    private Integer spotDiscount;
    @Column(name = "DISCOUNT")
    private Integer discount;
    @Column(name = "ALLOTMENT_NO")
    private String allotmentNo;
    @Column(name = "ALLOTMENT_DT")
    private Date allotmentDt;
    @Column(name = "ORDER_NUMBER")
    private String orderNumber;
    @Column(name = "CUSTOMER_CODE")
    private String customerCode;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "DSE_NAME")
    private String dseName;
    @Column(name = "FINANCE_CONTROL_NUMBER")
    private String financeControlNumber;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "NET_BASIC")
    private Integer netBasic;
    @Column(name = "PHY_LOCATION_TYPE")
    private String phyLocationType;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "FROM_DATE")
    private Date fromDate;
    @Column(name = "TO_DATE")
    private Date toDate;
    @Column(name = "RESERVE_YN")
    private String reserveYN;
    @Column(name = "RESERVE_FROM_DATE")
    private Date reserveFromDate;
    @Column(name = "RESERVE_TO_DATE")
    private Date reserveToDate;
    @Column(name = "REMARKS1")
    private String remarks1;
    @Column(name = "OPERATING_UNIT")
    private Integer ou;

    @Column(name = "CREATIONDATE")
    private Date creationDate;
    @Column(name = "UPDATIONDATE")
    private Date updationDate;
    @Column(name = "CREATEDBY")
    private String createdBy;
    @Column(name = "UPDATEDBY")
    private String updatedBy;

    public SsDmsStockTruevalue() {
    }

    public String getDealerLocation() {
        return dealerLocation;
    }

    public void setDealerLocation(String dealerLocation) {
        this.dealerLocation = dealerLocation;
    }

    public String getBuyingLocation() {
        return buyingLocation;
    }

    public void setBuyingLocation(String buyingLocation) {
        this.buyingLocation = buyingLocation;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getFuelDesc() {
        return fuelDesc;
    }

    public void setFuelDesc(String fuelDesc) {
        this.fuelDesc = fuelDesc;
    }

    public String getVariantDesc() {
        return variantDesc;
    }

    public void setVariantDesc(String variantDesc) {
        this.variantDesc = variantDesc;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public void setVariantCode(String variantCode) {
        this.variantCode = variantCode;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getMfgYear() {
        return mfgYear;
    }

    public void setMfgYear(Integer mfgYear) {
        this.mfgYear = mfgYear;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(String vehStatus) {
        this.vehStatus = vehStatus;
    }

    public String getMulInvNo() {
        return mulInvNo;
    }

    public void setMulInvNo(String mulInvNo) {
        this.mulInvNo = mulInvNo;
    }

    public Date getMulInvDt() {
        return mulInvDt;
    }

    public void setMulInvDt(Date mulInvDt) {
        this.mulInvDt = mulInvDt;
    }

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public Date getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(Date grnDate) {
        this.grnDate = grnDate;
    }

    public Date getRfDate() {
        return rfDate;
    }

    public void setRfDate(Date rfDate) {
        this.rfDate = rfDate;
    }

    public String getAgeing() {
        return ageing;
    }

    public void setAgeing(String ageing) {
        this.ageing = ageing;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getRfAmount() {
        return rfAmount;
    }

    public void setRfAmount(Integer rfAmount) {
        this.rfAmount = rfAmount;
    }

    public Integer getInterestAmountOnInvtry() {
        return interestAmountOnInvtry;
    }

    public void setInterestAmountOnInvtry(Integer interestAmountOnInvtry) {
        this.interestAmountOnInvtry = interestAmountOnInvtry;
    }

    public Integer getSpotDiscount() {
        return spotDiscount;
    }

    public void setSpotDiscount(Integer spotDiscount) {
        this.spotDiscount = spotDiscount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getAllotmentNo() {
        return allotmentNo;
    }

    public void setAllotmentNo(String allotmentNo) {
        this.allotmentNo = allotmentNo;
    }

    public Date getAllotmentDt() {
        return allotmentDt;
    }

    public void setAllotmentDt(Date allotmentDt) {
        this.allotmentDt = allotmentDt;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDseName() {
        return dseName;
    }

    public void setDseName(String dseName) {
        this.dseName = dseName;
    }

    public String getFinanceControlNumber() {
        return financeControlNumber;
    }

    public void setFinanceControlNumber(String financeControlNumber) {
        this.financeControlNumber = financeControlNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getNetBasic() {
        return netBasic;
    }

    public void setNetBasic(Integer netBasic) {
        this.netBasic = netBasic;
    }

    public String getPhyLocationType() {
        return phyLocationType;
    }

    public void setPhyLocationType(String phyLocationType) {
        this.phyLocationType = phyLocationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReserveYN() {
        return reserveYN;
    }

    public void setReserveYN(String reserveYN) {
        this.reserveYN = reserveYN;
    }

    public Date getReserveFromDate() {
        return reserveFromDate;
    }

    public void setReserveFromDate(Date reserveFromDate) {
        this.reserveFromDate = reserveFromDate;
    }

    public Date getReserveToDate() {
        return reserveToDate;
    }

    public void setReserveToDate(Date reserveToDate) {
        this.reserveToDate = reserveToDate;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }



    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Date updationDate) {
        this.updationDate = updationDate;
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

    public Integer getOu() {
        return ou;
    }

    public void setOu(Integer ou) {
        this.ou = ou;
    }

}
