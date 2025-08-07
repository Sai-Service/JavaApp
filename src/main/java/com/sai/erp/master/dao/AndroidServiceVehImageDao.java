/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.AndroidServiceVehImage;
import com.sai.erp.master.entity.AndroidTvVehImage;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IT-HARSH
 */
public interface AndroidServiceVehImageDao  extends CrudRepository<AndroidServiceVehImage, Integer> {
    
     Optional<AndroidServiceVehImage> findByRegNo(String regNo);
    
    Optional<AndroidServiceVehImage> findByRegNoAndVehImageNotNull(String regNo);
    
    Optional<AndroidServiceVehImage> findByRegNoAndVehImage2NotNull(String regNo);
    
    Optional<AndroidServiceVehImage> findByRegNoAndVehImage3NotNull(String regNo);
    
}
