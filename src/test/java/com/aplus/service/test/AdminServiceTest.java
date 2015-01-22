package com.aplus.service.test;

import com.aplus.service.BaseTest;
import com.aplus.entity.AdminEntity;
import com.aplus.services.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by lifang on 2015/1/22.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class AdminServiceTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    private AdminService adminService;

    @Test
    public void testPersist(){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setPassword("admin".toCharArray());
        adminEntity.setUsername("admin");
//        adminService.persist(adminEntity);
    }

}
