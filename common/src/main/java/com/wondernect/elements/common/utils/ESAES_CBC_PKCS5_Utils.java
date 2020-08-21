package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ESAES128Utils
 * Author: chenxun
 * Date: 2020/8/21 18:22
 * Description:
 */
public final class ESAES_CBC_PKCS5_Utils {

    private static final Logger logger = LoggerFactory.getLogger(ESAES_CBC_PKCS5_Utils.class);

    private static final String CHARSET_NAME = "UTF-8";

    private static final String AES_NAME = "AES";

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String IV = "ndolJQodt26Xpd6T";

    /*
     * 加密
     * content 需要加密的内容
     * key 加密秘钥
     * return 返回加密后的内容
     */
    public static String encrypt(String content, String key) {
        if (key == null) {
            logger.error("Key为空null");
            return ESStringUtils.EMPTY;
        }
        if (key.length() != 16) {
            logger.error("Key长度不是16位");
            return ESStringUtils.EMPTY;
        }
        try {
            //设置密钥规范为AES
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //CBC模式需要配置偏移量，设置一个向量，达到密码唯一性，增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            //此处使用BASE64做转码功能
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception ex) {
            logger.error("加密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    /*
     * 解密方法
     * content 需要解密的密文
     * key 解密的秘钥
     * return 解密后的内容
     */
    public static String decrypt(String content, String key) {
        if (key == null) {
            logger.error("Key为空null");
            return ESStringUtils.EMPTY;
        }
        if (key.length() != 16) {
            logger.error("Key长度不是16位");
            return ESStringUtils.EMPTY;
        }
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //CBC模式需要配置偏移量，设置这个后，不会出来同一个明文加密为同一个密文的问题，达到密文唯一性
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            //先用base64解密
            byte[] encrypted = new BASE64Decoder().decodeBuffer(content);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original);
        } catch (Exception ex) {
            logger.error("解密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        String contents = "学号|收费年度|时间戳";
        String encrypt = encrypt(contents, "mClPAiClEW1OKvZP");
        System.out.println("加密后:" + encrypt);
        String decrypt = decrypt(encrypt, "mClPAiClEW1OKvZP");
        System.out.println("解密后:" + decrypt);
    }
}
