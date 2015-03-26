package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.dao.CompanyDao;
import com.aplus.entity.CompanyEntity;
import com.aplus.services.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<CompanyEntity, Long> implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<CompanyEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public CompanyEntity findByCompanyNo(String companyNo) {
        if(StringUtils.isBlank(companyNo)){
            return null;
        }
        return companyDao.findByCompanyNo(companyNo);
    }
}
