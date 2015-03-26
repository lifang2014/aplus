package com.aplus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lifang on 2015/2/1.
 */
@Entity
@Table(name = "t_identity")
public class IdentityEntity extends BaseEntity{

    /**
     * 前缀,字母组成
     */
    @Column(length = 12)
    private String prefix;

    /**
     * 后缀,数字组成
     */
    private Long suffix = 1000L;

    /**
     * 增长幅度
     */
    private Integer step = 1;

    /**
     * 对应的表
     */
    @Column(length = 50, nullable = false)
    private String clazz;

    public IdentityEntity() {
    }

    public IdentityEntity(String prefix, Long suffix, Integer step, String clazz) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.step = step;
        this.clazz = clazz;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getSuffix() {
        return suffix;
    }

    public void setSuffix(Long suffix) {
        this.suffix = suffix;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
