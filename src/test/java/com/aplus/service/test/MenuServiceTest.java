package com.aplus.service.test;

import com.aplus.entity.MenuEntity;
import com.aplus.service.BaseTest;
import com.aplus.services.MenuService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lifang on 2015/2/1.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class MenuServiceTest extends BaseTest{

    @Autowired
    private MenuService menuService;

    @Test
    public void testFindByName(){
        String name = "系统管理";
        MenuEntity menuEntity = menuService.findByName(name);
        Assert.assertNotNull(menuEntity);
    }
}
