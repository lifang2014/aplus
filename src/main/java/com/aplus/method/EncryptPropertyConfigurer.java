package com.aplus.method;

import com.aplus.utils.DESUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 继承PropertyPlaceholderConfigurer,配置支持密文属性的属性文件
 * Created by li.fang on 2014/11/13.
 */
public class EncryptPropertyConfigurer extends PropertyPlaceholderConfigurer{

    private Logger logger = LoggerFactory.getLogger(EncryptPropertyConfigurer.class);

    private String[] encryptPropNames = {"jdbc.username","jdbc.password"};

    /**
     * 对特定属性的属性值进行转换
     * @param propertyName 属性名称
     * @param propertyValue 属性值
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        logger.info("The properties name is : {}, value is : ", propertyName, propertyValue);
        if(isEncryptProp(propertyName)){
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            logger.info(decryptValue);
            return decryptValue;
        }else{
            return propertyValue;
        }
    }

    /**
     * 判断属性是否需要解密
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        for(String encryptPropName : encryptPropNames){
            if(StringUtils.equals(encryptPropName,propertyName)){
                return true;
            }
        }
        return false;
    }


}
