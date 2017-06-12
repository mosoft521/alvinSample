package com.gmail.mosoft521.copy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 2017/6/12 0012.
 */
public class DeepCopyArrayListOfBean {
    public static void main(String[] args) {
        List<Bean> srcList = new ArrayList<Bean>();
        Bean copyBean = new Bean();
        copyBean.setName("first");
        copyBean.setAge(11);
        srcList.add(copyBean);
        copyBean = new Bean();
        copyBean.setName("second");
        copyBean.setAge(22);
        srcList.add(copyBean);
        copyBean = new Bean();
        copyBean.setName("third");
        copyBean.setAge(33);
        srcList.add(copyBean);

        // 浅copy
        List<Bean> destList = new ArrayList<Bean>(Arrays.asList(new Bean[srcList.size()]));
        Collections.copy(destList, srcList);

        destList.get(1).setAge(1234);
        destList.get(1).setName("tmp");

        for (Bean copyBean1 : srcList) {
            System.out.println("src: " + copyBean1);
        }
        System.out.println();
        for (Bean copyBean1 : destList) {
            System.out.println("dest1: " + copyBean1);
        }
        System.out.println("=========================================");
        System.out.println();

        //深copy
        List<Bean> destList2 = new ArrayList<Bean>();
        for (Bean src : srcList) {
            Bean b = (Bean) src.clone();
            destList2.add(b);
        }

        destList2.get(2).setAge(999);
        destList2.get(2).setName("copy");

        for (Bean copyBean1 : srcList) {
            System.out.println("src: " + copyBean1);
        }
        System.out.println();
        for (Bean copyBean2 : destList2) {
            System.out.println("dest2: " + copyBean2);
        }
    }
}
/*
src: CopyBean:: Name=first Age=11
src: CopyBean:: Name=tmp Age=1234
src: CopyBean:: Name=third Age=33

dest1: CopyBean:: Name=first Age=11
dest1: CopyBean:: Name=tmp Age=1234
dest1: CopyBean:: Name=third Age=33
=========================================

src: CopyBean:: Name=first Age=11
src: CopyBean:: Name=tmp Age=1234
src: CopyBean:: Name=third Age=33

dest2: CopyBean:: Name=first Age=11
dest2: CopyBean:: Name=tmp Age=1234
dest2: CopyBean:: Name=copy Age=999
 */