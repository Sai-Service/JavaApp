/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.DetailsByVin;
import com.sai.erp.master.entity.SsDmsStockService;
import com.sai.erp.master.entity.SsVehStockLogin;
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
public interface SsDmsStockServiceDao extends CrudRepository<SsDmsStockService, Integer> {

    //query for vehicle enquiry via regno
    @Query(value = "select  stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VIN,'-') VIN, nvl(stock.VARIANT,'-') VARIANT,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.COLORDESC,'-') COLORDESC, nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(STOCK.REGDATE),0) REGDATE, nvl(to_char(stock.JOBCARDDATE),0) JOBCARDDATE ,\n"
            + "            nvl(stock.ENGINNO,'-') ENGINENO,nvl(stock.CHASSISNO,'-') CHASSISNO, stock.VEH_STATUS, nvl(stock.status,'-') status,nvl(to_char(stock.ERPACCTNO),0) ERPACCTNO, \n"
            + "            nvl(stock.DMSLOCATION,'-') DMSLOCATION,  nvl(stock.ERPLOC,'-') ERPLOC, stock.OUID, nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "            nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO, nvl(stock.TECHNICIAN,'-') TECHNICIAN,\n"
            + "              nvl(stock.LASTUPDATEDBY,'-') LASTUPDATEDBY, nvl(stock.CREATED_BY,'-') CREATED_BY, nvl(stock.DMSCUSTID,'-') DMSCUSTID,\n"
            + "               nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "               nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from SS_DMS_INV_STOCK_SERVICE stock LEFT JOIN ANDROID_SERVICE_VEH_IMAGE img ON stock.REGNO = img.REGNO where stock.REGNO=?1 ", nativeQuery = true)
    public List<Map> getSrDetailsByRegNo(String regNo);

    //query for vehicle enquiry via chassis no
    @Query(value = "select   stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VIN,'-') VIN, nvl(stock.VARIANT,'-') VARIANT,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.COLORDESC,'-') COLORDESC, nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(STOCK.REGDATE),0) REGDATE, nvl(to_char(stock.JOBCARDDATE),0) JOBCARDDATE ,\n"
            + "            nvl(stock.ENGINNO,'-') ENGINENO,nvl(stock.CHASSISNO,'-') CHASSISNO, stock.VEH_STATUS, nvl(stock.status,'-') status,nvl(to_char(stock.ERPACCTNO),0) ERPACCTNO, \n"
            + "            nvl(stock.DMSLOCATION,'-') DMSLOCATION,  nvl(stock.ERPLOC,'-') ERPLOC, stock.OUID, nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "            nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO, nvl(stock.TECHNICIAN,'-') TECHNICIAN,\n"
            + "              nvl(stock.LASTUPDATEDBY,'-') LASTUPDATEDBY, nvl(stock.CREATED_BY,'-') CREATED_BY, nvl(stock.DMSCUSTID,'-') DMSCUSTID,\n"
            + "               nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "               nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from SS_DMS_INV_STOCK_SERVICE stock LEFT JOIN ANDROID_SERVICE_VEH_IMAGE img ON stock.REGNO = img.REGNO where stock.chassisNo=?1 ", nativeQuery = true)
    public List<Map> getSrDetailsByChassis(String chassisNo);

    //query for vehicle enquiry via job card no
    @Query(value = "select   stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VIN,'-') VIN, nvl(stock.VARIANT,'-') VARIANT,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.COLORDESC,'-') COLORDESC, nvl(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(STOCK.REGDATE),0) REGDATE, nvl(to_char(stock.JOBCARDDATE),0) JOBCARDDATE ,\n"
            + "            nvl(stock.ENGINNO,'-') ENGINENO,nvl(stock.CHASSISNO,'-') CHASSISNO, stock.VEH_STATUS, nvl(stock.status,'-') status,nvl(to_char(stock.ERPACCTNO),0) ERPACCTNO, \n"
            + "            nvl(stock.DMSLOCATION,'-') DMSLOCATION,  nvl(stock.ERPLOC,'-') ERPLOC, stock.OUID, nvl(stock.PHYSICALLOCATION,'-') PHYSICALLOCATION, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + "            nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(to_char(stock.CONTACTNO),0) CONTACTNO, nvl(stock.TECHNICIAN,'-') TECHNICIAN,\n"
            + "              nvl(stock.LASTUPDATEDBY,'-') LASTUPDATEDBY, nvl(stock.CREATED_BY,'-') CREATED_BY, nvl(stock.DMSCUSTID,'-') DMSCUSTID,\n"
            + "               nvl(IMG.VEHIMAGE, '-' ) vehImage,  nvl(IMG.VEHIMAGE2, '-' ) vehImage2,  nvl(IMG.VEHIMAGE3, '-' ) vehImage3,  nvl(IMG.VEHIMAGE4, '-' ) vehImage4,  nvl(IMG.VEHIMAGE5, '-' ) vehImage5,\n"
            + "               nvl(to_char(img.lastUploadDate),'-')lastUploadDate, nvl(img.lastUploadedBy,'-') lastUploadedBy\n"
            + "             from SS_DMS_INV_STOCK_SERVICE stock LEFT JOIN ANDROID_SERVICE_VEH_IMAGE img ON stock.REGNO = img.REGNO where stock.jobCardNo=?1 ", nativeQuery = true)
    public List<Map> getSrDetailsByJobCardNo(String jobCardNo);

    //query for vehicle IN details by reg no
    @Query(value = " select  nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VARIANT,'-') VARIANT, nvl(stock.JOBCARDNO,'-') JOBCARDNO,\n"
            + " NVL(TO_CHAR(JOBCARDDATE),'-') JOBCARDDATE,  nvl(stock.COLOR,'-') COLOR, nvl(stock.FUEL_DESC,'-') FUEL_DESC, stock.REGNO,\n"
            + "  nvl(stock.VIN,'-') VIN, NVL(stock.ENGINNO,'-') ENGINNO, stock.VEH_STATUS, stock.PHYSICALLOCATION , trf.STOCK_TRF_NO, NVL(trf.FROM_LOCATION,'-') FROM_LOCATION,\n"
            + "  nvl(trf.fromKm,0) FROMKM ,NVL(trf.TO_LOCATION,'-') TO_LOCATION, nvl(trf.DRIVER_NAME,'-') DRIVER_NAME\n"
            + "                      from ss_dms_inv_stock_service stock,ss_dms_stock_trf_service trf where stock.REGNO=trf.REG_NO and stock.veh_status='Stock Transfer In-Transit'\n"
            + "                       and trf.received_by is null and trf.recd_date is null and stock.regno=?1 ", nativeQuery = true)
    public List<Map> getSrDetStockInByRegNo(String regNo);

    //query for vehicle IN details by chassisno 
    @Query(value = "select  nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VARIANT,'-') VARIANT, nvl(stock.JOBCARDNO,'-') JOBCARDNO,\n"
            + " NVL(TO_CHAR(JOBCARDDATE),'-') JOBCARDDATE,  nvl(stock.COLOR,'-') COLOR, nvl(stock.FUEL_DESC,'-') FUEL_DESC, stock.REGNO,\n"
            + "  nvl(stock.VIN,'-') VIN, NVL(stock.ENGINNO,'-') ENGINNO, stock.VEH_STATUS, stock.PHYSICALLOCATION , trf.STOCK_TRF_NO, NVL(trf.FROM_LOCATION,'-') FROM_LOCATION,\n"
            + "  nvl(trf.fromKm,0) FROMKM ,NVL(trf.TO_LOCATION,'-') TO_LOCATION, nvl(trf.DRIVER_NAME,'-') DRIVER_NAME\n"
            + "                      from ss_dms_inv_stock_service stock,ss_dms_stock_trf_service trf where stock.REGNO=trf.REG_NO and stock.veh_status='Stock Transfer In-Transit'\n"
            + "                       and trf.received_by is null and trf.recd_date is null and stock.chassisNo=?1 and trf.ou=?2 ", nativeQuery = true)
    public List<Map> getSrDetStockInByChassis(String chassisNo, Integer ou);

    //query for vehicle IN details by job card no
    @Query(value = " select  nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.VARIANT,'-') VARIANT, nvl(stock.JOBCARDNO,'-') JOBCARDNO,\n"
            + " NVL(TO_CHAR(JOBCARDDATE),'-') JOBCARDDATE,  nvl(stock.COLOR,'-') COLOR, nvl(stock.FUEL_DESC,'-') FUEL_DESC, stock.REGNO,\n"
            + "  nvl(stock.VIN,'-') VIN, NVL(stock.ENGINNO,'-') ENGINNO, stock.VEH_STATUS, stock.PHYSICALLOCATION , trf.STOCK_TRF_NO, NVL(trf.FROM_LOCATION,'-') FROM_LOCATION,\n"
            + "  nvl(trf.fromKm,0) FROMKM ,NVL(trf.TO_LOCATION,'-') TO_LOCATION, nvl(trf.DRIVER_NAME,'-') DRIVER_NAME\n"
            + "                      from ss_dms_inv_stock_service stock,ss_dms_stock_trf_service trf where stock.REGNO=trf.REG_NO and stock.veh_status='Stock Transfer In-Transit'\n"
            + "                       and trf.received_by is null and trf.recd_date is null and stock.jobCardNo=?1", nativeQuery = true)
    public List<Map> getSrDetStockInByJobCardNo(String jobCardNo);

    //query to update the veh status as STOCK in service_stock table
    @Modifying
    @Transactional
    @Query(value = "update SsDmsStockService set  veh_status=?1,PHYSICALLOCATION=?2 where regNo=?3")
    public void updateSrVehStatus(String vehStatus, String physicalLocation, String regNo);

    // query for stock taking details by reg no - SERVICE
    @Query(value = " select REGNO, CHASSISNO, JOBCARDNO, nvl(MODELDESC,'-') MODELDESC , nvl(VARIANT,'') VARIANT,nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE ,  nvl(ERPACCTNO,'0') ERPACCTNO ,PHYSICALLOCATION, VEH_STATUS, \n"
            + "                         nvl(COLOR,'-') COLOR, nvl(COLORDESC,'-') COLORDESC,  NVL(fuel_desc,'-') FUEL_DESC, nvl(vin,'-') vin, ENGINNO, \n"
            + "                  NVL(status,'-') status, OUID from SS_DMS_INV_STOCK_SERVICE where VEH_STATUS not in ('DELIVERED')  AND REGNO=?1", nativeQuery = true)
    public List<Map> getByRegNo(String regNo);

    //query for stock taking details by chassis no - SERVICE 
    @Query(value = "select REGNO, CHASSISNO, JOBCARDNO, nvl(MODELDESC,'-') MODELDESC , nvl(VARIANT,'') VARIANT,nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE,  nvl(ERPACCTNO,0) ERPACCTNO ,PHYSICALLOCATION, VEH_STATUS, \n"
            + "                         nvl(COLOR,'-') COLOR, nvl(COLORDESC,'-') COLORDESC,  NVL(fuel_desc,'-') FUEL_DESC, nvl(vin,'-') vin, ENGINNO, \n"
            + "                  NVL(status,'-') status, OUID from SS_DMS_INV_STOCK_SERVICE where VEH_STATUS not in ('DELIVERED')  AND chassisno=?1 and ouid=?2 ", nativeQuery = true)
    public List<Map> getByChassisNo(String chassisNo, Integer ouId);

    // query for stock taking details by job card no - SERVICE
    @Query(value = " select REGNO, CHASSISNO, JOBCARDNO, nvl(MODELDESC,'-') MODELDESC , nvl(VARIANT,'') VARIANT,nvl(to_char(JOBCARDDATE),'-')JOBCARDDATE,  nvl(ERPACCTNO,0) ERPACCTNO ,PHYSICALLOCATION, VEH_STATUS, \n"
            + "                         nvl(COLOR,'-') COLOR, nvl(COLORDESC,'-') COLORDESC,  NVL(fuel_desc,'-') FUEL_DESC, nvl(vin,'-') vin, ENGINNO, \n"
            + "                  NVL(status,'-') status, OUID from SS_DMS_INV_STOCK_SERVICE where VEH_STATUS not in ('DELIVERED')  AND JOBCARDNO=?1", nativeQuery = true)
    public List<Map> getByJobCardNo(String jobCardNo);

    //query for reports//
    //query for overall SERVICE  vehicles which are not delivered in an ou 
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE,'DD-MM-YYYY'),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(DMSCUSTID,'-') DMSCUSTID, nvl(CONTACTNO,'0') CONTACTNO, nvl(SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(CREATED_BY,'-') CREATED_BY, nvl(to_char(CREATIONDATE),'-') CREATIONDATE , nvl(TECHNICIAN,'-') TECHNICIAN, nvl(DMSLOCATION,'-') DMSLOCATION, nvl(ERPLOC,'-') ERPLOC,\n"
            + "  stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS NOT IN ('DELIVERED')", nativeQuery = true)
    public List<Map> getSrStockDetailsByOu(Integer ouId);

    //query for overall SERVICE vehicles in an ou for SERVICE dept 
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(stock.COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(stock.JOBCARDDATE),'-') JOBCARDDATE, nvl(stock.DEPT,'-') DEPT,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE,'DD-MM-YYYY'),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(stock.DMSCUSTID,'-') DMSCUSTID, nvl(stock.CONTACTNO,'0') CONTACTNO, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(stock.CREATED_BY,'-') CREATED_BY, nvl(to_char(stock.CREATIONDATE),'-') CREATIONDATE , nvl(stock.TECHNICIAN,'-') TECHNICIAN, nvl(stock.DMSLOCATION,'-') DMSLOCATION,\n"
            + "  nvl(stock.ERPLOC,'-') ERPLOC, stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS NOT IN ('DELIVERED') and stock.dept='SERVICE'", nativeQuery = true)
    public List<Map> getSrStockDetailsServByOu(Integer ouId);

    //query for overall SERVICE vehicles in an ou for BODYSHOP dept 
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(stock.COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(to_char(stock.JOBCARDDATE),'-') JOBCARDDATE, nvl(stock.DEPT,'-') DEPT,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE,'DD-MM-YYYY'),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(stock.DMSCUSTID,'-') DMSCUSTID, nvl(stock.CONTACTNO,'0') CONTACTNO, nvl(stock.SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(stock.CREATED_BY,'-') CREATED_BY, nvl(to_char(stock.CREATIONDATE),'-') CREATIONDATE , nvl(stock.TECHNICIAN,'-') TECHNICIAN, nvl(stock.DMSLOCATION,'-') DMSLOCATION,\n"
            + "  nvl(stock.ERPLOC,'-') ERPLOC, stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS NOT IN ('DELIVERED') and stock.dept='DP'", nativeQuery = true)
    public List<Map> getSrStockDetailsDpByOu(Integer ouId);

    //query for overall delivered SERVICE vehicles in an ou
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(stock.dept,'-') DEPT, nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(DMSCUSTID,'-') DMSCUSTID, nvl(CONTACTNO,'0') CONTACTNO, nvl(SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(CREATED_BY,'-') CREATED_BY, nvl(to_char(CREATIONDATE),'-') CREATIONDATE , nvl(TECHNICIAN,'-') TECHNICIAN, nvl(DMSLOCATION,'-') DMSLOCATION, nvl(ERPLOC,'-') ERPLOC,\n"
            + "  stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED'", nativeQuery = true)
    public List<Map> getSrStockDelvDetailsByOu(Integer ouId);

    //query for overall delivered SERVICE vehicles of service dept in an ou
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(stock.dept,'-') DEPT, nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(DMSCUSTID,'-') DMSCUSTID, nvl(CONTACTNO,'0') CONTACTNO, nvl(SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(CREATED_BY,'-') CREATED_BY, nvl(to_char(CREATIONDATE),'-') CREATIONDATE , nvl(TECHNICIAN,'-') TECHNICIAN, nvl(DMSLOCATION,'-') DMSLOCATION, nvl(ERPLOC,'-') ERPLOC,\n"
            + "  stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED' and stock.dept='SERVICE'", nativeQuery = true)
    public List<Map> getSrStockDelvDetailsServByOu(Integer ouId);

    //query for overall delivered SERVICE vehicles of dp dept in an ou
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY stock.REGNO asc) AS sr_no,\n"
            + "stock.REGNO, nvl(stock.MODELDESC,'-') MODELDESC, nvl(stock.FUEL_DESC,'-') FUEL_DESC, nvl(stock.CHASSISNO,'-') CHASSISNO, nvl(stock.VIN,'-') VIN, stock.ENGINNO,\n"
            + "nvl(COLORDESC,'-') COLORDESC, nvl(stock.VARIANT,'-') VARIANT, NVL(stock.JOBCARDNO,'-') JOBCARDNO, nvl(stock.dept,'-') DEPT, nvl(to_char(JOBCARDDATE),'-') JOBCARDDATE,\n"
            + " nvl(stock.COLOR,'-') COLOR, nvl(stock.ERPACCTNO,'0') ERPACCTNO , stock.VEH_STATUS, nvl(stock.status,'-') STATUS, nvl(to_char(stock.REGDATE),'-') REG_DATE,\n"
            + " nvl(stock.CUSTNAME,'-') CUSTNAME, nvl(DMSCUSTID,'-') DMSCUSTID, nvl(CONTACTNO,'0') CONTACTNO, nvl(SERVICEADVISOR,'-') SERVICEADVISOR,\n"
            + " nvl(CREATED_BY,'-') CREATED_BY, nvl(to_char(CREATIONDATE),'-') CREATIONDATE , nvl(TECHNICIAN,'-') TECHNICIAN, nvl(DMSLOCATION,'-') DMSLOCATION, nvl(ERPLOC,'-') ERPLOC,\n"
            + "  stock.PHYSICALLOCATION, stock.OUID\n"
            + " from SS_DMS_INV_STOCK_SERVICE stock  WHERE stock.OUID=?1\n"
            + "AND stock.VEH_STATUS='DELIVERED' and stock.dept='DP'", nativeQuery = true)
    public List<Map> getSrStockDelvDetailsDpByOu(Integer ouId);

//    //query for SERVICE reports -- overall summary by LOCATION count
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getSrLocationCountAll(Integer ouId);

//     //query for SERVICE reports -- overall summary by LOCATION count for service dept
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION' and a.dept='SERVICE'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getSrLocationCountServ(Integer ouId);
//    
//     //query for SERVICE reports -- overall summary by LOCATION count for dp dept

    @Query(value = "select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION' and a.dept='DP'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0", nativeQuery = true)
    public List<Map> getSrLocationCountDp(Integer ouId);
//    
//     ///query for SERVICE reports -- overall summary by LOCATION count sum

    @Query(value = "SELECT  SUM(a.ftotalstock) AS ftotalstock from\n"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getSrLocationCountAllSum(Integer ouId);
//    
//      ///query for SERVICE reports -- overall  summary by LOCATION count for service sum

    @Query(value = "SELECT  SUM(a.ftotalstock) AS ftotalstock from\n"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION' and a.dept='SERVICE'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getSrLocationCountServSum(Integer ouId);
//    
//      ///query for SERVICE reports -- overall TOTAL summary by LOCATION count for dp sum

    @Query(value = "SELECT  SUM(a.ftotalstock) AS ftotalstock from\n"
            + "(select ROW_NUMBER() OVER (ORDER BY a.description) AS sr_no, a.description,A.Stock,A.STKTRF,A.Delivered,a.Stock+a.STKTRF+a.Delivered totalstock,\n"
            + "a.UNINVOICE,a.INVOICE ,a.ReadyForDelivered, a.UNINVOICE+a.INVOICE+a.ReadyForDelivered ftotalstock from\n"
            + "                        ( \n"
            + "                        select distinct  a.PHYSICALLOCATION description,\n"
            + "                        sum(case when VEH_STATUS='STOCK' then 1 else 0 end) Stock,\n"
            + "                        sum(case when VEH_STATUS in ('Stock Transfer In-Transit') then 1 else 0 end) STKTRF,\n"
            + "                        sum(case when VEH_STATUS  in ('DELIVERED') then 1 else 0 end) Delivered,\n"
            + "                        sum(case when a.VEH_STATUS in ('STOCK','Stock Transfer In-Transit') and \n"
            + "                         (a.status is null or a.status in('UNINVOICE'))  then 1 else 0 end) UNINVOICE,\n"
            + "                       sum(case when STATUS='INVOICE' then 1 else 0 end) INVOICE,\n"
            + "                       sum(case when STATUS='Ready For Delivered' then 1 else 0 end) ReadyForDelivered\n"
            + "                        from SS_DMS_INV_STOCK_SERVICE a,fnd_lookup_values FLV\n"
            + "                        where 1=1 and a.OUID=?1 and  A.PHYSICALLOCATION=FLV.meaning  and flv.lookup_type like 'SS_PHYSICAL_LOCATION' and a.dept='DP'\n"
            + "                        group by a.PHYSICALLOCATION order by  a.PHYSICALLOCATION)a where  (a.UNINVOICE+a.INVOICE+a.ReadyForDelivered) !=0)a", nativeQuery = true)
    public List<Map> getSrLocationCountDpSum(Integer ouId);


//    @Modifying
//    @Transactional
//    @Query(value = "update SsDmsStockService set  vehStatus=?1   where jobCardNo=?2")
//    public void updateStkTrfMakeVehStatus(String vehStatus, String jobCardNo);

//    @Modifying
//    @Transactional
//    @Query(value = "update SsDmsStockService set  vehStatus=?1, physicalLocation=?2   where jobCardNo=?3")
//    public void updateWsStkTrfRecVehStatus(String status, String toLocation, String jobCardNo);

    public Optional<SsDmsStockService> findByRegNo(String regNo);

}
