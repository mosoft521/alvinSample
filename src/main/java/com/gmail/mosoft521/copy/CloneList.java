package com.gmail.mosoft521.copy;

import java.util.ArrayList;

/**
 * Created by root on 2017/6/12 0012.
 */
public class CloneList {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("大中国1");
        list.add("大中国2");
        ArrayList listNew = (ArrayList) list.clone();
        listNew.add("333");
        System.out.println(list.hashCode());
        System.out.println(listNew.hashCode());
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
        for (int i = 0; i < listNew.size(); i++) {
            System.out.println(listNew.get(i));
        }
    }
}
/*
920136450
-1540490479

大中国1
大中国2

大中国1
大中国2
333
 */