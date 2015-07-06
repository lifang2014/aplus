package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.AuditEntity;
import com.aplus.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/6/28.
 */
@Service
public class AuditServiceImpl extends BaseServiceImpl<AuditEntity, Long> implements AuditService{

    @Override
    @Autowired
    public void setBaseDao(BaseDao<AuditEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
