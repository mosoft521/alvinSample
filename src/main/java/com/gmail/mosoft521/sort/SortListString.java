package com.gmail.mosoft521.sort;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 2017/6/9 0009.
 */
public class SortListString {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("刘媛媛");
        list.add("王硕");
        list.add("李明");
        list.add("刘迪");
        list.add("刘布");

        //升序
        Collections.sort(list, Collator.getInstance(java.util.Locale.CHINA));//注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println("");
    }
}
/*
李明
刘布
刘迪
刘媛媛
王硕
 */