package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.LoginLogEntity;
import com.aplus.services.BaseService;
import com.aplus.services.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/24.
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogEntity, Long> implements LoginLogService{

    @Override
    @Autowired
    public void setBaseDao(BaseDao<LoginLogEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
