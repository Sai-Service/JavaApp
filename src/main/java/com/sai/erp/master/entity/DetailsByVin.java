/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "gd_fdi_trans")
public class DetailsByVin implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private BigDecimal utd;

    private String variant_cd;
    private String model_cd;
    private String chassis_num;
    private String engine_num;
    private String trans_ref_num;
    private String trans_type;
    private String vin;

    public BigDecimal getUtd() {
        return utd;
    }

    public void setUtd(BigDecimal utd) {
        this.utd = utd;
    }

    public DetailsByVin() {
    }

    public String getVariant_cd() {
        return variant_cd;
    }

    public void setVariant_cd(String variant_cd) {
        this.variant_cd = variant_cd;
    }

    public String getModel_cd() {
        return model_cd;
    }

    public void setModel_cd(String model_cd) {
        this.model_cd = model_cd;
    }

    public String getChassis_num() {
        return chassis_num;
    }

    public void setChassis_num(String chassis_num) {
        this.chassis_num = chassis_num;
    }

    public String getEngine_num() {
        return engine_num;
    }

    public void setEngine_num(String engine_num) {
        this.engine_num = engine_num;
    }

    public String getTrans_ref_num() {
        return trans_ref_num;
    }

    public void setTrans_ref_num(String trans_ref_num) {
        this.trans_ref_num = trans_ref_num;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

}
