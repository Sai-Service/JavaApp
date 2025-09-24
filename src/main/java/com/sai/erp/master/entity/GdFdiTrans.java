/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HarshG
 */
@Entity
@Table(name = "gd_fdi_trans")
public class GdFdiTrans implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private BigDecimal utd;
    @Column(name = "PARENT_GROUP")
    private String parentGroup;
    @Column(name = "DEALER_MAP_CD")
    private Integer dealerMapCd;
    @Column(name = "LOC_CD")
    private String locCd;
    @Column(name = "COMP_FA")
    private String compFa;
    @Column(name = "MUL_DEALER_CD")
    private String mulDealerCd;
    @Column(name = "OUTLET_CD")
    private String outletCd;
    @Column(name = "TRANS_TYPE")
    private String transType;
    @Column(name = "TRANS_DATE")
    private String transDate;
    @Column(name = "TRANS_ID")
    private String transId;
    @Column(name = "TRANS_REF_NUM")
    private String transRefNum;
    @Column(name = "TRANS_REF_DATE")
    private String transRefDate;
    @Column(name = "TRANS_QTY")
    private Integer transQty;
    @Column(name = "TRANS_SEGMENT")
    private String transSegment;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "MODEL_CD")
    private String modelCd;
    @Column(name = "VARIANT_CD")
    private String variantCd;
    @Column(name = "ECOLOR_CD")
    private String ecolorCd;
    @Column(name = "BASIC_PRICE")
    private Float basicPrice;
    @Column(name = "DISCOUNT")
    private Float discount;
    @Column(name = "TAXABLE_VALUE")
    private Float taxableValue;
    @Column(name = "SERVICE_AMOUNT")
    private Float serviceAmount;
    @Column(name = "GST_NO")
    private String gstNo;
    @Column(name = "PLACE_OF_SUPPLY")
    private String placeOfSupply;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "EXECUTIVE")
    private String executive;
    @Column(name = "TEAM_HEAD")
    private String teamHead;
    @Column(name = "FINC_NAME")
    private String fincName;
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "DEPOSIT_BANK")
    private String depositBank;
    @Column(name = "PAYMENT_FOR")
    private String paymentFor;
    @Column(name = "GE1")
    private String ge1;
    @Column(name = "GE2")
    private String ge2;
    @Column(name = "GE3")
    private String ge3;
    @Column(name = "GE4")
    private String ge4;
    @Column(name = "GE5")
    private String ge5;
    @Column(name = "GE6")
    private String ge6;
    @Column(name = "GE7")
    private String ge7;
    @Column(name = "GE8")
    private String ge8;
    @Column(name = "GE9")
    private String ge9;
    @Column(name = "GE10")
    private String ge10;
    @Column(name = "GE11")
    private String ge11;
    @Column(name = "GE12")
    private String ge12;
    @Column(name = "GE13")
    private String ge13;
    @Column(name = "GE14")
    private String ge14;
    @Column(name = "GE15")
    private String ge15;
    @Column(name = "GD_FDI_TRANS_ID", nullable = false)
    private Integer gdFdiTransId;
    @Column(name = "CREATED_DATE")
    private String createdDate;
    @Column(name = "ENGINE_NUM")
    private String engineNum;
    @Column(name = "CHASSIS_NUM")
    private String chassisNum;
    @Column(name = "CUST_ID")
    private String custId;
    @Column(name = "HSN_NO")
    private String hsnNo;
    @Column(name = "AX_FLAG")
    private String axFlag;

    public GdFdiTrans() {
    }


    public BigDecimal getUtd() {
        return utd;
    }

    public void setUtd(BigDecimal utd) {
        this.utd = utd;
    }

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup;
    }

    public Integer getDealerMapCd() {
        return dealerMapCd;
    }

    public void setDealerMapCd(Integer dealerMapCd) {
        this.dealerMapCd = dealerMapCd;
    }

    public String getLocCd() {
        return locCd;
    }

    public void setLocCd(String locCd) {
        this.locCd = locCd;
    }

    public String getCompFa() {
        return compFa;
    }

    public void setCompFa(String compFa) {
        this.compFa = compFa;
    }

    public String getMulDealerCd() {
        return mulDealerCd;
    }

    public void setMulDealerCd(String mulDealerCd) {
        this.mulDealerCd = mulDealerCd;
    }

    public String getOutletCd() {
        return outletCd;
    }

    public void setOutletCd(String outletCd) {
        this.outletCd = outletCd;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransRefNum() {
        return transRefNum;
    }

    public void setTransRefNum(String transRefNum) {
        this.transRefNum = transRefNum;
    }

    public String getTransRefDate() {
        return transRefDate;
    }

    public void setTransRefDate(String transRefDate) {
        this.transRefDate = transRefDate;
    }

    public Integer getTransQty() {
        return transQty;
    }

    public void setTransQty(Integer transQty) {
        this.transQty = transQty;
    }

    public String getTransSegment() {
        return transSegment;
    }

    public void setTransSegment(String transSegment) {
        this.transSegment = transSegment;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelCd() {
        return modelCd;
    }

    public void setModelCd(String modelCd) {
        this.modelCd = modelCd;
    }

    public String getVariantCd() {
        return variantCd;
    }

    public void setVariantCd(String variantCd) {
        this.variantCd = variantCd;
    }

    public String getEcolorCd() {
        return ecolorCd;
    }

    public void setEcolorCd(String ecolorCd) {
        this.ecolorCd = ecolorCd;
    }

    public Float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(Float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(Float taxableValue) {
        this.taxableValue = taxableValue;
    }

    public Float getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Float serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPlaceOfSupply() {
        return placeOfSupply;
    }

    public void setPlaceOfSupply(String placeOfSupply) {
        this.placeOfSupply = placeOfSupply;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getExecutive() {
        return executive;
    }

    public void setExecutive(String executive) {
        this.executive = executive;
    }

    public String getTeamHead() {
        return teamHead;
    }

    public void setTeamHead(String teamHead) {
        this.teamHead = teamHead;
    }

    public String getFincName() {
        return fincName;
    }

    public void setFincName(String fincName) {
        this.fincName = fincName;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }

    public String getGe1() {
        return ge1;
    }

    public void setGe1(String ge1) {
        this.ge1 = ge1;
    }

    public String getGe2() {
        return ge2;
    }

    public void setGe2(String ge2) {
        this.ge2 = ge2;
    }

    public String getGe3() {
        return ge3;
    }

    public void setGe3(String ge3) {
        this.ge3 = ge3;
    }

    public String getGe4() {
        return ge4;
    }

    public void setGe4(String ge4) {
        this.ge4 = ge4;
    }

    public String getGe5() {
        return ge5;
    }

    public void setGe5(String ge5) {
        this.ge5 = ge5;
    }

    public String getGe6() {
        return ge6;
    }

    public void setGe6(String ge6) {
        this.ge6 = ge6;
    }

    public String getGe7() {
        return ge7;
    }

    public void setGe7(String ge7) {
        this.ge7 = ge7;
    }

    public String getGe8() {
        return ge8;
    }

    public void setGe8(String ge8) {
        this.ge8 = ge8;
    }

    public String getGe9() {
        return ge9;
    }

    public void setGe9(String ge9) {
        this.ge9 = ge9;
    }

    public String getGe10() {
        return ge10;
    }

    public void setGe10(String ge10) {
        this.ge10 = ge10;
    }

    public String getGe11() {
        return ge11;
    }

    public void setGe11(String ge11) {
        this.ge11 = ge11;
    }

    public String getGe12() {
        return ge12;
    }

    public void setGe12(String ge12) {
        this.ge12 = ge12;
    }

    public String getGe13() {
        return ge13;
    }

    public void setGe13(String ge13) {
        this.ge13 = ge13;
    }

    public String getGe14() {
        return ge14;
    }

    public void setGe14(String ge14) {
        this.ge14 = ge14;
    }

    public String getGe15() {
        return ge15;
    }

    public void setGe15(String ge15) {
        this.ge15 = ge15;
    }

    public Integer getGdFdiTransId() {
        return gdFdiTransId;
    }

    public void setGdFdiTransId(Integer gdFdiTransId) {
        this.gdFdiTransId = gdFdiTransId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getChassisNum() {
        return chassisNum;
    }

    public void setChassisNum(String chassisNum) {
        this.chassisNum = chassisNum;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getHsnNo() {
        return hsnNo;
    }

    public void setHsnNo(String hsnNo) {
        this.hsnNo = hsnNo;
    }

    public String getAxFlag() {
        return axFlag;
    }

    public void setAxFlag(String axFlag) {
        this.axFlag = axFlag;
    }

}
