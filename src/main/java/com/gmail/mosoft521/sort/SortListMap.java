package com.gmail.mosoft521.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/9 0009.
 */
public class SortListMap {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 444);
        map.put("123", "刘媛媛");
        map.put("abc", "王硕");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 123);
        map.put("123", "李明");
        map.put("abc", "刘迪");
        map.put("aaa", "刘布");
        list.add(map);

        //升序
        MapComparator mc = new MapComparator();
        Collections.sort(list, mc);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("");
    }
}
