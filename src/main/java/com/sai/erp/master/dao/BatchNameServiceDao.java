/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.dto.srBatchNameDto;
import com.sai.erp.master.dto.tvBatchNameDto;
import com.sai.erp.master.entity.BatchNameService;
import com.sai.erp.master.entity.BatchNameTrueValue;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author IT-HARSH
 */
public interface BatchNameServiceDao extends JpaRepository<BatchNameService, Long>{
 
   
    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_SERVICE where locationId=?1 and createdBy=?2 and batchName is not null and batchStatus='open'", nativeQuery = true)
    public List<Map> getBatchByLocation(Integer locationId, String createdBy);

    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY a.UPDATIONDATE) AS srno, a.ID,a.reg_no, nvl(a.VIN,'-') VIN, nvl(a.CHASSIS_NO,'-') CHASSIS_NO,\n" +
"                a.ENGINE_NO,a.JOBCARDNO,  a.MODEL_DESC, a.COLOUR, a.FUEL_DESC, a.LOCATIONID, a.LOCATIONNAME, a.BATCHNAME, \n" +
"                a.BATCHCREATIONDATE, a.BATCHSTATUS, a.BATCHCODEENDDATE, a.SCANCREATIONTIME, a.CREATIONDATE, a.CREATEDBY, a.UPDATIONDATE, a.UPDATEDBY\n" +
"             FROM SS_BATCHCODE_ANDROID_SERVICE a WHERE batchName =?1", nativeQuery = true)
    public List<Map> getSrBatchDetails(String batchName);

    public Optional<BatchNameService> findByBatchNameAndRegNo(String batchName, String regNo);

    
     @Query(value = "select distinct new com.sai.erp.master.dto.srBatchNameDto(batchName,batchStatus) from BatchNameService where batchName=?1 and locationName=?2 ")
    public List<srBatchNameDto> getBatchStatus(String batchName, String location);
    
    @Query(value = "select distinct to_date(batchCreationDate) from SS_BATCHCODE_ANDROID_SERVICE where batchName=?1", nativeQuery = true)
    public Date getSrBatchDate(String batchName);

    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_SERVICE where batchName=?1", nativeQuery = true)
    public String getSrBatchName(String batchName);

    @Query(value = "select distinct batchStatus from SS_BATCHCODE_ANDROID_SERVICE where locationName=?1", nativeQuery = true)
    public List<Map> getEXBatchStatus( String location);

    @Query(value = "select distinct batchName from SS_BATCHCODE_ANDROID_SERVICE where locationName=?1 and  batchStatus='open' ", nativeQuery = true)
    public List<Map> getSrBatchOpen(String location);
    
}
