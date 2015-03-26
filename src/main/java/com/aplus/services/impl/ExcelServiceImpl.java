package com.aplus.services.impl;

import com.aplus.exception.ServiceException;
import com.aplus.services.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by lifang on 2015/2/22.
 */
@Service
public class ExcelServiceImpl implements ExcelService{

    private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    public void resolve(String filePath, int numLine) {

        if(!(filePath.endsWith(".xlsx") || filePath.endsWith(".xls"))){
            throw new ServiceException("不合法的文件类型,请上传Excel类型文件!");
        }

        // 1. 打开excel
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            logger.error("open/create Excel failure, filePath={}", filePath);
            throw new ServiceException("打开excel文件失败");
        }

        //获取第一个Sheet
        Sheet sheet = wb.getSheetAt(0);

        //sheet总行数
        int lastRowNum = sheet.getLastRowNum();

        Map<Integer, Double> map = new HashMap<Integer, Double>();
        for(int i = 0; i < lastRowNum; i++){
            Row row = sheet.getRow(i);
            //默认取第一列
            Cell cell = row.getCell(0);

            Double value = 0D;
            try{
                value = Double.valueOf(cell + "");
            }catch (Exception e){
                logger.warn(e.getMessage());
            }

            map.put(i, value);
        }

        List<Integer> lstRandoms = new ArrayList<Integer>();

        Random random = new Random();
        for(int i = 0; i < numLine; i++){
            int value = random.nextInt(map.size());
            logger.info("value : " + value);
            while(lstRandoms.contains(value)){
                value = Math.round(map.size());
            }
            lstRandoms.add(value);
        }

        double result = 0;
        for(Integer value : lstRandoms){
            result += map.get(value);
        }

        logger.info("size : " + map.size());
        logger.info("result : " + result/numLine);
    }

}
