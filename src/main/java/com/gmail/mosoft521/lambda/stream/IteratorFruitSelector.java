package com.gmail.mosoft521.lambda.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IteratorFruitSelector extends FruitSelector {
    @Override
    protected List<Double> doFilter(List<Fruit> fruits) {
        //get the fruit name is 'apple'
        List<Fruit> appleList = new ArrayList<>();
        Iterator<Fruit> iterator = fruits.iterator();
        for (; iterator.hasNext(); ) {
            Fruit fruit = iterator.next();
            if (fruit.getName().equals(CANDIDATE_FRUIT)) {
                appleList.add(fruit);
            }
        }
        //do sort.
        Collections.sort(appleList, (o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) return 1;
            else if (o1.getPrice() == o2.getPrice()) return 0;
            else return -1;
        });
        //do filter.
        List<Double> applePriceList = new ArrayList<>();
        for (Fruit fruit : appleList) {
            applePriceList.add(fruit.getPrice());
        }
        return applePriceList;
    }
}