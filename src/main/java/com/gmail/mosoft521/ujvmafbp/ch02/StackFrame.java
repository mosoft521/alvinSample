package com.gmail.mosoft521.ujvmafbp.ch02;

public class StackFrame {
    public static void main(String[] args) {
        add(1, 2);
    }

    private static int add(int a, int b) {
        int c = 0;
        c = a + b;
        return c;
    }
}
