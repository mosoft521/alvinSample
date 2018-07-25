package com.gmail.mosoft521.env;

/**
 * 获取系统的环境变量
 * 对于windows在系统属性-->高级-->环境变量中设置的变量将显示在此
 * 对于linux,通过export设置的变量将显示在此
 * getenv是获取系统的环境变更，对于windows对在系统属性-->高级-->环境变量中设置的变量将显示在此(对于linux,通过export设置的变量将显示在此)
 * getProperties是获取系统的相关属性,包括文件编码,操作系统名称,区域,用户名等,此属性一般由jvm自动获取,不能设置.
 */

public class ShowEnv {

    public static void main(String[] args) {
        System.out.println(System.getenv());  //直接打印显示

//        // 遍历显示
//        Map<String, String> envmap = System.getenv();
//        for(Map.Entry<String, String> entry:envmap.entrySet()){
//            System.out.println(entry.getKey()+"--->"+entry.getValue());
//        }

        System.out.println(System.getenv("java.home"));
        System.out.println(System.getenv("JAVA_HOME"));
        System.out.println("###################");
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("JAVA_HOME"));
    }
}
/*
null
C:\tools\Java\jdk1.8.0_161
###################
C:\tools\Java\jdk1.8.0_161\jre
null
 */