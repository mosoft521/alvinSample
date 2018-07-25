package com.gmail.mosoft521.lambda.stream;

import org.junit.Test;

import java.util.List;

/**
 * Created by wangwenjun on 2015/8/8.
 */
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
}