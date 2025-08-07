/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsStockTrf;
import com.sai.erp.master.entity.SsDmsStockTrfTruevalue;
import com.sai.erp.master.entity.SsDmsStockTruevalue;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IT-HARSH
 */
public interface SsDmsStockTrfTruevalueDao extends CrudRepository<SsDmsStockTrfTruevalue, Integer>{
      
    public Optional<SsDmsStockTrfTruevalue> findByStockTrfNo(String stockTrfNo);
    
    @Query(value = " select trf.stock_trf_no,trf.stock_trf_date,stock.REG_NO, nvl(stock.VIN,'-') VIN, nvl(stock.chassis_no,'-') chassis_no,stock.model_desc,\n" +
" stock.VEH_STATUS,  stock.VARIANT_CODE,\n" +
"trf.MADE_BY transferred_by,trf.FROM_LOCATION,\n" +
"            nvl(trf.FROMKM,0)fromKm, nvl(trf.driver_name,'-') driver_name\n" +
"            from ss_dms_stock_tv stock,ss_dms_stock_trf_tv trf \n" +
"            where trf.TO_LOCATION=?1\n" +
"            and stock.veh_status='Stock Transfer In-Transit'\n" +
"            and stock.REG_NO=trf.REG_NO \n" +
"            and trf.received_by is null\n" +
"            and trf.recd_date is null", nativeQuery = true)
    public List<Map> getByTvTransList(String toLocation);
    
    
}
