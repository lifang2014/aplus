package com.aplus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 主菜单
 * Created by lifang on 2015/1/31.
 */
@Entity
@Table(name = "t_menu")
public class MenuEntity extends BaseEntity{

    /**
     * 菜单名称
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 是否启用
     */
    private Boolean isEnabled = true;

    /**
     * 主菜单包含的子菜单
     */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SubMenuEntity> subMenus = new HashSet<SubMenuEntity>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Set<SubMenuEntity> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<SubMenuEntity> subMenus) {
        this.subMenus = subMenus;
    }
}
