package com.gmail.mosoft521.re;

public class Re3Test {
    public static void main(String[] args) {
        String url_base = "http://yonbip-biz-t.yonyouauto.com";//yonbip-biz-t.yonyouauto.com
        //截取http://
        String[] urls = url_base.split("//");
        String URL_BASE = urls[1];
        System.out.println(URL_BASE);


        String url_base2 = "http://yonbip-biz-dev.yonyouauto.com/";//yonbip-biz-dev.yonyouauto.com/
        //截取http://
        String[] urls2 = url_base2.split("//");
        String URL_BASE2 = urls2[1].substring(0, urls2[1].length() - 1);
        System.out.println(URL_BASE2);
    }
}
