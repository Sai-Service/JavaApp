/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "ss_jobcarddetails_for_app")
public class JobCardDetailsForApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DETAIL_FOR", length = 100)
    private String detailFor;

    @Column(name = "JOB_CARD_NO", length = 50)
    private String jobCardNo;

    @Column(name = "CHECK_IN_DATE", length = 50)
    private String checkInDate;

    @Column(name = "CUSTOMER_NAME", length = 150)
    private String customerName;

    @Column(name = "JOB_CARD_DATE_TIME", length = 50)
    private String jobCardDateTime;

    @Column(name = "PHONE_NO", length = 20)
    private String phoneNo;

    @Column(name = "CUST_CATG", length = 50)
    private String custCatg;

    @Column(name = "REG_NO", length = 30)
    private String regNo;

    @Column(name = "MILEAGE")
    private Long mileage;

    @Column(name = "MODEL", length = 100)
    private String model;

    @Column(name = "CHASSIS", length = 100)
    private String chassis;

    @Column(name = "SERVICE_TYPE", length = 100)
    private String serviceType;

    @Column(name = "SERVICE_GROUP", length = 100)
    private String serviceGroup;

    @Column(name = "SRV_ADVISOR", length = 100)
    private String srvAdvisor;

    @Column(name = "TECHNICIAN", length = 100)
    private String technician;

    @Column(name = "PROMISED_DATE", length = 50)
    private String promisedDate;

    @Column(name = "REVISED_PROMISE_DATE", length = 50)
    private String revisedPromiseDate;

    @Column(name = "READY_DATE_TIME", length = 50)
    private String readyDateTime;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "DAYS", length = 10)
    private String days;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "CREATION_DATE")
    private java.util.Date creationDate;

    @Column(name = "OU_ID")
    private Long ouId;

    @Column(name = "LOCATION", length = 50)
    private String location;

    public JobCardDetailsForApp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetailFor() {
        return detailFor;
    }

    public void setDetailFor(String detailFor) {
        this.detailFor = detailFor;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getJobCardDateTime() {
        return jobCardDateTime;
    }

    public void setJobCardDateTime(String jobCardDateTime) {
        this.jobCardDateTime = jobCardDateTime;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustCatg() {
        return custCatg;
    }

    public void setCustCatg(String custCatg) {
        this.custCatg = custCatg;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public String getSrvAdvisor() {
        return srvAdvisor;
    }

    public void setSrvAdvisor(String srvAdvisor) {
        this.srvAdvisor = srvAdvisor;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getPromisedDate() {
        return promisedDate;
    }

    public void setPromisedDate(String promisedDate) {
        this.promisedDate = promisedDate;
    }

    public String getRevisedPromiseDate() {
        return revisedPromiseDate;
    }

    public void setRevisedPromiseDate(String revisedPromiseDate) {
        this.revisedPromiseDate = revisedPromiseDate;
    }

    public String getReadyDateTime() {
        return readyDateTime;
    }

    public void setReadyDateTime(String readyDateTime) {
        this.readyDateTime = readyDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
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

    public Long getOuId() {
        return ouId;
    }

    public void setOuId(Long ouId) {
        this.ouId = ouId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
