package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.MemberEntity;
import com.aplus.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/4/18.
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberEntity, Long> implements MemberService{

    @Override
    @Autowired
    public void setBaseDao(BaseDao<MemberEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

}
