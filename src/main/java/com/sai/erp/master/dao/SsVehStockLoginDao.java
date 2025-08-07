/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;


import com.sai.erp.master.entity.SsVehStockLogin;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface SsVehStockLoginDao extends CrudRepository<SsVehStockLogin, Integer> {

    public SsVehStockLogin findByLoginNameAndPassword(String loginName, String password);
  
    public Optional<SsVehStockLogin> findByLoginName(String loginName);

}

