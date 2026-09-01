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

    @Query(value
            = " SELECT NVL(J.SRV_ADVISOR, '-')   service_advisor,\n"
            + "       NVL(J.SERVICE_TYPE, '-')  transSegment,\n"
            + "       CASE \n"
            + "           WHEN UPPER(NVL(J.SERVICE_TYPE, 'X')) LIKE '%BANDP%' \n"
            + "           THEN 'BODYSHOP' \n"
            + "           ELSE 'SERVICE' \n"
            + "       END deptAlloted,\n"
            + "       NVL(J.JOB_CARD_NO, '-')    job_card_no,\n"
            + "       NVL(J.JOB_CARD_DATE_TIME, '-') job_card_date_time\n"
            + "FROM (\n"
            + "    SELECT JC.*\n"
            + "    FROM SS_JOBCARDDETAILS_FOR_APP JC\n"
            + "    WHERE UPPER(TRIM(JC.REG_NO)) = UPPER(TRIM(:regNo))\n"
            + "    ORDER BY\n"
            + "        CASE\n"
            + "            -- Format: DD-MM-YYYY HH24:MI\n"
            + "            WHEN REGEXP_LIKE(JC.JOB_CARD_DATE_TIME,\n"
            + "                             '^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}')\n"
            + "            THEN TO_DATE(\n"
            + "                     SUBSTR(JC.JOB_CARD_DATE_TIME, 1, 16)\n"
            + "                     DEFAULT NULL ON CONVERSION ERROR,\n"
            + "                     'DD-MM-YYYY HH24:MI'\n"
            + "                 )\n"
            + "            -- Format: YYYY-MM-DD HH24:MI:SS\n"
            + "            WHEN REGEXP_LIKE(JC.JOB_CARD_DATE_TIME,\n"
            + "                             '^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}')\n"
            + "            THEN TO_DATE(\n"
            + "                     SUBSTR(JC.JOB_CARD_DATE_TIME, 1, 19)\n"
            + "                     DEFAULT NULL ON CONVERSION ERROR,\n"
            + "                     'YYYY-MM-DD HH24:MI:SS'\n"
            + "                 )\n"
            + "            ELSE NULL\n"
            + "        END DESC NULLS LAST,\n"
            + "        JC.ID DESC\n"
            + ") J\n"
            + "WHERE ROWNUM = 1", nativeQuery = true)
    List<Map> getLatestJobCardByRegNo(@Param("regNo") String regNo);
}
