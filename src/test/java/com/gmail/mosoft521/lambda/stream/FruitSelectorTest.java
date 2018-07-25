package com.gmail.mosoft521.lambda.stream;

import org.junit.Test;

import java.util.List;

public class FruitSelectorTest {
    @Test
    public void testIterator() {
        FruitSelector selector = new IteratorFruitSelector();
        List<Double> result = selector.select();
        System.out.println(result);
    }

    @Test
    public void testStream() {
        FruitSelector selector = new StreamFruitSelector();
        List<Double> result = selector.select();
        System.out.println(result);
    }

    @Test
    public void testTradition() {
        long startTime = System.currentTimeMillis();
        int start = 0;
        int end = 15000;
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += i;
        }
        System.out.println("Tradition Result:" + sum + ",speed time:" + (System.currentTimeMillis() - startTime));
    }
}