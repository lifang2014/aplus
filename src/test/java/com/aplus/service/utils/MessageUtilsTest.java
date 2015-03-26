package com.aplus.service.utils;

import com.aplus.service.BaseTest;
import com.aplus.utils.MessageUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by lifang on 2015/1/31.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class MessageUtilsTest extends BaseTest{

    @Test
    public void testMessageUtils(){
        String msg = MessageUtils.getMessage("main.menu.list");
        logger.info("msg : {}", msg);
    }

}
