package com.gmail.mosoft521.stream;

import java.util.Arrays;

public class Demo1 {
    public static int addIntData(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        int[] array = {23, 43, 56, 97, 32};
        Arrays.stream(array).reduce((x, y) -> x + y).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).reduce(Integer::sum).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).reduce(Demo1::addIntData).ifPresent(s -> System.out.println(s));

        Arrays.stream(array).parallel().reduce((x, y) -> x + y).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).parallel().reduce(Integer::sum).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).parallel().reduce(Demo1::addIntData).ifPresent(s -> System.out.println(s));
    }
}
/*
251
251
251
251
251
251
 */