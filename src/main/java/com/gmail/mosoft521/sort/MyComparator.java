package com.gmail.mosoft521.sort;

import java.util.Comparator;

/**
 * Created by root on 2017/6/9 0009.
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        User sdto1 = (User) o1;

        User sdto2 = (User) o2;

        return sdto1.getAge() - sdto2.getAge();

    }
}