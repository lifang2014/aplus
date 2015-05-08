package com.aplus.service.shiro.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by lifang on 2015/3/26.
 */
public class MyRealm01 implements Realm{

    /**
     * 返回Realm唯一名称
     * @return
     */
    @Override
    public String getName() {
        return "MyRealm01";
    }

    /**
     * 判断此Realm是否支持Token
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    /**
     * 根据Token获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        String credentials = new String((char[])authenticationToken.getCredentials());
        if(!StringUtils.equals("zhang", username)){
            throw new UnknownAccountException();
        }
        if(!StringUtils.equals("12345", credentials)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, credentials, getName());
    }
}
