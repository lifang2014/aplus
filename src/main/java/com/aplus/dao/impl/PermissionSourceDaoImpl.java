package com.aplus.dao.impl;

import com.aplus.dao.BaseDao;
import com.aplus.dao.PermissionSourceDao;
import com.aplus.entity.PermissionSourceEntity;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/5/1.
 */
@Repository
public class PermissionSourceDaoImpl extends BaseDaoImpl<PermissionSourceEntity, Long> implements PermissionSourceDao{

    private PathBuilder<PermissionSourceEntity> pathBuilder = new PathBuilder<PermissionSourceEntity>(PermissionSourceEntity.class, "o");

    @Override
    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action) {
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        return jpaQuery.from(pathBuilder).where(
                pathBuilder.getString("subject").eq(subject)
                        .and(pathBuilder.getString("source").eq(source))
                        .and(pathBuilder.getString("action").eq(action))).singleResult(pathBuilder);
    }
}
