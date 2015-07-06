package com.aplus.iterceptor;

import com.aplus.entity.AuditEntity;
import com.aplus.entity.BaseEntity;
import com.aplus.logs.IAuditLog;
import com.aplus.services.AdminService;
import com.aplus.services.AuditService;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lifang on 2015/6/28.
 */
public class AuditLogInterceptor extends EmptyInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptor.class);

    @Autowired
    private AuditService auditService;
    @Autowired
    private AdminService adminService;

    private Set persists = new HashSet();
    private Set selects = new HashSet();
    private Set updates = new HashSet();
    private Set deletes = new HashSet();

    @Override
    public String onPrepareStatement(String sql) {
        if(logger.isDebugEnabled()) {
            logger.debug("execute sql : {}", sql);
        }
        return super.onPrepareStatement(sql);
    }

    /**
     * 删除时调用
     * @param entity
     * @param id
     * @param state
     * @param propertyNames
     * @param types
     */
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof IAuditLog){
            deletes.add(entity);
        }
    }

    /**
     * 更新数据时调用，但数据还没有更新到数据库
     * @param entity
     * @param id
     * @param currentState
     * @param previousState
     * @param propertyNames
     * @param types
     * @return
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if(entity instanceof IAuditLog){
            updates.add(entity);
        }
        return false;
    }

    /**
     * 读取时调用
     * @param entity
     * @param id
     * @param state
     * @param propertyNames
     * @param types
     * @return
     */
    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof IAuditLog){
            selects.add(entity);
        }
        return false;
    }

    /**
     *  保存数据的时候调用，数据还没有保存到数据库.
     * @param entity
     * @param id
     * @param state
     * @param propertyNames
     * @param types
     * @return
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if(entity instanceof IAuditLog){
            persists.add(entity);
        }
        return false;
    }


    /**
     * 保存，删除，更新 在提交之前调用 (通常在 postFlush 之前).
     * @param entities
     */
    @Override
    public void preFlush(Iterator entities) {
        super.preFlush(entities);
    }

    /**
     *  提交之后调用(commit之后)
     * @param entities
     */
    @Override
    public void postFlush(Iterator entities) {
        if(logger.isDebugEnabled()){
            logger.debug("write log");
        }
        try{
            for (Iterator it = persists.iterator(); it.hasNext();) {
                IAuditLog entity = (IAuditLog) it.next();
                AuditEntity auditEntity = new AuditEntity(AuditEntity.Action.PERSIST,
                        entity.getLogDetail(), entity.getLogId() + "", entity.getClass().getName());
                auditService.persist(auditEntity);
            }

            for (Iterator it = updates.iterator(); it.hasNext();) {
                IAuditLog entity = (IAuditLog) it.next();
                AuditEntity auditEntity = new AuditEntity(AuditEntity.Action.UPDATE,
                        entity.getLogDetail(), entity.getLogId() + "", entity.getClass().getName());
                auditService.persist(auditEntity);
            }

            for (Iterator it = deletes.iterator(); it.hasNext();) {
                IAuditLog entity = (IAuditLog) it.next();
                AuditEntity auditEntity = new AuditEntity(AuditEntity.Action.DELETE,
                        entity.getLogDetail(), entity.getLogId() + "", entity.getClass().getName());
                auditService.persist(auditEntity);
            }

            for (Iterator it = selects.iterator(); it.hasNext();) {
                IAuditLog entity = (IAuditLog) it.next();
                AuditEntity auditEntity = new AuditEntity(AuditEntity.Action.SELECT,
                        entity.getLogDetail(), entity.getLogId() + "", entity.getClass().getName());
                auditService.persist(auditEntity);
            }
        }finally {
            persists.clear();
            selects.clear();
            updates.clear();
            deletes.clear();
        }
    }


}
