package com.aplus.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Member类型
 * Created by lifang on 2015/1/31.
 */
@Entity
@Table(name = "t_member_type")
public class MemberTypeEntity extends BaseEntity{

    /**
     * 类型名称
     */
    private String name;

    /**
     * 排序值
     */
    private Integer sort = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
