package com.gmail.mosoft521.base64;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    //base64字符串转byte[]
    public static byte[] base64StringToByte(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    //byte[]转base64
    public static String byteToBase64String(byte[] b) {
        return Base64.encodeBase64String(b);
    }
}