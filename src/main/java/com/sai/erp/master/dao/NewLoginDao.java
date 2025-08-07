/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsVehStockLogin;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Harsh Gawde
 */
public interface NewLoginDao extends JpaRepository<SsVehStockLogin, Long> {

    public Optional<SsVehStockLogin> findByLoginName(String loginName);

    @Query(value = "SELECT MAX(id) FROM SS_VEH_STOCK_LOGIN", nativeQuery = true)
    long findLastInsertedId();

    @Query(value = "select stock.loginname,stock.password,stock.ouid,stock.locid,stock.location,STOCK.LOCATION_NAME,\n"
            + "STOCK.DEPTNAME from ss_veh_stock_login stock where loginname=?1", nativeQuery = true)
    public List<Map> getdetailsByLoginName(String loginName);
    
    
    @Query(value = "select trim(meaning) locationName ,LOOKUP_CODE locId\n" +
"  from fnd_lookup_values where lookup_type like 'SS_PHYSICAL_LOCATION' and ATTRIBUTE1=?1", nativeQuery = true)
    public List<Map> detailsByOuId(String ouId);
}
