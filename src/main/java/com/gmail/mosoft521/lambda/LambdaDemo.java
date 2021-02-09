package com.gmail.mosoft521.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        MyNumber myNumber;
        myNumber = () -> 123.45;
        System.out.println("A fixed value: " + myNumber.getValue());

        myNumber = () -> Math.random() * 100;
        System.out.println("A random value: " + myNumber.getValue());
        System.out.println("Another random value: " + myNumber.getValue());
    }
}
/*
A fixed value: 123.45
A random value: 94.48238968906705
Another random value: 32.1028132738517
 */