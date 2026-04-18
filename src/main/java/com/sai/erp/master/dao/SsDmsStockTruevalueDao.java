/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsStockTruevalue;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-HARSH
 */
public interface SsDmsStockTruevalueDao extends CrudRepository<SsDmsStockTruevalue, Integer> {

    ///for getting vehicle overall details by registration no-- TRUE VALUE
    ///from ss_dms_stock_tv table
    //Vehicle enquiry form
    @Query(value = " select   a.REG_NO, nvl(a.MODEL_DESC,'-') MODEL_DESC, nvl(a.FUEL_DESC,'-') FUEL_DESC, nvl(a.VARIANT_DESC,'-') VARIANT_DESC,\n"
            + " nvl(a.VARIANT_CODE,'-') VARIANT_CODE, nvl(a.CHASSIS_NO,'-') CHASSIS_NO, nvl(a.VIN,'-') VIN, nvl(a.ENGINE_NO,'-') ENGINE_NO,\n"
            + "            nvl(a.COLOUR,'-') COLOUR,nvl(a.COLOUR_CODE,'-') COLOUR_CODE, a.VEH_STATUS, nvl(a.status,'-') status,nvl(a.MANUFACTURER,'-') MANUFACTURER, a.LOCATION,\n"
            + "              nvl(a.DEALER_LOCATION,'-') DEALER_LOCATION, a.OPERATING_UNIT,\n"
            + "              nvl(a.UPDATEDBY,'-') UPDATEDBY, nvl(a.BUYING_LOCATION,'-') BUYING_LOCATION, nvl(a.MFG_YEAR,0) MFG_YEAR,\n"
            + "              nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "              nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from ss_dms_stock_tv  a  LEFT JOIN ANDROID_TV_VEH_IMAGE img ON a.reg_no = img.reg_no\n"
            + "                WHERE  a.reg_no=?1", nativeQuery = true)
    public List<Map> getTvDetailsByRegNo(String regNo);

    ///for getting vehicle overall details by chassis no-- TRUE VALUE
    ///from ss_dms_stock_tv table
    //Vehicle enquiry form
    @Query(value = " select   a.REG_NO, nvl(a.MODEL_DESC,'-') MODEL_DESC, nvl(a.FUEL_DESC,'-') FUEL_DESC, nvl(a.VARIANT_DESC,'-') VARIANT_DESC,\n"
            + " nvl(a.VARIANT_CODE,'-') VARIANT_CODE, nvl(a.CHASSIS_NO,'-') CHASSIS_NO, nvl(a.VIN,'-') VIN, nvl(a.ENGINE_NO,'-') ENGINE_NO,\n"
            + "            nvl(a.COLOUR,'-') COLOUR,nvl(a.COLOUR_CODE,'-') COLOUR_CODE, a.VEH_STATUS, nvl(a.status,'-') status,nvl(a.MANUFACTURER,'-') MANUFACTURER, a.LOCATION,\n"
            + "              nvl(a.DEALER_LOCATION,'-') DEALER_LOCATION, a.OPERATING_UNIT,\n"
            + "              nvl(a.UPDATEDBY,'-') UPDATEDBY, nvl(a.BUYING_LOCATION,'-') BUYING_LOCATION, nvl(a.MFG_YEAR,0) MFG_YEAR,\n"
            + "              nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "              nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from ss_dms_stock_tv  a  LEFT JOIN ANDROID_TV_VEH_IMAGE img ON a.reg_no = img.reg_no\n"
            + "                WHERE  a.chassis_no=?1", nativeQuery = true)
    public List<Map> getTvDetailsByChassis(String chassisNo);

    ///for getting vehicle overall details by vin no-- TRUE VALUE
    ///from ss_dms_stock_tv table
    //Vehicle enquiry form
    @Query(value = " select   a.REG_NO, nvl(a.MODEL_DESC,'-') MODEL_DESC, nvl(a.FUEL_DESC,'-') FUEL_DESC, nvl(a.VARIANT_DESC,'-') VARIANT_DESC,\n"
            + " nvl(a.VARIANT_CODE,'-') VARIANT_CODE, nvl(a.CHASSIS_NO,'-') CHASSIS_NO, nvl(a.VIN,'-') VIN, nvl(a.ENGINE_NO,'-') ENGINE_NO,\n"
            + "            nvl(a.COLOUR,'-') COLOUR,nvl(a.COLOUR_CODE,'-') COLOUR_CODE, a.VEH_STATUS, nvl(a.status,'-') status,nvl(a.MANUFACTURER,'-') MANUFACTURER, a.LOCATION,\n"
            + "              nvl(a.DEALER_LOCATION,'-') DEALER_LOCATION, a.OPERATING_UNIT,\n"
            + "              nvl(a.UPDATEDBY,'-') UPDATEDBY, nvl(a.BUYING_LOCATION,'-') BUYING_LOCATION, nvl(a.MFG_YEAR,0) MFG_YEAR,\n"
            + "              nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "              nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from ss_dms_stock_tv  a  LEFT JOIN ANDROID_TV_VEH_IMAGE img ON a.reg_no = img.reg_no\n"
            + "                WHERE  a.vin=?1", nativeQuery = true)
    public List<Map> getTvDetailsByVin(String vin);

    @Query(value = "select VIN from SS_DMS_STOCK_TV WHERE CHASSIS_NO=?1 ", nativeQuery = true)
    public List<Map> getTvVinNoByChassis(String chassis_no);

    @Query(value = " select stock.CHASSIS_NO, stock.MODEL_DESC, stock.VARIANT_CODE ,stock.VARIANT_DESC,\n"
            + "                         nvl(stock.COLOUR,'-') COLOUR,  stock.FUEL_DESC, stock.REG_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO, stock.VEH_STATUS, stock.LOCATION ,\n"
            + "                         trf.STOCK_TRF_NO, trf.FROM_LOCATION,nvl(trf.fromKm,0) FROMKM ,trf.TO_LOCATION, nvl(trf.DRIVER_NAME,'-') DRIVER_NAME\n"
            + "                      from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf where stock.reg_no=trf.reg_no\n"
            + "                       and received_by is null and recd_date is null and trf.reg_no=?1  ", nativeQuery = true)
    public List<Map> getTvDetStockInByRegNo(String regNo);

    @Query(value = "select stock.CHASSIS_NO, stock.MODEL_DESC, stock.VARIANT_CODE ,stock.VARIANT_DESC,\n"
            + "                         nvl(stock.COLOUR,'-') COLOUR,  stock.FUEL_DESC, stock.REG_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO, stock.VEH_STATUS, stock.LOCATION ,\n"
            + "                         trf.STOCK_TRF_NO, trf.FROM_LOCATION,nvl(trf.fromKm,0) FROMKM ,trf.TO_LOCATION, nvl(trf.DRIVER_NAME,'-') DRIVER_NAME\n"
            + "                      from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf where stock.chassis_no=trf.chassis_no\n"
            + "                       and received_by is null and recd_date is null and stock.chassis_no=?1 and trf.ou=?2 ", nativeQuery = true)
    public List<Map> getTvDetStockInByChassis(String chassisNo, Integer ou);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsStockTruevalue set  veh_status=?1,location=?2 where regNo=?3")
    public void updateTvVehStatus(String vehStatus, String location, String regNo);

    ///query for stock taking true value --by reg no
    @Query(value = " select REG_NO, CHASSIS_NO, nvl(MODEL_DESC,'-') MODEL_DESC , nvl(VARIANT_DESC,'-') VARIANT_DESC, nvl(VARIANT_CODE,'-') VARIANT_CODE ,LOCATION, VEH_STATUS, \n"
            + "                         nvl(COLOUR,'-') COLOUR,  nvl(fuel_desc,'-') fuel_desc, nvl(vin,'-') vin, ENGINE_NO, \n"
            + "                  NVL(status,'-') status, OPERATING_UNIT from ss_dms_stock_tv where VEH_STATUS not in ('DELIVERED')  AND REG_NO=?1", nativeQuery = true)
    public List<Map> getByRegNo(String regNo);

    ///query for stock taking true value --by vin no
    @Query(value = " select REG_NO, CHASSIS_NO, nvl(MODEL_DESC,'-') MODEL_DESC , nvl(VARIANT_DESC,'-') VARIANT_DESC, nvl(VARIANT_CODE,'-') VARIANT_CODE ,\n"
            + "LOCATION, VEH_STATUS, nvl(COLOUR,'-') COLOUR,  nvl(fuel_desc,'-') fuel_desc, nvl(vin,'-') vin, ENGINE_NO, \n"
            + " NVL(status,'-') status, OPERATING_UNIT from ss_dms_stock_tv where VEH_STATUS not in ('DELIVERED')  AND vin=?1", nativeQuery = true)
    public List<Map> getByVin(String vin);

    ///query for stock taking true value -- by chassis no
    @Query(value = " select  stock.reg_no, nvl(stock.vin,'-') vin,nvl(stock.chassis_no,'-') chassis_no, nvl(stock.model_desc,'-') model_desc,\n"
            + " nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(STOCK.COLOUR,'-') COLOUR, nvl(stock.VARIANT_CODE,'-') VARIANT_CODE, stock.engine_no,\n"
            + "    stock.veh_status ,stock.location, nvl(stock.status,'-') status,stock.operating_unit\n"
            + "from ss_dms_stock_tv stock where stock.CHASSIS_NO =?1 And stock.operating_unit=?2\n"
            + "and stock.VEH_STATUS not in ('DELIVERED') ", nativeQuery = true)
    public List<Map> getByChassisNo(String chassisNo, Integer ouId);

    ///query for true value reports- overall stock by ou
    @Query(value = " select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + "stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + "stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + "from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND (stock.VEH_STATUS!= ('DELIVERED') AND (stock.VEH_STATUS!= ('Bought-Cancel') ) )", nativeQuery = true)
    public List<Map> getTvStockDetailsByOu(Integer ou);

    ///query for true value reports- overall MARUTI  stock by ou
    //FOR MARUTI TRUE VALUE VEHICLES
    @Query(value = " select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + "stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + "stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + "from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND (stock.VEH_STATUS!= ('DELIVERED') AND (stock.VEH_STATUS!= ('Bought-Cancel') ) ) and  stock.MANUFACTURER='MARUTI'", nativeQuery = true)
    public List<Map> getTvStockDetailsMarutiByOu(Integer ou);

    ///query for true value reports- overall NONMARUTI stock by ou
    //FOR NON MARUTI TRUE VALUE VEHICLES
    @Query(value = " select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + "stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + "stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + "from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND (stock.VEH_STATUS!= ('DELIVERED') AND (stock.VEH_STATUS!= ('Bought-Cancel') ) ) and  stock.MANUFACTURER='NON-MARUTI'", nativeQuery = true)
    public List<Map> getTvStockDetailsNonMarutiByOu(Integer ou);

    //query for true value reports- overall stock transfer by ou
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,"
            + " trf.stock_trf_no,trf.stock_trf_date,stock.REG_NO, nvl(stock.VIN,'-') VIN, nvl(stock.chassis_no,'-') chassis_no,\n"
            + "nvl(stock.model_desc,'-') model_desc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT_CODE,'-') VARIANT_CODE,\n"
            + " nvl(stock.MANUFACTURER,'-') MANUFACTURER, nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location,\n"
            + "            nvl(trf.FROMKM,0)fromKm,nvl(trf.toKm,0)toKm, nvl(trf.driver_name,'-') driver_name\n"
            + "            from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf \n"
            + "            where stock.veh_status='Stock Transfer In-Transit'\n"
            + "            and stock.REG_NO=trf.REG_NO \n"
            + "            and trf.received_by is null\n"
            + "            and trf.recd_date is null\n"
            + "            and stock.operating_unit=trf.ou\n"
            + "            and trf.ou=?1", nativeQuery = true)
    public List<Map> getTvStockTrfDetailsByOu(Integer ou);

    //query for true value reports- overall stock transfer by ou
    //for maruti vehicles true value
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,"
            + " trf.stock_trf_no,trf.stock_trf_date,stock.REG_NO, nvl(stock.VIN,'-') VIN, nvl(stock.chassis_no,'-') chassis_no,\n"
            + "nvl(stock.model_desc,'-') model_desc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT_CODE,'-') VARIANT_CODE,\n"
            + "nvl(stock.MANUFACTURER,'-') MANUFACTURER, nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location,\n"
            + "            nvl(trf.FROMKM,0)fromKm,nvl(trf.toKm,0)toKm, nvl(trf.driver_name,'-') driver_name\n"
            + "            from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf \n"
            + "            where stock.veh_status='Stock Transfer In-Transit'\n"
            + "            and stock.REG_NO=trf.REG_NO \n"
            + "            and trf.received_by is null\n"
            + "            and trf.recd_date is null\n"
            + "            and stock.operating_unit=trf.ou\n"
            + "            and trf.ou=?1 and STOCK.MANUFACTURER='MARUTI'", nativeQuery = true)
    public List<Map> getTvStockTrfMarutiDetailsByOu(Integer ou);

    //query for true value reports- overall stock transfer by ou
    //for non maruti vehicles true value
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY trf.STOCK_TRF_DATE desc) AS sr_no,"
            + " trf.stock_trf_no,trf.stock_trf_date,stock.REG_NO, nvl(stock.VIN,'-') VIN, nvl(stock.chassis_no,'-') chassis_no,\n"
            + "nvl(stock.model_desc,'-') model_desc, stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(stock.VARIANT_CODE,'-') VARIANT_CODE,\n"
            + "nvl(stock.MANUFACTURER,'-') MANUFACTURER, nvl(trf.MADE_BY,'-') transferred_by,trf.FROM_LOCATION, trf.to_location,\n"
            + "            nvl(trf.FROMKM,0)fromKm,nvl(trf.toKm,0)toKm, nvl(trf.driver_name,'-') driver_name\n"
            + "            from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf \n"
            + "            where stock.veh_status='Stock Transfer In-Transit'\n"
            + "            and stock.REG_NO=trf.REG_NO \n"
            + "            and trf.received_by is null\n"
            + "            and trf.recd_date is null\n"
            + "            and stock.operating_unit=trf.ou\n"
            + "            and trf.ou=?1 and STOCK.MANUFACTURER='NON-MARUTI'", nativeQuery = true)
    public List<Map> getTvStockTrfNonMarutiDetailsByOu(Integer ou);

    ///query for true value reports- overall stock delivered by ou
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + " stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + " stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + " from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED'", nativeQuery = true)
    public List<Map> getTvStockDelvDetailsByOu(Integer ou);

    ///query for true value reports- overall stock delivered by ou
    //for maruti true value vehicles
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + " stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + " stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + " from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED' and stock.MANUFACTURER='MARUTI'", nativeQuery = true)
    public List<Map> getTvStockDelvMarutiDetailsByOu(Integer ou);

    ///query for true value reports- overall stock delivered by ou
    //for non maruti true value vehicles
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REG_NO asc) AS sr_no,\n"
            + "stock.REG_NO, nvl(stock.MODEL_DESC,'-') MODEL_DESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSIS_NO,'-') CHASSIS_NO, nvl(stock.VIN,'-') VIN, stock.ENGINE_NO,\n"
            + "nvl(stock.VARIANT_DESC,'-') VARIANT_DESC, NVL(stock.VARIANT_CODE,'-') VARIANT_CODE, nvl(stock.COLOUR,'-') COLOUR, nvl(stock.COLOUR_CODE,'-') COLOUR_CODE ,\n"
            + " stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REG_DATE),'-') REG_DATE, nvl(to_char(stock.MFG_YEAR),'-') MFG_YEAR, stock.KMS,nvl(to_char(stock.OWNERS),'-')OWNERS, nvl(stock.INSURANCE_DETAILS,'-') INSURANCE_DETAILS,\n"
            + " stock.MANUFACTURER, nvl(stock.GRN_NO,'-') GRN_NO, nvl(to_char(stock.GRN_DATE),'-') GRN_DATE, nvl(stock.CUSTOMER_NAME,'-') CUSTOMER_NAME,\n"
            + "  stock.LOCATION, stock.OPERATING_UNIT\n"
            + " from SS_DMS_STOCK_TV stock  WHERE stock.OPERATING_UNIT=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED' and stock.MANUFACTURER='NON-MARUTI'", nativeQuery = true)
    public List<Map> getTvStockDelvNonMarutiDetailsByOu(Integer ou);

    ///query for true value reports -- overall summary by model count
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getTvLocationCountAll(Integer ouId);

    ///query for true value reports -- overall summary  model count sum
    @Query(value = "SELECT sum(a.ftotalstock) as ftotalstock from"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getTvLocationCountAllSum(Integer ouId);

    ///query for true value reports -- overall summary of maruti vehicles by model count
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        and a.MANUFACTURER='MARUTI'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getTvLocationCountMaruti(Integer ouId);

    ///query for true value reports -- overall summary of maruti vehicles by model count's sum
    @Query(value = "SELECT sum(a.ftotalstock) as ftotalstock from"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        and a.MANUFACTURER='MARUTI'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getTvLocationCountMarutiSum(Integer ouId);

    ///query for true value reports -- overall summary of non maruti vehicles by model count
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        and a.MANUFACTURER='NON-MARUTI'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getTvLocationCountNonMaruti(Integer ouId);

    ///query for true value reports -- overall summary of non maruti vehicles by model count's sum
    @Query(value = "SELECT sum(a.ftotalstock) as ftotalstock from"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct   a.location description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from ss_dms_stock_tv a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OPERATING_UNIT=?1 and  A.LOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION_TV'\n"
            + "                        and a.MANUFACTURER='NON-MARUTI'\n"
            + "                        group by a.location order by  a.location)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getTvLocationCountNonMarutiSum(Integer ouId);

    public Optional<SsDmsStockTruevalue> findByRegNo(String regNo);

}
