package com.aplus.entity;

import javax.persistence.*;

/**
 * Created by lifang on 2015/6/28.
 */
@Entity
@Table(name = "t_log_audit")
public class AuditEntity extends BaseEntity{

    public enum Action{PERSIST, DELETE, UPDATE, SELECT}

    @Enumerated(EnumType.STRING)
    private Action action;

    private String detail;

    @Column(length = 30, nullable = false)
    private String entityId;

    @Column(length = 50, nullable = false)
    private String entityName;

    public AuditEntity() {}

    public AuditEntity(Action action, String detail, String entityId, String entityName) {
        this.action = action;
        this.detail = detail;
        this.entityId = entityId;
        this.entityName = entityName;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
