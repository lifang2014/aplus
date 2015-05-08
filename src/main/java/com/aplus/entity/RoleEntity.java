package com.aplus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 角色
 * 1. Admin , Role 多对多关系
 * 2. Role , Permission 多对多关系
 * Created by lifang on 2015/1/24.
 */
@Entity
@Table(name = "t_auth_role")
public class RoleEntity extends BaseEntity{

    /**
     * 角色名称
     */
    @Column(length = 16, nullable = false)
    private String name;

    /**
     * 是否系统内置角色
     */
    private boolean isSystem;

    /**
     * 角色描述
     */
    @Column(length = 255)
    private String description;

    /**
     * 角色对应的权限
     * @ElementCollection 组件集合映射, 表示权限不能脱离角色而存在.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_role_authority")
    private List<String> authorities = new LinkedList<String>();

    /**
     * 角色对应的管理员
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<AdminEntity> admins = new HashSet<AdminEntity>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Set<AdminEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<AdminEntity> admins) {
        this.admins = admins;
    }
}
