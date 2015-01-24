package com.aplus.params;


import java.io.Serializable;

/**
 * 保存用户登录信息
 * Created by Jerry on 2014/11/28.
 */
public class Principal implements Serializable{

    private Long id;//用户ID

    private String username;//用户名

    public Principal(Long id, String username){
        this.setId(id);
        this.setUsername(username);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }


}
