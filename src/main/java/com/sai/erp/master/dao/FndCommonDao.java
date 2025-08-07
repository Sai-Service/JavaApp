/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.FndCommonLookup;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jyoti K
 */
public interface FndCommonDao extends CrudRepository<FndCommonLookup, Integer> {

//    List<FndCommonLookup> getBycmnType(String cmnType);
    public Optional<FndCommonLookup> findByCmnType(String cmnType);
//    public Optional<FndCommonLookups> findBycmnidAndcmntype(Integer cmnid,String cmntype);

    List<FndCommonLookup> findByStatus(String status);

    public Optional<FndCommonLookup> findBycmnDesc(String cmnDesc);

    @Query(value = " SELECT distinct cmnType FROM fnd_common_lookup  where status in (?1) ", nativeQuery = true)
    public List<String> getBystatus(String status);

    @Query(value = "select fcl.cmnCode username,fcl.cmnDesc emp_name from ss_jobslip_login sjl,fnd_common_lookup fcl where sjl.loginname=?1 AND sjl.attribute1='ADMIN' \n"
            + "and fcl.cmntype='Executive' and fcl.attribute3=sjl.dmsLoc and sjl.dmsLoc=?2 and fcl.status='Active' AND fcl.attribute2='USER'", nativeQuery = true)
    public List<Map> getExeList(String login_name, String dmsLoc);

    public Optional<FndCommonLookup> findByCmnCode(String attribute2);

    public Optional<FndCommonLookup> findByAttribute1(String attribute1);

    @Query(value = "SELECT ATTRIBUTE1 , CMNDESC FROM FND_COMMON_LOOKUP WHERE CMNTYPE=?1", nativeQuery = true)
    public List<Map> getLocByCmnType(String cmnType);

     @Query(value = "SELECT  CMNDESC FROM FND_COMMON_LOOKUP  WHERE CMNTYPE=?1", nativeQuery = true)
    public List<Map> getTestDriveDetailsByCmnType(String cmnType);
    
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.VIN,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-') FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC, \n"
            + "              a.COLOUR_CODE || '-' || a.COLOUR COLOUR,nvl(a.GRN_NO,'-') GRN_NO,nvl(to_char(a.GRN_DATE),'-') GRN_DATE,a.GRN_RECD_DATE, nvl(to_char(trunc(sysdate - a.grn_date)),'-')  as AGEING ,a.MUL_INV_NO, a.MUL_INV_DT, \n"
            + "             (select 'STock Transfer '||decode(b.recd_date,null,'Pending Receiving at '|| b.to_location,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stk_remark, \n"
            + "             (select decode(b.recd_date,null,b.stock_trf_no,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stock_trf_no , \n"
            + "             a.PURCHASE_PRICE,a.RATE_OF_INTEREST,nvl(a.ALLOTMENT_NO,'-')ALLOTMENT_NO, \n"
            + "             nvl(to_char(a.ALLOTMENT_DT),'-')ALLOTMENT_DT,nvl(a.ORDER_NUMBER,'-')sobNo,a.OPERATING_UNIT,a.NET_BASIC,nvl(to_char(a.TRANSPORT_AMOUNT),'-')TRANSPORT_AMOUNT,a.SERVICE_CHARGES, \n"
            + "             a.CGST_PAYABLE,a.SGST_PAYABLE,a.IGST_PAYABLE,a.CESS_PAYABLE,a.SPOT_DISCOUNT,a.DISCOUNT,a.LOCATION,a.VEH_STATUS,NVL(a.status,'-')status, a.DEALER_LOCATION, \n"
            + "             nvl(a.KEY_NO,'-') KEY_NO \n"
            + "             , \n"
            + "             nvl((select gd.TRANS_ID \n"
            + "             from gd_fdi_trans gd \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1),'-' )trans_id,  \n"
            + "             nvl((select gfd1.TRANS_DATE \n"
            + "             from gd_fdi_trans gd, gd_fdi_trans gfd1 \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.TRANS_REF_NUM=gfd1.trans_id \n"
            + "             and gd.loc_cd=gfd1.loc_cd \n"
            + "             and gfd1.trans_type='ORDBK' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')Bookin_Date, \n"
            + "             nvl((select rcta.trx_number \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')trx_number, \n"
            + "             nvl((select rcta.trx_date \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),null)trx_date,\n"
            + "             nvl( nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n"
            + "nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') PARTY_NAME\n"
            + "             from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV\n"
            + "             where OPERATING_UNIT = ?1\n"
            + "             and  A.VARIANT_CODE=FLV.LOOKUP_CODE and veh_status <> 'DELIVERED' order by a.MODEL_DESC,a.GRN_DATE", nativeQuery = true)
    public List<Map> getStockDetailsAll(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.VIN,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-') FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC, \n"
            + "              a.COLOUR_CODE || '-' ||\n"
            + "             a.COLOUR COLOUR,nvl(a.GRN_NO,'-') GRN_NO,nvl(to_char(a.GRN_DATE),'-') GRN_DATE,a.GRN_RECD_DATE, nvl(to_char(trunc(sysdate - a.grn_date)),'-')  as AGEING , a.MUL_INV_NO, a.MUL_INV_DT, \n"
            + "             (select 'STock Transfer '||decode(b.recd_date,null,'Pending Receiving at '|| b.to_location,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stk_remark, \n"
            + "             (select decode(b.recd_date,null,b.stock_trf_no,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stock_trf_no , \n"
            + "             a.PURCHASE_PRICE,a.RATE_OF_INTEREST,nvl(a.ALLOTMENT_NO,'-')ALLOTMENT_NO, \n"
            + "             nvl(to_char(a.ALLOTMENT_DT),'-')ALLOTMENT_DT,nvl(a.ORDER_NUMBER,'-')sobNo,a.OPERATING_UNIT,a.NET_BASIC,nvl(to_char(a.TRANSPORT_AMOUNT),'-')TRANSPORT_AMOUNT,a.SERVICE_CHARGES, \n"
            + "             a.CGST_PAYABLE,a.SGST_PAYABLE,a.IGST_PAYABLE,a.CESS_PAYABLE,a.SPOT_DISCOUNT,a.DISCOUNT,a.LOCATION,a.VEH_STATUS,NVL(a.status,'-')status, a.DEALER_LOCATION, \n"
            + "             nvl(a.KEY_NO,'-')KEY_NO \n"
            + "             , \n"
            + "             nvl((select gd.TRANS_ID \n"
            + "             from gd_fdi_trans gd \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1),'-' )trans_id,  \n"
            + "             nvl((select gfd1.TRANS_DATE \n"
            + "             from gd_fdi_trans gd, gd_fdi_trans gfd1 \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.TRANS_REF_NUM=gfd1.trans_id \n"
            + "             and gd.loc_cd=gfd1.loc_cd \n"
            + "             and gfd1.trans_type='ORDBK' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')Bookin_Date, \n"
            + "             nvl((select rcta.trx_number \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')trx_number, \n"
            + "             nvl((select rcta.trx_date \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),null)trx_date,\n"
            + "             nvl( nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n"
            + "nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') PARTY_NAME\n"
            + "             from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV \n"
            + "             where OPERATING_UNIT =?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n"
            + "                and upper(flv.description) like upper('%NEXA%')  \n"
            + "             and veh_status <> 'DELIVERED' order by a.MODEL_DESC,a.GRN_DATE", nativeQuery = true)
    public List<Map> getStockDetailsNexa(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.VIN,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-') FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC, \n"
            + "              a.COLOUR_CODE || '-' || a.COLOUR COLOUR,nvl(a.GRN_NO,'-') GRN_NO,nvl(to_char(a.GRN_DATE),'-') GRN_DATE,a.GRN_RECD_DATE, nvl(to_char(trunc(sysdate - a.grn_date)),'-')  as AGEING ,a.MUL_INV_NO, a.MUL_INV_DT, \n"
            + "             (select 'STock Transfer '||decode(b.recd_date,null,'Pending Receiving at '|| b.to_location,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stk_remark, \n"
            + "             (select decode(b.recd_date,null,b.stock_trf_no,'Received') \n"
            + "             from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "             and b.to_location = a.location AND b.RECD_DATE is null and rownum=1) stock_trf_no , \n"
            + "             a.PURCHASE_PRICE,a.RATE_OF_INTEREST,nvl(a.ALLOTMENT_NO,'-')ALLOTMENT_NO, \n"
            + "             nvl(to_char(a.ALLOTMENT_DT),'-')ALLOTMENT_DT,nvl(a.ORDER_NUMBER,'-')sobNo,a.OPERATING_UNIT,a.NET_BASIC,nvl(to_char(a.TRANSPORT_AMOUNT),'-')TRANSPORT_AMOUNT,a.SERVICE_CHARGES, \n"
            + "             a.CGST_PAYABLE,a.SGST_PAYABLE,a.IGST_PAYABLE,a.CESS_PAYABLE,a.SPOT_DISCOUNT,a.DISCOUNT,a.LOCATION,a.VEH_STATUS,NVL(a.status,'-')status, a.DEALER_LOCATION, \n"
            + "             nvl(a.KEY_NO,'-')KEY_NO \n"
            + "             , \n"
            + "             nvl((select gd.TRANS_ID \n"
            + "             from gd_fdi_trans gd \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1),'-' )trans_id, \n"
            + "             nvl((select gfd1.TRANS_DATE \n"
            + "             from gd_fdi_trans gd, gd_fdi_trans gfd1 \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and gd.TRANS_REF_NUM=gfd1.trans_id \n"
            + "             and gd.loc_cd=gfd1.loc_cd \n"
            + "             and gfd1.trans_type='ORDBK' \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')Bookin_Date, \n"
            + "             nvl((select rcta.trx_number \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),'-')trx_number, \n"
            + "             nvl((select rcta.trx_date \n"
            + "             from gd_fdi_trans gd, ra_customer_trx_all rcta \n"
            + "             where gd.vin=a.vin \n"
            + "             and gd.trans_type='VS' \n"
            + "             and rcta.attribute4=gd.trans_id \n"
            + "             and rcta.ATTRIBUTE7=gd.loc_cd \n"
            + "             and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id) \n"
            + "             and rownum=1 \n"
            + "             ),null)trx_date,\n"
            + "            nvl( nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n"
            + "nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n"
            + "and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') PARTY_NAME\n"
            + "             from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV \n"
            + "             where OPERATING_UNIT = ?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n"
            + "    and upper(flv.description) NOT like upper('%NEXA%') \n"
            + "             and veh_status <> 'DELIVERED' order by a.MODEL_DESC,a.GRN_DATE", nativeQuery = true)
    public List<Map> getStockDetailsArena(Integer ouId);

    @Query(value = "select a.VIN,a.CHASSIS_NO,a.ENGINE_NO,a.MODEL_DESC,a.FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC,\n"
            + " a.COLOUR_CODE || '-' || a.COLOUR COLOUR,nvl(a.GRN_NO,'-') GRN_NO,nvl(to_char(a.GRN_DATE),'-') GRN_DATE,a.GRN_RECD_DATE, nvl(to_char(trunc(sysdate - a.grn_date)),'-')  as AGEING ,a.MUL_INV_NO, a.MUL_INV_DT,\n"
            + "(select 'STock Transfer '||decode(b.recd_date,null,'Pending Receiving at '|| b.to_location,'Received')\n"
            + "from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "and b.to_location = a.location AND a.rate_of_interest=b.stock_trf_no) stk_remark,\n"
            + "(select decode(b.recd_date,null,b.stock_trf_no,'Received')\n"
            + "from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "and b.to_location = a.location AND a.rate_of_interest=b.stock_trf_no) stock_trf_no ,\n"
            + "a.PURCHASE_PRICE,a.RATE_OF_INTEREST,nvl(a.ALLOTMENT_NO,'-')ALLOTMENT_NO,\n"
            + "nvl(to_char(a.ALLOTMENT_DT),'-')ALLOTMENT_DT,nvl(a.ORDER_NUMBER,'-')sobNo,a.OPERATING_UNIT,a.NET_BASIC,nvl(to_char(a.TRANSPORT_AMOUNT),'-')TRANSPORT_AMOUNT,a.SERVICE_CHARGES,\n"
            + "a.CGST_PAYABLE,a.SGST_PAYABLE,a.IGST_PAYABLE,a.CESS_PAYABLE,a.SPOT_DISCOUNT,a.DISCOUNT,a.LOCATION,a.VEH_STATUS,a.DEALER_LOCATION,\n"
            + "nvl(a.KEY_NO,'-') KEY_NO \n"
            + ",\n"
            + "nvl((select gd.TRANS_ID \n"
            + "from gd_fdi_trans gd\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1),'-' )trans_id,\n"
            + "nvl((select sob.ERP_ACC_NO\n"
            + "from gd_fdi_trans gd, ss_dms_erp_sob_dtls sob\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and gd.trans_ref_num=sob.TRANS_ID\n"
            + "and sob.LOC_CD=gd.LOC_CD\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1\n"
            + "),'-')cust_acc_no,\n"
            + "nvl((select hp.PARTY_NAME\n"
            + "from gd_fdi_trans gd, ss_dms_erp_sob_dtls sob, hz_cust_accounts hca,hz_parties hp\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and gd.trans_ref_num=sob.TRANS_ID\n"
            + "and sob.LOC_CD=gd.LOC_CD\n"
            + "and sob.ERP_ACC_NO=hca.account_number\n"
            + "and hca.party_id=hp.party_id\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1\n"
            + "),'-') party_name,\n"
            + "nvl((select gfd1.TRANS_DATE\n"
            + "from gd_fdi_trans gd, gd_fdi_trans gfd1\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and gd.TRANS_REF_NUM=gfd1.trans_id\n"
            + "and gd.loc_cd=gfd1.loc_cd\n"
            + "and gfd1.trans_type='ORDBK'\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1\n"
            + "),'-')Bookin_Date,\n"
            + "nvl((select rcta.trx_number\n"
            + "from gd_fdi_trans gd, ra_customer_trx_all rcta\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and rcta.attribute4=gd.trans_id\n"
            + "and rcta.ATTRIBUTE7=gd.loc_cd\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1\n"
            + "),'-')trx_number,\n"
            + "nvl((select rcta.trx_date\n"
            + "from gd_fdi_trans gd, ra_customer_trx_all rcta\n"
            + "where gd.vin=a.vin\n"
            + "and gd.trans_type='VS'\n"
            + "and rcta.attribute4=gd.trans_id\n"
            + "and rcta.ATTRIBUTE7=gd.loc_cd\n"
            + "and gd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gd.trans_id)\n"
            + "and rownum=1\n"
            + "),null)trx_date\n"
            + "from ss_dms_inv_stock a\n"
            + "where veh_status <> 'DELIVERED'", nativeQuery = true)
    public List<Map> getStockDetailsAllOU();

    @Query(value = "select a.DEALER_LOCATION,a.MODEL_DESC,a.FUEL_DESC,a.VARIANT_DESC,a.COLOUR,a.CHASSIS_NO,a.ENGINE_NO,a.VEHSTATUS,a.VIN, \n"
            + "(select 'STock Transfer '||decode(b.recd_date,null,'Pending Receiving at '|| b.to_location,'Received') \n"
            + "from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "and b.to_location = a.location AND a.rate_of_interest=b.stock_trf_no) stk_remark, \n"
            + "(select decode(b.recd_date,null,b.stock_trf_no,'Received') \n"
            + "from  ss_dms_stock_trf b where b.chassis_no=a.chassis_no \n"
            + "and b.to_location = a.location AND a.rate_of_interest=b.stock_trf_no) stock_trf_no  from ss_dms_inv_stock a \n"
            + "where a.OPERATING_UNIT =?1 and vehstatus != 'DELIVERED' and a.chassis_no = ?2 ", nativeQuery = true)
    public List<Map> getStockDetailsByOuChassis(Integer ouId, String chassis_no);

    @Query(value = "select VIN from ss_dms_inv_stock WHERE CHASSIS_NO=?1 ", nativeQuery = true)
    public List<Map> getVinNoByChassis(String chassis_no);

    @Query(value = "SELECT Ss_Dms_Inv_Stock.chassis_no,\n"
            + "        Ss_Dms_Inv_Stock.model_desc,\n"
            + "        Ss_Dms_Inv_Stock.variant_desc,\n"
            + "        Ss_Dms_Inv_Stock.fuel_desc,\n"
            + "        Ss_Dms_Inv_Stock.colour,\n"
            + "        Ss_Dms_Inv_Stock.vin,\n"
            + "        Ss_Dms_Inv_Stock.engine_no,\n"
            + "        Ss_Dms_Inv_Stock.veh_status,\n"
            + "        nvl(Ss_Dms_Inv_Stock.status,'-')status,\n"
            + "        nvl(Ss_Dms_Inv_Stock.allotment_no,'-')allotment_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.allotment_dt),'-')allotment_dt,\n"
            + "        nvl(Ss_Dms_Inv_Stock.order_number,'-')sobNo,\n"
            + "        nvl(Ss_Dms_Inv_Stock.LOCATION,'-')LOCATION,\n"
            + "        nvl(Ss_Dms_Inv_Stock.dealer_location,'-')dealer_location,\n"
            + "       nvl( Ss_Dms_Inv_Stock.mul_inv_no,'-')mul_inv_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.mul_inv_dt),'-')mul_inv_dt,\n"
            + "        nvl(Ss_Dms_Inv_Stock.grn_no,'-')grn_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.grn_date),'-')grn_date,\n"
            + "  nvl(to_char(trunc(SYSDATE - Ss_Dms_Inv_Stock.GRN_DATE)),'-') AS AGEING, \n"
            + "        nvl(Ss_Dms_Inv_Stock.remarks1,'-')remarks1,\n"
            + "       nvl(ANDROID_VEH_IMAGE.vehImage,'-')vehImage, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage2,'-')vehImage2, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage3,'-')vehImage3, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage4,'-')vehImage4, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage5,'-')vehImage5, \n"
            + "nvl(to_char(ANDROID_VEH_IMAGE.lastUploadDate),'-')lastUploadDate, \n"
            + "nvl(ANDROID_VEH_IMAGE.lastUploadedBy,'-')lastUploadedBy \n"
            + "        FROM Ss_Dms_Inv_Stock\n"
            + "        LEFT JOIN ANDROID_VEH_IMAGE ON Ss_Dms_Inv_Stock.chassis_no = ANDROID_VEH_IMAGE.chassis_no\n"
            + "        WHERE Ss_Dms_Inv_Stock.chassis_no = ?1", nativeQuery = true)
    public List<Map> getStockDetailsByChassis(String chassis_no);

    @Query(value = "SELECT Ss_Dms_Inv_Stock.chassis_no,\n"
            + "        Ss_Dms_Inv_Stock.model_desc,\n"
            + "        Ss_Dms_Inv_Stock.variant_desc,\n"
            + "        Ss_Dms_Inv_Stock.fuel_desc,\n"
            + "        Ss_Dms_Inv_Stock.colour,\n"
            + "        Ss_Dms_Inv_Stock.vin,\n"
            + "        Ss_Dms_Inv_Stock.engine_no,\n"
            + "        Ss_Dms_Inv_Stock.veh_status,\n"
            + "        nvl(Ss_Dms_Inv_Stock.status,'-')status,\n"
            + "        nvl(Ss_Dms_Inv_Stock.allotment_no,'-')allotment_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.allotment_dt),'-')allotment_dt,\n"
            + "        nvl(Ss_Dms_Inv_Stock.order_number,'-')sobNo,\n"
            + "        nvl(Ss_Dms_Inv_Stock.LOCATION,'-')LOCATION,\n"
            + "        nvl(Ss_Dms_Inv_Stock.dealer_location,'-')dealer_location,\n"
            + "       nvl( Ss_Dms_Inv_Stock.mul_inv_no,'-')mul_inv_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.mul_inv_dt),'-')mul_inv_dt,\n"
            + "        nvl(Ss_Dms_Inv_Stock.grn_no,'-')grn_no,\n"
            + "        nvl(to_char(Ss_Dms_Inv_Stock.grn_date),'-')grn_date,\n"
             + "  nvl(to_char(trunc(SYSDATE - Ss_Dms_Inv_Stock.GRN_DATE)),'-') AS AGEING, \n"
            + "        nvl(Ss_Dms_Inv_Stock.remarks1,'-')remarks1,\n"
            + "       nvl(ANDROID_VEH_IMAGE.vehImage,'-')vehImage, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage2,'-')vehImage2, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage3,'-')vehImage3, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage4,'-')vehImage4, \n"
            + "nvl(ANDROID_VEH_IMAGE.vehImage5,'-')vehImage5, \n"
            + "nvl(to_char(ANDROID_VEH_IMAGE.lastUploadDate),'-')lastUploadDate, \n"
            + "nvl(ANDROID_VEH_IMAGE.lastUploadedBy,'-')lastUploadedBy \n"
            + "        FROM Ss_Dms_Inv_Stock\n"
            + "        LEFT JOIN ANDROID_VEH_IMAGE ON Ss_Dms_Inv_Stock.chassis_no = ANDROID_VEH_IMAGE.chassis_no\n"
            + "        WHERE Ss_Dms_Inv_Stock.vin = ?1", nativeQuery = true)
    public List<Map> getStockDetailsByVin(String vin);

    @Query(value = "select CMNID,CMNDESC from FND_COMMON_LOOKUP where CMNTYPE='VehicleOutCode' and Status ='Active'", nativeQuery = true)
    public List<Map> getByCmnType(String cmnType);
    
 
}
