package com.aplus.filter;

import com.aplus.entity.AdminEntity;
import com.aplus.entity.LoginLogEntity;
import com.aplus.enums.LoginModeEnum;
import com.aplus.params.Principal;
import com.aplus.services.AdminService;
import com.aplus.services.LoginLogService;
import com.aplus.utils.CipherUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by lifang on 2015/1/24.
 */
public class AuthenticationRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(AuthenticationRealm.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginLogService loginLogService;

    @Value("${system.login.locked.count}")
    private String lockedCount;

    @Value("${system.login.locked.seconds}")
    private String lockedSeconds;


    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        Principal principal = (Principal)principals.getPrimaryPrincipal();
        AdminEntity adminEntity = adminService.findById(principal.getId());
        simpleAuthenticationInfo.setRoles(adminEntity.getStrRoles());
        simpleAuthenticationInfo.setStringPermissions(adminEntity.getStrPermission());
        return simpleAuthenticationInfo;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    @Transactional
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        String host = usernamePasswordToken.getHost();

        logger.info("login username : {}", username);
        logger.info("login password : {}", password);
        logger.info("login host : {}", host);

        if(StringUtils.isBlank(username) || StringUtils.isBlank(String.valueOf(password))){
            throw new UnknownAccountException();
        }
        AdminEntity adminEntity = adminService.findByUsername(username);
        if(adminEntity != null){
            if(adminEntity.getIsLocked()){
                Date nowDate = new Date();
                if(nowDate.getTime() - adminEntity.getLockedDate().getTime() > getMillisecond()){
                    adminEntity.setIsLocked(false);
                }else {
                    throw new LockedAccountException();
                }
            }
            if(CipherUtils.isMD5Equal(new String(adminEntity.getPassword()), password)){
                //登录成功,登录次数加1,失败次数归零,并记录登录日志
                int loginCount = adminEntity.getLoginCount() + 1;
                adminEntity.setLoginCount(loginCount);
                adminEntity.setFailedCount(0);
                adminService.merge(adminEntity);
                loginLogService.persist(new LoginLogEntity(adminEntity, host, new Date(), LoginModeEnum.USERNAME));
                logger.info("登录成功");
                return new SimpleAuthenticationInfo(
                        new Principal(adminEntity.getId(), adminEntity.getUsername()),
                        password,
                        getName());

            }else{
                //登录失败,登录失败次数加1
                logger.info("登录失败");
                int failedCount = adminEntity.getFailedCount() + 1;
                if(failedCount >= getLockCount()){
                    adminEntity.setIsLocked(true);
                    adminEntity.setLockedDate(new Date());
                }
                adminEntity.setFailedCount(failedCount);
                adminService.merge(adminEntity);
                throw new IncorrectCredentialsException();
            }
        }
        throw new UnknownAccountException();
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    private long getMillisecond(){
        if(StringUtils.isBlank(lockedSeconds)){
            return 30 * 1000 * 60;
        }
        Integer second = Integer.valueOf(lockedSeconds);
        return second * 1000 * 60;
    }

    private int getLockCount(){
        if(StringUtils.isBlank(lockedCount)){
            return 5;
        }
        return Integer.valueOf(lockedCount);
    }

}
