package com.aplus.service.shiro;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.aplus.service.BaseTest;
import com.aplus.utils.CipherUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

/**
 * Created by lifang on 2015/3/26.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class ShiroDemo01 extends BaseTest{

    @Test
    public void testShiroDemo01(){

        //1. 获取SecurityManager工厂
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-demo01.ini");

        //2. 获取SecurityManager实例,并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3. 获取Subject以及创建用户名,密码身份校验Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "12345");


        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            //身份校验失败
            logger.warn(e.getMessage(), e);
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        if(subject.isAuthenticated()) {
            //退出
            subject.logout();
        }

    }


    @Test
    public void testShiroDemo02(){

        //1. 获取SecurityManager工厂
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-demo02.ini");

        //2. 获取SecurityManager实例,并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3. 获取Subject以及创建用户名,密码身份校验Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "12345");

        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            //身份校验失败
            logger.warn(e.getMessage(), e);
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        if(subject.isAuthenticated()) {
            //退出
            subject.logout();
        }

    }

    @Test
    public void testShiroDemo03(){
        login("classpath:shiro/shiro-demo03.ini", "zhang", "1234");

        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()) {
            Assert.assertTrue(subject.hasRole("role1"));
            Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));

            logger.info("name : {}", subject.getPrincipal());
            logger.info("isRememberMe : {}", subject.isRemembered());
            logger.info("isRunAs : {}", subject.isRunAs());

            subject.runAs(new SimplePrincipalCollection("wang", ""));

            logger.info("isRunAs : {}", subject.isRunAs());

            PrincipalCollection principalCollection = subject.getPreviousPrincipals();
            if(principalCollection != null) {
                for (Object name : principalCollection.asList()) {
                    logger.info("name : {}", name);
                }
            }

            Session session = subject.getSession();

            logger.info("session id : {}", session.getId());
            logger.info("session host : {}", session.getHost());

            if(subject.isRunAs()){
                logger.info("releaseRunAs : " + subject.releaseRunAs());
            }

            logger.info("has role1 : {}", subject.hasRole("role3"));
        }
    }


    @Test
    public void testShiroDemo4(){
        String str = "111111";
        String salt = "hello world";
        String base64 = org.apache.shiro.codec.Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64);

        String md5 = new Md5Hash(str, salt, 5).toString();

        logger.info("{} : {} : {}", str, base64, str2);
        logger.info("{} : {} : {}", md5, CipherUtils.getTime64MD5("123456"), md5);
    }

    @Test
    public void testAuthPassword(){
        char[] p = {'1','2','3','4','5','6'};
        logger.info("password1 : {}", CipherUtils.getTime64MD5(p.toString()));
        logger.info("password2 : {}", CipherUtils.getTime64MD5(new String(p)));
    }


    private void login(String iniFile, String username, String password){

        //获取SecurityFactory工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);

        //获取SecurityManager实例
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);

        if(subject.isAuthenticated()){
            logger.info("login success");
        }else{
            logger.warn("login failed");
        }
    }

}
