package com.aplus.service.test;

import com.aplus.entity.IdentityEntity;
import com.aplus.entity.MemberEntity;
import com.aplus.service.BaseTest;
import com.aplus.services.IdentityService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lifang on 2015/2/1.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class IdentityServiceTest extends BaseTest{

    @Autowired
    private IdentityService identityService;

    @Test
    public void testPersist(){
        IdentityEntity identityEntity = new IdentityEntity("AA",2000L, 2, MemberEntity.class.getName());
        identityService.persist(identityEntity);
    }

    @Test
    public void getIdentity(){
        String identity = identityService.getIdentity(MemberEntity.class);
        Assert.assertNotNull(identity);
        logger.info(identity);
    }
}
