package com.gmail.mosoft521.stream;

import java.util.Arrays;
//不可变类
public class Invariant {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(arr).map((x) -> x = x + 1).forEach(System.out::println);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::println);
    }
}
