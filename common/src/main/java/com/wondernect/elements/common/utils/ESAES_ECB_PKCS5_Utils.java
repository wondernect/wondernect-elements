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
public final class ESAES_ECB_PKCS5_Utils {

    private static final Logger logger = LoggerFactory.getLogger(ESAES_ECB_PKCS5_Utils.class);

    private static final String CHARSET_NAME = "UTF-8";

    private static final String AES_NAME = "AES";

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content      原文
     * @param key 加密密码
     * @return String
     * @throws Exception 异常
     */
    public static String encrypt(String content, String key) throws Exception {
        byte[] raw = key.getBytes(CHARSET_NAME);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_NAME);
        Cipher cipher = Cipher.getInstance(ALGORITHM);//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes(CHARSET_NAME));
        //此处使用BASE64做转码功能
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * 解密
     *
     * @param content   密文
     * @param key 加密密码
     * @return String
     * @throws Exception 异常
     */
    public static String decrypt(String content, String key) throws Exception {
        byte[] raw = key.getBytes(CHARSET_NAME);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_NAME);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        //先用base64解密
        byte[] encrypted = new BASE64Decoder().decodeBuffer(content);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original,CHARSET_NAME);
    }

    public static void main(String[] args) {
        String contents = "学号|收费年度|时间戳";
        String encrypt = encrypt(contents, "mClPAiClEW1OKvZP");
        System.out.println("加密后:" + encrypt);
        String decrypt = decrypt(encrypt, "mClPAiClEW1OKvZP");
        System.out.println("解密后:" + decrypt);
    }
}
