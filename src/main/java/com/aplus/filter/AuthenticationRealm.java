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
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by lifang on 2015/1/24.
 */
public class AuthenticationRealm extends AuthenticatingRealm {

    private Logger logger = LoggerFactory.getLogger(AuthenticationRealm.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginLogService loginLogService;

    @Value("system.login.locked.count")
    private String lockedCount;

    @Value("system.login.locked.seconds")
    private String lockedSeconds;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        String host = usernamePasswordToken.getHost();
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
            if(CipherUtils.isMD5Equal(adminEntity.getPassword(), password)){
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
                if(failedCount >= Integer.valueOf(lockedCount)){
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


    private long getMillisecond(){
        if(StringUtils.isBlank(lockedSeconds)){
            return 30 * 1000 * 60;
        }
        Integer second = Integer.valueOf(lockedSeconds);
        return second * 1000 * 60;
    }

}
