/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SSDmsWsTestTrack;
import com.sai.erp.master.entity.SsDmsWsTestDrive;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author SAGAR PAWAR
 */
public interface SsDmsWsTestTrackDao extends JpaRepository<SSDmsWsTestTrack, Long>{
    
     public Optional<SSDmsWsTestTrack> findByRegNoAndStatus(String regNo,String status);
     
      public Optional<SSDmsWsTestTrack> findByRegNoAndStatusOrderByCreationDateDesc(String regNo,String status);
     
     public List<SSDmsWsTestTrack> getByRegNo(String regNo);
     
     public List<SSDmsWsTestTrack> getByRegNoOrderByTrfIdDesc(String regNo);

    public Optional<SSDmsWsTestTrack> findByTrfId(Integer trfId);
    
@Query(value = "SELECT " +
            "ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, " +
            "TD_ID, REG_NO, ENGINE_NO, VIN, TECHNICIAN_NAME, AUTHORISED_BY, DEPT, OUT_TIME, IN_TIME, FINAL_TIME, LOCATION, LOC_CODE, OU, " +
            "CREATED_BY, CREATION_DATE, ATTRIBUTE1, ATTRIBUTE2, " +
            "TO_CHAR(CREATION_DATE, 'DD-Mon-YYYY HH24:MI:SS') AS FORMATTED_CREATION_DATE, " +
            "UPDATED_BY, UPDATION_DATE, REMARKS, STATUS " +
            "FROM SS_DMS_WS_TEST_TRACK " +
            "WHERE CREATION_DATE BETWEEN TO_DATE(:from_date, 'DD-Mon-YYYY') AND TO_DATE(:to_date, 'DD-Mon-YYYY') + 0.99999 " +
            "AND LOC_CODE = NVL(:loc_code, LOC_CODE) " +
            "AND OU = NVL(:ou, OU)",
            nativeQuery = true)
    List<Map<String, Object>> getTestTrackDatewise(
            @Param("from_date") String fromDate,
            @Param("to_date") String toDate,
            @Param("loc_code") Integer locCode,
            @Param("ou") Integer ou);

    
 

}
