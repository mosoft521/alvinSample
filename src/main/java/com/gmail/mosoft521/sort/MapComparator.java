package com.gmail.mosoft521.sort;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by root on 2017/6/9 0009.
 */
public class MapComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        Map map1 = (Map) o1;

        Map map2 = (Map) o2;

        return (int) map1.get("id") - (int) map2.get("id");

    }
}