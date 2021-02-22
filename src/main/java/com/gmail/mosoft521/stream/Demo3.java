package com.gmail.mosoft521.stream;

import java.util.Arrays;
import java.util.List;

public class Demo3 {
    public static void main(String[] args) {
        List<Integer> list2 = Arrays.asList(5, 6, 7);
        int res = list2.parallelStream().reduce(1, (s1, s2) -> s1 * s2, (p, q) -> p * q);
        System.out.println(res);
    }
}
/*
210
 */