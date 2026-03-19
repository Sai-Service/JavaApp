/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.BatchNameServiceCutOfDate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface BatchNameServiceCutOfDateDao extends CrudRepository<BatchNameServiceCutOfDate, BigDecimal> {

    @Query(value = " SELECT DISTINCT  to_char(TRUNC(CUT_OFf_DATE),'DD-MM-YYYY') AS BATCH_CREATION_DATE\n"
            + "FROM SS_DMS_INV_STOCK_TAKING_SR\n"
            + "WHERE cut_off_date BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE) and OUID=?1", nativeQuery = true)
    public List<Map> getSrBatchCutOfDate(Integer ou);

}
