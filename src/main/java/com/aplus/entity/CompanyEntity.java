package com.aplus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by lifang on 2015/1/31.
 */
@Entity
@Table(name = "t_hr_company")
public class CompanyEntity extends BaseEntity{

    /**
     * 公司名称(简称)
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 全称
     */
    @Column(length = 100, nullable = false)
    private String fullName;

    /**
     * 编号
     */
    @Column(length = 32, nullable = false)
    private String companyNo;

    /**
     * 公司Logo
     */
    @Column(length = 32)
    private String logo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public Long getCompanyId() {
        return this.getId();
    }

}
