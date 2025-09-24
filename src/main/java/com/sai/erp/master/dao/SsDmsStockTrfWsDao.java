/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsStockTrfWs;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Harsh Gawde
 */
public interface SsDmsStockTrfWsDao extends CrudRepository<SsDmsStockTrfWs, Integer> {

//    @Query("select  NVL(max(stockTrfNo),'1') from SsDmsStockTrfWs where ou=?1 and stockTrfNo like 'STAN%' ")
//    public String getMaxSrlNo(Integer ou);
//    @Query("select  max(TO_NUMBER(SUBSTR(stockTrfNo, INSTR(stockTrfNo, '-', -1) + 1))) as last_serial_number from SsDmsStockTrfWs where ou=?1 and stockTrfNo like 'STAN%' ")
//    public Integer getMaxSrlNo(Integer ou);
    @Query(
            value = " select max(TO_NUMBER(SUBSTR(stock_trf_no, INSTR(stock_trf_no, '-', -1) + 1))) as last_serial_number \n"
            + "            from SS_DMS_STOCK_TRF_WS where ou = ?1 and STOCK_TRF_NO like 'STAN%'",
            nativeQuery = true
    )
    String getMaxSrlNo(Integer ou);

    //query for fetching TRANSFER image paths from ss_dms_stock_trf_ws
    @Query(value = " select TRF_IMAGE_1, TRF_IMAGE_2, TRF_IMAGE_3, TRF_IMAGE_4, TRF_IMAGE_5, TRF_IMAGE_6, TRF_IMAGE_7, TRF_IMAGE_8 ,\n"
            + "TRF_IMAGE_9, TRF_IMAGE_10, TRF_IMAGE_11, TRF_IMAGE_12, TRF_IMAGE_13, TRF_IMAGE_14  from SS_DMS_STOCK_TRF_WS \n"
            + "where JOBCARDNO=?1 ", nativeQuery = true)
    List<String> findTrfImagePathsByJobCardNo(String jobCardNo);

    //query for fethcing RECEIVED image paths from ss_dms_stock_trf_ws
    @Query(value = " select REC_IMAGE_1, REC_IMAGE_2, REC_IMAGE_3, REC_IMAGE_4, REC_IMAGE_5, REC_IMAGE_6, REC_IMAGE_7, REC_IMAGE_8 ,\n"
            + "REC_IMAGE_9, REC_IMAGE_10, REC_IMAGE_11, REC_IMAGE_12, REC_IMAGE_13, REC_IMAGE_14  from SS_DMS_STOCK_TRF_WS \n"
            + "where JOBCARDNO=?1", nativeQuery = true)
    List<String> findRecImagePathsByJobCardNo(String jobCardNo);

    public Optional<SsDmsStockTrfWs> findByStockTrfNo(String stkTrfNo);

    //QUERY FOR STOCK REC FROM TRF WS TABLE (OLD)
//    @Query(value = "select   trf.REG_NO,  nvl(trf.CHASSIS_NO,'-') CHASSIS_NO, nvl(TRF.STOCK_TRF_NO,'-') STOCK_TRF_NO,  NVL(TO_CHAR(TRF.STOCK_TRF_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') STOCK_TRF_DATE, \n"
//            + "nvl(trf.VIN,'-') VIN,  nvl(trf.JOBCARDNO,'-') JOBCARDNO, NVL(TRF.MADE_BY,'-') MADE_BY, NVL(TRF.FROM_LOCATION,'-') FROM_LOCATION, NVL(TRF.TO_LOCATION,'-') TO_LOCATION,\n"
//            + "nvl(trf.driver_name,'-') driver_name, nvl(TRF.FROM_LOC_CODE,'-') FROM_LOC_CODE, nvl(trf.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(trf.from_km,0) from_km , nvl(trf.dept,'-') dept, \n"
//            + " nvl(trf.ENGINE_NO,'-') ENGINENO,  stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(stock.MODELDESC,'-') MODEL_DESC,\n"
//            + " NVL(stock.STATUS,'-') STATUS,  NVL(to_char(stock.ERPACCTNO),0) ERPACCTNO, \n"
//            + " nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
//            + "  nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO , nvl(to_char(trf.ou),0) ou\n"
//            + "  from SS_DMS_STOCK_TRF_WS trf , SS_DMS_INV_STOCK_SERVICE stock where trf.jobCardNo=?1 and TRF.JOBCARDNO=STOCK.JOBCARDNO \n"
//            + "and trf.RECEIVED_BY is null and TRF.RECD_DATE is null", nativeQuery = true)
//    public List<Map> getWsVehReceiveDetByJobCardNo(String jobCardNo);
    
    //QUERY FOR STOCK REC FROM TRF WS TABLE & GD FDI(NEW)
    @Query(value = " select   trf.REG_NO,  nvl(trf.CHASSIS_NO,'-') CHASSIS_NO, nvl(TRF.STOCK_TRF_NO,'-') STOCK_TRF_NO,  NVL(TO_CHAR(TRF.STOCK_TRF_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') STOCK_TRF_DATE, \n"
            + "nvl(trf.VIN,'-') VIN,  nvl(trf.JOBCARDNO,'-') JOBCARDNO, NVL(TRF.MADE_BY,'-') MADE_BY, NVL(TRF.FROM_LOCATION,'-') FROM_LOCATION, NVL(TRF.TO_LOCATION,'-') TO_LOCATION,\n"
            + "nvl(trf.driver_name,'-') driver_name, nvl(TRF.FROM_LOC_CODE,'-') FROM_LOC_CODE, nvl(trf.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(trf.from_km,0) from_km , nvl(trf.dept,'-') dept, \n"
            + "nvl(trf.ENGINE_NO,'-') ENGINENO,   NVL(stock.VARIANT_CD,'-') VARIANT_CODE,  NVL(stock.MODEL_CD,'-') MODEL_DESC,\n"
            + "nvl(stock.EXECUTIVE,'-') SERVICEADVISOR,\n"
            + "nvl(stock.CUST_NAME,'-') CUSTNAME , nvl(to_char(trf.ou),0) ou\n"
            + "from SS_DMS_STOCK_TRF_WS trf , gd_fdi_trans stock where trf.jobCardNo=?1 and stock.trans_type='WI' AND TRF.JOBCARDNO=STOCK.TRANS_REF_NUM \n"
            + "and trf.RECEIVED_BY is null and TRF.RECD_DATE is null ORDER BY STOCK.TRANS_REF_DATE DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    public List<Map> getWsVehReceiveDetByJobCardNo(String jobCardNo);

//    @Query(value = "select   trf.REG_NO,  nvl(trf.CHASSIS_NO,'-') CHASSIS_NO, nvl(TRF.STOCK_TRF_NO,'-') STOCK_TRF_NO, NVL(TO_CHAR(TRF.STOCK_TRF_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') STOCK_TRF_DATE, \n"
//            + "nvl(trf.VIN,'-') VIN,  nvl(trf.JOBCARDNO,'-') JOBCARDNO, NVL(TRF.MADE_BY,'-') MADE_BY, NVL(TRF.FROM_LOCATION,'-') FROM_LOCATION, NVL(TRF.TO_LOCATION,'-') TO_LOCATION,\n"
//            + "nvl(trf.driver_name,'-') driver_name, nvl(TRF.FROM_LOC_CODE,'-') FROM_LOC_CODE, nvl(trf.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(trf.from_km,0) from_km , nvl(trf.dept,'-') dept, \n"
//            + " nvl(trf.ENGINE_NO,'-') ENGINENO,  stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(stock.MODELDESC,'-') MODEL_DESC,\n"
//            + " NVL(stock.STATUS,'-') STATUS,  NVL(to_char(stock.ERPACCTNO),0) ERPACCTNO, \n"
//            + " nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
//            + "  nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO , nvl(to_char(trf.ou),0) ou\n"
//            + "  from SS_DMS_STOCK_TRF_WS trf , SS_DMS_INV_STOCK_SERVICE stock where trf.reg_no=?1 and TRF.JOBCARDNO=STOCK.JOBCARDNO \n"
//            + "and trf.RECEIVED_BY is null and TRF.RECD_DATE is null ", nativeQuery = true)
//    public List<Map> getWsVehReceiveDetByRegNo(String regNo);
    
    //QUERY FOR STOCK REC FROM TRF WS TABLE & GD FDI(NEW)
    @Query(value = "select   trf.REG_NO,  nvl(trf.CHASSIS_NO,'-') CHASSIS_NO, nvl(TRF.STOCK_TRF_NO,'-') STOCK_TRF_NO, NVL(TO_CHAR(TRF.STOCK_TRF_DATE, 'YYYY-MM-DD HH24:MI:SS'),'-') STOCK_TRF_DATE, \n"
            + "nvl(trf.VIN,'-') VIN,  nvl(trf.JOBCARDNO,'-') JOBCARDNO, NVL(TRF.MADE_BY,'-') MADE_BY, NVL(TRF.FROM_LOCATION,'-') FROM_LOCATION, NVL(TRF.TO_LOCATION,'-') TO_LOCATION,\n"
            + "nvl(trf.driver_name,'-') driver_name, nvl(TRF.FROM_LOC_CODE,'-') FROM_LOC_CODE, nvl(trf.AUTHORISED_BY,'-') AUTHORISED_BY, nvl(trf.from_km,0) from_km , nvl(trf.dept,'-') dept, \n"
            + "nvl(trf.ENGINE_NO,'-') ENGINENO,  NVL(stock.VARIANT_CD,'-') VARIANT_CODE,  NVL(stock.MODEL_CD,'-') MODEL_DESC,\n"
            + "nvl(stock.EXECUTIVE,'-') SERVICEADVISOR, nvl(stock.CUST_NAME,'-') CUSTNAME,nvl(to_char(trf.ou),0) ou\n"
            + "from SS_DMS_STOCK_TRF_WS trf , GD_FDI_TRANS stock where trf.reg_no=?1 and TRF.JOBCARDNO=STOCK.TRANS_REF_NUM\n"
            + "and trf.RECEIVED_BY is null and TRF.RECD_DATE is null ORDER BY TRF.STOCK_TRF_DATE DESC FETCH FIRST 1 ROWS ONLY ", nativeQuery = true)
    public List<Map> getWsVehReceiveDetByRegNo(String regNo);

    @Query(value = "select nvl(reg_no,'-') REG_NO, nvl(chassis_no,'-')chassis_no, nvl(stock_trf_no,'-')stock_trf_no, nvl(to_char(stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,\n"
            + "nvl(jobCardNo,'-')jobCardNo, nvl(made_by,'-')made_by, nvl(from_location,'-')from_location, nvl(to_location,'-')to_location,\n"
            + " nvl(received_by,'-') received_by, nvl(to_char(recd_date,'DD-MM-YYYY HH24:MI:SS'),'-') recd_date,nvl(driver_name,'-') driver_name,nvl(from_km,0) from_km ,\n"
            + " nvl(to_km,0) to_km, nvl(ou,0)ou from ss_dms_stock_trf_ws where reg_no=?1 order by stock_trf_date desc ", nativeQuery = true)
    List<Map> getWsVehTransHist(String regNo);

    //query for getting stk trans details report by from and to date in ss_dms_stock_trf_ws table
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock_trf_date asc) AS sr_no,\n"
            + " nvl(reg_no,'-') REG_NO, nvl(chassis_no,'-') chassis_no, nvl(stock_trf_no,'-') stock_trf_no, nvl(to_char(stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,\n"
            + "nvl(jobCardNo,'-') jobcardno, nvl(engine_no,'-') engine_no, nvl(vin,'-') vin, nvl(made_by,'-') made_by, nvl(from_location,'-') from_location,\n"
            + "nvl(to_location,'-') to_location, nvl(received_by,'-') received_by,  nvl(to_char(recd_date,'DD-MM-YYYY HH24:MI:SS'),'-') recd_date, nvl(driver_name,'-') driver_name,\n"
            + "nvl(authorised_by,'-') authorised_by, nvl(ou,0) ou, nvl(from_km,0) from_km, nvl(to_km,0) to_km, nvl(dept,'-') dept,nvl(created_by,'-') created_by\n"
            + "from ss_dms_stock_trf_ws where ou=:ou  and  stock_trf_date between :fromDate and :toDate", nativeQuery = true)
    public List<Map> getWsStkTransDetailsByOu(Integer ou, Date fromDate, Date toDate);

    //query for getting stk trans details report by from and to date in ss_dms_stock_trf_ws table, service dept
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock_trf_date asc) AS sr_no,\n"
            + " nvl(reg_no,'-') REG_NO, nvl(chassis_no,'-') chassis_no, nvl(stock_trf_no,'-') stock_trf_no, nvl(to_char(stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,\n"
            + "nvl(jobCardNo,'-') jobcardno, nvl(engine_no,'-') engine_no, nvl(vin,'-') vin, nvl(made_by,'-') made_by, nvl(from_location,'-') from_location,\n"
            + "nvl(to_location,'-') to_location, nvl(received_by,'-') received_by,  nvl(to_char(recd_date,'DD-MM-YYYY HH24:MI:SS'),'-') recd_date, nvl(driver_name,'-') driver_name,\n"
            + "nvl(authorised_by,'-') authorised_by, nvl(ou,0) ou, nvl(from_km,0) from_km, nvl(to_km,0) to_km, nvl(dept,'-') dept,nvl(created_by,'-') created_by\n"
            + "from ss_dms_stock_trf_ws where ou=:ou  and dept='SERVICE'  and  stock_trf_date between :fromDate and :toDate", nativeQuery = true)
    public List<Map> getWsStkTransDetailsServByOu(Integer ou, Date fromDate, Date toDate);

    //query for getting stk trans details report by from and to date in ss_dms_stock_trf_ws table , dp dept
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock_trf_date asc) AS sr_no,\n"
            + " nvl(reg_no,'-') REG_NO, nvl(chassis_no,'-') chassis_no, nvl(stock_trf_no,'-') stock_trf_no, nvl(to_char(stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,\n"
            + "nvl(jobCardNo,'-') jobcardno, nvl(engine_no,'-') engine_no, nvl(vin,'-') vin, nvl(made_by,'-') made_by, nvl(from_location,'-') from_location,\n"
            + "nvl(to_location,'-') to_location, nvl(received_by,'-') received_by, nvl(to_char(recd_date,'DD-MM-YYYY HH24:MI:SS'),'-') recd_date , nvl(driver_name,'-') driver_name,\n"
            + "nvl(authorised_by,'-') authorised_by, nvl(ou,0) ou, nvl(from_km,0) from_km, nvl(to_km,0) to_km, nvl(dept,'-') dept,nvl(created_by,'-') created_by\n"
            + "from ss_dms_stock_trf_ws where ou=:ou  and dept='DP' and  stock_trf_date between :fromDate and :toDate", nativeQuery = true)
    public List<Map> getWsStkTransDetailsDpByOu(Integer ou, Date fromDate, Date toDate);

    public Optional<SsDmsStockTrfWs> findFirstByJobCardNoOrderByCreationDateDesc(String jobCardNo);

}
