/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsVehWashMailMaster;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface SsVehWashMailMasterDao extends CrudRepository<SsVehWashMailMaster, Integer> {
    
    
    @Query(value = " SELECT USER_NAME, EMAIL_ID FROM SS_VEH_WASH_MAIL_MASTER WHERE OU_ID=?1 AND LOC_ID=?2", nativeQuery = true)
    public List<Map> getWashMailByOuAndLocId(Integer ouId, Integer locId);
    
    
     @Query(value = " SELECT USER_NAME, EMAIL_ID FROM SS_VEH_WASH_MAIL_MASTER WHERE  OU_ID=?1 AND LOC_ID=?2  and attribute1='PARK' ", nativeQuery = true)
    public List<Map> getParkMailByOuAndLocId(Integer ouId, Integer locId);
}
