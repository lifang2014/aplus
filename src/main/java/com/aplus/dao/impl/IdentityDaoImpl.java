package com.aplus.dao.impl;

import com.aplus.dao.IdentityDao;
import com.aplus.entity.IdentityEntity;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/2/1.
 */
@Repository
public class IdentityDaoImpl extends BaseDaoImpl<IdentityEntity, Long> implements IdentityDao{

    @Override
    public IdentityEntity findByClazz(String clazz) {
        PathBuilder<IdentityEntity> pb = new PathBuilder<IdentityEntity>(IdentityEntity.class, "o");
        JPAQuery query = new JPAQuery(entityManager);
        return query.from(pb).where(pb.getString("clazz").eq(clazz)).singleResult(pb);
    }
}
