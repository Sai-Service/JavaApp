/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsInvStockNew;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
public interface SsDmsInvStockOriginalDao extends CrudRepository<SsDmsInvStockNew, Integer> {

    public Optional<SsDmsInvStockNew> findByVin(String vin);

    public Optional<SsDmsInvStockNew> findByChassisNo(String chassis_no);

    @Query(value = " select chassis_no CHASSIS_NUM, model_desc MODEL_CD, VARIANT_CODE VARIANT_CD, "
            + "colour, fuel_desc, vin from ss_dms_inv_stock where vin=?1", nativeQuery = true)
    public List<Map> getByVinNumber(String vin);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsInvStockNew set  status=?1 where VIN=?2")
    public void updateDelvStatus(String status, String VIN);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsInvStockNew set  vehStatus=?1 where vin=?2")
    public void updateVehStatus(String vehStatus, String VIN);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsInvStockNew set  vehStatus=?1 ,location=?2 where vin=?3")
    public void updateDelvStatusAndLoc(String vehStatus, String location, String VIN);

    @Modifying
    @Transactional
    @Query(value = "update SsDmsInvStockNew set  vehStatus=?1,location=?2 where vin=?3")
    public void updateStkTrfStatus(String vehStatus, String location, String VIN);

    //query to find veh details for washing by vin no in inv stock table
    @Query(value = "SELECT nvl(CHASSIS_NO,'-') CHASSIS_NO,  nvl(VIN,'-') VIN, nvl(VARIANT_DESC,'-') VARIANT_DESC, nvl(ENGINE_NO,'-') ENGINE_NO,\n"
            + " nvl(DSE_NAME,'-') DSE_NAME from SS_DMS_INV_STOCK WHERE VIN=?1", nativeQuery = true)
    public List<Map> getVehDetailsByVin(String vin);

    @Query(value
            = " SELECT NVL(S.VIN,'-') VIN, NVL(S.CHASSIS_NO,'-') CHASSIS_NO, \n"
            + "NVL(S.ENGINE_NO,'-') ENGINE_NO, NVL(S.MODEL_DESC,'-') MODEL_DESC, \n"
            + "NVL(S.VARIANT_DESC,'-') VARIANT_DESC, NVL(S.COLOUR,'-') COLOUR, \n"
            + "NVL(S.VEH_STATUS,'-') VEH_STATUS, NVL(S.REMARKS,'-') REMARKS, \n"
            + " CASE WHEN UPPER(NVL(S.REMARKS,'X')) LIKE '%Demo%Car%' \n"
            + "  THEN 'DEMO CAR' ELSE 'SALES' END VEH_TYPE \n"
            + " FROM ( SELECT * FROM SS_DMS_INV_STOCK \n"
            + " WHERE UPPER(TRIM(CHASSIS_NO)) = UPPER(TRIM(:chassisNo)) \n"
            + " AND UPPER(TRIM(ENGINE_NO))  = UPPER(TRIM(:engineNo)) \n"
            + " ORDER BY CASE WHEN UPPER(NVL(REMARKS,'X')) LIKE '%Demo%Car%' \n"
            + "     THEN 0 ELSE 1 END, \n"
            + "     GRN_DATE DESC NULLS LAST ) S \n"
            + " WHERE ROWNUM = 1", nativeQuery = true)
    List<Map> findStockByChassisAndEngine(@Param("chassisNo") String chassisNo,
            @Param("engineNo") String engineNo);
}
