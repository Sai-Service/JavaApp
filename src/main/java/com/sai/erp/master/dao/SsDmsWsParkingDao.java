/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsWsParking;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HarshG
 */
public interface SsDmsWsParkingDao extends CrudRepository<SsDmsWsParking, Object> {

    public Optional<SsDmsWsParking> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    @Query(value = "   SELECT NVL(P.REG_NO,'-') REG_NO, NVL(P.CHASSIS_NO,'-') CHASSIS_NO,\n"
            + "             NVL(P.ENGINE_NO,'-') ENGINE_NO, NVL(P.VIN,'-') VIN, NVL(P.DEPT,'-') DEPT, NVL(P.DRIVER_OUT,'-') DRIVER_OUT, NVL(P.OUT_KM,0) OUT_KM, \n"
            + "            NVL(TO_CHAR(P.OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,NVL(P.LOC_ID,0) LOC_ID, NVL(P.OU_ID,0) OU_ID, NVL(P.LOCATION,'-') LOCATION,\n"
            + "             NVL(P.GATE_NO,'-') GATE_NO, NVL(P.GATE_TYPE,'-') GATE_TYPE, NVL(P.REMARKS,'-') REMARKS,NVL(P.CUST_NAME,'-') CUST_NAME , \n"
            + "             NVL(P.ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(P.ATTRIBUTE2,'-') MODEL_DESC, NVL(P.ATTRIBUTE3,'-') deptAlloted,\n"
            + "            NVL(P.PARKING_REASON,'-') PARKING_REASON\n"
            + "            FROM SS_DMS_WS_PARKING P  WHERE \n"
            + "             P.REG_NO =:regNo AND P.IN_TIME IS NULL", nativeQuery = true)
    public List<Map> getVehInParkingDetails(String regNo);

//    @Query(value = " SELECT NVL(P.REG_NO,'-') REG_NO, NVL(P.CHASSIS_NO,'-') CHASSIS_NO, NVL(P.PARKING_DESC,'-') PARKING_DESC,\n"
//            + " NVL(P.ENGINE_NO,'-') ENGINE_NO, NVL(P.VIN,'-') VIN, NVL(P.DEPT,'-') DEPT, NVL(P.DRIVER_IN,'-') DRIVER_IN, NVL(P.IN_KM,0) IN_KM, \n"
//            + "  NVL(TO_CHAR(P.IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,NVL(P.LOC_ID,0) LOC_ID, NVL(P.OU_ID,0) OU_ID, NVL(P.LOCATION,'-') LOCATION,\n"
//            + "  NVL(P.GATE_NO,'-') GATE_NO, NVL(P.GATE_TYPE,'-') GATE_TYPE, NVL(P.REMARKS,'-') REMARKS,NVL(P.CUST_NAME,'-') CUST_NAME , \n"
//            + " NVL(P.ATTRIBUTE2,'-') MODEL_DESC, NVL(P.ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(P.ATTRIBUTE3,'-') deptAlloted, \n"
//            + "  NVL(P.PARKING_REASON,'-') PARKING_REASON\n"
//            + "  FROM SS_DMS_WS_PARKING P  WHERE \n"
//            + "  P.REG_NO =:regNo AND P.OUT_TIME IS NULL", nativeQuery = true)
//    public List<Map> getVehOutParkingDetails(String regNo);
    
    @Query(value = " SELECT NVL(P.REG_NO,'-') REG_NO, NVL(P.CHASSIS_NO,'-') CHASSIS_NO, \n"
        + "        NVL(P.PARKING_DESC,'-') PARKING_DESC, NVL(P.ENGINE_NO,'-') ENGINE_NO, \n"
        + "        NVL(P.VIN,'-') VIN, NVL(P.DEPT,'-') DEPT, NVL(P.DRIVER_IN,'-') DRIVER_IN, \n"
        + "        NVL(P.IN_KM,0) IN_KM, \n"
        + "        NVL(TO_CHAR(P.IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, \n"
        + "        NVL(P.LOC_ID,0) LOC_ID, NVL(P.OU_ID,0) OU_ID, NVL(P.LOCATION,'-') LOCATION, \n"
        + "        NVL(P.GATE_NO,'-') GATE_NO, NVL(P.GATE_TYPE,'-') GATE_TYPE, \n"
        + "        NVL(P.REMARKS,'-') REMARKS, NVL(P.CUST_NAME,'-') CUST_NAME, \n"
        + "        NVL(P.ATTRIBUTE2,'-') MODEL_DESC, NVL(P.ATTRIBUTE1,'-') SERVICE_ADVISOR, \n"
        + "        NVL(P.ATTRIBUTE3,'-') deptAlloted, NVL(P.PARKING_REASON,'-') PARKING_REASON \n"
        + "   FROM ( SELECT * FROM SS_DMS_WS_PARKING \n"
        + "           WHERE REG_NO = :regNo AND OUT_TIME IS NULL \n"
        + "        ORDER BY CREATION_DATE DESC ) P \n"
        + "  WHERE ROWNUM = 1", nativeQuery = true)
public List<Map> getVehOutParkingDetails(@Param("regNo") String regNo);

    public Optional<SsDmsWsParking> findTopByRegNoAndInTimeIsNullAndOutTimeIsNotNullOrderByCreationDateDesc(String regNo);

    public Optional<SsDmsWsParking> findTopByRegNoAndOutTimeIsNullAndInTimeIsNotNullOrderByCreationDateDesc(String regNo);

    //query for parking report...ou and loc and dept wise
//    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, \n"
//            + " NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DEPT,'-') DEPT, NVL(DRIVER_IN,'-') DRIVER_IN,\n"
//            + " NVL(DRIVER_OUT,'-') DRIVER_OUT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
//            + " NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_ID,0) LOC_ID, NVL(OU_ID,0) OU_ID,\n"
//            + " NVL(GATE_NO,'-') GATE_NO, NVL(GATE_TYPE,'-') GATE_TYPE, NVL(REMARKS,'-') REMARKS, NVL(CUST_NAME,'-') CUST_NAME,\n"
//            + "  NVL(PARKING_REASON, '-') PARKING_REASON, NVL(PARKING_DESC,'-') PARKING_DESC, NVL(ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(ATTRIBUTE2,'-') MODEL_DESC,\n"
//            + "  NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
//            + "  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(ATTRIBUTE3,'-') deptAlloted\n"
//            + "  FROM SS_DMS_WS_PARKING WHERE OU_ID=:ouId and LOC_ID=:locId  AND CREATION_DATE BETWEEN :fromDate AND :toDate ", nativeQuery = true)
//    public List<Map> getParkingDetails(Integer ouId, Integer locId, Date fromDate, Date toDate);
    //along with jc details
    @Query(value = " SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY p.CREATION_DATE) AS sr_no,\n"
            + "    NVL(p.REG_NO,'-')            REG_NO,\n"
            + "    NVL(p.CHASSIS_NO,'-')        CHASSIS_NO,\n"
            + "    NVL(p.ENGINE_NO,'-')         ENGINE_NO,\n"
            + "    NVL(p.VIN,'-')               VIN,\n"
            + "    NVL(p.DEPT,'-')               DEPT,\n"
            + "    NVL(p.DRIVER_IN,'-')          DRIVER_IN,\n"
            + "    NVL(p.DRIVER_OUT,'-')         DRIVER_OUT,\n"
            + "    NVL(p.OUT_KM,0)               OUT_KM,\n"
            + "    NVL(TO_CHAR(p.OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-')  OUT_TIME,\n"
            + "    NVL(p.IN_KM,0)                 IN_KM,\n"
            + "    NVL(TO_CHAR(p.IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-')    IN_TIME,\n"
            + "    NVL(p.LOCATION,'-')            LOCATION,\n"
            + "    NVL(p.LOC_ID,0)                LOC_ID,\n"
            + "    NVL(p.OU_ID,0)                  OU_ID,\n"
            + "    NVL(p.GATE_NO,'-')              GATE_NO,\n"
            + "    NVL(p.GATE_TYPE,'-')            GATE_TYPE,\n"
            + "    NVL(p.REMARKS,'-')              REMARKS,\n"
            + "    NVL(p.CUST_NAME,'-')            CUST_NAME,\n"
            + "    NVL(p.PARKING_REASON,'-')       PARKING_REASON,\n"
            + "    NVL(p.PARKING_DESC,'-')         PARKING_DESC,\n"
            + "    NVL(p.ATTRIBUTE1,'-')           SERVICE_ADVISOR,\n"
            + "    NVL(p.ATTRIBUTE2,'-')           MODEL_DESC,\n"
            + "    NVL(p.CREATED_BY,'-')           CREATED_BY,\n"
            + "    NVL(TO_CHAR(p.CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,\n"
            + "    NVL(p.UPDATED_BY,'-')           UPDATED_BY,\n"
            + "    NVL(TO_CHAR(p.UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-')  UPDATION_DATE,\n"
            + "    NVL(p.ATTRIBUTE3,'-')           deptAlloted,\n"
            + "    CASE WHEN jc.JOB_CARD_NO IS NOT NULL THEN 'YES' ELSE 'NO' END   JOB_CARD,\n"
            + "    NVL(jc.JOB_CARD_DATE_TIME,'-')                                   JOB_CARD_DATE,\n"
            + "    NVL(jc.JOB_CARD_NO,'-')                                          JOB_CARD_NO , NVL(p.ATTRIBUTE4,'-')  vehType \n"
            + "FROM SS_DMS_WS_PARKING p\n"
            + "LEFT JOIN LATERAL (\n"
            + "    SELECT j.JOB_CARD_NO,\n"
            + "           j.JOB_CARD_DATE_TIME\n"
            + "    FROM (\n"
            + "        SELECT j2.JOB_CARD_NO,\n"
            + "               j2.JOB_CARD_DATE_TIME,\n"
            + "               j2.REG_NO,\n"
            + "               TO_DATE(j2.JOB_CARD_DATE_TIME, 'DD-MM-YYYY HH24:MI') AS JOB_CARD_DT\n"
            + "        FROM ss_jobcarddetails_for_app j2\n"
            + "        WHERE j2.JOB_CARD_DATE_TIME IS NOT NULL\n"
            + "          AND REGEXP_LIKE(j2.JOB_CARD_DATE_TIME, '^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$')\n"
            + "    ) j\n"
            + "    WHERE j.REG_NO = p.REG_NO\n"
            + "      AND j.JOB_CARD_DT >= TRUNC(p.CREATION_DATE)\n"
            + "      AND j.JOB_CARD_DT <  TRUNC(p.CREATION_DATE) + 2\n"
            + "    ORDER BY j.JOB_CARD_DT DESC\n"
            + "    FETCH FIRST 1 ROW ONLY\n"
            + ") jc ON 1 = 1\n"
            + "WHERE p.OU_ID = :ouId\n"
            + "  AND p.LOC_ID = :locId\n"
            + "  AND p.CREATION_DATE BETWEEN :fromDate AND :toDate\n"
            + "ORDER BY p.CREATION_DATE", nativeQuery = true)
    public List<Map> getParkingDetails(Integer ouId, Integer locId, Date fromDate, Date toDate);

    //query for getting parking history
    //getParkingHistory
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO,NVL(STATUS,'-') STATUS, \n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DEPT,'-') DEPT, NVL(DRIVER_IN,'-') DRIVER_IN,\n"
            + " NVL(DRIVER_OUT,'-') DRIVER_OUT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "  NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_ID,0) LOC_ID, NVL(OU_ID,0) OU_ID,\n"
            + "NVL(GATE_NO,'-') GATE_NO, NVL(GATE_TYPE,'-') GATE_TYPE, NVL(REMARKS,'-') REMARKS, NVL(CUST_NAME,'-') CUST_NAME,\n"
            + "NVL(PARKING_REASON, '-') PARKING_REASON, NVL(PARKING_DESC,'-') PARKING_DESC, NVL(ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(ATTRIBUTE2,'-') MODEL_DESC,\n"
            + " NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(ATTRIBUTE3,'-') deptAlloted\n"
            + " FROM SS_DMS_WS_PARKING WHERE REG_NO=:regNo ORDER BY CREATION_DATE DESC ", nativeQuery = true)
    public List<Map> getParkingHistory(String regNo);

    //query for veh already parked in parking
    @Query(value = " SELECT DISTINCT REG_NO, STATUS FROM SS_DMS_WS_PARKING WHERE STATUS='IN' AND DEPT=?1 AND LOC_ID=?2", nativeQuery = true)
    public List<Map> getVehInList(String regNo, Integer locId);

    @Query(value = " select distinct attribute2 as parkingLocation from  ss_gate_type_master where location=:location\n"
            + "AND attribute2 is not null", nativeQuery = true)
    public List<Map> getParkLocations(String location);

}
