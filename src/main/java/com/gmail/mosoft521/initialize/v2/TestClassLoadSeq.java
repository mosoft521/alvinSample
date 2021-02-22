package com.gmail.mosoft521.initialize.v2;

public class TestClassLoadSeq {
    public static void main(String[] args) {
        new Son();
    }
}
/*
父类静态成员变量的初始化
父类静态代码块初始化
子类静态代码块初始化
父类代码块初始化
父类无参构造函数初始化完成

子类show()方法：i=0
子类代码块初始化
子类构造函数初始化完成

子类成员变量初始化完成：s=子类私有成员变量
子类show()方法：i=1
 */