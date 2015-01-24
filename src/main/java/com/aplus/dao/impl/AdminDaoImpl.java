package com.aplus.dao.impl;

import com.aplus.dao.AdminDao;
import com.aplus.entity.AdminEntity;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/1/22.
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<AdminEntity, Long> implements AdminDao {

    @Override
    public AdminEntity findByUsername(String username) {
        PathBuilder<AdminEntity> pb = new PathBuilder<AdminEntity>(AdminEntity.class, "o");
        JPAQuery query = new JPAQuery(entityManager);
        AdminEntity adminEntity = query.from(pb).where(pb.getString("username").eq(username)).singleResult(pb);
        return adminEntity;
    }
}
