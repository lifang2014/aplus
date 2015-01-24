package com.aplus.services.impl;

import com.aplus.dao.AdminDao;
import com.aplus.dao.BaseDao;
import com.aplus.services.AdminService;
import com.aplus.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lifang on 2015/1/22.
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, Long> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<AdminEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
