package com.aplus.service;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by lifang on 2015/1/22.
 */
public class BaseTest extends AbstractJUnit4SpringContextTests{

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    private Long startTime = null;

    @After
    public void after(){
        Long endTime = System.currentTimeMillis();
        logger.info("==============================");
        logger.info(":::::::::::run time = {}", (endTime - startTime));
        logger.info("==============================");
    }

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
    }

    @Test
    public void testRun(){

    }
}
