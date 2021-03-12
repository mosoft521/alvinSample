package com.gmail.mosoft521.interview.date20210312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<Integer>(Arrays.asList(10, 20, 30, null));//[10,20,30,null]
        dataList.subList(2, 4).set(0, 40);//[10,20,40,null]
        dataList = new ArrayList<Integer>(dataList);//[10,20,40,null]
        dataList.add(50);//[10,20,40,null,50]
        dataList.set(2, 100);//[10,20,100,null,50]
        for (Integer v : dataList) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
/*
10 20 100 null 50
 */