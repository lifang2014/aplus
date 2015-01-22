package com.common.aplus.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lifang on 2015/1/19.
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseEntity implements Serializable{

    @Id
    @TableGenerator(name="GEN_INDEX",table="t_generator",
            pkColumnName = "gen_key",valueColumnName = "gen_value",
            pkColumnValue = "id",allocationSize = 2,initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "GEN_INDEX")
    private Long id;
    private Date createDate;//创建时间
    private Date modifyDate;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
