/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Lenovo
 */
@Immutable
@Entity
@Table(name = "ORG_ORGANIZATION_DEFINITIONS")
public class OrgDefinition implements Serializable{
    
       @Id
    private Long id = 1L;

  
    
    @NotNull
    private String organization_name;
    
    @NotNull
    private Integer organization_id;
    private Integer operating_unit;
    
    public OrgDefinition() {
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getOperating_unit() {
        return operating_unit;
    }

    public void setOperating_unit(Integer operating_unit) {
        this.operating_unit = operating_unit;
    }
      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
