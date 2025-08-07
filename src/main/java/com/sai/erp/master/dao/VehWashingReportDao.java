/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsVehWashingRegister;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HarshG
 */
public interface VehWashingReportDao extends JpaRepository<SsVehWashingRegister, Long> {

    //used for washing report based on ouid
    @Query(value = " select ROW_NUMBER() OVER (ORDER BY creation_date desc) AS SR_NO , NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') chassis_no,\n"
            + "NVL(ATTRIBUTE4,'-') VIN,NVL(VEH_WASH_NO,'-') VEH_WASH_NO,\n"
            + " NVL(case when model='null' then '-' else model end,'-') MODEL,\n"
            + "NVL(case when service_advisor='null' then '-' else service_advisor end,'-') service_advisor,\n"
            + "NVL(TO_CHAR(promised_time,'DD-MM-YYYY HH24:MI:SS'),'-') promised_time,\n"
            + " NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + " NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + "NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + "NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + "NVL(TO_CHAR(DS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') DS_OUT_TIME,\n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS ,\n"
            + "NVL(ATTRIBUTE1,'-') IN_TIME , NVL(ATTRIBUTE2, '-') OUT_TIME\n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE OU_ID=:ouId and LOC_ID=:locId  and CREATION_DATE BETWEEN :fromDate AND :toDate\n"
            + "ORDER BY CREATION_DATE DESC", nativeQuery = true)
    public List<Map> getVehWashingReportByOuIdAndLocId(Integer ouId, Integer locId, Date fromDate, Date toDate);

    //used for veh wash histoy based on regno
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + "NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + "NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + "NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + "NVL(TO_CHAR(DS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_OUT_TIME, NVL(DRY_WASH_STAGE,'-') DRY_WASH_STAGE ,\n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS,\n"
            + "NVL(ATTRIBUTE1,'-') IN_TIME , NVL(ATTRIBUTE2, '-') OUT_TIME\n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE REG_NO=:regNo order by creation_date desc", nativeQuery = true)
    public List<Map> getVehWashHistoryByRegNo(String regNo);

    @Query(value = " select ROW_NUMBER() OVER (ORDER BY creation_date desc) AS \"SR NO\" , NVL(REG_NO,'-') \"VEHICLE REGISTRATION NO\",\n"
            + " NVL(case when model='null' then '-' else model end,'-') MODEL,\n"
            + "NVL(case when service_advisor='null' then '-' else service_advisor end,'-') \"SERVICE ADVISOR\",\n"
            + " NVL(washing_supervisor,'-') \"WASHING SUPERVISOR\", NVL(TO_CHAR(FS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"1ST STAGE IN TIME\",\n"
            + " NVL(AIR_BLOW_STN,'-') \"AIR BLOW-1ST STAGE\", NVL(UNDERBODY_STN,'-') \"UNDER BODY-1ST STAGE\", NVL(ENGINE_ROOM_STN,'-') \"ENGINE ROOM-1ST STAGE\",\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"1ST STAGE OUT TIME\",\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"2ND STAGE IN TIME\" , NVL(LOOSE_ITEMS_STN,'-') \"LOOSE ITEM-2ND STAGE\", \n"
            + "NVL(VEH_INTERIOR_STN,'-') \"VEH INTERIOR-2ND STAGE\", NVL(TO_CHAR(SS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"2ND STAGE OUT TIME\" , \n"
            + " NVL(TO_CHAR(DS_IN_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"3RD STAGE IN TIME\" , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') \"VEH EXTERIOR-3RD STAGE\", NVL(GLASS_POLISH_STN,'-') \"GLASS POLISH-3RD STAGE\", \n"
            + "NVL(TO_CHAR(DS_OUT_TIME,'DD-MM-YYYY HH24:MI:SS'),'-') \"3RD STAGE OUT TIME\" , \n"
            + "NVL(TO_CHAR(CREATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') \"CREATION DATE\"\n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE OU_ID=:ouId and LOC_ID=:locId  and CREATION_DATE BETWEEN :fromDate AND :toDate\n"
            + "ORDER BY CREATION_DATE DESC", nativeQuery = true)
    List<Object[]> getVehWashFilteredReport(
            @Param("ouId") Integer ouId,
            @Param("locId") Integer locId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate
    );

    @Query(value = " select ROW_NUMBER() OVER (ORDER BY svw.reg_no asc) AS sr_no, \n"
            + "svw.reg_no ,trunc(creation_date)creation_date,\n"
            + "(select flv.description || '-'|| Substr(msi.description,1,21) mum_model from mtl_system_items_b msi,CSI_ITEM_INSTANCES cii, fnd_lookup_values flv  where cii.instance_number=svw.reg_no\n"
            + "AND msi.ORGANIZATION_ID=119 and msi.INVENTORY_ITEM_ID=cii.INVENTORY_ITEM_ID\n"
            + "AND flv.lookup_code = substr(msi.segment1,3,LENGTH(substr(msi.segment1,3,(instr(msi.segment1,'-')-3))))\n"
            + ") Model ,max(svw.fs_in_time)fsInTime,max(svw.air_blow_stn)AirBlow,max(svw.underbody_stn)unBody,max(svw.engine_room_stn)engRoom,max(svw.fs_out_time)fsOutTime\n"
            + ",max(svw.ss_in_time)secdInTime,max(svw.loose_items_stn)loosItem,max(svw.veh_interior_stn)vehIntrerior,max(svw.ss_out_time)secdOutTime,max(svw.ds_in_time)dryInTime,\n"
            + "max(svw.veh_exterior_stn) vehExt,max(svw.glass_polish_stn) glassPolish,max(svw.ds_out_time)DryOutTime,max(svw.status)status\n"
            + "from SS_VEH_WASHING_REGISTER svw\n"
            + "where to_date(svw.creation_date) between :fromDate and :toDate and svw.ou_id=:ouId and svw.loc_id=nvl(:locId,svw.loc_id)\n"
            + "group by\n"
            + "svw.reg_no ,svw.service_advisor,Model,trunc(creation_date)\n"
            + "order By reg_no", nativeQuery = true)
    public List<Map> getVehWashRegisterReportByOuIdAndLocId(Integer ouId, Integer locId, Date fromDate, Date toDate);

    //used for veh wash histoy based on CHASSIS NO AND OUID
    @Query(value = " select NVL(REG_NO,'-') REG_NO, NVL(chassis_no,'-') CHASSIS_NO, NVL(ATTRIBUTE4,'-') VIN,\n"
            + "NVL(VEH_WASH_NO,'-') VEH_WASH_NO, NVL(model,'-') MODEL,\n"
            + "NVL(service_advisor,'-') service_advisor, NVL(TO_CHAR(promised_time,'YYYY-MM-DD HH24:MI:SS'),'-') promised_time,\n"
            + "NVL(washing_supervisor,'-') washing_supervisor, NVL(TO_CHAR(FS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_IN_TIME,\n"
            + "NVL(AIR_BLOW_STN,'-') AIR_BLOW_STN, NVL(UNDERBODY_STN,'-') UNDERBODY_STN, NVL(ENGINE_ROOM_STN,'-') ENGINE_ROOM_STN,\n"
            + "NVL(TO_CHAR(FS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') FS_OUT_TIME, NVL(FIRST_STAGE,'-') FIRST_STAGE,\n"
            + "NVL(TO_CHAR(SS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_IN_TIME , NVL(LOOSE_ITEMS_STN,'-') LOOSE_ITEMS_STN, \n"
            + "NVL(VEH_INTERIOR_STN,'-') VEH_INTERIOR_STN, NVL(TO_CHAR(SS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') SS_OUT_TIME , \n"
            + "NVL(SECOND_STAGE,'-') SECOND_STAGE , NVL(TO_CHAR(DS_IN_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_IN_TIME , \n"
            + "NVL(VEH_EXTERIOR_STN,'-') VEH_EXTERIOR_STN, NVL(GLASS_POLISH_STN,'-') GLASS_POLISH_STN, \n"
            + "NVL(TO_CHAR(DS_OUT_TIME,'YYYY-MM-DD HH24:MI:SS'),'-') DS_OUT_TIME, NVL(DRY_WASH_STAGE,'-') DRY_WASH_STAGE ,\n"
            + "NVL(TO_CHAR(LOC_ID),'-') LOC_ID, NVL(LOCATION,'-') LOCATION, NVL(TO_CHAR(OU_ID),'-') OU_ID, NVL(CREATED_BY,'-') CREATED_BY,\n"
            + "NVL(TO_CHAR(CREATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') CREATION_DATE, NVL(UPDATED_BY,'-') UPDATED_BY,\n"
            + "NVL(TO_CHAR(UPDATION_DATE,'YYYY-MM-DD HH24:MI:SS'),'-') UPDATION_DATE, NVL(STATUS,'-') STATUS,\n"
            + "NVL(ATTRIBUTE1,'-') IN_TIME , NVL(ATTRIBUTE2, '-') OUT_TIME\n"
            + "FROM SS_VEH_WASHING_REGISTER WHERE CHASSIS_NO=?1 and OU_ID=?2 order by creation_date desc", nativeQuery = true)
    public List<Map> getVehWashHistoryByChassisNo(String chassisNo, Integer ouId);
}
