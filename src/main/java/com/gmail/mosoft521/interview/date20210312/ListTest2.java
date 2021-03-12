package com.gmail.mosoft521.interview.date20210312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest2 {
    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<Integer>(Arrays.asList(10, 20, 30, null));//[10,20,30,null]
        List<Integer> dataList2 = dataList.subList(2, 4);//[30, null]
        dataList2.set(0, 40);//[40, null]
//        dataList = new ArrayList<Integer>(dataList);//[10,20,40,null] 这个和ListTest.java结果一样
        dataList = new ArrayList<Integer>(dataList2);//[40,null]
        dataList.add(50);//[40,null,50]
        dataList.set(2, 100);//[40,null,100]
        for (Integer v : dataList) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
/*
40 null 100
 */