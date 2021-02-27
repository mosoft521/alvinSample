// generics/CompilerIntelligence.java
package com.gmail.mosoft521.generics;

import java.util.*;

public class CompilerIntelligence {

    public static void main(String[] args) {
        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple) flist.get(0); // No warning
        System.out.println(a);
        flist.contains(new Apple()); // Argument is 'Object'
        System.out.println(flist.contains(new Apple()));
        flist.indexOf(new Apple()); // Argument is 'Object'
        System.out.println(flist.indexOf(new Apple()));
    }

}
