package com.aplus.service.test;

import com.aplus.entity.AdminEntity;
import com.aplus.service.BaseTest;
import com.aplus.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.regex.Pattern;


/**
 * Created by lifang on 2015/1/22.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class AdminServiceTest extends BaseTest{

    private Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Autowired
    private AdminService adminService;


    @Test
    public void testPersist(){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setPassword("123456".toCharArray());
        adminEntity.setUsername("admin");
        adminService.persist(adminEntity);
    }

    @Test
    public void testDeleteById(){
        adminService.remove(2L);
    }

    @Test
    public void testDelete(){
        AdminEntity adminEntity = adminService.findById(4L);
        Assert.assertNotNull(adminEntity);
        logger.info("username:{}", adminEntity.getUsername());
        logger.info("password:{}", String.valueOf(adminEntity.getPassword()));
        adminService.remove(adminEntity.getId());
    }

    @Test
    public void testDelete2(){
        AdminEntity adminEntity = adminService.findByUsername("123456");
        Assert.assertNotNull(adminEntity);
        logger.info("adminEntity:{}", adminEntity);
        adminService.remove(adminEntity.getId());
    }
}
