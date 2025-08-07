/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dao;

import com.sai.saivehicledelivery.entity.SsVehDelvLogin;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Harsh Gawde
 */
public interface SsVehDelvLoginDao extends CrudRepository<SsVehDelvLogin, Integer> {

    public SsVehDelvLogin findByLoginNameAndPassword(String loginName, String password);

     public Optional<SsVehDelvLogin> findDetailsByLoginNameAndPassword(String loginName, String password);
//    public Optional<SsVehDelvLogin> findByLoginName(String loginName);
}
