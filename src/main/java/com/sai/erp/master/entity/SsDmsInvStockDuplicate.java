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
 * @author Lenovo
 */
@Entity
@Table(name = "ANDROID_VEH_IMAGE")

public class SsDmsInvStockDuplicate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer image_id;
    @Column(name = "chassis_no")
    private String chassisNo;
    private String model_desc;
    private String variant_desc;
    private String fuel_desc;
    private String colour;
    private String vin;
    private String engine_no;
    @Column(name = "VEH_STATUS")
    private String vehStatus;
    private String allotment_no;
    private Date allotment_dt;
    private String order_number;
    private String LOCATION;
    private String dealer_location;
    private String mul_inv_no;
    private Date mul_inv_dt;
    private String grn_no;
    private Date grn_date;
    private Date grn_recd_date;
    private String ageing;
    private String customer_code;
    private String customer_name;
    private String dse_name;
    private String remarks1;
    private String vehImage;
    private String vehImage2;
    private String vehImage3;
    private Date lastUploadDate;
    private String lastUploadedBy;
    private String vehImage4;
    private String vehImage5;

    public SsDmsInvStockDuplicate() {
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getChassis_no() {
        return chassisNo;
    }

    public void setChassis_no(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getModel_desc() {
        return model_desc;
    }

    public void setModel_desc(String model_desc) {
        this.model_desc = model_desc;
    }

    public String getVariant_desc() {
        return variant_desc;
    }

    public void setVariant_desc(String variant_desc) {
        this.variant_desc = variant_desc;
    }

    public String getFuel_desc() {
        return fuel_desc;
    }

    public void setFuel_desc(String fuel_desc) {
        this.fuel_desc = fuel_desc;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(String vehStatus) {
        this.vehStatus = vehStatus;
    }

    public String getAllotment_no() {
        return allotment_no;
    }

    public void setAllotment_no(String allotment_no) {
        this.allotment_no = allotment_no;
    }

    public Date getAllotment_dt() {
        return allotment_dt;
    }

    public void setAllotment_dt(Date allotment_dt) {
        this.allotment_dt = allotment_dt;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getDealer_location() {
        return dealer_location;
    }

    public void setDealer_location(String dealer_location) {
        this.dealer_location = dealer_location;
    }

    public String getMul_inv_no() {
        return mul_inv_no;
    }

    public void setMul_inv_no(String mul_inv_no) {
        this.mul_inv_no = mul_inv_no;
    }

    public Date getMul_inv_dt() {
        return mul_inv_dt;
    }

    public void setMul_inv_dt(Date mul_inv_dt) {
        this.mul_inv_dt = mul_inv_dt;
    }

    public String getGrn_no() {
        return grn_no;
    }

    public void setGrn_no(String grn_no) {
        this.grn_no = grn_no;
    }

    public Date getGrn_date() {
        return grn_date;
    }

    public void setGrn_date(Date grn_date) {
        this.grn_date = grn_date;
    }

    public Date getGrn_recd_date() {
        return grn_recd_date;
    }

    public void setGrn_recd_date(Date grn_recd_date) {
        this.grn_recd_date = grn_recd_date;
    }

    public String getAgeing() {
        return ageing;
    }

    public void setAgeing(String ageing) {
        this.ageing = ageing;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDse_name() {
        return dse_name;
    }

    public void setDse_name(String dse_name) {
        this.dse_name = dse_name;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getVehImage() {
        return vehImage;
    }

    public void setVehImage(String vehImage) {
        this.vehImage = vehImage;
    }

    public String getVehImage2() {
        return vehImage2;
    }

    public void setVehImage2(String vehImage2) {
        this.vehImage2 = vehImage2;
    }

    public String getVehImage3() {
        return vehImage3;
    }

    public void setVehImage3(String vehImage3) {
        this.vehImage3 = vehImage3;
    }

    public Date getLastUploadDate() {
        return lastUploadDate;
    }

    public void setLastUploadDate(Date lastUploadDate) {
        this.lastUploadDate = lastUploadDate;
    }

    public String getLastUploadedBy() {
        return lastUploadedBy;
    }

    public void setLastUploadedBy(String lastUploadedBy) {
        this.lastUploadedBy = lastUploadedBy;
    }

    public String getVehImage4() {
        return vehImage4;
    }

    public void setVehImage4(String vehImage4) {
        this.vehImage4 = vehImage4;
    }

    public String getVehImage5() {
        return vehImage5;
    }

    public void setVehImage5(String vehImage5) {
        this.vehImage5 = vehImage5;
    }

    
}
