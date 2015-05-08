package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.MemberTypeEntity;
import com.aplus.services.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class MemberTypeServiceImpl extends BaseServiceImpl<MemberTypeEntity, Long> implements MemberTypeService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<MemberTypeEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
