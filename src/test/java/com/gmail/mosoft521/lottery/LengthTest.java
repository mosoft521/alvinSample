package com.gmail.mosoft521.lottery;

/**
 * Created by Alvin on 2016/5/18 0018.
 */
public class LengthTest {
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    static int sizeOfInt(int x) {
        for (int i = 0; ; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(sizeOfInt(1234));
        System.out.println(sizeOfInt(123));
        System.out.println(sizeOfInt(12345));
        System.out.println(sizeOfInt(12));
        System.out.println("=============");
        System.out.println(Integer.toString(1234).length());
        System.out.println(Integer.toString(123).length());
        System.out.println(Integer.toString(12345).length());
        System.out.println(Integer.toString(12).length());
    }

}
