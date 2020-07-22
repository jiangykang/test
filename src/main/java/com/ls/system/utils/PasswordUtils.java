package com.ls.system.utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import static com.ls.system.constant.Constants.INITIAL_PASSWORD;

/**
 * 功能描述: <br> 密码相关
 *
 * @Author: Min.Hu
 * @Date: 2020/4/27 8:52
 */
public class PasswordUtils {
    /**
     * PWD_SALT_LENGTH: 密码加密盐值长度
     */
    public static final int PWD_SALT_LENGTH = 18;
    /**
     * PWD_ALGORITHM_NAME: 密码加密算法
     */
    public static final String PWD_ALGORITHM_NAME = "md5";

    /**
     * PWD_ALGORITHM_NAME: 密码加密次数
     */
    public static final int PWD_HASH_ITERATIONS = 2;

    /**
     * 生成密码<br/>
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String generatePwdEncrypt(String pwd, String salt) {
        SimpleHash hash =
                new SimpleHash(PWD_ALGORITHM_NAME, pwd, salt, PWD_HASH_ITERATIONS);
        return hash.toString();
    }

    /**
     * 生成盐值<br/>
     *
     * @return
     */
    public static String generateSalt() {
        return RandomStringUtils.randomAlphabetic(PWD_SALT_LENGTH);
    }


    public static void main(String[] args) {
        String generateSalt = generateSalt();
        String generatePwdEncrypt = generatePwdEncrypt(INITIAL_PASSWORD, generateSalt);
        System.out.println(generateSalt);
        System.out.println(generatePwdEncrypt);

    }

}