package com.sai.erp.master.dao;

import com.sai.erp.master.entity.AndroidVehQr;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AndroidVehQrRepository extends JpaRepository<AndroidVehQr, Long> {

    AndroidVehQr findByChassisNo(String chassisNo);

    @Query(value = "select qr.vin,qr.reasonCode,nvl((select gate_pass_id from ss_gate_pass_sales where order_number =gft.utd),'0') GatepassYN\n"
            + "from ANDROID_VEH_QR qr,gd_fdi_trans gft\n"
            + "where qr.vin=gft.vin and gft.trans_type='VD' and qr.chassis_no=?1", nativeQuery = true)
    public List<Map> getByChassis(String chassisNo);

//    select stock.vin,stock.VEH_STATUS, from SS_dms_inv_stock stock,ss_dms_stock_trf trf where stock.location=?1 and stock.veh_status=?2
//and stock.vin=trf.vin and trf.received_by is null
    @Query(value = " select stock.location,stock.vin,stock.chassis_no,stock.model_desc,stock.VEH_STATUS, trf.MADE_BY transferred_by, nvl(null,'-') reasoncode,FROM_LOCATION,\n" +
"            nvl(trf.FRMKM,'-')frmKm, trf.driver_name , STOCK_TRF_NO\n" +
"            from SS_dms_inv_stock stock,ss_dms_stock_trf trf \n" +
"            where trf.TO_LOCATION=?1\n" +
"            and stock.veh_status=?2\n" +
"            and stock.vin=trf.vin \n" +
"            and trf.received_by is null\n" +
"            and trf.recd_date is null", nativeQuery = true)
    public List<Map> getByTransferList(String toLocation, String vehStatus);

       @Query(value = "select stock.location, stock.vin,stock.chassis_no,stock.VEH_STATUS,stock.model_desc,stock.variant_desc,stock.colour,stock.fuel_desc\n" +
"            from SS_dms_inv_stock stock\n" +
"            where stock.location=?1\n" +
"            and stock.veh_status=?2 order by vin", nativeQuery = true)
    public List<Map> getByTransferListIntransit(String toLocation, String vehStatus);

    
    public Optional<AndroidVehQr> findByVin(String vin);

    @Query(value = " select chassis_no CHASSIS_NUM, model_desc MODEL_CD, VARIANT_CODE VARIANT_CD,LOCATION, \n"
            + "             COLOUR,  fuel_desc, vin, engine_no, status from ss_dms_inv_stock where vin=?1", nativeQuery = true)
    public List<Map> getByVinNumber(String vin);

//    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Intransit,A.STOCK,A.STKTRF,a.Intransit+a.stock+a.stktrf totalstock,a.uninvoice,a.invoice,a.totsummary ftotalstock from\n"
//            + "            ( \n"
//            + "            select distinct   FLV.DESCRIPTION,\n"
//            + "            sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n"
//            + "            sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n"
//            + "            sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
//            + "            sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED')  then 1 else 0 end) uninvoice,\n"
//            + "            sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
//            + "            sum(case when VEH_STATUS not in ('PHYSICALLY DELIVERED','PHYSICALY DELIVERED','DELIVERED') then 1 else 0 end) totsummary\n"
//            + "            from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n"
//            + "            where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n"
//            + "            group by FLV.DESCRIPTION order by  FLV.DESCRIPTION)a", nativeQuery = true)
   // updated on 24 july 2024
    
    
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Intransit,A.STOCK,A.STKTRF,a.Intransit+a.stock+a.stktrf totalstock,\n" +
"a.unalloted,a.alloted ,a.invoice, a.unalloted+a.alloted+a.invoice ftotalstock from\n" +
"                        ( \n" +
"                        select distinct   FLV.DESCRIPTION,\n" +
"                        sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n" +
"                        sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n" +
"                        sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and \n" +
"                         (a.status is null or a.status in('Vehicle Intransit - Stock In','Stock Transfer Intransit - Stock In','UNALLOTED'))  then 1 else 0 end) unalloted,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status='ALLOTED')  then 1 else 0 end) alloted,\n" +
"                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE\n" +
"                        from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n" +
"                        group by FLV.DESCRIPTION order by  FLV.DESCRIPTION)a where  (a.unalloted+a.alloted+a.invoice) !=0", nativeQuery = true)
    public List<Map> getByModelCount(Integer ouId);

//    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Intransit,A.STOCK,A.STKTRF,a.Intransit+a.stock+a.stktrf totalstock,a.uninvoice,a.invoice,a.totsummary ftotalstock from\n"
//            + "            ( \n"
//            + "            select distinct a.location description,\n"
//            + "            sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n"
//            + "            sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n"
//            + "            sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
//            + "            sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED')  then 1 else 0 end) uninvoice,\n"
//            + "            sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
//            + "            sum(case when VEH_STATUS not in ('PHYSICALLY DELIVERED','PHYSICALY DELIVERED','DELIVERED') then 1 else 0 end) totsummary\n"
//            + "            from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n"
//            + "            where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n"
//            + "            group by a.location order by  a.location)a", nativeQuery = true)
    //updated on 24 july 2024
    
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Intransit,A.STOCK,A.STKTRF,a.Intransit+a.stock+a.stktrf totalstock,\n" +
"a.unalloted,a.alloted,a.invoice,a.unalloted+a.alloted+a.invoice ftotalstock from\n" +
"                        ( \n" +
"                        select distinct a.location description,\n" +
"                        sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n" +
"                        sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n" +
"                        sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  \n" +
"                        (a.status is null or a.status in('Vehicle Intransit - Stock In','Stock Transfer Intransit - Stock In','UNALLOTED'))  then 1 else 0 end) unalloted,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status='ALLOTED')  then 1 else 0 end) alloted,\n" +
"                        sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE\n" +
"                        from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n" +
"                        group by a.location order by  a.location)a where  (a.unalloted+a.alloted+a.invoice) !=0", nativeQuery = true)
    public List<Map> getByLocCount(Integer ouId);

//    @Query(value = "SELECT sum(b.MODEL_DESC_count)totCount from (\n"
//            + "select  count(MODEL_DESC) MODEL_DESC_count \n"
//            + "from ss_dms_inv_stock a\n"
//            + "where OPERATING_UNIT = ?1\n"
//            + "and veh_status <> 'DELIVERED'\n"
//            + "group by a.MODEL_DESC\n"
//            + ")b", nativeQuery = true)
    ///18-07-2024
    
//    @Query(value = "select SUM(a.totsummary) ftotalstock from\n" +
//"                      ( \n" +
//"                      select distinct   FLV.DESCRIPTION,\n" +
//"                      sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n" +
//"                      sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n" +
//"                      sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n" +
//"                      sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED')  then 1 else 0 end) uninvoice,\n" +
//"                      sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n" +
//"                      sum(case when VEH_STATUS not in ('PHYSICALLY DELIVERED','PHYSICALY DELIVERED','DELIVERED') then 1 else 0 end) totsummary\n" +
//"                      from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
//"                      where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n" +
//"                      group by FLV.DESCRIPTION order by  FLV.DESCRIPTION)a", nativeQuery = true)
    //updated on 25-07-2024
    
    
    @Query(value = "SELECT \n" +
"    SUM(a.ftotalstock) AS ftotalstock from\n" +
"(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Intransit,A.STOCK,A.STKTRF,a.Intransit+a.stock+a.stktrf totalstock,\n" +
"a.unalloted,a.alloted ,a.invoice, a.unalloted+a.alloted+a.invoice ftotalstock from\n" +
"                        ( \n" +
"                        select distinct   FLV.DESCRIPTION,\n" +
"                        sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n" +
"                        sum(case when VEH_STATUS in ('STOCK') then 1 else 0 end) stock,\n" +
"                        sum(case when VEH_STATUS  in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and \n" +
"                         (a.status is null or a.status in('Vehicle Intransit - Stock In','Stock Transfer Intransit - Stock In','UNALLOTED'))  then 1 else 0 end) unalloted,\n" +
"                        sum(case when a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status='ALLOTED')  then 1 else 0 end) alloted,\n" +
"                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE\n" +
"                        from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                        group by FLV.DESCRIPTION order by  FLV.DESCRIPTION)a where  a.unalloted+a.alloted+a.invoice !=0)a", nativeQuery = true)
    public List<Map> getByAllModelCount(Integer ouId);

    ////key no column added nvl(a.VARIANT_DESC,'-')VARIANT_DESC,
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no,a.LOCATION, a.vin,a.CHASSIS_NO, nvl(a.KEY_NO,'-') KEY_NO, a.ENGINE_NO,nvl(a.MODEL_DESC,'-') MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,nvl(a.VARIANT_CODE||'-'||a.VARIANT_DESC,'-')VARIANT_CODE,\n" +
"                        a.COLOUR_CODE || '-' || a.COLOUR COLOUR,a.GRN_NO||'-'||(to_char(a.GRN_DATE))GRN_NO,a.MUL_INV_NO||'/'||nvl(to_char(a.MUL_INV_DT),'/') MUL_INV_NO,\n" +
"                       a.VEH_STATUS,nvl(a.status,'-')status,\n" +
"                       nvl(nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n" +
"nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_NAME \n" +
"                        from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n" +
"                       and  a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and \n" +
"                       (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED') order by a.MODEL_DESC,nvl(to_char(a.GRN_DATE),'-') ", nativeQuery = true)
    public List<Map> getByUnInvoiceVehAll(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no,a.LOCATION, a.vin,a.CHASSIS_NO, nvl(a.KEY_NO,'-') KEY_NO, a.ENGINE_NO,nvl(a.MODEL_DESC,'-') MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,nvl(a.VARIANT_CODE||'-'||a.VARIANT_DESC,'-')VARIANT_CODE,\n" +
"                        a.COLOUR_CODE || '-' || a.COLOUR COLOUR,a.GRN_NO||'-'||(to_char(a.GRN_DATE))GRN_NO,a.MUL_INV_NO||'/'||nvl(to_char(a.MUL_INV_DT),'/') MUL_INV_NO,\n" +
"                        a.VEH_STATUS,nvl(a.status,'-')status, \n" +
"                          nvl(nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n" +
"nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_NAME \n" +
"                         from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE   and upper(flv.description) like upper('%NEXA%') \n" +
"                        and a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and  (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED') order by a.MODEL_DESC,nvl(to_char(a.GRN_DATE),'-') ", nativeQuery = true)
    public List<Map> getByUnInvoiceVehNexa(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no,a.LOCATION, a.vin,a.CHASSIS_NO, nvl(a.KEY_NO,'-') KEY_NO, a.ENGINE_NO,nvl(a.MODEL_DESC,'-') MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,nvl(a.VARIANT_CODE||'-'||a.VARIANT_DESC,'-')VARIANT_CODE,\n" +
"                        a.COLOUR_CODE || '-' || a.COLOUR COLOUR,a.GRN_NO||'-'||(to_char(a.GRN_DATE))GRN_NO,a.MUL_INV_NO||'-'||nvl(to_char(a.MUL_INV_DT),'-') MUL_INV_NO,\n" +
"                       a.VEH_STATUS,nvl(a.status,'-')status, \n" +
"                            nvl(nvl(a.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_ACC_NO,\n" +
"nvl(nvl(a.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=a.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)),'-') CUST_NAME\n" +
"                         from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n" +
"                        where a.OPERATING_UNIT=?1 \n" +
"                         and   upper(flv.description) not like upper('%NEXA%') and A.VARIANT_CODE=FLV.LOOKUP_CODE \n" +
"                       and  a.VEH_STATUS in ('In-Transit','STOCK','Stock Transfer In-Transit') and \n" +
"                       (a.status is null or a.status='ALLOTED' or a.status='STKTRF-DELIVERED') ", nativeQuery = true)
    public List<Map> getByUnInvoiceVehArena(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.* from (select\n" +
"            stock.location,to_char(trunc(sysdate,'MM'), 'DD-MON-YYYY') firstDate, \n" +
"            to_char(trunc(sysdate), 'DD-MON-YYYY') sysdate1,\n" +
"           nvl(stock.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) ACCOUNT_NUM,\n" +
"nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
"            nvl((select gfd.TEAM_HEAD\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')EXECUTIVE,\n" +
"            nvl((select gfd.EXECUTIVE\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')TEAM_HEAD,to_char(stock.vin)vin, to_char(stock.CHASSIS_NO)CHASSIS_NO,to_char(stock.ENGINE_NO)ENGINE_NO,to_char(stock.MODEL_DESC)MODEL_DESC,\n" +
"            to_char(stock.VARIANT_CODE)VARIANT_CODE,to_char(stock.VARIANT_DESC)VARIANT_DESC,to_char(stock.COLOUR_CODE) || '-'|| to_char(stock.COLOUR)COLOUR,\n" +
"            nvl((select to_char(gft.TRANS_ID)\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')DMSINVNO,\n" +
"            nvl((select gft.trans_ref_num\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')sob_no,\n" +
"            nvl((select sgp.gate_pass_id \n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),0)gate_pass_id ,\n" +
"            (select to_date(sgp.DATE_OF_DELIVERY)\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1)DATE_OF_DELIVERY ,\n" +
"            nvl((select to_char(sgp.SERVICE_LOCATION )\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),'-')gatePassLocation ,\n" +
"            nvl((select vehstatus from ANDROID_VEH_QR qr \n" +
"            where qr.vin =to_char(stock.vin)\n" +
"            and (qr.VEHSTATUS not like 'DELIVERED' or qr.VEHSTATUS is null)),'-')vehstatus, status \n" +
"             from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV \n" +
"            where  stock.OPERATING_UNIT=?1\n" +
"            and  stock.VARIANT_CODE=FLV.LOOKUP_CODE and stock.VEH_STATUS='DELIVERED'   and stock.status='Ready For Delivery' )a\n" +
"            where  trunc(to_date(DATE_OF_DELIVERY)) between firstDate and sysdate1", nativeQuery = true)
    public List<Map> getByPhyInAll(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.* from (select\n" +
"            stock.location,to_char(trunc(sysdate,'MM'), 'DD-MON-YYYY') firstDate, \n" +
"            to_char(trunc(sysdate), 'DD-MON-YYYY') sysdate1,\n" +
"           nvl(stock.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) ACCOUNT_NUM,\n" +
"nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
"            nvl((select gfd.TEAM_HEAD\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')EXECUTIVE,\n" +
"            nvl((select gfd.EXECUTIVE\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')TEAM_HEAD,to_char(stock.vin)vin, to_char(stock.CHASSIS_NO)CHASSIS_NO,to_char(stock.ENGINE_NO)ENGINE_NO,to_char(stock.MODEL_DESC)MODEL_DESC,\n" +
"            to_char(stock.VARIANT_CODE)VARIANT_CODE,to_char(stock.VARIANT_DESC)VARIANT_DESC,to_char(stock.COLOUR_CODE) || '-'|| to_char(stock.COLOUR)COLOUR,\n" +
"            nvl((select to_char(gft.TRANS_ID)\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')DMSINVNO,\n" +
"            nvl((select gft.trans_ref_num\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')sob_no,\n" +
"            nvl((select sgp.gate_pass_id \n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),0)gate_pass_id ,\n" +
"            (select to_date(sgp.DATE_OF_DELIVERY)\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1)DATE_OF_DELIVERY ,\n" +
"            nvl((select to_char(sgp.SERVICE_LOCATION )\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),'-')gatePassLocation ,\n" +
"            nvl((select vehstatus from ANDROID_VEH_QR qr \n" +
"            where qr.vin =to_char(stock.vin)\n" +
"            and (qr.VEHSTATUS not like 'DELIVERED' or qr.VEHSTATUS is null)),'-')vehstatus, status \n" +
"             from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV \n" +
"            where  stock.OPERATING_UNIT=?1 and  stock.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) like upper('%NEXA%') \n" +
"            and stock.VEH_STATUS='DELIVERED'   and stock.status='Ready For Delivery' )a\n" +
"            where  trunc(to_date(DATE_OF_DELIVERY)) between firstDate and sysdate1", nativeQuery = true)
    public List<Map> getByPhyInNexa(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.* from (select\n" +
"            stock.location,to_char(trunc(sysdate,'MM'), 'DD-MON-YYYY') firstDate, \n" +
"            to_char(trunc(sysdate), 'DD-MON-YYYY') sysdate1,\n" +
"            nvl(stock.CUSTOMER_CODE,(select cust_id from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) ACCOUNT_NUM,\n" +
"nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
"            nvl((select gfd.TEAM_HEAD\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')EXECUTIVE,\n" +
"            nvl((select gfd.EXECUTIVE\n" +
"            from gd_fdi_trans gfd where gfd.vin=to_char(stock.vin)\n" +
"            and gfd.trans_type='VS'\n" +
"            and gfd.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gfd.trans_id)\n" +
"            and rownum=1),'-')TEAM_HEAD,to_char(stock.vin)vin, to_char(stock.CHASSIS_NO)CHASSIS_NO,to_char(stock.ENGINE_NO)ENGINE_NO,to_char(stock.MODEL_DESC)MODEL_DESC,\n" +
"            to_char(stock.VARIANT_CODE)VARIANT_CODE,to_char(stock.VARIANT_DESC)VARIANT_DESC,to_char(stock.COLOUR_CODE) || '-'|| to_char(stock.COLOUR)COLOUR,\n" +
"            nvl((select to_char(gft.TRANS_ID)\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')DMSINVNO,\n" +
"            nvl((select gft.trans_ref_num\n" +
"            from  gd_fdi_trans gft\n" +
"            where gft.vin=to_char(stock.vin)\n" +
"            and gft.trans_type='VS'\n" +
"            and gft.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft.trans_id)\n" +
"            and rownum=1),'-')sob_no,\n" +
"            nvl((select sgp.gate_pass_id \n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),0)gate_pass_id ,\n" +
"            (select to_date(sgp.DATE_OF_DELIVERY)\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1)DATE_OF_DELIVERY ,\n" +
"            nvl((select to_char(sgp.SERVICE_LOCATION )\n" +
"            from ss_gate_pass_sales  sgp, gd_fdi_trans gft1, gd_fdi_trans gfd\n" +
"            where sgp.order_number =gfd.utd\n" +
"            and gft1.vin =to_char(stock.vin)\n" +
"            and gft1.trans_type='VS'\n" +
"            and gft1.TRANS_REF_NUM=gfd.trans_id\n" +
"            and gfd.trans_type='ORDBK'\n" +
"            and gft1.loc_cd=gfd.loc_cd\n" +
"            and gft1.trans_id not in (select trans_ref_num from gd_fdi_trans where trans_ref_num=gft1.trans_id)\n" +
"            and rownum=1),'-')gatePassLocation ,\n" +
"            nvl((select vehstatus from ANDROID_VEH_QR qr \n" +
"            where qr.vin =to_char(stock.vin)\n" +
"            and (qr.VEHSTATUS not like 'DELIVERED' or qr.VEHSTATUS is null)),'-')vehstatus, status \n" +
"             from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV \n" +
"            where  stock.OPERATING_UNIT=?1 and  stock.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) NOT like upper('%NEXA%')\n" +
"            and stock.VEH_STATUS='DELIVERED'   and stock.status='Ready For Delivery' )a\n" +
"            where  trunc(to_date(DATE_OF_DELIVERY)) between firstDate and sysdate1", nativeQuery = true)
    public List<Map> getByPhyInArena(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY gft.trans_date) AS sr_no,\n" +
" nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
" gft.EXECUTIVE,gft.TEAM_HEAD,stock.vin,stock.chassis_no CHASSIS_NUM, nvl(stock.KEY_NO,'-') KEY_NO, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, \n" +
"            gft.trans_id dmsInvoiceNo, gft.trans_ref_num sobNo, TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) dms_inv_date, stock.veh_status \n" +
"            from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"            where 1=1\n" +
"            and stock.vin=gft.vin \n" +
"            and gft.trans_type='VS' \n" +
"            and gft.trans_ref_num= gft1.trans_id \n" +
"            and gft.loc_cd=gft1.loc_cd \n" +
"            and  stock.VARIANT_CODE=FLV.LOOKUP_CODE and gft1.trans_type='ORDBK'\n" +
"            and stock.status='INVOICE'\n" +
"            and stock.OPERATING_UNIT =?1\n" +
"            and TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' )between '01-FEB-2023' and ?2 ORDER BY TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) ASC ", nativeQuery = true)
    public List<Map> getByINDAll(Integer ouId, Date toDate);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY gft.trans_date) AS sr_no,\n" +
" nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
" gft.EXECUTIVE,gft.TEAM_HEAD,stock.vin,stock.chassis_no CHASSIS_NUM, nvl(stock.KEY_NO,'-') KEY_NO, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, \n" +
"            gft.trans_id dmsInvoiceNo, gft.trans_ref_num sobNo, TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) dms_inv_date, stock.veh_status \n" +
"            from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"            where 1=1\n" +
"            and stock.vin=gft.vin \n" +
"            and gft.trans_type='VS' \n" +
"            and  stock.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) like upper('%NEXA%') and gft.trans_ref_num= gft1.trans_id \n" +
"            and gft.loc_cd=gft1.loc_cd \n" +
"            and gft1.trans_type='ORDBK'\n" +
"            and stock.status='INVOICE' \n" +
"            and stock.OPERATING_UNIT =?1\n" +
"            and TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' )between '01-FEB-2023' and ?2 ORDER BY TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) ASC ", nativeQuery = true)
    public List<Map> getByINDNexa(Integer ouId, Date toDate);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY gft.trans_date) AS sr_no,\n" +
"nvl(stock.CUSTOMER_NAME,(select CUST_NAME from gd_fdi_trans gd where vin=stock.vin and trans_type='VS'\n" +
"and trans_id not in(select trans_ref_num from gd_fdi_trans where trans_type='VC' and trans_ref_num=gd.trans_ref_num) and rownum=1)) CUST_NAME,\n" +
" gft.EXECUTIVE,gft.TEAM_HEAD,stock.vin , nvl(stock.KEY_NO,'-') KEY_NO, stock.chassis_no CHASSIS_NUM, stock.model_desc MODEL_CD, stock.VARIANT_CODE VARIANT_CD,stock.engine_no engine_num ,stock.mul_inv_no dmsinvno, \n" +
"            gft.trans_id dmsInvoiceNo, gft.trans_ref_num sobNo, TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) dms_inv_date, stock.veh_status \n" +
"            from ss_dms_inv_stock stock, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gft, gd_fdi_trans gft1\n" +
"            where 1=1\n" +
"            and stock.vin=gft.vin \n" +
"            and gft.trans_type='VS' \n" +
"            and gft.trans_ref_num= gft1.trans_id \n" +
"            and gft.loc_cd=gft1.loc_cd \n" +
"            and gft1.trans_type='ORDBK'\n" +
"            and stock.status='INVOICE' \n" +
"            and stock.OPERATING_UNIT =?1\n" +
"            and  stock.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) NOT like upper('%NEXA%') and TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' )between '01-FEB-2023' and ?2 ORDER BY TO_DATE (SUBSTR (gft.trans_date, 1, 19), 'YYYY-MM-DD HH24:MI:SS' ) ASC ", nativeQuery = true)
    public List<Map> getByINDArena(Integer ouId, Date toDate);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.vin,a.CHASSIS_NO,a.ENGINE_NO,a.MODEL_DESC,a.FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC,a.COLOUR_CODE,a.COLOUR,\n" +
"            a.VEH_STATUS, nvl(a.status,'-') status  ,\n" +
"            a.MUL_INV_NO,  nvl(a.KEY_NO,'-') KEY_NO, a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n" +
"            ,a.OPERATING_UNIT\n" +
"            from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gd\n" +
"            where a.vin = gd.vin\n" +
"            and  a.VARIANT_CODE=FLV.LOOKUP_CODE and gd.trans_type='VD'\n" +
"            and a.VEH_STATUS in ('In-Transit')\n" +
"            and a.OPERATING_UNIT=?1", nativeQuery = true)
    public List<Map> getByIntransitAll(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no, a.vin,a.CHASSIS_NO,a.ENGINE_NO,a.MODEL_DESC,a.FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC,a.COLOUR_CODE,a.COLOUR,\n" +
"            a.VEH_STATUS, nvl(a.status,'-') status  ,\n" +
"            a.MUL_INV_NO, nvl(a.KEY_NO,'-') KEY_NO, a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n" +
"            ,a.OPERATING_UNIT\n" +
"            from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gd\n" +
"            where a.vin = gd.vin\n" +
"            and gd.trans_type='VD'\n" +
"            and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) like upper('%NEXA%') and a.VEH_STATUS in ('In-Transit')\n" +
"            and a.OPERATING_UNIT=?1", nativeQuery = true)
    public List<Map> getByIntransitNexa(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.MODEL_DESC) AS sr_no , nvl(a.KEY_NO,'-') KEY_NO, a.vin,a.CHASSIS_NO,a.ENGINE_NO,a.MODEL_DESC,a.FUEL_DESC,a.VARIANT_CODE,a.VARIANT_DESC,a.COLOUR_CODE,a.COLOUR,\n" +
"            a.VEH_STATUS, nvl(a.status,'-') status  ,\n" +
"            a.MUL_INV_NO,a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n" +
"            ,a.OPERATING_UNIT\n" +
"            from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV, gd_fdi_trans gd\n" +
"            where a.vin = gd.vin\n" +
"            and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n" +
"                and upper(flv.description) NOT like upper('%NEXA%') and gd.trans_type='VD'\n" +
"            and a.VEH_STATUS in ('In-Transit')\n" +
"            and a.OPERATING_UNIT=?1", nativeQuery = true)
    public List<Map> getByIntransitArena(Integer ouId);

    @Query(value = "select distinct  FLV.DESCRIPTION,\n"
            + "sum(case when VEH_STATUS not in ('INVOICE','DELIVERED') then 1 else 0 end) uninvoice,\n"
            + "sum(case when VEH_STATUS='STOCK' then 1 else 0 end) STOCK,\n"
            + "sum(case when VEH_STATUS='INVOICE' then 1 else 0 end) INVOICE\n"
            + "from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n"
            + "where 1=1 and a.OPERATING_UNIT=?1 AND A.VARIANT_CODE=FLV.LOOKUP_CODE \n"
            + "group by FLV.DESCRIPTION order by FLV.DESCRIPTION", nativeQuery = true)
    public List<Map> getBySummaryUninvoice(Integer ouId);

    @Query(value = "select distinct   FLV.DESCRIPTION,\n"
            + "sum(case when VEH_STATUS='In-Transit' then 1 else 0 end) Intransit,\n"
            + "sum(case when VEH_STATUS='STOCK' then 1 else 0 end) stock,\n"
            + "sum(case when VEH_STATUS='STK-TRF' then 1 else 0 end) STKTRF\n"
            + "from ss_dms_inv_stock a,FND_LOOKUP_VALUES_VL FLV\n"
            + "where 1=1 and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE \n"
            + "group by FLV.DESCRIPTION order by  FLV.DESCRIPTION", nativeQuery = true)
    public List<Map> getBySummaryIntransit(Integer ouId);

    ///keyno added
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.location) AS sr_no, a.vin,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,a.VARIANT_CODE,nvl(a.VARIANT_DESC,'-')VARIANT_DESC,a.COLOUR_CODE,a.COLOUR, nvl(a.KEY_NO,'-') KEY_NO ,\n" +
"            a.MUL_INV_NO,a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n" +
"            ,a.VEH_STATUS, NVL(a.STATUS,'-') status\n" +
"            from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV \n" +
"            where a.VEH_STATUS='Stock Transfer In-Transit' \n" +
"            and a.OPERATING_UNIT=?1 and  A.VARIANT_CODE=FLV.LOOKUP_CODE order by a.location", nativeQuery = true)
    public List<Map> getByStkTrfIntransitAll(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.location) AS sr_no, a.vin,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,a.VARIANT_CODE,nvl(a.VARIANT_DESC,'-')VARIANT_DESC,a.COLOUR_CODE,a.COLOUR, nvl(a.KEY_NO,'-') KEY_NO,\n"
            + "a.MUL_INV_NO,a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n"
            + ",a.VEH_STATUS, NVL(a.STATUS,'-') status\n"
            + "from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV \n"
            + "where a.VEH_STATUS='Stock Transfer In-Transit' \n"
            + "and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n"
            + "	and upper(flv.description) like upper('%NEXA%') and a.OPERATING_UNIT=?1 order by a.location", nativeQuery = true)
    public List<Map> getByStkTrfIntransitNexa(Integer ouId);

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.location) AS sr_no, a.vin,a.CHASSIS_NO,a.ENGINE_NO,nvl(a.MODEL_DESC,'-')MODEL_DESC,nvl(a.FUEL_DESC,'-')FUEL_DESC,a.VARIANT_CODE,nvl(a.VARIANT_DESC,'-')VARIANT_DESC,a.COLOUR_CODE,a.COLOUR, nvl(a.KEY_NO,'-') KEY_NO,\n"
            + "a.MUL_INV_NO,a.MUL_INV_DT,nvl(a.GRN_NO,'-')GRN_NO,nvl(to_char(a.GRN_DATE),'-')GRN_DATE,a.LOCATION\n"
            + ",a.VEH_STATUS, NVL(a.STATUS,'-') status\n"
            + "from ss_dms_inv_stock a, FND_LOOKUP_VALUES_VL FLV \n"
            + "where a.VEH_STATUS='Stock Transfer In-Transit' and  A.VARIANT_CODE=FLV.LOOKUP_CODE\n"
            + "	and upper(flv.description) NOT like upper('%NEXA%') \n"
            + "and a.OPERATING_UNIT=?1 order by a.location", nativeQuery = true)
    public List<Map> getByStkTrfIntransitArena(Integer ouId);

    @Query(value = " select * from ss_dms_inv_stock where vin=?1", nativeQuery = true)
    public List<Map> getByVinNumberNew(String vin);
    
    
    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY stock.location) AS sr_no,stock.location,stock.vin,stock.chassis_no,stock.engine_no,stock.model_desc,stock.variant_code|| '-'||stock.variant_desc,\n"

            + "colour_code || colour colour,stock.allotment_no,stock.allotment_dt \n"

            + "FROM ss_dms_inv_stock stock,FND_LOOKUP_VALUES_VL FLV where stock.status='ALLOTED' AND stock.OPERATING_UNIT=?1 \n"

            + "and stock.VARIANT_CODE=FLV.LOOKUP_CODE and upper(flv.description) not like upper('%NEXA%') order by stock.location", nativeQuery = true)

    public List<Map> getByAllotedArena(Integer ouId);

   

    

       @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY stock.location) AS sr_no,stock.location,stock.vin,stock.chassis_no,stock.engine_no,stock.model_desc,stock.variant_code|| '-'||stock.variant_desc,\n"

            + "colour_code || colour colour,stock.allotment_no,stock.allotment_dt \n"

            + "FROM ss_dms_inv_stock stock,FND_LOOKUP_VALUES_VL FLV where stock.status='ALLOTED' AND stock.OPERATING_UNIT=?1 \n"

            + "and stock.VARIANT_CODE=FLV.LOOKUP_CODE and upper(flv.description) like upper('%NEXA%') order by stock.location", nativeQuery = true)

    public List<Map> getByAllotedNexa(Integer ouId);

 

   

       @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY stock.location) AS sr_no,stock.location,stock.vin,stock.chassis_no,stock.engine_no,stock.model_desc,stock.variant_code|| '-'||stock.variant_desc,\n"

            + "colour_code || colour colour,stock.allotment_no,stock.allotment_dt \n"

            + "FROM ss_dms_inv_stock stock,FND_LOOKUP_VALUES_VL FLV where stock.status='ALLOTED' AND stock.OPERATING_UNIT=?1 \n"

            + "and stock.VARIANT_CODE=FLV.LOOKUP_CODE order by stock.location", nativeQuery = true)

    public List<Map> getByAllotedAll(Integer ouId);

}
