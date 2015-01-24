package com.aplus.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 *     1.每一个Admin都必须对应一个Member(反之不成立),也就是说Member必须要有对应Admin才有登录权限.
 * </pre>
 * Created by lifang on 2015/1/19.
 */
@Entity
@Table(name = "t_admin")
public class AdminEntity extends BaseEntity{

    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @Column(length = 64, nullable = false)
    private char[] password;

    /**
     * 帐号是否锁定
     * 锁定状态不允许登录
     */
    private Boolean isLocked;

    /**
     * 登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    /**
     * 锁定时间,时间过期及解锁
     */
    private Date lockedDate;

    /**
     * 登录次数
     */
    private Integer loginCount = 0;

    /**
     * 失败次数
     */
    private Integer failedCount = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
