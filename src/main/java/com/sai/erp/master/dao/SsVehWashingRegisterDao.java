/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsVehWashingRegister;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HarshG
 */
public interface SsVehWashingRegisterDao extends JpaRepository<SsVehWashingRegister, Long> {

    public Optional<SsVehWashingRegister> findFirstByRegNoOrderByCreationDateDesc(String regNo);

    @Query(value = "SELECT cmncode\n"
            + "FROM (\n"
            + "    SELECT cmncode, \n"
            + "           ROW_NUMBER() OVER (PARTITION BY cmncode ORDER BY createddate DESC) AS rn\n"
            + "    FROM FND_COMMON_LOOKUP \n"
            + "    WHERE CMNTYPE = ?1\n"
            + ") ranked\n"
            + "WHERE rn = 1", nativeQuery = true)
    public List<Map> getWashStageByCmnType(String cmnType);

    @Query(value = "SELECT CMNDESC FROM FND_COMMON_LOOKUP WHERE CMNTYPE='WASHSTAGE' AND CMNCODE=?1", nativeQuery = true)
    public List<Map> getWashStageTypeByCmnCode(String cmnCode);

    //used for veh washing function
    //used to get details for new veh in master table
//    @Query(value = " SELECT cii.instance_number, msib.attribute6 chassis_no,\n"
//            + "       msib.attribute12 engine_no, cii.install_date registration_date,\n"
//            + "       hca.account_number,\n"
//            + "       CASE\n"
//            + "          WHEN hp.party_type = 'PERSON'\n"
//            + "             THEN    hp.person_first_name\n"
//            + "                  || ' '\n"
//            + "                  || hp.person_middle_name\n"
//            + "                  || ' '\n"
//            + "                  || hp.person_last_name\n"
//            + "          ELSE party_name\n"
//            + "       END cust_name,\n"
//            + "       msib.inventory_item_id,\n"
//            + "       (SELECT MAX (variant_cd)\n"
//            + "          FROM gd_fdi_trans\n"
//            + "         WHERE ge1 = cii.instance_number AND trans_type = 'WI') variant_code,\n"
//            + "       (SELECT fvl.meaning\n"
//            + "          FROM (SELECT   gd.variant_cd\n"
//            + "                    FROM gd_fdi_trans gd\n"
//            + "                   WHERE gd.ge1 = cii.instance_number AND gd.trans_type = 'WI'\n"
//            + "                ORDER BY gd.trans_date DESC) a,\n"
//            + "               fnd_lookup_values_vl fvl\n"
//            + "         WHERE ROWNUM = 1\n"
//            + "           AND fvl.lookup_code = variant_cd\n"
//            + "           AND attribute_category = 'SS_MSIL_SALES_CODE') vehicle_desc,\n"
//            + "       (SELECT *\n"
//            + "          FROM (SELECT   executive\n"
//            + "                    FROM gd_fdi_trans\n"
//            + "                   WHERE ge1 = cii.instance_number AND trans_type = 'WI'\n"
//            + "                ORDER BY trans_date DESC) a\n"
//            + "         WHERE ROWNUM = 1) service_advisor\n"
//            + "  FROM csi_item_instances cii,\n"
//            + "       mtl_system_items_b msib,\n"
//            + "       hz_parties hp,\n"
//            + "       hz_cust_accounts hca\n"
//            + "WHERE cii.instance_number = ?1\n"
//            + "   and cii.inventory_item_id = msib.inventory_item_id\n"
//            + "   and hp.party_id = cii.owner_party_id\n"
//            + "   and cii.owner_party_account_id = hca.cust_account_id\n"
//            + "   and msib.organization_id = 119", nativeQuery = true)
//    public List<Map> getVehDetailsCsiByRegNo(String regNo);
    
    
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

    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + " NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + " NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + " NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + " FROM SS_VEH_WASHING_REGISTER WHERE REG_NO=:regNo", nativeQuery = true)
    public List<Map> getVehFirstWashDetailsByRegNo(String regNo);

    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + " NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + " NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + " NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + " NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + " NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN,\n"
            + " NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + " FROM SS_VEH_WASHING_REGISTER WHERE REG_NO=:regNo", nativeQuery = true)
    public List<Map> getVehSecondWashDetailsByRegNo(String regNo);

    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + " NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + " NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + " NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + " NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + " NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + " NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + " NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + " NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + " FROM SS_VEH_WASHING_REGISTER WHERE REG_NO=:regNo", nativeQuery = true)
    public List<Map> getVehThirdWashDetailsByRegNo(String regNo);

    @Query(value = " SELECT veh_wash_no \n"
            + "    FROM (\n"
            + "        SELECT veh_wash_no, \n"
            + "               TO_NUMBER(REGEXP_SUBSTR(veh_wash_no, '[^-]+$', 1, 1)) AS serial_no\n"
            + "        FROM ss_veh_washing_register\n"
            + "        WHERE loc_id = :locId AND ou_id = :ouId\n"
            + "    ) \n"
            + "    ORDER BY serial_no DESC \n"
            + "    FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    String getMaxVehWashNoByLocId(Integer locId, Integer ouId);

    //query used to find veh wash OUT details by regno in washing table 
    //used for workshop vehicles
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + " NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + " NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + " NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + " NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + " NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + " NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + " NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + " NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "  NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "  NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + " FROM SS_VEH_WASHING_REGISTER WHERE REG_NO=:regNo ORDER BY CREATION_DATE DESC", nativeQuery = true)
    public List<Map> getVehWashOutDetailsByRegNo(String regNo);

    public Optional<SsVehWashingRegister> findFirstByAttribute4OrderByCreationDateDesc(String vin);

    //query used to find sales veh wash OUT details by vin i.e. attribute4
    //use to make wash OUT entry for sales vehicles
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(ATTRIBUTE4,'-') VIN, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + "NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + "NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + "NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE ATTRIBUTE4=?1 ORDER BY CREATION_DATE DESC", nativeQuery = true)
    public List<Map> getVehWashOutSalesDetailsByAttribute4(String vin);

    //query used to find sales veh wash OUT details by chassisno and ouid
    //use to make wash OUT entry for sales vehicles
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(ATTRIBUTE4,'-') VIN, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + "NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + "NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + "NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS \n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE CHASSIS_NO=?1 and OU_ID=?2  ORDER BY CREATION_DATE DESC\n"
            + "fetch first 1 rows only", nativeQuery = true)
    public List<Map> getVehWashOutSalesDetailsByChassisNoAndOuId(String chassisNo, Integer ouId);

    public Optional<SsVehWashingRegister> findFirstByChassisNoAndOuIdOrderByCreationDateDesc(String chassisNo, Integer ouId);

}
