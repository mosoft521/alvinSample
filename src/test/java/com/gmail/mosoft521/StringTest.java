package com.gmail.mosoft521;

import java.nio.charset.Charset;

public class StringTest {
    public static void main(String[] args) {
        String name = "陈宁";
        byte[] content = name.getBytes();
        String xxx = new String(content, Charset.forName("GBK"));
        System.out.println(xxx);
    }
}
