package com.aplus.entity;

import javax.persistence.*;

/**
 * 子菜单
 * Created by lifang on 2015/1/31.
 */
@Entity
@Table(name = "t_sub_menu")
public class SubMenuEntity extends BaseEntity{

    /**
     * 菜单名称
     */
    @Column(length = 32, unique = false)
    private String name;

    /**
     * 访问路径
     */
    @Column(length = 100, unique = false)
    private String path;

    /**
     * 是否启用
     */
    private Boolean isEnabled = true;

    /**
     * 子菜单所属的主菜单
     */
    @ManyToOne
    @JoinColumn(name = "menuId")
    private MenuEntity menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}
