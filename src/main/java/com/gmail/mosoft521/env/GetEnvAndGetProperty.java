package com.gmail.mosoft521.env;

public class GetEnvAndGetProperty {
    public static void main(String[] args) {
        System.out.println("Java运行时环境版本:" + System.getProperty("java.version"));
        System.out.println("Java 运行时环境供应商:" + System.getProperty("java.vendor"));
        System.out.println("Java 供应商的URL:" + System.getProperty("java.vendor.url"));
        System.out.println("Java安装目录:" + System.getProperty("java.home"));
        System.out.println("Java 虚拟机规范版本:" + System.getProperty("java.vm.specification.version"));
        System.out.println("Java 类格式版本号:" + System.getProperty("java.class.version"));
        System.out.println("Java类路径:" + System.getProperty("java.class.path"));
        System.out.println("操作系统的名称:" + System.getProperty("os.name"));
        System.out.println("操作系统的架构:" + System.getProperty("os.arch"));
        System.out.println("操作系统的版本:" + System.getProperty("os.version"));
        System.out.println("用户的主目录:" + System.getProperty("user.home"));
        System.out.println("用户的当前工作目录:" + System.getProperty("user.dir"));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("自定义变量getProperty CONF_LOCATION:" + System.getProperty("conf.location"));
        System.out.println("--------------------------------------------");
        System.out.println("自定义变量getenv CONF_LOCATION:" + System.getenv("conf.location"));

    }
}
/*
Java运行时环境版本:1.8.0_161
Java 运行时环境供应商:Oracle Corporation
Java 供应商的URL:http://java.oracle.com/
Java安装目录:C:\tools\Java\jdk1.8.0_161\jre
Java 虚拟机规范版本:1.8
Java 类格式版本号:52.0
Java类路径:
操作系统的名称:Windows 10
操作系统的架构:amd64
操作系统的版本:10.0
用户的主目录:C:\Users\Alvin
用户的当前工作目录:E:\ws_ij_alvin\alvinSample
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
自定义变量getProperty CONF_LOCATION:null
--------------------------------------------
自定义变量getenv CONF_LOCATION:null

Process finished with exit code 0

 */