/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.SsDmsInvStockDuplicate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Lenovo
 */
//public interface SsDmsInvStockDuplicateDao extends CrudRepository<SsDmsInvStockDuplicate, Integer> {
//    public Optional<SsDmsInvStockDuplicate> findByChassis_no(String chassis_no);
//}

public interface SsDmsInvStockDuplicateDao extends CrudRepository<SsDmsInvStockDuplicate, Integer> {
    Optional<SsDmsInvStockDuplicate> findByVin(String vin);
    
    Optional<SsDmsInvStockDuplicate> findByVinAndVehImageNotNull(String vin);
    
    Optional<SsDmsInvStockDuplicate> findByVinAndVehImage2NotNull(String vin);
    
    Optional<SsDmsInvStockDuplicate> findByVinAndVehImage3NotNull(String vin);

}


