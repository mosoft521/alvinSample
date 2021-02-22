package com.gmail.mosoft521.stream;

import java.util.ArrayList;

public class StreamDemo3 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

//        double productOfSqrRoots = myList.parallelStream().reduce(
//                1.0,
//                (a, b) -> a * Math.sqrt(b)
//        );//ERR: 25.34613228333002

//        double productOfSqrRoots = myList.parallelStream().reduce(
//                1.0,
//                (a, b) -> a * Math.sqrt(b),
//                (a, b) -> a * b
//        );//OK: 1603.246705906486

//        double productOfSqrRoots = myList.stream().reduce(
//                1.0,
//                (a, b) -> a * Math.sqrt(b)
//        );//OK: 1603.2467059064866

        double productOfSqrRoots = myList.stream().reduce(
                1.0,
                (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b
        );//OK: 1603.246705906486
        System.out.println(productOfSqrRoots);
    }
}
