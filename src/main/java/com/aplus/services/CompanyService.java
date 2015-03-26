package com.aplus.services;

import com.aplus.entity.CompanyEntity;

/**
 * Created by lifang on 2015/1/31.
 */
public abstract interface CompanyService extends BaseService<CompanyEntity, Long>{

    public CompanyEntity findByCompanyNo(String companyNo);
}
