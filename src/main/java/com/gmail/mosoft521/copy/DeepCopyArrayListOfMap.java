package com.gmail.mosoft521.copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/6/12 0012.
 */
public class DeepCopyArrayListOfMap {
    public static void main(String[] args) {
        List<Map<String, Object>> srcList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 111);
        map.put("name", "first");
        srcList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 222);
        map.put("name", "second");
        srcList.add(map);
        map = new HashMap<String, Object>();
        map.put("id", 333);
        map.put("name", "third");
        srcList.add(map);

        //List<Bean> destList = new ArrayList<Bean>(Arrays.asList(new Bean[srcList.size()]));
//        ArrayList<Map<String, Object>> destList = (ArrayList) ((ArrayList) srcList).clone();
        List<Map<String, Object>> destList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> m : srcList) {
            HashMap<String, Object> mNew = (HashMap) ((HashMap) m).clone();
            destList.add(mNew);
        }
        destList.get(1).put("plus", "plus");

        for (Map<String, Object> m : srcList) {
            System.out.println("src: " + m);
        }
        System.out.println();

        for (Map<String, Object> m : destList) {
            System.out.println("dest: " + m);
        }
    }
}
/*
src: {name=first, id=111}
src: {name=second, id=222}
src: {name=third, id=333}

dest: {name=first, id=111}
dest: {name=second, plus=plus, id=222}
dest: {name=third, id=333}
 */