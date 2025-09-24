/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.GdFdiTrans;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HarshG
 */
public interface GdFdiTransDao extends CrudRepository<GdFdiTrans, Integer>{
    
        public Optional<GdFdiTrans> findFirstByTransRefNumAndTransTypeOrderByTransRefDateDesc(String transRefNum,String transType);
    
}
