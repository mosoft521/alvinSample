package com.gmail.mosoft521.lambda;

public class LambdasAsArgumentsDemo {
    static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambdas add power to Java";
        String outStr;
        System.out.println("Here is input String: " + inStr);

        outStr = stringOp(str -> str.toUpperCase(), inStr);
        System.out.println("The string in uppercase: " + outStr);

        outStr = stringOp(str -> {
            String r = "";
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ')
                    r += str.charAt(i);
            }
            return r;
        }, inStr);
        System.out.println("The string with spaces removed: " + outStr);

        StringFunc reverse = str -> {
            String r = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                r += str.charAt(i);
            }
            return r;
        };
        System.out.println("The string reversed: " + stringOp(reverse, inStr));
    }
}
/*
Here is input String: lambdas add power to Java
The string in uppercase: LAMBDAS ADD POWER TO JAVA
The string with spaces removed: lambdasaddpowertoJava
The string reversed: avaJ ot rewop dda sadbmal
 */