package com.gmail.mosoft521.interview;

class A {
    public static int j = 0;
    public int i;
    A() {
        i = (j++ != 0) ? ++j : --j;
    }
}

public class M {
    static A a2 = new A();

    public static void main(String[] args) {
        A a1 = new A();

        System.out.println(a1.i);
        System.out.println(a2.i);
    }

}
