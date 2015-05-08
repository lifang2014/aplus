package com.aplus.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限资源表
 * Created by lifang on 2015/5/1.
 */
@Entity
@Table(name = "t_auth_permission_source")
public class PermissionSourceEntity extends BaseEntity{

    /**
     * 名称
     */
    private String name;

    /**
     * 主体
     */
    private String subject;

    /**
     * 资源
     */
    private String source;

    /**
     * 操作
     */
    private String action;

    public PermissionSourceEntity(){}

    public PermissionSourceEntity(String name, String subject, String source, String action) {
        this.name = name;
        this.subject = subject;
        this.source = source;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSouce() {
        return source;
    }

    public void setSouce(String source) {
        this.source = source;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
