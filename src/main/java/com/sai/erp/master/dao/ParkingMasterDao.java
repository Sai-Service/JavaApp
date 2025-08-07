/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsTdParkingMaster;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface ParkingMasterDao extends CrudRepository<SsTdParkingMaster, Object>{

    
    @Query(value = " SELECT DISTINCT DEPARTMENT FROM SS_TD_PARKING_MASTER WHERE OU_ID=?1 AND LOC_ID=?2", nativeQuery = true)
    public List<Map> getDepartmentByOuIdAndLocId(Integer ouId, Integer locId);
    
    @Query(value = " SELECT DRIVER_NAME  FROM SS_TD_PARKING_MASTER WHERE OU_ID=?1 AND LOC_ID=?2 AND DEPARTMENT=?3", nativeQuery = true)
    public List<Map> getDriverNameByOuIdAndLocIdAndDepartment(Integer ouId, Integer locId, String department);
    
    
     @Query(value = "SELECT ATTRIBUTE1  FROM SS_TD_PARKING_MASTER WHERE DEPARTMENT='PARKING' AND OU_ID=?1 AND LOC_ID=?2", nativeQuery = true)
    public List<Map> getBayByOuIdAndLocIdAndDepartment(Integer ouId, Integer locId);
    
    
    
}
