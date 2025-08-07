/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.AndroidVehQr;
import com.sai.erp.master.entity.SsDmsInvStockOriginal;
import com.sai.erp.master.entity.SsDmsStockTrf;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
public interface SsDmsStockTrfDao extends CrudRepository<SsDmsStockTrf, Integer> {

    public Optional<SsDmsStockTrf> findByVin(String vin);

    @Query("select  NVL(max(stockTrfNo),'1') from SsDmsStockTrf where ou=?1 and stockTrfNo like 'STAN%' ")
    public String getMaxSrlNo(Integer ou);

    @Query("SELECT subStr(stockTrfNo, INSTR(stockTrfNo, '-')+1)\n"
            + "FROM SsDmsStockTrf where ou=?1 and stockTrfNo like 'STAN%' ")
    public String getMaxSrlNo1(Integer ou);

    public Optional<SsDmsStockTrf> findByStockTrfNo(String stkTrfNo);

 

}
