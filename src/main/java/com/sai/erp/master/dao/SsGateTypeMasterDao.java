/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsGateTypeMaster;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HarshG
 */
public interface SsGateTypeMasterDao extends JpaRepository<SsGateTypeMaster, Long> {

    //USED FOR FETCHING GATE NO AND GATE TYPE BASED ON LOC ID
    @Query(value = " SELECT  GATE_NO, GATE_TYPE FROM SS_GATE_TYPE_MASTER WHERE LOC_ID=:locId and gate_type is not null ORDER BY CREATION_DATE  ", nativeQuery = true)
    public List<Map> getGateDetailsByLocId(Integer locId);

    //USED FOR FETCHING GATE NO BASED ON LOC ID
    @Query(value = " SELECT GATE_NO FROM SS_GATE_TYPE_MASTER WHERE LOC_ID=?1", nativeQuery = true)
    public List<Map> getGateNoByLocId(Integer locId);

    //USED FOR FETCHING GATE TYPE BASED ON LOC ID
    @Query(value = " SELECT GATE_TYPE FROM SS_GATE_TYPE_MASTER WHERE LOC_ID=?1", nativeQuery = true)
    public List<Map> getGateTypeByLocId(Integer locId);

    //USED FOR FETCHING GATE TYPE BASED ON LOC ID
    @Query(value = " SELECT GATE_TYPE FROM SS_GATE_TYPE_MASTER WHERE LOC_ID=?1 AND GATE_NO=?2", nativeQuery = true)
    public List<Map> getGateTypeByLocIdAndGateNo(Integer locId, Integer gateNo);

    
    //used for fetching gate type for parking
    @Query(value = " SELECT GATE_TYPE,GATE_NO FROM SS_GATE_TYPE_MASTER WHERE LOC_ID=?1 AND ATTRIBUTE1=?2", nativeQuery = true)
    public List<Map> getGateTypeForParking(Integer locId, String attribute1);
}
