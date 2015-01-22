package com.aplus.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <pre>
 *     1.每一个Admin都必须对应一个Employee(反之不成立),也就是说Employee必须要有对应Admin才有登录权限.
 * </pre>
 * Created by lifang on 2015/1/19.
 */
@Entity
@Table(name = "t_admin")
public class AdminEntity extends BaseEntity{

    @NotBlank
    private String username;

    @NotBlank
    @Column(length = 64, nullable = false)
    private char[] password;

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
