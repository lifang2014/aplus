package com.common.aplus.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <pre>
 *     1.每一个Admin都必须对应一个Employee(反之不成立),也就是说Employee必须要有对应Admin才有登录权限.
 * </pre>
 * Created by lifang on 2015/1/19.
 */
@Entity
@Table(name = "t_admin")
public class Admin extends BaseEntity{

    @NotBlank
    private String username;

    @NotBlank
    @Column(length = 64, nullable = false)
    private Char[] password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Char[] getPassword() {
        return password;
    }

    public void setPassword(Char[] password) {
        this.password = password;
    }
}
