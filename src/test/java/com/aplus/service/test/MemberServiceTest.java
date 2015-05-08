package com.aplus.service.test;

import com.aplus.service.BaseTest;
import com.aplus.services.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lifang on 2015/4/18.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class MemberServiceTest extends BaseTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testBatchSave(){
        //73957ms
        //69554ms
//        memberService.batchSave();
    }
}
