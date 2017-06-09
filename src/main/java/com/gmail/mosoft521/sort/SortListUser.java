package com.gmail.mosoft521.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 2017/6/9 0009.
 */
public class SortListUser {
    public static void main(String[] args) {
        List<User> studentList = new ArrayList();

        User s = new User();

        s.setName("yuanyuan");

        s.setAge(22);

        studentList.add(s);

        User s1 = new User();

        s1.setName("lily");

        s1.setAge(23);

        studentList.add(s1);

        s1 = new User();

        s1.setName("xxx");

        s1.setAge(99);

        studentList.add(s1);

        MyComparator mc = new MyComparator();

        Collections.sort(studentList, mc);     //按照age升序 22，23，
        Collections.reverse(studentList);    //按照age降序 23,22
        System.out.println("xx");
    }
}
