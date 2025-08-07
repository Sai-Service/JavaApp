/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.DetailsByVin;
import com.sai.erp.master.entity.OrgDefinition;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo
 */
public interface OrgDefinitionDao extends JpaRepository<OrgDefinition, Long> {
    @Query(value="select trim(meaning) locationName ,LOOKUP_CODE locId\n" +
"  from fnd_lookup_values where lookup_type like 'SS_PHYSICAL_LOCATION' and ATTRIBUTE1=?1 ", nativeQuery = true)
    public List<Map>getByOrgOu(Integer operating_unit);
    
    
        @Query(value="select trim(meaning) locationName ,LOOKUP_CODE locId\n" +
"  from fnd_lookup_values where lookup_type like 'SS_PARKING_LOCATION' and ATTRIBUTE1=?1 ", nativeQuery = true)
    public List<Map>getByOrgOuPark(Integer operating_unit);
    
    @Query(value="select trim(meaning) locationName  from fnd_lookup_values where lookup_type like 'SS_PHYSICAL_LOCATION' and lookup_code=?1 ", nativeQuery = true)
    public String getByLocId(String locId);
    
     @Query(value="select trim(lookup_code) locId  from fnd_lookup_values where lookup_type like 'SS_PHYSICAL_LOCATION' and meaning=?1", nativeQuery = true)
    public String getByLocationName(String locationName);
}
