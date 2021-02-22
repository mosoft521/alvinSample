package com.gmail.mosoft521.onjava8;

import static com.gmail.mosoft521.onjava8.RandInts.rands;

public class ForEach {
    static final int SZ = 14;

    public static void main(String[] args) {
        rands().limit(SZ).forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ).parallel().forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ).parallel().forEachOrdered(n -> System.out.format("%d ", n));
    }
}
