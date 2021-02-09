package com.gmail.mosoft521.lambda;

public class BlockLambdaDemo {
    public static void main(String[] args) {
        NumericFunc fac = n -> {
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
The factoral of 3 is 6
The factoral of 5 is 120
 */