package com.gmail.mosoft521.lambda;

public class BlockLambdaDemo2 {
    public static void main(String[] args) {
        StringFunc reverse = str -> {
            String r = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                r += str.charAt(i);
            }
            return r;
        };

        System.out.println("lambda reversed is " + reverse.func("lambda"));
        System.out.println("Expression reversed is " + reverse.func("Expression"));
    }
}
/*
lambda reversed is adbmal
Expression reversed is noisserpxE
 */