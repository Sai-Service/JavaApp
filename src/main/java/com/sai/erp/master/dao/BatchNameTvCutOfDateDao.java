/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.BatchNameTvCutOfDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Harsh Gawde
 */
public interface BatchNameTvCutOfDateDao extends JpaRepository<BatchNameTvCutOfDate, Long> {
    
//       @Query(value = "select count(*)   from \n" +
//"SS_DMS_INV_STOCK_TAKING_TV\n" +
//"where operating_unit=?1  and TO_DATE(CUT_OFF_DATE)=?2", nativeQuery = true)
//    public List<Map> getTvBatchCutOfDate(Integer ou, Date cutOffDate);
    
    
     @Query(value = " SELECT DISTINCT  to_char(TRUNC(CUT_OFF_DATE),'DD-MM-YYYY') AS BATCH_CREATION_DATE\n" +
"FROM ss_dms_inv_stock_taking_tv\n" +
"WHERE CUT_OFF_DATE BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE) and OPERATING_UNIT=?1", nativeQuery = true)
    public List<Map> getTvBatchCutOfDate(Integer ou);
}
