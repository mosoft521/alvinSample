package com.gmail.mosoft521.stream;

import java.util.Arrays;
import java.util.List;

public class ReduceToString {
    public static void main(String[] args) {
        //Reduce Array to String.
        String[] array = {"Mohan", "Sohan", "Mahesh"};
        Arrays.stream(array).reduce((x, y) -> x + "," + y)
                .ifPresent(s -> System.out.println("Array to String: " + s));
        //Reduce List to String.
        List<String> list = Arrays.asList("Mohan", "Sohan", "Mahesh");
        list.stream().reduce((x, y) -> x + "," + y)
                .ifPresent(s -> System.out.println("List to String: " + s));
    }
}
/*
Array to String: Mohan,Sohan,Mahesh
List to String: Mohan,Sohan,Mahesh
 */