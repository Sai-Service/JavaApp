/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.dto.tvBatchNameDto;
import com.sai.erp.master.dto.batchName1Dto;
import com.sai.erp.master.entity.BatchName;
import com.sai.erp.master.entity.BatchNameTrueValue;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IT-HARSH
 */
public interface BatchNameTrueValueDao extends JpaRepository<BatchNameTrueValue, Long>{

    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_TV where locationId=?1 and createdBy=?2 and batchName is not null and batchStatus='open'", nativeQuery = true)
    public List<Map> getBatchByLocation(Integer locationId, String createdBy);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY a.UPDATIONDATE) AS srno, a.ID,a.reg_no, nvl(a.VIN,'-') VIN, nvl(a.CHASSIS_NO,'-') CHASSIS_NO,\n"
            + "    a.ENGINE_NO, a.MODEL_DESC, a.COLOUR, a.FUEL_DESC, a.LOCATIONID, a.LOCATIONNAME, a.BATCHNAME, \n"
            + "    a.BATCHCREATIONDATE, a.BATCHSTATUS, a.BATCHCODEENDDATE, a.SCANCREATIONTIME, a.CREATIONDATE, a.CREATEDBY, a.UPDATIONDATE, a.UPDATEDBY\n"
            + " FROM SS_BATCHCODE_ANDROID_TV a WHERE batchName =?1", nativeQuery = true)
    public List<Map> getTvBatchDetails(String batchName);

    public Optional<BatchNameTrueValue> findByBatchNameAndRegNo(String batchName, String regNo);

    
     @Query(value = "select distinct new com.sai.erp.master.dto.tvBatchNameDto(batchName,batchStatus) from BatchNameTrueValue where batchName=?1 and locationName=?2 ")
    public List<tvBatchNameDto> getBatchStatus(String batchName, String location);
    
    @Query(value = "select distinct trunc(to_date(batchCreationDate)) from SS_BATCHCODE_ANDROID_TV where batchName=?1", nativeQuery = true)
    public Date getTvBatchDate(String batchName);

    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_TV where batchName=?1", nativeQuery = true)
    public String getTvBatchName(String batchName);

    @Query(value = "select distinct batchStatus from SS_BATCHCODE_ANDROID_TV where locationName=?1", nativeQuery = true)
    public List<Map> getEXBatchStatus( String location);

    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_TV where locationName=?1 and  batchStatus='open' ", nativeQuery = true)
    public List<Map> getTvBatchOpen(String location);
    
    

    

}
