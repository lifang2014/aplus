package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.RoleEntity;
import com.aplus.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/4/18.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Long> implements RoleService{

    @Override
    @Autowired
    public void setBaseDao(BaseDao<RoleEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

}
