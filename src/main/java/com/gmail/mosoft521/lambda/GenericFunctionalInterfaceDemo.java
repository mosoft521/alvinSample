package com.gmail.mosoft521.lambda;

public class GenericFunctionalInterfaceDemo {
    public static void main(String[] args) {
        SomeFunc<String> reverse = str -> {
            String r = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                r += str.charAt(i);
            }
            return r;
        };
        System.out.println("lambda reversed is " + reverse.func("lambda"));
        System.out.println("Expression reversed is " + reverse.func("Expression"));

        SomeFunc<Integer> fac = n -> {
            int r = 1;
            for (int i = 1; i <= n; i++) {
                r *= i;
            }
            return r;
        };

        System.out.println("The factoral of 3 is " + fac.func(3));
        System.out.println("The factoral of 5 is " + fac.func(5));
    }
}
/*
lambda reversed is adbmal
Expression reversed is noisserpxE
The factoral of 3 is 6
The factoral of 5 is 120
 */