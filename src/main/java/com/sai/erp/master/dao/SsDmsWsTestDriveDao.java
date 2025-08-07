/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsStockTrfWs;
import com.sai.erp.master.entity.SsDmsWsTestDrive;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-HARSH
 */
public interface SsDmsWsTestDriveDao extends JpaRepository<SsDmsWsTestDrive, Long> {

    @Query(value = "select   stock.REGNO,  nvl(stock.CHASSISNO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN,  nvl(stock.JOBCARDNO,'-') JOBCARDNO,  nvl(to_char(stock.JOBCARDDATE),'-') JOBCARDDATE ,\n"
            + " nvl(stock.ENGINNO,'-') ENGINENO,  stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(STOCK.MODELDESC,'-') MODEL_DESC,\n"
            + " NVL(STOCK.STATUS,'-') STATUS,  NVL(to_char(STOCK.ERPACCTNO),0) ERPACCTNO,   nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, \n"
            + " nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR, NVL(STOCK.DEPT,'-') DEPT, NVL(STOCK.DMSLOCATION,'-') DMSLOCATION,\n"
            + "  nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO , nvl(to_char(stock.ouId),0) ouId\n"
            + "  from SS_DMS_INV_STOCK_SERVICE stock where stock.jobCardNo=?1 and STOCK.VEH_STATUS='STOCK'", nativeQuery = true)
    public List<Map> getTestDriveOutDetByJobCardNo(String jobCardNo);

    @Query(value = "select   TEST.REG_NO,  nvl(TEST.CHASSIS_NO,'-') CHASSIS_NO, nvl(TEST.TEST_DRIVE_NO,'-') TEST_DRIVE_NO, nvl(TEST.VIN,'-') VIN, \n"
            + "nvl(TEST.JOBCARDNO,'-') JOBCARDNO,  nvl(TEST.ENGINE_NO,'-') ENGINE_NO, \n"
            + "NVL(TEST.DRIVER_NAME,'-') DRIVER_NAME,NVL(TEST.AUTHORISED_BY,'-') AUTHORISED_BY, \n"
            + "NVL(TEST.DEPT,'-') DEPT, nvl(TEST.IN_KM,0) IN_KM, NVL(TO_CHAR(TEST.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + " nvl(TEST.LOCATION,'-') LOCATION, nvl(TEST.LOC_CODE,0) LOC_CODE,  nvl(to_char(TEST.OU),0) OU,  nvl(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + " NVL(TO_CHAR(TEST.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, nvl(TEST.REMARKS,'-') REMARKS\n"
            + " from SS_DMS_WS_TEST_DRIVE TEST where TEST.REG_NO=?1 \n"
            + " AND TEST.OUT_KM IS NULL AND TEST.OUT_TIME IS NULL", nativeQuery = true)
    public List<Map> getTestDriveOutDetByRegNo(String regNo);

    @Query(value = " select   TEST.REG_NO,  nvl(TEST.CHASSIS_NO,'-') CHASSIS_NO, nvl(TEST.TEST_DRIVE_NO,'-') TEST_DRIVE_NO, nvl(TEST.VIN,'-') VIN, \n"
            + "nvl(TEST.JOBCARDNO,'-') JOBCARDNO,  nvl(TEST.ENGINE_NO,'-') ENGINE_NO, \n"
            + "NVL(TEST.DRIVER_NAME,'-') DRIVER_NAME,NVL(TEST.AUTHORISED_BY,'-') AUTHORISED_BY, \n"
            + "NVL(TEST.DEPT,'-') DEPT, nvl(TEST.IN_KM,0) IN_KM, NVL(TO_CHAR(TEST.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "nvl(TEST.OUT_KM,0) OUT_KM, NVL(TO_CHAR(TEST.OUT_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,\n"
            + " nvl(TEST.LOCATION,'-') LOCATION, nvl(TEST.LOC_CODE,0) LOC_CODE,  nvl(to_char(TEST.OU),0) OU,  nvl(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + " NVL(TO_CHAR(TEST.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, nvl(TEST.REMARKS,'-') REMARKS\n"
            + " from SS_DMS_WS_TEST_DRIVE TEST where TEST.REG_NO=?1 AND TEST.STATUS='STOCK'", nativeQuery = true)
    public List<Map> getTestDriveOutNewDetByRegNo(String regNo);

    @Query(value = " select   TEST.REG_NO,  nvl(TEST.CHASSIS_NO,'-') CHASSIS_NO, nvl(TEST.TEST_DRIVE_NO,'-') TEST_DRIVE_NO, nvl(TEST.VIN,'-') VIN, \n"
            + "nvl(TEST.JOBCARDNO,'-') JOBCARDNO,  nvl(TEST.ENGINE_NO,'-') ENGINE_NO, \n"
            + "NVL(TEST.DRIVER_NAME,'-') DRIVER_NAME,NVL(TEST.AUTHORISED_BY,'-') AUTHORISED_BY, \n"
            + "NVL(TEST.DEPT,'-') DEPT, nvl(TEST.IN_KM,0) IN_KM, NVL(TO_CHAR(TEST.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "nvl(TEST.OUT_KM,0) OUT_KM, NVL(TO_CHAR(TEST.OUT_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,\n"
            + " nvl(TEST.LOCATION,'-') LOCATION, nvl(TEST.LOC_CODE,0) LOC_CODE,  nvl(to_char(TEST.OU),0) OU,  nvl(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + " NVL(TO_CHAR(TEST.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, nvl(TEST.REMARKS,'-') REMARKS\n"
            + " from SS_DMS_WS_TEST_DRIVE TEST where TEST.REG_NO=?1 AND TEST.STATUS='NEW'", nativeQuery = true)
    public List<Map> getTestDriveOutFinalDetByRegNo(String regNo);

//    @Query(value = "select MAX(test_drive_no) from SS_DMS_WS_TEST_DRIVE where loc_code=?1 AND OU=?2 and dept=?3 and test_drive_no like  '%TRANSIT%'", nativeQuery = true)
//    String getMaxTestDriveNoByLocCodeAndOuAndDept(Integer locCode, Integer ou, String dept);
    public Optional<SsDmsWsTestDrive> findByJobCardNo(String jobCardNo);

    public Optional<SsDmsWsTestDrive> findFirstByJobCardNoOrderByCreationDateDesc(String jobCardNo);

//    @Query(value = "select   test.REG_NO,  nvl(test.CHASSIS_NO,'-') CHASSIS_NO, nvl(test.TEST_DRIVE_NO,'-') TEST_DRIVE_NO ,\n"
//            + "  nvl(test.JOBCARDNO,'-') JOBCARDNO, nvl(test.ENGINE_NO,'-') ENGINE_NO, nvl(test.VIN,'-') VIN,\n"
//            + " nvl(test.driver_name,'-') driver_name, nvl(test.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(test.dept,'-') dept, \n"
//            + " NVL(TO_CHAR(TEST.OUT_KM),0) OUT_KM, NVL(TO_CHAR(test.OUT_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(TEST.LOCATION,'-') LOCATION,\n"
//            + "  NVL(TEST.LOC_CODE,0) LOC_CODE, nvl(to_char(test.ou),0) ou, NVL(TEST.CREATED_BY,'-') CREATED_BY,\n"
//            + "  NVL(TO_CHAR(test.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,  nvl(test.REMARKS,'-') REMARKS,\n"
//            + "stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(stock.MODELDESC,'-') MODEL_DESC,\n"
//            + "NVL(stock.STATUS,'-') STATUS, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
//            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO\n"
//            + "from SS_DMS_WS_TEST_DRIVE test , SS_DMS_INV_STOCK_SERVICE stock where test.jobCardNo=?1 and test.JOBCARDNO=STOCK.JOBCARDNO \n"
//            + "and test.reg_no=stock.regno  and test.IN_KM is null and test.IN_TIME is null and test_drive_no like '%TD%' ", nativeQuery = true)
//    public List<Map> getTestDriveInDetByJobCardNo(String jobCardNo);
    @Query(value = " select   test.REG_NO,  nvl(test.CHASSIS_NO,'-') CHASSIS_NO, nvl(test.TEST_DRIVE_NO,'-') TEST_DRIVE_NO ,\n"
            + " nvl(test.JOBCARDNO,'-') JOBCARDNO, nvl(test.ENGINE_NO,'-') ENGINE_NO, nvl(test.VIN,'-') VIN,\n"
            + " nvl(test.driver_name,'-') driver_name, nvl(test.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(test.dept,'-') dept, \n"
            + " NVL(TO_CHAR(TEST.OUT_KM),0) OUT_KM, NVL(TO_CHAR(test.OUT_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(TEST.LOCATION,'-') LOCATION,\n"
            + "NVL(TEST.LOC_CODE,0) LOC_CODE, nvl(to_char(test.ou),0) ou, NVL(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + " NVL(TO_CHAR(test.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,  nvl(test.REMARKS,'-') REMARKS\n"
            + " from SS_DMS_WS_TEST_DRIVE test where test.reg_No=?1\n"
            + "and test.IN_KM is null and test.IN_TIME is null AND TEST.STATUS='INTRANSIT'", nativeQuery = true)
    public List<Map> getTestDriveInDetByRegNo(String regNo);

    @Query(value = " select   TEST.REG_NO,  nvl(TEST.CHASSIS_NO,'-') CHASSIS_NO, nvl(TEST.TEST_DRIVE_NO,'-') TEST_DRIVE_NO, nvl(TEST.VIN,'-') VIN, \n"
            + "            nvl(TEST.JOBCARDNO,'-') JOBCARDNO,  nvl(TEST.ENGINE_NO,'-') ENGINE_NO, \n"
            + "            NVL(TEST.DRIVER_NAME,'-') DRIVER_NAME,NVL(TEST.AUTHORISED_BY,'-') AUTHORISED_BY, \n"
            + "            NVL(TEST.DEPT,'-') DEPT, nvl(TEST.IN_KM,0) IN_KM, NVL(TO_CHAR(TEST.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "            nvl(TEST.OUT_KM,0) OUT_KM, NVL(TO_CHAR(TEST.OUT_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,\n"
            + "             nvl(TEST.LOCATION,'-') LOCATION, nvl(TEST.LOC_CODE,0) LOC_CODE,  nvl(to_char(TEST.OU),0) OU,  nvl(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + "             NVL(TO_CHAR(TEST.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, nvl(TEST.REMARKS,'-') REMARKS\n"
            + "             from SS_DMS_WS_TEST_DRIVE TEST where TEST.REG_NO=?1 AND TEST.STATUS='INTRANSIT'", nativeQuery = true)
    public List<Map> getTestDriveInNewDetByRegNo(String regNo);

    public Optional<SsDmsWsTestDrive> findByTestDriveNo(String testDriveNo);

    @Query(value = " SELECT NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO , NVL(TEST_DRIVE_NO,'-') TEST_DRIVE_NO,NVL(DRIVER_NAME,'-') DRIVER_NAME, NVL(JOBCARDNO,'-') JOBCARDNO,\n"
            + "NVL(LOCATION,'-') LOCATION, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME,\n"
            + "NVL(IN_KM,0) IN_KM, NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,  NVL(CREATED_BY,'-') CREATED_BY,\n"
            + " NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(REMARKS,'-') REMARKS,\n"
            + " NVL(UPDATED_BY,'-') UPDATED_BY,  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE,\n"
            + "NVL(OU,0) OU, NVL(GATE_PASS_ID,'-') GATE_PASS_ID, NVL(ATTRIBUTE1,'-') CUSTOMER_NAME, NVL(ATTRIBUTE2,'-') REASON,\n"
            + "NVL(ATTRIBUTE3,'-') GATE_NO, NVL(ATTRIBUTE4,'-') GATE_TYPE, NVL(ATTRIBUTE5,'-') DESCRIPTION\n"
            + " FROM SS_DMS_WS_TEST_DRIVE WHERE REG_NO=?1\n"
            + "AND GATE_PASS_ID IS NULL ORDER BY CREATION_DATE DESC", nativeQuery = true)
    List<Map> getWsVehTestDriveHistory(String regNo);

    //query for getting test drive report by from and to date in ss_dms_ws_test_drive table
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(TEST_DRIVE_NO,'-') TEST_DRIVE_NO, NVL(JOBCARDNO,'-') JOBCARDNO,\n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DRIVER_NAME,'-') DRIVER_NAME, NVL(AUTHORISED_BY,'-') AUTHORISED_BY,\n"
            + "NVL(DEPT,'-') DEPT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_CODE,0) LOC_CODE, NVL(OU,0) OU,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(REMARKS,'-') REMARKS\n"
            + "FROM SS_DMS_WS_TEST_DRIVE WHERE OU=:ou and loc_code=:locId AND CREATION_DATE BETWEEN :fromDate AND :toDate ", nativeQuery = true)
    public List<Map> getTestDriveDetailsByOu(Integer ou, Integer locId, Date fromDate, Date toDate);

    //query for getting test drive report by from and to date in ss_dms_ws_test_drive table
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(TEST_DRIVE_NO,'-') TEST_DRIVE_NO, NVL(JOBCARDNO,'-') JOBCARDNO,\n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DRIVER_NAME,'-') DRIVER_NAME, NVL(AUTHORISED_BY,'-') AUTHORISED_BY,\n"
            + "NVL(DEPT,'-') DEPT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_CODE,0) LOC_CODE, NVL(OU,0) OU,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(REMARKS,'-') REMARKS\n"
            + "FROM SS_DMS_WS_TEST_DRIVE WHERE OU=:ou and loc_code=:locId AND dept='SERVICE' and CREATION_DATE BETWEEN :fromDate AND :toDate ", nativeQuery = true)
    public List<Map> getTestDriveDetailsServByOu(Integer ou, Integer locId, Date fromDate, Date toDate);

    //query for getting test drive report by from and to date in ss_dms_ws_test_drive table
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(TEST_DRIVE_NO,'-') TEST_DRIVE_NO, NVL(JOBCARDNO,'-') JOBCARDNO,\n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(VIN,'-') VIN, NVL(DRIVER_NAME,'-') DRIVER_NAME, NVL(AUTHORISED_BY,'-') AUTHORISED_BY,\n"
            + "NVL(DEPT,'-') DEPT, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(IN_KM,0) IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(LOCATION,'-') LOCATION, NVL(LOC_CODE,0) LOC_CODE, NVL(OU,0) OU,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY,  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(REMARKS,'-') REMARKS\n"
            + "FROM SS_DMS_WS_TEST_DRIVE WHERE OU=:ou and loc_code=:locId AND dept='DP' AND CREATION_DATE BETWEEN :fromDate AND :toDate ", nativeQuery = true)
    public List<Map> getTestDriveDetailsDpByOu(Integer ou, Integer locId, Date fromDate, Date toDate);

    //query for getting new vehicle in ws details by regno from stock service table
//    @Query(value = "select   stock.REGNO,  nvl(stock.CHASSISNO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, nvl(stock.ENGINNO,'-') ENGINENO,\n"
//            + "nvl(TEST.IN_KM,0) IN_KM,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(STOCK.MODELDESC,'-') MODEL_DESC,\n"
//            + " NVL(to_char(STOCK.ERPACCTNO),0) ERPACCTNO,  NVL(stock.JOBCARDNO,'-') JOBCARDNO, NVL(STOCK.DEPT,'-') DEPT, nvl(stock.CUSTNAME,'-') CUSTNAME,\n"
//            + " nvl(to_char(stock.CONTACTNO),0) CONTACTNO  from SS_DMS_INV_STOCK_SERVICE stock, SS_DMS_WS_TEST_DRIVE TEST\n"
//            + "  where stock.regNo=?1  AND test.reg_no=stock.regno", nativeQuery = true)
//    public List<Map> getNewVehDetailsServByRegNo(String regNo);
    @Query(value = "select   stock.REGNO,  nvl(stock.CHASSISNO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, nvl(stock.ENGINNO,'-') ENGINENO,\n"
            + "nvl(TEST.IN_KM,0) IN_KM,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(STOCK.MODELDESC,'-') MODEL_DESC,NVL(STOCK.SERVICEADVISOR,'-') SERVICE_ADVISOR,\n"
            + " NVL(to_char(STOCK.ERPACCTNO),0) ERPACCTNO,  NVL(stock.JOBCARDNO,'-') JOBCARDNO, NVL(STOCK.DEPT,'-') DEPT, nvl(stock.CUSTNAME,'-') CUSTNAME,\n"
            + " nvl(to_char(stock.CONTACTNO),0) CONTACTNO  from SS_DMS_INV_STOCK_SERVICE stock, SS_DMS_WS_TEST_DRIVE TEST\n"
            + "  where stock.regNo=?1  AND test.reg_no=stock.regno", nativeQuery = true)
    public List<Map> getNewVehDetailsServByRegNo(String regNo);

    @Query(value = "select   TEST.REG_NO,  nvl(TEST.CHASSIS_NO,'-') CHASSIS_NO, nvl(TEST.TEST_DRIVE_NO,'-') TEST_DRIVE_NO, nvl(TEST.VIN,'-') VIN, \n"
            + "            nvl(TEST.JOBCARDNO,'-') JOBCARDNO,  nvl(TEST.ENGINE_NO,'-') ENGINE_NO, \n"
            + "            NVL(TEST.DRIVER_NAME,'-') DRIVER_NAME,NVL(TEST.AUTHORISED_BY,'-') AUTHORISED_BY, \n"
            + "            NVL(TEST.DEPT,'-') DEPT, nvl(TEST.IN_KM,0) IN_KM, NVL(TO_CHAR(TEST.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "             nvl(TEST.LOCATION,'-') LOCATION, nvl(TEST.LOC_CODE,0) LOC_CODE,  nvl(to_char(TEST.OU),0) OU,  nvl(TEST.CREATED_BY,'-') CREATED_BY,\n"
            + "             NVL(TO_CHAR(TEST.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, nvl(TEST.REMARKS,'-') REMARKS\n"
            + "             from SS_DMS_WS_TEST_DRIVE TEST where TEST.REG_NO=?1\n"
            + "             AND TEST.OUT_KM IS NULL AND TEST.OUT_TIME IS NULL AND TEST.STATUS='NEW'", nativeQuery = true)
    public List<Map> getNewVehDetailsTestByRegNo(String regNo);

    @Query(value = " SELECT test_drive_no \n"
            + "FROM (\n"
            + "       SELECT test_drive_no, \n"
            + "      TO_NUMBER(REGEXP_SUBSTR(test_drive_no, '[^-]+$', 1, 1)) AS serial_no\n"
            + "       FROM ss_dms_ws_test_drive\n"
            + "      WHERE loc_code = ?1 AND ou =?2 and dept=?3\n"
            + " ) \n"
            + " ORDER BY serial_no DESC \n"
            + "  FETCH FIRST 1 ROWS ONLY ", nativeQuery = true)
    String getMaxTestDriveNoNewByLocCodeAndOuAndDept(Integer locCode, Integer ou, String dept);

//    @Query(value = " select nvl(max(TO_NUMBER(SUBSTR(test_drive_no, INSTR(test_drive_no, '-', -1) + 1))),null) as last_serial_number\n"
//            + "  from SS_DMS_WS_TEST_DRIVE where loc_code=?1 and ou=?2 and dept=?3", nativeQuery = true)
//    String getMaxTestDriveNoNewByLocCodeAndOuAndDept(Integer locCode, Integer ou, String dept);
    public Optional<SsDmsWsTestDrive> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsWsTestDrive set  status=?1, location=?2   where testDriveNo=?3 and regNo=?4 and status like '%INTRANSIT%'")
    public void updateOldTestDriveInStatus(String status, String location, String testDriveNo, String regNo);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsWsTestDrive set  status=?1, location=?2   where testDriveNo=?3 and regNo=?4 and status like '%STOCK%'")
    public void updateOldTestDriveOutStatus(String status, String location, String testDriveNo, String regNo);
///

//    @Query(value = "select   test.REG_NO,  nvl(test.CHASSIS_NO,'-') CHASSIS_NO, nvl(test.TEST_DRIVE_NO,'-') TEST_DRIVE_NO ,\n"
//            + "              nvl(test.JOBCARDNO,'-') JOBCARDNO, nvl(test.ENGINE_NO,'-') ENGINE_NO, nvl(test.VIN,'-') VIN,\n"
//            + "             nvl(test.driver_name,'-') driver_name, nvl(test.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(test.dept,'-') dept, \n"
//            + "             NVL(TO_CHAR(TEST.IN_KM),0) IN_KM, NVL(TO_CHAR(test.IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME, NVL(TEST.LOCATION,'-') LOCATION,\n"
//            + "              NVL(TEST.LOC_CODE,0) LOC_CODE, nvl(to_char(test.ou),0) ou, NVL(TEST.CREATED_BY,'-') CREATED_BY,\n"
//            + "              NVL(TO_CHAR(test.CREATION_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,  nvl(test.REMARKS,'-') REMARKS,\n"
//            + "            stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(stock.MODELDESC,'-') MODEL_DESC,\n"
//            + "            NVL(stock.STATUS,'-') STATUS, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
//            + "             nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO\n"
//            + "            from SS_DMS_WS_TEST_DRIVE test , SS_DMS_INV_STOCK_SERVICE stock where test.reg_No=?1 and  \n"
//            + "             test.reg_no=stock.regno  and test.OUT_KM is null and test.OUT_TIME is null and test_drive_no like '%TRANSIT%' ", nativeQuery = true)
//    public List<Map> getWsNewVehicleOutDetByRegNo(String regNo);
    public Optional<SsDmsWsTestDrive> findByRegNo(String regNo);

    //query for getting test drive details gate wise as per location and ou and between dates
    //report as per gates in diiferent locations
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY TD.CREATION_DATE) AS SR_NO, \n"
            + "    TD.REG_NO, \n"
            + "    TD.DRIVER_NAME, \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_KM) \n"
            + "    END AS Gate_INOUT_OUTKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_TIME) \n"
            + "    END AS Gate_INOUT_OUTTIME,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_KM) \n"
            + "    END AS Gate_INOUT_INKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_TIME) \n"
            + "    END AS Gate_INOUT_INTIME,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_KM END) AS Gate_IN_inKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_TIME END) AS Gate_IN_inTime,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_KM END) AS Gate_OUT_outKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_TIME END) AS Gate_OUT_outTime\n"
            + "FROM SS_DMS_WS_TEST_DRIVE TD\n"
            + "JOIN SS_GATE_TYPE_MASTER SGTM \n"
            + "    ON TD.LOC_CODE = SGTM.LOC_ID \n"
            + "    AND TD.ATTRIBUTE3 = SGTM.GATE_NO AND TD.ATTRIBUTE4=SGTM.GATE_TYPE\n"
            + "WHERE TD.OU =:ou AND  TD.LOC_CODE=:locId AND TD.CREATION_DATE BETWEEN :fromDate AND :toDate \n"
            + "GROUP BY  \n"
            + "    TD.REG_NO,  \n"
            + "    TD.DRIVER_NAME,  \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,  \n"
            + "    TD.CREATION_DATE,\n"
            + "    TD.LOC_CODE", nativeQuery = true)
    public List<Map> getTdGatewiseByOuAndLocId(Integer ou, Integer locId, Date fromDate, Date toDate);

    //query for getting test drive details gate wise as per location and ou and between dates and service dept
    //report as per gates in diiferent locations
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY TD.CREATION_DATE) AS SR_NO, \n"
            + "    TD.REG_NO, \n"
            + "    TD.DRIVER_NAME, \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_KM) \n"
            + "    END AS Gate_INOUT_OUTKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_TIME) \n"
            + "    END AS Gate_INOUT_OUTTIME,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_KM) \n"
            + "    END AS Gate_INOUT_INKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_TIME) \n"
            + "    END AS Gate_INOUT_INTIME,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_KM END) AS Gate_IN_inKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_TIME END) AS Gate_IN_inTime,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_KM END) AS Gate_OUT_outKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_TIME END) AS Gate_OUT_outTime\n"
            + "FROM SS_DMS_WS_TEST_DRIVE TD\n"
            + "JOIN SS_GATE_TYPE_MASTER SGTM \n"
            + "    ON TD.LOC_CODE = SGTM.LOC_ID \n"
            + "    AND TD.ATTRIBUTE3 = SGTM.GATE_NO AND TD.ATTRIBUTE4=SGTM.GATE_TYPE\n"
            + "WHERE TD.OU =:ou AND  TD.LOC_CODE=:locId AND TD.DEPT='SERVICE' AND TD.CREATION_DATE BETWEEN :fromDate AND :toDate\n"
            + "GROUP BY  \n"
            + "    TD.REG_NO,  \n"
            + "    TD.DRIVER_NAME,  \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,  \n"
            + "    TD.CREATION_DATE,\n"
            + "    TD.LOC_CODE", nativeQuery = true)
    public List<Map> getTdGatewiseServByOuAndLocId(Integer ou, Integer locId, Date fromDate, Date toDate);

    //query for getting test drive details gate wise as per location and ou and between dates and dp dept
    //report as per gates in diiferent locations
    @Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY TD.CREATION_DATE) AS SR_NO, \n"
            + "    TD.REG_NO, \n"
            + "    TD.DRIVER_NAME, \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_KM) \n"
            + "    END AS Gate_INOUT_OUTKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.OUT_TIME) \n"
            + "    END AS Gate_INOUT_OUTTIME,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_KM) \n"
            + "    END AS Gate_INOUT_INKM,\n"
            + "    CASE \n"
            + "        WHEN (SELECT COUNT(DISTINCT SGTM.GATE_NO) \n"
            + "              FROM SS_GATE_TYPE_MASTER SGTM \n"
            + "              WHERE SGTM.LOC_ID = TD.LOC_CODE) = 1 \n"
            + "             AND MAX(SGTM.GATE_TYPE) = 'IN-OUT' \n"
            + "        THEN MAX(TD.IN_TIME) \n"
            + "    END AS Gate_INOUT_INTIME,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_KM END) AS Gate_IN_inKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'IN' THEN TD.IN_TIME END) AS Gate_IN_inTime,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_KM END) AS Gate_OUT_outKm,\n"
            + "    MAX(CASE WHEN SGTM.GATE_TYPE = 'OUT' THEN TD.OUT_TIME END) AS Gate_OUT_outTime\n"
            + "FROM SS_DMS_WS_TEST_DRIVE TD\n"
            + "JOIN SS_GATE_TYPE_MASTER SGTM \n"
            + "    ON TD.LOC_CODE = SGTM.LOC_ID \n"
            + "    AND TD.ATTRIBUTE3 = SGTM.GATE_NO AND TD.ATTRIBUTE4=SGTM.GATE_TYPE\n"
            + "WHERE TD.OU =:ou AND  TD.LOC_CODE=:locId AND TD.DEPT='DP' AND TD.CREATION_DATE BETWEEN :fromDate AND :toDate\n"
            + "GROUP BY  \n"
            + "    TD.REG_NO,  \n"
            + "    TD.DRIVER_NAME,  \n"
            + "    TD.TEST_DRIVE_NO,\n"
            + "    TD.DEPT,  \n"
            + "    TD.CREATION_DATE,\n"
            + "    TD.LOC_CODE", nativeQuery = true)
    public List<Map> getTdGatewiseDpByOuAndLocId(Integer ou, Integer locId, Date fromDate, Date toDate);

    @Query(value = " select nvl(MAX(TD_ID),0) from ss_dms_ws_test_drive WHERE GATE_PASS_ID IS not NULL AND REG_NO=?1 AND STATUS='STOCK' ORDER BY TD_ID DESC ", nativeQuery = true)
    public Integer getMaxTdIdMultiple(String regNo);

    @Query(value = " select NVL(MAX(TD_ID),0) from ss_dms_ws_test_drive WHERE GATE_PASS_ID IS not NULL and status='DELIVERED' AND REG_NO=?1 ORDER BY TD_ID DESC ", nativeQuery = true)
    public Integer getMaxTdIdSingle(String regNo);

    @Query(value = " select IN_KM from ss_dms_ws_test_drive WHERE td_id=?1 ORDER BY TD_ID DESC ", nativeQuery = true)
    public Integer getMaxTdIdKm(Integer tdId);

    //query for fetching  test drive vehicles delivered
    @Query(value = " SELECT NVL(REG_NO,'-') REG_NO, NVL(TEST_DRIVE_NO,'-') TEST_DRIVE_NO,\n"
            + " NVL(IN_KM,0) IN_KM, NVL(TO_CHAR(IN_TIME, 'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME\n"
            + "FROM SS_DMS_WS_TEST_DRIVE WHERE STATUS='DELIVERED' AND OUT_KM IS NULL AND GATE_PASS_ID IS NOT NULL AND REG_NO=:regNo", nativeQuery = true)
    public List<Map> getWsVehDetTestDriveDeliver(String regNo);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsWsTestDrive set  status=?1  where regNo=?2 AND gatePassId is not null ")
    public void updateTdStatusPhyDeliver(String status, String regNo);

//----------------------------------------------------------------------------------------------------------------------------------    
    //auto update status to physically delivered -----after 8 days
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE ss_dms_ws_test_drive SET status = 'PHYSICALLY DELIVERED' "
//            + "WHERE gate_pass_id IS NOT NULL AND CREATION_DATE <= :cutoffDate", nativeQuery = true)
//    void updateStatusToPhysicallyDelivered(@Param("cutoffDate") LocalDateTime cutoffDate);
}
