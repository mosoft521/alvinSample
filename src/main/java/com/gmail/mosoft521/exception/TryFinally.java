package com.gmail.mosoft521.exception;

public class TryFinally {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        System.out.println("结果： " + new TryFinally().test());
    }

    static int test() {
        int i = 1;
        try {
            System.out.println("try里面的i : " + i);
            return i;
        } finally {
            System.out.println("进入finally...");
            ++i;
            System.out.println("fianlly里面的i : " + i);
        }
    }
}
