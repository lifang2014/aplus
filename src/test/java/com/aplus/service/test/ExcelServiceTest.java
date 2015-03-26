package com.aplus.service.test;

import com.aplus.service.BaseTest;
import com.aplus.services.ExcelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by lifang on 2015/2/24.
 */
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-db.xml"})
public class ExcelServiceTest extends BaseTest{

    @Autowired
    private ExcelService excelService;


    @Test
    public void testResolve(){

        String filePath = "C:\\Users\\lifang\\Desktop\\1.xlsx";

        excelService.resolve(filePath, 5);

    }

}
