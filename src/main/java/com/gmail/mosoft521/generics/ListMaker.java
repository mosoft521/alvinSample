// generics/ListMaker.java
package com.gmail.mosoft521.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMaker<T> {
    List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<>();
        List<String> stringList = stringMaker.create();
        stringList.add("hello");
        stringList.add("world");
        System.out.println(stringList);
    }
}
/*
[hello, world]
 */