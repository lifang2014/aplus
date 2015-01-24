package com.aplus.listener;

import com.aplus.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by lifang on 2015/1/23.
 */
public class EntityListener {

    /**
     * Entity持久化之前,自动创建时间
     * @param baseEntity
     */
    @PrePersist
    public void prePersist(BaseEntity baseEntity){
        baseEntity.setCreateDate(new Date());
        baseEntity.setModifyDate(new Date());
    }

    /**
     * Entity更新时,自动修改更新时间
     * @param baseEntity
     */
    @PreUpdate
    public void preUpdate(BaseEntity baseEntity){
        baseEntity.setModifyDate(new Date());
    }

}
