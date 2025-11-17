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

/**
 *
 * @author HarshG
 */
public interface SsDmsWsParkingDao extends CrudRepository<SsDmsWsParking, Object> {

    public Optional<SsDmsWsParking> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    @Query(value = "   SELECT NVL(P.REG_NO,'-') REG_NO, NVL(P.CHASSIS_NO,'-') CHASSIS_NO,\n"
            + " NVL(P.ENGINE_NO,'-') ENGINE_NO, NVL(P.VIN,'-') VIN, NVL(P.DEPT,'-') DEPT, NVL(P.DRIVER_OUT,'-') DRIVER_OUT, NVL(P.OUT_KM,0) OUT_KM, \n"
            + "NVL(TO_CHAR(P.OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,NVL(P.LOC_ID,0) LOC_ID, NVL(P.OU_ID,0) OU_ID, NVL(P.LOCATION,'-') LOCATION,\n"
            + " NVL(P.GATE_NO,'-') GATE_NO, NVL(P.GATE_TYPE,'-') GATE_TYPE, NVL(P.REMARKS,'-') REMARKS,NVL(P.CUST_NAME,'-') CUST_NAME , \n"
            + " NVL(P.ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(P.ATTRIBUTE2,'-') MODEL_DESC, \n"
            + "NVL(P.PARKING_REASON,'-') PARKING_REASON\n"
            + "FROM SS_DMS_WS_PARKING P  WHERE \n"
            + " P.REG_NO =?1 AND P.IN_TIME IS NULL ", nativeQuery = true)
    public List<Map> getVehInParkingDetails(String regNo);

    @Query(value = " SELECT NVL(P.REG_NO,'-') REG_NO, NVL(P.CHASSIS_NO,'-') CHASSIS_NO,\n"
            + "NVL(P.ENGINE_NO,'-') ENGINE_NO, NVL(P.VIN,'-') VIN, NVL(P.DEPT,'-') DEPT, NVL(P.DRIVER_IN,'-') DRIVER_IN, NVL(P.IN_KM,0) IN_KM, \n"
            + "NVL(TO_CHAR(P.IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,NVL(P.LOC_ID,0) LOC_ID, NVL(P.OU_ID,0) OU_ID, NVL(P.LOCATION,'-') LOCATION,\n"
            + "NVL(P.GATE_NO,'-') GATE_NO, NVL(P.GATE_TYPE,'-') GATE_TYPE, NVL(P.REMARKS,'-') REMARKS,NVL(P.CUST_NAME,'-') CUST_NAME , \n"
            + " NVL(P.ATTRIBUTE2,'-') MODEL_DESC, NVL(P.ATTRIBUTE1,'-') SERVICE_ADVISOR,\n"
            + " NVL(P.PARKING_REASON,'-') PARKING_REASON\n"
            + "FROM SS_DMS_WS_PARKING P  WHERE \n"
            + " P.REG_NO =?1 AND P.OUT_TIME IS NULL", nativeQuery = true)
    public List<Map> getVehOutParkingDetails(String regNo);

    public Optional<SsDmsWsParking> findTopByRegNoAndInTimeIsNullAndOutTimeIsNotNullOrderByCreationDateDesc(String regNo);

    public Optional<SsDmsWsParking> findTopByRegNoAndOutTimeIsNullAndInTimeIsNotNullOrderByCreationDateDesc(String regNo);

    //query for parking report...ou and loc and dept wise
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, \n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DEPT,'-') DEPT, NVL(DRIVER_IN,'-') DRIVER_IN,\n"
            + "NVL(DRIVER_OUT,'-') DRIVER_OUT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_ID,0) LOC_ID, NVL(OU_ID,0) OU_ID,\n"
            + "NVL(GATE_NO,'-') GATE_NO, NVL(GATE_TYPE,'-') GATE_TYPE, NVL(REMARKS,'-') REMARKS, NVL(CUST_NAME,'-') CUST_NAME,\n"
            + "NVL(PARKING_REASON, '-') PARKING_REASON, NVL(PARKING_DESC,'-') PARKING_DESC, NVL(ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(ATTRIBUTE2,'-') MODEL_DESC,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE\n"
            + "FROM SS_DMS_WS_PARKING WHERE OU_ID=:ouId and LOC_ID=:locId  AND CREATION_DATE BETWEEN :fromDate AND :toDate ", nativeQuery = true)
    public List<Map> getParkingDetails(Integer ouId, Integer locId, Date fromDate, Date toDate);

    //query for getting parking history
    //getParkingHistory
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO,NVL(STATUS,'-') STATUS, \n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DEPT,'-') DEPT, NVL(DRIVER_IN,'-') DRIVER_IN,\n"
            + "NVL(DRIVER_OUT,'-') DRIVER_OUT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_ID,0) LOC_ID, NVL(OU_ID,0) OU_ID,\n"
            + "NVL(GATE_NO,'-') GATE_NO, NVL(GATE_TYPE,'-') GATE_TYPE, NVL(REMARKS,'-') REMARKS, NVL(CUST_NAME,'-') CUST_NAME,\n"
            + "NVL(PARKING_REASON, '-') PARKING_REASON, NVL(PARKING_DESC,'-') PARKING_DESC, NVL(ATTRIBUTE1,'-') SERVICE_ADVISOR, NVL(ATTRIBUTE2,'-') MODEL_DESC,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE\n"
            + "FROM SS_DMS_WS_PARKING WHERE REG_NO=?1 ORDER BY CREATION_DATE DESC ", nativeQuery = true)
    public List<Map> getParkingHistory(String regNo);

    //query for veh already parked in parking
    @Query(value = " SELECT DISTINCT REG_NO, STATUS FROM SS_DMS_WS_PARKING WHERE STATUS='IN' AND DEPT=?1 AND LOC_ID=?2", nativeQuery = true)
    public List<Map> getVehInList(String regNo, Integer locId);
}
