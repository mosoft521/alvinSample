package com.gmail.mosoft521.stream;

import java.util.Arrays;

public class Demo2 {
    public static int addIntData(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        int[] array = {23, 43, 56, 97, 32};
        //Set start value. Result will be start value + sum of array.
        int startValue = 100;
        int sum = Arrays.stream(array).reduce(startValue, (x, y) -> x + y);
        System.out.println(sum);
        sum = Arrays.stream(array).reduce(startValue, Integer::sum);
        System.out.println(sum);
        sum = Arrays.stream(array).reduce(startValue, Demo2::addIntData);
        System.out.println(sum);

        sum = Arrays.stream(array).parallel().reduce(startValue, (x, y) -> x + y);
        System.out.println(sum);
        sum = Arrays.stream(array).parallel().reduce(startValue, Integer::sum);
        System.out.println(sum);
        sum = Arrays.stream(array).parallel().reduce(startValue, Demo2::addIntData);
        System.out.println(sum);
    }
}
/*
351
351
351
751
751
751
 */