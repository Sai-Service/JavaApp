/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "SS_DMS_INV_STOCK")
public class SsDmsInvStockNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "DEALER_LOCATION")
    private String delearLocation;
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
    private String status;
    @Column(name = "COLOUR_CODE")
    private String colourCode;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Id
    @Basic(optional = false)
    @Column(name = "VIN")
    private String vin;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "KEY_NO")
    private String keyNo;
    @Column(name = "DLR_STORE")
    private String dlrStore;
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
    @Column(name = "GRN_RECD_DATE")
    private Date grnRecdDate;
    @Column(name = "AGEING")
    private String ageing;
    @Column(name = "PURCHASE_PRICE")
    private Integer purchasePrice;
    @Column(name = "RATE_OF_INTEREST")
    private Integer rateOfInterest;
    @Column(name = "INTEREST_AMOUNT_ON_INVTRY")
    private Integer interestAmountOnIvtry;
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
    private Double netBasic;
    @Column(name = "TRANSPORT_AMOUNT")
    private Double transportAmount;
    @Column(name = "SERVICE_CHARGES")
    private Integer serviceCharges;

    @Column(name = "IGST_PAYABLE")
    private Double igstPayable;
    @Column(name = "CGST_PAYABLE")
    private Double cgstPayable;
    @Column(name = "SGST_PAYABLE")
    private Double sgstPayable;
    @Column(name = "CESS_PAYABLE")
    private Double cessPayable;
    @Column(name = "PHY_LOCATION_TYPE")
    private String phyLocationType;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;
    @Column(name = "RESERVE_YN")
    private String reserveYn;
    @Column(name = "RESERVE_FROM_DATE")
    private Date reserveFromDate;
    @Column(name = "RESERVE_TO_DATE")
    private Date reserveToDate;
    @Column(name = "REMARKS1")
    private String remarks1;
    @Column(name = "OPERATING_UNIT")
    private Integer operatingUnit;

    public SsDmsInvStockNew() {
    }

    public String getDelearLocation() {
        return delearLocation;
    }

    public void setDelearLocation(String delearLocation) {
        this.delearLocation = delearLocation;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(Integer rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public Integer getInterestAmountOnIvtry() {
        return interestAmountOnIvtry;
    }

    public void setInterestAmountOnIvtry(Integer interestAmountOnIvtry) {
        this.interestAmountOnIvtry = interestAmountOnIvtry;
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

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getDlrStore() {
        return dlrStore;
    }

    public void setDlrStore(String dlrStore) {
        this.dlrStore = dlrStore;
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

 

    public Date getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(Date grnDate) {
        this.grnDate = grnDate;
    }

    public Date getGrnRecdDate() {
        return grnRecdDate;
    }

    public void setGrnRecdDate(Date grnRecdDate) {
        this.grnRecdDate = grnRecdDate;
    }

    public String getAgeing() {
        return ageing;
    }

    public void setAgeing(String ageing) {
        this.ageing = ageing;
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

    public Double getNetBasic() {
        return netBasic;
    }

    public void setNetBasic(Double netBasic) {
        this.netBasic = netBasic;
    }

    public Double getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(Double transportAmount) {
        this.transportAmount = transportAmount;
    }

    public Integer getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Integer serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public Double getIgstPayable() {
        return igstPayable;
    }

    public void setIgstPayable(Double igstPayable) {
        this.igstPayable = igstPayable;
    }

    public Double getCgstPayable() {
        return cgstPayable;
    }

    public void setCgstPayable(Double cgstPayable) {
        this.cgstPayable = cgstPayable;
    }

    public Double getSgstPayable() {
        return sgstPayable;
    }

    public void setSgstPayable(Double sgstPayable) {
        this.sgstPayable = sgstPayable;
    }

    public Double getCessPayable() {
        return cessPayable;
    }

    public void setCessPayable(Double cessPayable) {
        this.cessPayable = cessPayable;
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

    public String getReserveYn() {
        return reserveYn;
    }

    public void setReserveYn(String reserveYn) {
        this.reserveYn = reserveYn;
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

    public Integer getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(Integer operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    
    
}
