package com.gmail.mosoft521.interview.date20210318;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamDistinct {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 13, 24, 11, 11, 14, 1, 2};
        int[] arrs = IntStream.of(arr).distinct().toArray();
        System.out.println(Arrays.toString(arrs));

        int[] arrays = IntStream.of(1, 13, 24, 11, 11, 14, 1, 2).distinct().peek(System.out::println).toArray();
        System.out.println(Arrays.toString(arrays));
        System.out.println(IntStream.of(1, 13, 24, 11, 11, 14, 1, 2).distinct().mapToObj(i -> i + "").collect(Collectors.<Integer>joining(",")));
    }
}
