/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.service;

import com.sai.erp.master.dao.AppVersionDao;
import com.sai.erp.master.entity.SsAppVersionChsEnq;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HarshG
 */
@Service
public class AppVersionService {

    @Autowired
    private AppVersionDao versionRepo;

}
