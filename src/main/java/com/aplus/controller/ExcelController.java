package com.aplus.controller;

import com.aplus.services.ExcelService;
import org.apache.commons.beanutils.locale.LocaleBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by lifang on 2015/2/22.
 */
@Controller
public class ExcelController extends BaseController{

    @Autowired
    private ExcelService excelService;


    @RequestMapping("/admin/excel/import")
    public String doImport(MultipartFile mFile, String value){
        ResourceBundle resource = PropertyResourceBundle.getBundle("common", LocaleBeanUtils.getDefaultLocale());
        String filePath = resource.getString("file.excel.temp.path");
        File file = new File(filePath + mFile.getOriginalFilename());
        if(StringUtils.isBlank(value)){
            value = "0";
        }else{
            try {
                mFile.transferTo(file);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return "";
    }

}
