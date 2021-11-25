package com.gmail.mosoft521.test;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        String strDate = localDate.toString();
        System.out.println(strDate);

        String url = "http://yonbip-biz-t.yonyouauto.com";
        String[] urls = url.split("//");
        System.out.println(urls[0]);
        System.out.println(urls[1]);
    }
}
