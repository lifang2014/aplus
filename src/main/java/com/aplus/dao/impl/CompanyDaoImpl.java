package com.aplus.dao.impl;

import com.aplus.dao.CompanyDao;
import com.aplus.entity.CompanyEntity;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/1/31.
 */
@Repository
public class CompanyDaoImpl extends BaseDaoImpl<CompanyEntity, Long> implements CompanyDao{

    @Override
    public CompanyEntity findByCompanyNo(String companyNo) {
        PathBuilder<CompanyEntity> pb = new PathBuilder<CompanyEntity>(CompanyEntity.class, "o");
        JPAQuery query = new JPAQuery(entityManager);
        CompanyEntity companyEntity = query.from(pb).where(pb.getString("companyNo").eq(companyNo)).singleResult(pb);
        return companyEntity;
    }
}
