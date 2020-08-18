package com.wondernect.elements.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by cxhome on 2017/8/26.
 * wondernect.com
 * @author sunbeam
 */
public final class ESSecurityUtils {

    private static final String CHARSET_NAME = "UTF-8";

    private static final String AES_NAME = "AES";

    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密
     * @param content 明文
     * @param KEY 密钥
     * @param IV 偏移量
     * @return 加密后内容
     */
    public static String encrypt(String content, String KEY, String IV) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(CHARSET_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     * @param content 加密内容
     * @param KEY 密钥
     * @param IV 偏移量
     * @return 解密后内容
     */
    public static String decrypt(String content, String KEY, String IV) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ESStringUtils.EMPTY;
    }

    /**
     * 一次MD5加密 md5(value)
     */
    public static String md5Crypt(String origin) {
        return DigestUtils.md5Hex(origin);
    }

    /**
     * 二次MD5加密 md5(md5(value))
     */
    public static String doubleMd5Crypt(String origin) {
        return md5Crypt(md5Crypt(origin));
    }

    public static void main(String[] args) {
        String contents = "学号|收费年度|时间戳";
        String encrypt = encrypt(contents, "mClPAiClEW1OKvZP", "ndolJQodt26Xpd6T");
        System.out.println("加密后:" + encrypt);
        String decrypt = decrypt(encrypt, "mClPAiClEW1OKvZP", "ndolJQodt26Xpd6T");
        System.out.println("解密后:" + decrypt);
    }
}
