/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.AndroidTvVehImage;
import com.sai.erp.master.entity.SsDmsInvStockDuplicate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IT-HARSH
 */
public interface AndroidTvVehImageDao extends CrudRepository<AndroidTvVehImage, Integer> {
    
    Optional<AndroidTvVehImage> findByRegNo(String regNo);
    
    Optional<AndroidTvVehImage> findByRegNoAndVehImageNotNull(String regNo);
    
    Optional<AndroidTvVehImage> findByRegNoAndVehImage2NotNull(String regNo);
    
    Optional<AndroidTvVehImage> findByRegNoAndVehImage3NotNull(String regNo);
}
