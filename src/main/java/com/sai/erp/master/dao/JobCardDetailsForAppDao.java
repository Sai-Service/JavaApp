/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.JobCardDetailsForApp;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HarshG
 */
public interface JobCardDetailsForAppDao extends JpaRepository<JobCardDetailsForApp, Long> {

    @Query(value =
        " SELECT NVL(J.SRV_ADVISOR,'-')   service_advisor, "
      + "        NVL(J.SERVICE_TYPE,'-')  transSegment, "
      + "        CASE WHEN UPPER(NVL(J.SERVICE_TYPE,'X')) LIKE '%BANDP%' "
      + "             THEN 'BODYSHOP' ELSE 'SERVICE' END deptAlloted, "
      + "        NVL(J.JOB_CARD_NO,'-')   job_card_no, "
      + "        NVL(J.JOB_CARD_DATE_TIME,'-') job_card_date_time "
      + " FROM ( SELECT JC.* "
      + "          FROM SS_JOBCARDDETAILS_FOR_APP JC "
      + "         WHERE UPPER(TRIM(JC.REG_NO)) = UPPER(TRIM(:regNo)) "
      + "      ORDER BY TO_DATE(SUBSTR(JC.JOB_CARD_DATE_TIME,1,16) "
      + "                       DEFAULT NULL ON CONVERSION ERROR, "
      + "                       'DD-MM-YYYY HH24:MI') DESC NULLS LAST, "
      + "               JC.ID DESC ) J "
      + " WHERE ROWNUM = 1", nativeQuery = true)
    List<Map> getLatestJobCardByRegNo(@Param("regNo") String regNo);
}