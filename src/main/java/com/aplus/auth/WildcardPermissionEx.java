package com.aplus.auth;

import org.apache.shiro.authz.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lifang on 2015/5/18.
 */
public class WildcardPermissionEx implements Permission{

    private Logger logger = LoggerFactory.getLogger(WildcardPermissionEx.class);

    @Override
    public boolean implies(Permission permission) {
        logger.info("shiro authentication");
        return true;
    }
}
