package com.wondernect.elements.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESAESUtils
 * Author: chenxun
 * Date: 2020-08-23 12:47
 * Description:
 */
public final class ESAES_ECB_ZERO_Utils {

    private static final Logger logger = LoggerFactory.getLogger(ESAES_ECB_ZERO_Utils.class);

    private static final String CHARSET_NAME = "UTF-8";

    private static final String AES_NAME = "AES";

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * BASE64加密
     * @param clearText 明文，待加密的内容
     * @param password 密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptBase64(String clearText, String password) {
        try {
            byte[] raw = password.getBytes(CHARSET_NAME);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM);//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(clearText.getBytes(CHARSET_NAME));
            //此处使用BASE64做转码功能
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception ex) {
            logger.error("加密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    /**
     * BASE64解密
     * @param cipherText 密文，带解密的内容
     * @param password 密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptBase64(String cipherText, String password) {
        try {
            byte[] raw = password.getBytes(CHARSET_NAME);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            byte[] encrypted = new BASE64Decoder().decodeBuffer(cipherText);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original,CHARSET_NAME);
        } catch (Exception ex) {
            logger.error("解密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    /**
     * HEX加密
     * @param clearText 明文，待加密的内容
     * @param password 密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptHex(String clearText, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(CHARSET_NAME), AES_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM); // "算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(clearText.getBytes(CHARSET_NAME));
            return Hex.encodeHexString(encrypted);
        } catch (Exception ex) {
            logger.error("加密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    /**
     * HEX解密
     * @param cipherText 密文，带解密的内容
     * @param password 密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptHex(String cipherText, String password) {
        try {
            byte[] encrypted = Hex.decodeHex(cipherText.toCharArray());
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(CHARSET_NAME), AES_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original, CHARSET_NAME);
        } catch (Exception ex) {
            logger.error("解密异常", ex);
            return ESStringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        // String contents = "学号|收费年度|时间戳";
        // String encrypt = encryptBase64(contents, "mClPAiClEW1OKvZP");
        // System.out.println("加密后:" + encrypt);
        // String decrypt = decryptBase64(encrypt, "mClPAiClEW1OKvZP");
        // System.out.println("解密后:" + decrypt);

        String contents = "zhang";
        String encrypt = encryptHex(contents, "zyl0zyl0zyl0zyl0");
        System.out.println("加密后:" + encrypt);
        String decrypt = decryptHex(encrypt, "zyl0zyl0zyl0zyl0");
        System.out.println("解密后:" + decrypt);

        // String decrypt = decryptHex("E01CE48AC889C730F05928327BFE4DA1", "zyl0zyl0zyl0zyl0");
        // System.out.println("解密后:" + decrypt);
    }
}
