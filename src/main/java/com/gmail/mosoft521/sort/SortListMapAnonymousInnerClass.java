package com.gmail.mosoft521.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/9 0009.
 */
public class SortListMapAnonymousInnerClass {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 999);
        map.put("999", "刘媛媛");
        map.put("abc", "王硕");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 111);
        map.put("111", "李明");
        map.put("abc", "刘迪");
        map.put("aaa", "刘布");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 333);
        map.put("333", "李明");
        map.put("abc", "刘迪");
        map.put("aaa", "刘布");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 222);
        map.put("222", "李明");
        map.put("abc", "刘迪");
        map.put("aaa", "刘布");
        list.add(map);

        //升序

        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                return (int) arg0.get("id") - (int) arg1.get("id");
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("");
    }
}
