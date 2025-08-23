/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsVehWashingRegisterGa;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface SsVehWashingRegisterGaDao extends CrudRepository<SsVehWashingRegisterGa, Integer> {

    @Query(value = "SELECT CMNDESC FROM FND_COMMON_LOOKUP WHERE CMNTYPE='WASHSTAGEGOA' ORDER BY CMNID", nativeQuery = true)
    public List<Map> getWashStageByCmnType();

    public Optional<SsVehWashingRegisterGa> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    @Query(value = "SELECT NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO,\n"
            + "NVL(VIN,'-') VIN, NVL(MODEL,'-') MODEL, NVL(SERVICE_ADVISOR,'-') SERVICE_ADVISOR,\n"
            + "NVL(WASHING_SUPERVISOR,'-') WASHING_SUPERVISOR, NVL(BODY_WASH,'-') BODY_WASH, NVL(FULL_WASH,'-') FULL_WASH,\n"
            + "NVL(DRY_BODY_WASH,'-') DRY_BODY_WASH, NVL(DRY_WASH_FULL,'-') DRY_WASH_FULL, NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(LOC_ID,0) LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(OU_ID,0) OU_ID,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY, NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,\n"
            + " NVL(UPDATED_BY,'-') UPDATED_BY, NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE,\n"
            + " NVL(STATUS,'-') STATUS  FROM SS_VEH_WASHING_REGISTER_GA \n"
            + "WHERE REG_NO=?1 ORDER BY CREATION_DATE DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    public List<Map> getVehWashOutGaDetailsByRegNo(String regNo);

    //query for getting the max veh wash no in washing register ga table
    @Query(value = " SELECT veh_wash_no FROM (\n"
            + " SELECT veh_wash_no, TO_NUMBER(REGEXP_SUBSTR(veh_wash_no, '[^-]+$', 1, 1)) AS serial_no\n"
            + " FROM ss_veh_washing_register_ga\n"
            + " WHERE loc_id = :locId AND ou_id = :ouId ) \n"
            + " ORDER BY serial_no DESC \n"
            + " FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    String getMaxVehWashNoByLocId(Integer locId, Integer ouId);

    public Optional<SsVehWashingRegisterGa> findFirstByVinOrderByCreationDateDesc(String vin);

    public Optional<SsVehWashingRegisterGa> findFirstByChassisNoAndOuIdOrderByCreationDateDesc(String chassisNo, Integer ouId);

    //query used to find sales veh wash OUT details by chassisno and ouid for goa
    //use to make wash OUT entry for sales vehicles - goa
    @Query(value = " SELECT NVL(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO,\n"
            + "NVL(VIN,'-') VIN, NVL(MODEL,'-') MODEL, NVL(SERVICE_ADVISOR,'-') SERVICE_ADVISOR,\n"
            + "NVL(WASHING_SUPERVISOR,'-') WASHING_SUPERVISOR, NVL(BODY_WASH,'-') BODY_WASH, NVL(FULL_WASH,'-') FULL_WASH,\n"
            + "NVL(DRY_BODY_WASH,'-') DRY_BODY_WASH, NVL(DRY_WASH_FULL,'-') DRY_WASH_FULL, NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,\n"
            + "NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME, NVL(LOC_ID,0) LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(OU_ID,0) OU_ID,\n"
            + "NVL(CREATED_BY,'-') CREATED_BY, NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE,\n"
            + " NVL(UPDATED_BY,'-') UPDATED_BY, NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE,\n"
            + " NVL(STATUS,'-') STATUS  FROM SS_VEH_WASHING_REGISTER_GA \n"
            + "WHERE CHASSIS_NO=?1 and OU_ID=?2 ORDER BY CREATION_DATE DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    public List<Map> getVehWashOutSalesGaDetailsByChassisNoAndOuId(String chassisNo, Integer ouId);

    @Query(value = "select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor,\n"
            + "NVL(BODY_WASH,'-') BODY_WASH, NVL(FULL_WASH,'-') FULL_WASH, NVL(DRY_BODY_WASH,'-') DRY_BODY_WASH,\n"
            + "NVL(DRY_WASH_FULL,'-') DRY_WASH_FULL, \n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME\n"
            + "FROM SS_VEH_WASHING_REGISTER_GA WHERE REG_NO=:regNo order by creation_date desc", nativeQuery = true)
    public List<Map> getVehWashHistoryGaByRegNo(String regNo);
    
    
     @Query(value = "select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor,\n"
            + "NVL(BODY_WASH,'-') BODY_WASH, NVL(FULL_WASH,'-') FULL_WASH, NVL(DRY_BODY_WASH,'-') DRY_BODY_WASH,\n"
            + "NVL(DRY_WASH_FULL,'-') DRY_WASH_FULL, \n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS,\n"
            + "NVL(TO_CHAR(IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') IN_TIME,NVL(TO_CHAR(OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') OUT_TIME\n"
            + "FROM SS_VEH_WASHING_REGISTER_GA WHERE CHASSIS_NO=:chassisNo order by creation_date desc", nativeQuery = true)
    public List<Map> getVehWashHistoryGaByChassisNo(String chassisNo);
    //used to get details for new veh in master table ALONG WITH vin no.
    @Query(value = "  SELECT distinct  GFT.VIN, cii.instance_number, msib.attribute6 chassis_no,\n"
            + "                   msib.attribute12 engine_no, cii.install_date registration_date,\n"
            + "                   hca.account_number,\n"
            + "                   CASE\n"
            + "                      WHEN hp.party_type = 'PERSON'\n"
            + "                         THEN    hp.person_first_name\n"
            + "                              || ' '\n"
            + "                              || hp.person_middle_name\n"
            + "                              || ' '\n"
            + "                              || hp.person_last_name\n"
            + "                      ELSE party_name\n"
            + "                   END cust_name,\n"
            + "                   msib.inventory_item_id,\n"
            + "                   (SELECT MAX (variant_cd)\n"
            + "                      FROM gd_fdi_trans\n"
            + "                     WHERE ge1 = cii.instance_number AND trans_type = 'WI') variant_code,\n"
            + "                   (SELECT fvl.meaning\n"
            + "                      FROM (SELECT   gd.variant_cd\n"
            + "                                FROM gd_fdi_trans gd\n"
            + "                               WHERE gd.ge1 = cii.instance_number AND gd.trans_type = 'WI'\n"
            + "                            ORDER BY gd.trans_date DESC) a,\n"
            + "                           fnd_lookup_values_vl fvl\n"
            + "                     WHERE ROWNUM = 1\n"
            + "                       AND fvl.lookup_code = variant_cd\n"
            + "                       AND attribute_category = 'SS_MSIL_SALES_CODE') vehicle_desc,\n"
            + "                   (SELECT *\n"
            + "                      FROM (SELECT   executive\n"
            + "                                FROM gd_fdi_trans\n"
            + "                               WHERE ge1 = cii.instance_number AND trans_type = 'WI'\n"
            + "                            ORDER BY trans_date DESC) a\n"
            + "                     WHERE ROWNUM = 1) service_advisor\n"
            + "              FROM csi_item_instances cii,\n"
            + "              gd_fdi_trans gft,\n"
            + "                   mtl_system_items_b msib,\n"
            + "                   hz_parties hp,\n"
            + "                   hz_cust_accounts hca\n"
            + "            WHERE cii.instance_number = ?1\n"
            + "               and cii.inventory_item_id = msib.inventory_item_id\n"
            + "               and cii.instance_number = gft.ge1\n"
            + "               and hp.party_id = cii.owner_party_id\n"
            + "               and cii.owner_party_account_id = hca.cust_account_id\n"
            + "               and msib.organization_id = 119", nativeQuery = true)
    public List<Map> getVehDetailsCsiByRegNo(String regNo);

    
    //query to fetch true value veh details for washing in
    @Query(value = "SELECT nvl(REG_NO,'-') REG_NO, NVL(CHASSIS_NO,'-') CHASSIS_NO, NVL(VIN,'-') VIN,\n"
            + "NVL(ENGINE_NO,'-') ENGINE_NO, NVL(MODEL_DESC,'-') MODEL_DESC, NVL(VARIANT_DESC,'-') VARIANT_DESC,\n"
            + "NVL(VARIANT_CODE,'-') VARIANT_CODE, NVL(FUEL_DESC,'-') FUEL_DESC, NVL(MANUFACTURER,'-') MANUFACTURER,\n"
            + "NVL(DSE_NAME,'-') DSE_NAME FROM SS_DMS_STOCK_TV WHERE REG_NO=:regNo", nativeQuery = true)
    public List<Map> getTvVehDetailsByRegNo(String regNo);
}
