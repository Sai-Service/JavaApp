/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dao;

import com.sai.saivehicledelivery.entity.CsiItemInstances;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface CsiItemInstancesDao extends CrudRepository<CsiItemInstances, Integer>{

    public Optional<CsiItemInstances> findByInstanceNumber(String vehicleNo);
    
}
