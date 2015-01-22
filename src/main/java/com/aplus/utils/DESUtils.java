package com.aplus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Li.fang on 2014/11/13.
 */
public class DESUtils {

    private static Logger logger = LoggerFactory.getLogger(DESUtils.class);

    //指定DES加密解密所用密钥
    private static Key key;

    private static String STR_KEY = "APLUS OFFICE SYSTEM";

    private static final String GENERATOR_DES = "DES";

    private static final String ENCODING_UTF8 = "UTF-8";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(GENERATOR_DES);
            generator.init(new SecureRandom(STR_KEY.getBytes()));
            key = generator.generateKey();
            generator = null;
        }catch (NoSuchAlgorithmException e){
            logger.warn("Cryptographic failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对字符串进行DES加密,返回BASE64编码的加密字符串
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getEncryptBase64String(String str){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try{
            byte[] strBytes = str.getBytes(ENCODING_UTF8);
            Cipher cipher= Cipher.getInstance(GENERATOR_DES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        }catch (Exception e){
            logger.warn("Cryptographic failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对BASE64编码的加密字符串进行解密,返回解密后的字符串
     * @param str BASE64加密字符串
     * @return 解密后的字符串
     */
    public static String getDecryptString(String str){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try{
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher= Cipher.getInstance(GENERATOR_DES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes, ENCODING_UTF8);
        }catch (Exception e){
            logger.warn("Decryption failure");
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试加密
     * @param args
     */
    public static void main(String[] args){
        String[] decryptStr = {"root","admin","jdbc:mysql://127.0.0.1/db_office?useUnicode=true&characterEncoding=utf-8"};
        String[] encryptStr = new String[3];
        for(int i = 0; i < decryptStr.length; i++){
            encryptStr[i] = getEncryptBase64String(decryptStr[i]);
            logger.info("{} : {}",decryptStr[i],getEncryptBase64String(decryptStr[i]));
        }
        for(int i = 0; i < encryptStr.length; i++){
            logger.info("{} : {}",encryptStr[i],getDecryptString(encryptStr[i]));
        }
        logger.info("time millis : {}",System.currentTimeMillis());
    }

}
