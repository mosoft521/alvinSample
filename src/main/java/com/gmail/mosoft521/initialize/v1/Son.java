package com.gmail.mosoft521.initialize.v1;

public class Son extends Father {
    static {
        System.out.println("子类静态代码块初始化");
    }

    {
        System.out.println("子类代码块初始化");
    }

    private static int i = initi();
    public static int initi() {
        System.out.println("子类静态成员变量的初始化");
        return 1;
    }
    private String s = "子类私有成员变量";

    public void show() {
        System.out.println("子类show()方法：i=" + i);
    }

    public Son() {
        System.out.println("子类构造函数初始化完成");
        System.out.println("子类成员变量初始化完成：s=" + s);
        show();
    }
}
