package com.aplus.entity;

import com.aplus.enums.LoginModeEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifang on 2015/1/23.
 */
@Entity
@Table(name = "t_login_log")
public class LoginLogEntity extends BaseEntity{

    /**
     * 登录IP
     */
    @Column(length = 64,nullable = false)
    private String loginIp;

    /**
     * 登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    /**
     * 登录模式
     */
    @Enumerated(EnumType.STRING)
    private LoginModeEnum loginMode;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public LoginModeEnum getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(LoginModeEnum loginMode) {
        this.loginMode = loginMode;
    }
}
