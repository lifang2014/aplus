package com.aplus.entity;

import com.aplus.utils.CipherUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     1.每一个Admin都必须对应一个Member(反之不成立),也就是说Member必须要有对应Admin才有登录权限.
 *     2.用户可以使用用户名, QQ,手机号, Email登录
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
     * QQ
     */
    @Column(length = 16)
    @Length(min = 6, max = 16)
    private String qq;

    /**
     * 手机
     */
    @Column(length = 16)
    @Length(min = 7, max = 16)
    private String phone;

    /**
     * 邮箱
     */
    @Column(length = 32)
    @Length(min = 6, max = 32)
    private String email;
    /**
     * 密码
     */
    @NotBlank
    @Column(length = 64, nullable = false)
    private char[] password;

    /**
     * 是否启用QQ登录
     */
    @Column(name = "is_enabled_qq")
    private boolean isEnabledQQ = false;

    /**
     * 是否启用手机登录
     */
    private boolean isEnabledPhone = false;

    /**
     * 是否启用Email登录
     */
    private boolean isEnabledEmail = false;

    /**
     * 帐号是否锁定
     * 锁定状态不允许登录
     */
    private Boolean isLocked = false;

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

    /**
     * 管理员拥有的角色
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_admin_role")
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();

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
        if(password.length != 64){
            this.password = CipherUtils.getTime64MD5(password);
        }else {
            this.password = password;
        }
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabledQQ() {
        return isEnabledQQ;
    }

    public void setEnabledQQ(boolean isEnabledQQ) {
        this.isEnabledQQ = isEnabledQQ;
    }

    public boolean isEnabledPhone() {
        return isEnabledPhone;
    }

    public void setEnabledPhone(boolean isEnabledPhone) {
        this.isEnabledPhone = isEnabledPhone;
    }

    public boolean isEnabledEmail() {
        return isEnabledEmail;
    }

    public void setEnabledEmail(boolean isEnabledEmail) {
        this.isEnabledEmail = isEnabledEmail;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
