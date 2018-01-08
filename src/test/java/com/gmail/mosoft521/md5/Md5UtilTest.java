package com.gmail.mosoft521.md5;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5UtilTest {
    /**
     * sign=MD5[MD5(accessToken)+MD5(paySN)+account]
     *
     * @param accessToken
     * @param paySn
     * @param account
     * @return
     */
    public static String sign(String accessToken, String paySn, String account) {
        String s1 = DigestUtils.md5Hex(accessToken);
        String s2 = DigestUtils.md5Hex(paySn);
        String sign = DigestUtils.md5Hex(s1 + s2 + account);
        return sign;
    }

    /**
     * 校验签名
     *
     * @param accessToken
     * @param paySn
     * @param account
     * @return
     */
    public static boolean check(String accessToken, String paySn, String account, String sign) {
        String s = sign(accessToken, paySn, account);
        return s.equalsIgnoreCase(sign);
    }

    public static void main(String[] args) {
        System.out.println(sign("d04b4f53d3494d2c9350b37ca4b7560d","353486f75dce4a32b623c2f46a80bdb7","zjw"));
    }
}
