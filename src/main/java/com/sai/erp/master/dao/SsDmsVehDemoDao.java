/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsVehDemo;
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
public interface SsDmsVehDemoDao extends CrudRepository<SsDmsVehDemo, Integer> {

    @Query(value = " SELECT NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(VIN,'-') VIN, NVL(MODEL_DESC,'-') MODEL_DESC, NVL(FUEL_DESC,'-') FUEL_DESC,\n"
            + "NVL(VARIANT_DESC,'-') VARIANT_DESC, NVL(ENGINE_NO,'-') ENGINE_NO\n"
            + "FROM SS_DMS_INV_STOCK WHERE REMARKS LIKE '%Demo Car%' AND VEH_STATUS='STOCK' AND CHASSIS_NO=:chassisNo", nativeQuery = true)
    public List<Map> getDemoVehDetailsByChassisNo(String chassisNo);

    //query for fetching demo veh details by regno in ss_dms_veh_demo table along with last kms
    @Query(value = " select NVL(cii.instance_number,'-') VEHICLE_NO, NVL(DEMO.CHASSIS_NO,'-') CHASSIS_NO, NVL(DEMO.VIN,'-') VIN, \n"
            + "NVL(DEMO.MODEL_DESC,'-') MODEL_DESC, NVL(DEMO.FUEL_DESC,'-') FUEL_DESC, NVL(DEMO.VARIANT_DESC,'-') VARIANT_DESC,\n"
            + "NVL(DEMO.ENGINE_NO,'-') ENGINE_NO, NVL(DEMO.IN_KM,'0') LAST_IN_KM,\n"
            + "NVL(TO_CHAR(IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') IN_TIME,\n"
            + "NVL(DEMO.FUEL_QTY,'0') FUEL_QTY\n"
            + "from mtl_system_items_b msi,CSI_ITEM_INSTANCES cii,SS_DMS_INV_STOCK stock, SS_DMS_VEH_DEMO demo\n"
            + "where msi.attribute6=stock.CHASSIS_NO and msi.attribute12=stock.ENGINE_NO AND msi.ORGANIZATION_ID=119\n"
            + "AND DEMO.CHASSIS_NO=STOCK.CHASSIS_NO AND DEMO.VIN=STOCK.VIN\n"
            + "and msi.INVENTORY_ITEM_ID=cii.INVENTORY_ITEM_ID and cii.instance_number=:regNo AND STOCK.REMARKS LIKE '%Demo Car%'\n"
            + "AND STOCK.VEH_STATUS='STOCK' ORDER BY UPDATION_DATE DESC \n"
            + "fetch first 1 rows only", nativeQuery = true)
    public List<Map> getDemoVehDetailsPresentByRegNo(String regNo);

    //query for fetching demo veh details by regno in stock table 
    @Query(value = " select NVL(cii.instance_number,'-') VEHICLE_NO, NVL(STOCK.CHASSIS_NO,'-') CHASSIS_NO, NVL(STOCK.VIN,'-') VIN, \n"
            + "NVL(STOCK.MODEL_DESC,'-') MODEL_DESC, NVL(STOCK.FUEL_DESC,'-') FUEL_DESC, NVL(STOCK.VARIANT_DESC,'-') VARIANT_DESC,\n"
            + "NVL(STOCK.ENGINE_NO,'-') ENGINE_NO\n"
            + " from mtl_system_items_b msi,CSI_ITEM_INSTANCES cii,SS_DMS_INV_STOCK stock\n"
            + " where msi.attribute6=stock.CHASSIS_NO and msi.attribute12=stock.ENGINE_NO AND msi.ORGANIZATION_ID=119\n"
            + "and msi.INVENTORY_ITEM_ID=cii.INVENTORY_ITEM_ID and cii.instance_number=:regNo AND STOCK.REMARKS LIKE '%Demo Car%'\n"
            + "AND STOCK.VEH_STATUS='STOCK'", nativeQuery = true)
    public List<Map> getDemoVehDetailsByRegNo(String regNo);

    @Query(value = " SELECT nvl(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(VIN,'-') VIN, NVL(MODEL_DESC,'-') MODEL_DESC, NVL(FUEL_DESC,'-') FUEL_DESC,\n"
            + "NVL(VARIANT_DESC,'-') VARIANT_DESC, NVL(ENGINE_NO,'-') ENGINE_NO, NVL(LOC_ID,0) LOC_ID , NVL(OU_ID,0) OU_ID,\n"
            + "NVL(LOCATION,'-') LOCATION, NVL(CUST_NAME,'-') CUST_NAME, NVL(CUST_CONTACT_NO,'-') CUST_CONTACT_NO, NVL(CUST_ADDRESS,'-') CUST_ADDRESS,\n"
            + "NVL(REMARKS,'-') REMARKS, NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') OUT_TIME,\n"
            + "NVL(FUEL_QTY,'0') FUEL_QTY,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY, NVL(TO_CHAR(CREATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') CREATION_DATE,\n"
            + "NVL(UPDATED_BY,'-') UPDATED_BY , NVL(TO_CHAR(UPDATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') UPDATION_DATE,\n"
            + "NVL(ATTRIBUTE1,'-') GATE_NO, NVL(ATTRIBUTE2,'-') GATE_TYPE\n"
            + "FROM SS_DMS_VEH_DEMO WHERE REG_NO=:regNo AND OUT_KM IS NOT NULL and IN_KM IS NULL ORDER BY CREATION_DATE DESC\n"
            + "fetch first 1 rows only", nativeQuery = true)
    public List<Map> getDemoVehInDetailsByRegNo(String regNo);

    public Optional<SsDmsVehDemo> findFirstByChassisNoOrderByCreationDateDesc(String chassisNo);

    public Optional<SsDmsVehDemo> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    //query for demo sales report by location and date wise
    @Query(value = " select ROW_NUMBER() OVER (ORDER BY CREATION_DATE DESC) AS sr_no, NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(MODEL_DESC,'-') MODEL_DESC, NVL(FUEL_DESC,'-') FUEL_DESC,\n"
            + "NVL(VARIANT_DESC,'-') VARIANT_DESC, NVL(ENGINE_NO,'-') ENGINE_NO, NVL(CUST_NAME,'-') CUST_NAME, NVL(CUST_ADDRESS,'-') CUST_ADDRESS,\n"
            + "NVL(CUST_CONTACT_NO,'-') CUST_CONTACT_NO, NVL(REMARKS,'-') REMARKS,\n"
            + "NVL(LOC_ID,0) LOC_ID, NVL(OU_ID,0) OU_ID,NVL(LOCATION,'-') LOCATION, NVL(ATTRIBUTE1,'-') ATTRIBUTE1, NVL(ATTRIBUTE2,'-') ATTRIBUTE2,\n"
            + "NVL(OUT_KM,0) OUT_KM, NVL(TO_CHAR(OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') OUT_TIME, \n"
            + "NVL(IN_KM,0) IN_KM, NVL(TO_CHAR(IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') IN_TIME,\n"
            + "NVL(FUEL_QTY,0) FUEL_QTY, NVL(ATTRIBUTE3,'-') ATTRIBUTE3, NVL(GATE_PASS_NO,0) GATE_PASS_NO,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY, NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,\n"
            + "NVL(UPDATED_BY,'-') UPDATED_BY,  NVL(TO_CHAR(UPDATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') UPDATION_DATE\n"
            + "FROM SS_DMS_VEH_DEMO WHERE OU_ID=?1 and LOC_ID=?2  and CREATION_DATE BETWEEN ?3 AND ?4\n"
            + "ORDER BY CREATION_DATE desc", nativeQuery = true)
    public List<Map> getDemoVehReportByOuIdAndLocId(Integer ouId, Integer locId, Date fromDate, Date toDate);

    //query for demo sales report by location and date wise
    @Query(value = " SELECT \n"
            + "     cii.instance_number,\n"
            + "    CASE\n"
            + "        WHEN (\n"
            + "            SELECT COUNT(*) \n"
            + "            FROM ss_dms_veh_demo d\n"
            + "            WHERE d.vin = stk.vin \n"
            + "              AND d.out_time IS NOT NULL \n"
            + "              AND d.in_time IS NULL\n"
            + "        ) > 0 THEN 'OUT FOR DEMO'\n"
            + "        ELSE 'AVAILABLE'\n"
            + "    END AS STATUS\n"
            + "FROM \n"
            + "    ss_dms_inv_stock stk, csi_item_instances cii, mtl_system_items_b msi\n"
            + "     where stk.remarks like '%Demo Car%'\n"
            + "   and msi.attribute6=stk.CHASSIS_NO and msi.attribute12=stk.ENGINE_NO AND msi.ORGANIZATION_ID=119\n"
            + "   and  msi.INVENTORY_ITEM_ID=cii.INVENTORY_ITEM_ID\n"
            + "    and stk.VEH_status='STOCK' and STK.OPERATING_UNIT=?1 and stk.location=?2", nativeQuery = true)
    public List<Map> getDemoVehStatusListByOu(Integer ouId, String location);

}
