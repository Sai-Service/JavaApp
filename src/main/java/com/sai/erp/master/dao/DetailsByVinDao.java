/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.AndroidVehQr;
import com.sai.erp.master.entity.DetailsByVin;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface DetailsByVinDao extends JpaRepository<DetailsByVin, Long> {
    
    
//     @Query(value="select VARIANT_CD,MODEL_CD,CHASSIS_NUM,ENGINE_NUM,TRANS_REF_NUM dmsinvno,  \n" +
//" from gd_fdi_trans where TRANS_TYPE='VD'and VIN=?1", nativeQuery = true)
    
    
     @Query(value="  select a.* from (\n" +
"select stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, stock.veh_status,\n" +
"nvl(gft.trans_ref_num,'-')trans_ref_num,\n" +
"nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft1.utd),'0') GatepassYN,stock.location,nvl(stock.status,'-')status,\n" +
"            nvl((select distinct stock_trf_no from ss_dms_stock_trf where vin=stock.vin and to_location=stock.location and rownum=1 ),'-')stkNo,  \n" +
"nvl((select FROM_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') FROM_LOCATION,\n" +
"nvl((select TO_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') to_loc,\n" +
"(select STOCK_TRF_DATE from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1) STOCK_TRF_DATE,\n" +
"nvl((select frmKm from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1),0) frmKm\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"where 1= 1 \n" +
"and stock.vin=gft.vin \n" +
"and gft.trans_type='VS'\n" +
"and gft.trans_ref_num= gft1.trans_id\n" +
"and gft.loc_cd=gft1.loc_cd\n" +
"and gft1.trans_type='ORDBK'\n" +
"union\n" +
"select stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, stock.veh_status,\n" +
"nvl(null,'-') trans_ref_num ,nvl(0,1) GatepassYN ,stock.location, nvl(stock.status,'-')status ,\n" +
"             + nvl((select distinct stock_trf_no from ss_dms_stock_trf where vin=stock.vin and to_location=stock.location and rownum=1 ),'-')stkNo,\n" +
"nvl((select FROM_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') FROM_LOCATION,\n" +
"nvl((select TO_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') to_loc,\n" +
"(select STOCK_TRF_DATE from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1) STOCK_TRF_DATE,\n" +
"nvl((select frmKm from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1),0) frmKm\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft \n" +
"where 1=1 \n" +
"and gft.vin=stock.vin \n" +
"and gft.trans_type='VD'\n" +
"and gft.vin not in (select vin from gd_fdi_trans where gft.vin=vin and trans_type='VS') \n" +
")a where a.vin =?1", nativeQuery = true)
    public List<Map>getByVinNumber(String vin);

    
      
     @Query(value=" select a.* from (\n" +
"select stock.vin,stock.chassis_no ChassisNo, stock.model_desc modelCd, stock.VARIANT_CODE variantCd,stock.engine_no engineNo ,stock.mul_inv_no dmsInvNo, stock.veh_status vehStatus,\n" +
"gft.trans_ref_num soNo,\n" +
"nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft1.utd),'0') GatepassYN,\n" +
"nvl((select to_char(DATE_OF_DELIVERY,'dd-MM-yyyy hh:mm:ss') from ss_gate_pass_sales where order_number =gft1.utd),null)DATE_OF_DELIVERY,\n" +
"nvl((select vehicle_no from ss_gate_pass_sales where order_number =gft1.utd),'-')vehicleNo,\n" +
"nvl((select CUSTOMER_NAME from ss_gate_pass_sales where order_number =gft1.utd),'-')CUSTOMER_NAME,\n" +
"stock.location, stock.status , nvl(stock.key_no,'-')key_no,( stock.COLOUR_CODE ||'-'||stock.COLOUR) color,\n" +
"(stock.VARIANT_CODE||'-'|| stock.model_desc) VARIANT_CD\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"where 1= 1 \n" +
"and stock.vin=gft.vin and stock.veh_status in ('DELIVERED') \n" +
"and stock.status = 'Ready For Delivery'\n" +
"and gft.trans_type='VS'\n" +
"and gft.trans_ref_num= gft1.trans_id\n" +
"and gft.loc_cd=gft1.loc_cd\n" +
"and gft1.trans_type='ORDBK'\n" +
"union\n" +
"select stock.vin,stock.chassis_no ChassisNo, stock.model_desc modelCd, stock.VARIANT_CODE variantCd,stock.engine_no engineNo ,stock.mul_inv_no dmsinvno, stock.veh_status vehStatus, \n" +
"null sobNo ,nvl(0,1) GatepassYN ,\n" +
"nvl (null,null)DATE_OF_DELIVERY,\n" +
"nvl(null,'-') vehicleNo,\n" +
"nvl(null,'-')CUSTOMER_NAME,\n" +
"stock.location, stock.status , nvl(stock.key_no,'-')key_no,( stock.COLOUR_CODE ||'-'||stock.COLOUR) color,\n" +
"(stock.VARIANT_CODE||'-'|| stock.model_desc) VARIANT_CD\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft \n" +
"where 1=1 \n" +
"and gft.vin=stock.vin and stock.veh_status in ('DELIVERED') \n" +
"and stock.status = 'Ready For Delivery'\n" +
"and gft.trans_type='VD'\n" +
"and gft.vin not in (select vin from gd_fdi_trans where gft.vin=vin and trans_type='VS')  \n" +
")a where a.vin =?1 ", nativeQuery = true)
    public List<Map>getByVinNumberDelv(String vin);
    
    
    @Query(value=" select a.* from (\n" +
"select stock.operating_unit, stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, stock.veh_status\n" +
", nvl(gft.trans_ref_num,'-')trans_ref_num,\n" +
"nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft1.utd),'0') GatepassYN,stock.location, nvl(stock.status,'-')status,\n" +
"nvl((select distinct stock_trf_no from ss_dms_stock_trf where vin=stock.vin and to_location=stock.location and rownum=1 ),'-')stkNo,\n" +
"nvl((select FROM_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') FROM_LOCATION,\n" +
"nvl((select TO_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') to_loc,\n" +
"(select STOCK_TRF_DATE from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1) STOCK_TRF_DATE,\n" +
"nvl((select frmKm from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1),0) frmKm\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"where 1= 1 \n" +
"and stock.vin=gft.vin \n" +
"and gft.trans_type='VS'\n" +
"and gft.trans_ref_num= gft1.trans_id\n" +
"and gft.loc_cd=gft1.loc_cd\n" +
"and gft1.trans_type='ORDBK'\n" +
"union\n" +
"select stock.operating_unit, stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, stock.veh_status, \n" +
"nvl(null,'-') trans_ref_num ,nvl(0,1) GatepassYN,stock.location, nvl(stock.status,'-')status,\n" +
"nvl((select distinct stock_trf_no from ss_dms_stock_trf where vin=stock.vin and to_location=stock.location and rownum=1 ),'-')stkNo,\n" +
"nvl((select FROM_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') FROM_LOCATION,\n" +
"nvl((select TO_LOCATION from  ss_dms_stock_trf b where b.vin=stock.vin and RECD_DATE is null and rownum=1),'-') to_loc,\n" +
"(select STOCK_TRF_DATE from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1) STOCK_TRF_DATE,\n" +
"nvl((select frmKm from  ss_dms_stock_trf b where  b.vin=stock.vin and RECD_DATE is null and rownum=1),0) frmKm\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft \n" +
"where 1=1 \n" +
"and gft.vin=stock.vin\n" +
"and gft.trans_type='VD'\n" +
"and gft.vin not in (select vin from gd_fdi_trans where gft.vin=vin and trans_type='VS')  \n" +
")a where a.CHASSIS_NUM =?1 and a.operating_unit=?2", nativeQuery = true)
    public List<Map>getByChassisNo(String chassisNo, Integer ouId);
   
    
//    ********Sequirity Find chassis
  
   @Query(value=" select a.* from (select stock.operating_unit,stock.vin,stock.chassis_no CHASSIS_NUM, stock.engine_no engine_num ,\n" +
"stock.mul_inv_no dmsinvno, stock.veh_status \n" +
", gft.trans_ref_num,\n" +
"nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft1.utd),'0') GatepassYN ,\n" +
"nvl((select to_char(DATE_OF_DELIVERY,'dd-MM-yyyy hh:mm:ss') from ss_gate_pass_sales where order_number =gft1.utd),null)DATE_OF_DELIVERY,\n" +
"nvl((select vehicle_no from ss_gate_pass_sales where order_number =gft1.utd),null)vehicleNo,\n" +
"nvl((select CUSTOMER_NAME from ss_gate_pass_sales where order_number =gft1.utd),'-')CUSTOMER_NAME,\n" +
"stock.location, stock.status, nvl(stock.key_no,'-')key_no,( stock.COLOUR_CODE ||'-'||stock.COLOUR) color,\n" +
"(stock.VARIANT_CODE||'-'|| stock.model_desc) VARIANT_CD\n" +
"from ss_dms_inv_stock stock, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"where 1= 1 \n" +
"and stock.vin=gft.vin \n" +
"and gft.trans_type='VS'\n" +
"and gft.trans_ref_num= gft1.trans_id\n" +
"and gft.loc_cd=gft1.loc_cd\n" +
"and gft1.trans_type='ORDBK'\n" +
"and stock.VEH_STATUS in ('DELIVERED')\n" +
"and stock.status='Ready For Delivery'\n" +
")a \n" +
"where a.CHASSIS_num =?1 and a.operating_unit=?2 ", nativeQuery = true)
    public List<Map>getByChassisNoForDelv(String chassisNo, Integer ouId);  
    
//     @Query(value=" select a.* from (select stock.operating_unit,stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, stock.veh_status \n" +
//", gft.trans_ref_num,\n" +
//"nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft1.utd),'0') GatepassYN ,stock.location, stock.status \n" +
//"from ss_dms_inv_stock stock, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
//"where 1= 1 \n" +
//"and stock.vin=gft.vin \n" +
//"and gft.trans_type='VS'\n" +
//"and gft.trans_ref_num= gft1.trans_id\n" +
//"and gft.loc_cd=gft1.loc_cd\n" +
//"and gft1.trans_type='ORDBK'\n" +
//"and stock.VEH_STATUS not in ('DELIVERED')\n" +
//"and stock.CHASSIS_NO not in (select CHASSIS_NO from ANDROID_VEH_QR where stock.CHASSIS_NO=CHASSIS_NO and VEHSTATUS  like 'DELIVERED')\n" +
//")a \n" +
//"where a.CHASSIS_num =?1 and a.operating_unit=?2 ", nativeQuery = true)
    
    //changed by harsh 11 july 2024
      @Query(value=" select stock.operating_unit,stock.vin,stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.variant_desc,stock.engine_no engine_num\n" +
"  ,stock.mul_inv_no dmsinvno, stock.veh_status ,stock.location, stock.status \n" +
"from ss_dms_inv_stock stock where stock.CHASSIS_NO =?1 And stock.operating_unit=?2\n" +
"and stock.VEH_STATUS not in ('DELIVERED') ", nativeQuery = true)
    public List<Map>getByChassisNoBatch(String chassisNo, Integer ouId);  
  
}
