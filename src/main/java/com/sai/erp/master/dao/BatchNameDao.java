/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.dto.batchName1Dto;
import com.sai.erp.master.entity.BatchName;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface BatchNameDao extends JpaRepository<BatchName, Long> {

    public List<BatchName> findByBatchName(String batchName);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY a.UPDATIONDATE) AS srno, a.ID, a.VIN, a.CHASSIS_NO, a.ENGIN_NO, a.MODEL_DESC, a.COLOUR, a.FUEL_DESC, a.LOCATIONID, a.LOCATIONNAME, a.BATCHNAME, a.BATCHCREATIONDATE, a.BATCHSTATUS, a.BATCHCODEENDDATE, \n" +
" a.SCANCREATIONTIME, a.CREATIONDATE, a.CREATEDBY, a.UPDATIONDATE, a.UPDATEDBY\n" +
" FROM ss_batchCode_android a WHERE batchName =?1", nativeQuery = true)
    public List<Map> getBatchDetails(String batchName);

    public Optional<BatchName> findByBatchNameAndVin(String batchName, String vin);

    @Query(value = "select distinct batchName from ss_batchCode_android where locationId=?1 and createdBy=?2 and batchName is not null and batchStatus='open'", nativeQuery = true)
    public List<Map> getBatchByFromLocation(Integer locationId, String createdBy);

    public Optional<BatchName> findByVin(String vin);

    @Query(value = "select distinct trunc(to_date(batchCreationDate)) from ss_batchCode_android where batchName=?1 ", nativeQuery = true)
    public Date getBatchDate(String batchName);

    @Query(value = "select distinct batchName from ss_batchCode_android where batchName=?1 ", nativeQuery = true)
    public String getBatchName(String batchName);

//     @Query(value = "select distinct batchStatus from ss_batchCode_android where batchName=?1 and locationName=?2 ", nativeQuery = true)
//    public String getBatchStatus(String batchName,String location);
    @Query(value = "select distinct new com.sai.erp.master.dto.batchName1Dto(batchName,batchStatus) from BatchName where batchName=?1 and locationName=?2 ")
    public List<batchName1Dto> getBatchStatus(String batchName, String location);

    @Query(value = "select distinct batchName from ss_batchCode_android where locationName=?1 and  batchStatus='open' ", nativeQuery = true)
    public List<Map> getBatchOpen(String location);

    @Query(value = "select distinct batchStatus from ss_batchCode_android where locationName=?1 ", nativeQuery = true)
    public List<Map> getEXBatchStatus(String locationName);

    public Optional<BatchName> findByBatchCreationDateAndVin(Date currentDate, String vin);

    public Optional<BatchName> findByBatchCreationDateAndVinAndBatchStatus(Date batchCreationDate, String vin, String batchStatus);
}
