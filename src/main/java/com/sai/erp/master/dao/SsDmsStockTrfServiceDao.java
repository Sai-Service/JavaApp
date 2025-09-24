/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsStockService;
import com.sai.erp.master.entity.SsDmsStockTrfService;
import com.sai.erp.master.entity.SsDmsStockTrfTruevalue;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IT-HARSH
 */
public interface SsDmsStockTrfServiceDao extends CrudRepository<SsDmsStockTrfService, Integer> {

    public Optional<SsDmsStockTrfService> findByStockTrfNo(String stockTrfNo);

    @Query(value = " select trf.stock_trf_no,trf.stock_trf_date,stock.REGNO REG_NO, nvl(stock.VIN,'-') VIN,nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(stock.chassisno,'-') chassisno,stock.modelDesc,\n"
            + "             stock.VEH_STATUS,  stock.VARIANT,\n"
            + "            trf.MADE_BY transferred_by,trf.FROM_LOCATION,\n"
            + "                        nvl(trf.FROM_KM,0)fromKm, nvl(trf.driver_name,'-') driver_name,TRF.DEPT\n"
            + "                        from ss_dms_inv_stock_service stock,ss_dms_stock_trf_ws trf \n"
            + "                        where trf.TO_LOCATION=?1 and trf.dept=?2\n"
            + "                        and stock.veh_status='Stock Transfer In-Transit'\n"
            + "                        and stock.REGNO=trf.REG_NO \n"
            + "                        and trf.received_by is null\n"
            + "                        and trf.recd_date is null", nativeQuery = true)
    public List<Map> getBySrTransList(String toLocation, String dept);

    @Query(value = " select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,\n"
            + "              trf.stock_trf_no,nvl(to_char(TRF.stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,stock.REGNO, nvl(stock.VIN,'-') VIN, nvl(stock.chassisno,'-') chassisno, \n"
            + "nvl(stock.JOBCARDNO,'-') JOBCARDNO,nvl(STOCK.DEPT,'-') DEPT, nvl(to_char(stock.JOBCARDDATE,'DD-MM-YYYY HH24:MI:SS'),'-') JOBCARDDATE, nvl(stock.COLORDESC,'-') COLORDESC,\n"
            + " nvl(stock.ERPACCTNO,'0') ERPACCTNO, nvl(stock.CUSTNAME,'-') CUSTNAME , nvl(stock.CONTACTNO,'0') CONTACTNO, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "  nvl(stock.TECHNICIAN,'-') TECHNICIAN , nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, \n"
            + "nvl(stock.modeldesc,'-') modeldesc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT,'-') VARIANT, \n"
            + " nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location, \n"
            + "            nvl(trf.FROM_KM,0)from_Km,nvl(trf.to_Km,0)to_Km, nvl(trf.driver_name,'-') driver_name \n"
            + "            from SS_DMS_INV_STOCK_SERVICE stock, SS_DMS_STOCK_TRF_WS trf  \n"
            + "            where stock.veh_status='Stock Transfer In-Transit' \n"
            + "            and stock.REGNO=trf.REG_NO  \n"
            + "            and trf.received_by is null \n"
            + "            and trf.recd_date is null \n"
            + "            and stock.OUID=trf.ou\n"
            + "            and trf.ou=?", nativeQuery = true)
    public List<Map> getSrStockTrfDetailsByOu(Integer ou);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,\n"
            + "              trf.stock_trf_no,nvl(to_char(TRF.stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,stock.REGNO, nvl(stock.VIN,'-') VIN, nvl(stock.chassisno,'-') chassisno, \n"
            + "nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(STOCK.DEPT,'-') DEPT, nvl(to_char(stock.JOBCARDDATE,'DD-MM-YYYY HH24:MI:SS'),'-') JOBCARDDATE, nvl(stock.COLORDESC,'-') COLORDESC,\n"
            + " nvl(stock.ERPACCTNO,'0') ERPACCTNO, nvl(stock.CUSTNAME,'-') CUSTNAME , nvl(stock.CONTACTNO,'0') CONTACTNO, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "  nvl(stock.TECHNICIAN,'-') TECHNICIAN , nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, \n"
            + "nvl(stock.modeldesc,'-') modeldesc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT,'-') VARIANT, \n"
            + " nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location, \n"
            + "            nvl(trf.FROM_KM,0)from_Km,nvl(trf.to_Km,0)to_Km, nvl(trf.driver_name,'-') driver_name \n"
            + "            from SS_DMS_INV_STOCK_SERVICE stock, SS_DMS_STOCK_TRF_WS trf  \n"
            + "            where stock.veh_status='Stock Transfer In-Transit' \n"
            + "            and stock.REGNO=trf.REG_NO  \n"
            + "            and trf.received_by is null \n"
            + "            and trf.recd_date is null \n"
            + "            and stock.OUID=trf.ou\n"
            + "            and trf.ou=?1 AND stock.dept='SERVICE' ", nativeQuery = true)
    public List<Map> getSrStockTrfDetailsServByOu(Integer ou);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,\n"
            + "              trf.stock_trf_no,nvl(to_char(TRF.stock_trf_date,'DD-MM-YYYY HH24:MI:SS'),'-') stock_trf_date,stock.REGNO, nvl(stock.VIN,'-') VIN, nvl(stock.chassisno,'-') chassisno, \n"
            + "nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(STOCK.DEPT,'-') DEPT, nvl(to_char(stock.JOBCARDDATE,'DD-MM-YYYY HH24:MI:SS'),'-') JOBCARDDATE, nvl(stock.COLORDESC,'-') COLORDESC,\n"
            + " nvl(stock.ERPACCTNO,'0') ERPACCTNO, nvl(stock.CUSTNAME,'-') CUSTNAME , nvl(stock.CONTACTNO,'0') CONTACTNO, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "  nvl(stock.TECHNICIAN,'-') TECHNICIAN , nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, \n"
            + "nvl(stock.modeldesc,'-') modeldesc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT,'-') VARIANT, \n"
            + " nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location, \n"
            + "            nvl(trf.FROM_KM,0)from_Km,nvl(trf.to_Km,0)to_Km, nvl(trf.driver_name,'-') driver_name \n"
            + "            from SS_DMS_INV_STOCK_SERVICE stock, SS_DMS_STOCK_TRF_WS trf  \n"
            + "            where stock.veh_status='Stock Transfer In-Transit' \n"
            + "            and stock.REGNO=trf.REG_NO  \n"
            + "            and trf.received_by is null \n"
            + "            and trf.recd_date is null \n"
            + "            and stock.OUID=trf.ou\n"
            + "            and trf.ou=?1 AND stock.dept='DP' ", nativeQuery = true)
    public List<Map> getSrStockTrfDetailsDpByOu(Integer ou);

    //QUERY FOR VEH TRF DETAILS BY JBC NO IN SERV STOCK TABLE (OLD)
//     @Query(value = "select   stock.REGNO,  nvl(stock.CHASSISNO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN,  nvl(stock.JOBCARDNO,'-') JOBCARDNO,  nvl(to_char(stock.JOBCARDDATE),0) JOBCARDDATE ,\n" +
//" nvl(stock.ENGINNO,'-') ENGINENO,  stock.VEH_STATUS,  NVL(stock.VARIANT,'-') VARIANT_CODE,  NVL(STOCK.MODELDESC,'-') MODEL_DESC,\n" +
//" NVL(STOCK.STATUS,'-') STATUS,  NVL(to_char(STOCK.ERPACCTNO),0) ERPACCTNO,   nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, \n" +
//" nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR, NVL(STOCK.DEPT,'-') DEPT, NVL(STOCK.DMSLOCATION,'-') DMSLOCATION,\n" +
//"  nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO , nvl(to_char(stock.ouId),0) ouId,\n" +
//"  (SELECT DISTINCT TRANS_SEGMENT FROM GD_FDI_TRANS where TRANS_REF_NUM=stock.JOBCARDNO AND LOC_CD=STOCK.DMSLOCATION and rownum=1) transSegment\n" +
//"  from SS_DMS_INV_STOCK_SERVICE stock where stock.jobCardNo=?1", nativeQuery = true)
//    public List<Map> getWsVehTransDetByJobCardNo(String jobCardNo); 
    //QUERY FOR VEH TRF DETAILS BY JBC NO IN GD FDI (NEW)
    @Query(value = "select NVL(GFT.GE1,'-') REGNO, NVL(GFT.CHASSIS_NUM,'-') CHASSIS_NO, NVL(GFT.VIN,'-') VIN, NVL(GFT.TRANS_REF_NUM,'-') JOBCARDNO, nvl(GFT.TRANS_REF_DATE,'-') JOBCARDDATE,\n"
            + "NVL(GFT.ENGINE_NUM,'-') ENGINENO, NVL(GFT.VARIANT_CD,'-') VARIANT_CODE, NVL(MODEL_CD,'-') MODEL_DESC, NVL(LOC.ERP_LOC,'-') LOCATION,\n"
            + "NVL(GFT.EXECUTIVE,'-') SERVICEADVISOR,NVL(GFT.TRANS_SEGMENT,'-') transSegment, NVL(GFT.LOC_CD,'-') DMSLOCATION, NVL(GFT.CUST_NAME,'-') CUSTNAME,\n"
            + "NVL(LOC.ORG_ID,0) OUID from gd_fdi_trans GFT, CSI_ITEM_INSTANCES CSI, SS_DMS_LOCATION_MASTER LOC\n"
            + "WHERE GFT.TRANS_TYPE='WI'  AND GFT.GE1=CSI.INSTANCE_NUMBER AND GFT.TRANS_REF_NUM=?1 AND GFT.LOC_CD=LOC.LOC_CD  \n"
            + "ORDER BY GFT.TRANS_REF_DATE DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    public List<Map> getWsVehTransDetByJobCardNo(String jobCardNo);

    @Query(value = " select NVL(GFT.GE1,'-') REGNO, NVL(GFT.CHASSIS_NUM,'-') CHASSIS_NO, NVL(GFT.VIN,'-') VIN, NVL(GFT.TRANS_REF_NUM,'-') JOBCARDNO, nvl(GFT.TRANS_REF_DATE,'-') JOBCARDDATE,\n"
            + "NVL(GFT.ENGINE_NUM,'-') ENGINENO, NVL(GFT.VARIANT_CD,'-') VARIANT_CODE, NVL(MODEL_CD,'-') MODEL_DESC, NVL(LOC.ERP_LOC,'-') LOCATION,\n"
            + "NVL(GFT.EXECUTIVE,'-') SERVICEADVISOR,NVL(GFT.TRANS_SEGMENT,'-') transSegment, NVL(GFT.LOC_CD,'-') DMSLOCATION, NVL(GFT.CUST_NAME,'-') CUSTNAME,\n"
            + "NVL(LOC.ORG_ID,0) OUID from gd_fdi_trans GFT, CSI_ITEM_INSTANCES CSI, SS_DMS_LOCATION_MASTER LOC\n"
            + "WHERE GFT.TRANS_TYPE='WI'  AND GFT.GE1=CSI.INSTANCE_NUMBER AND GFT.GE1=?1 AND GFT.LOC_CD=LOC.LOC_CD  \n"
            + "ORDER BY GFT.TRANS_REF_DATE DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    public List<Map> getWsVehTransDetByRegNo(String regNo);

}
