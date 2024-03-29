package com.gexiao.miaosha.util;

import org.springframework.util.DigestUtils;

/**
 * @author : gexiao
 * @since : 2019/11/18 17:40
 */
public class MD5Utils {

    private static final String DEFAULT_SLAT = "dsfs42";

    public static String getMD5(String plainText) {
        plainText = plainText + "s" + DEFAULT_SLAT;
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

    /**
     * 校验用户输入的密码与数据库密码做比对
     * @param input
     * @param dbPassword
     * @return
     */
    public static Boolean checkPassword(String input, String dbPassword) {
        String md5 = getMD5(input);
        if (md5.equals(dbPassword)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String password = "";
        String md5 = getMD5(password);
        System.out.println("md5 = " + md5);
    }
}
