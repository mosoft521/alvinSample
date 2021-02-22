package com.gmail.mosoft521.value_ref;

public class Test1 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println("Before change, n = " + n);
        changeData(n);
        System.out.println("After changeData(n), n = " + n);
    }

    public static void changeData(int nn) {
        nn = 10;
    }
}
/*
Before change, n = 3
After changeData(n), n = 3
 */