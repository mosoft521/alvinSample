package com.gmail.mosoft521.initialize.v2;

public class Father {
    private static String s = print();

    static {
        System.out.println("父类静态代码块初始化");
    }

    {
        System.out.println("父类代码块初始化");
    }

    public static String print() {
        System.out.println("父类静态成员变量的初始化");
        return "父类静态方法";
    }

    public Father() {
        System.out.println("父类无参构造函数初始化完成");
        show();
    }

    public void show() {
        System.out.println("父类show()方法");
    }
}
