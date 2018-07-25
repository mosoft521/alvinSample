package com.gmail.mosoft521.lambda.stream;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangwenjun on 2015/8/8.
 */
public class StreamFruitSelector extends FruitSelector {
    @Override
    protected List<Double> doFilter(List<Fruit> fruits) {
        return fruits.stream().filter(f -> f.getName().equals(CANDIDATE_FRUIT))
                .sorted(Comparator.comparing(Fruit::getPrice))
                .map(Fruit::getPrice)
                .collect(toList());
    }
}