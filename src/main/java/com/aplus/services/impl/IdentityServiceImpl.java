package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.dao.IdentityDao;
import com.aplus.entity.IdentityEntity;
import com.aplus.services.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lifang on 2015/2/1.
 */
@Service
public class IdentityServiceImpl extends BaseServiceImpl<IdentityEntity, Long> implements IdentityService{

    @Autowired
    private IdentityDao identityDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<IdentityEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    @Transactional(readOnly = true)
    public IdentityEntity findByClazz(String clazz) {
        if(StringUtils.isNotBlank(clazz)){
            return identityDao.findByClazz(clazz);
        }
        return null;
    }

    @Override
    @Transactional
    public String getIdentity(Class clazz) {
        IdentityEntity identityEntity = findByClazz(clazz.getName());
        if(identityEntity != null){
            String prefix = identityEntity.getPrefix();
            Long suffix = identityEntity.getSuffix() + identityEntity.getStep();
            identityEntity.setSuffix(suffix);
            return new StringBuffer(prefix).append(suffix).toString();
        }else{
            identityEntity = new IdentityEntity("AA", 1000L, 1, clazz.getName());
            persist(identityEntity);
            return new StringBuffer(identityEntity.getPrefix()).append(identityEntity.getSuffix()).toString();
        }
    }
}
