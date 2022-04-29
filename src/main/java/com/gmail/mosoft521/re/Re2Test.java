package com.gmail.mosoft521.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Re2Test {
    public static void main(String[] args) {
        String mobile = "+86-13651301931";
        String mobile2 = "13651301931";

        Pattern p = Pattern.compile("(\\+\\d+\\-)?(\\d+)");
        Matcher m = p.matcher(mobile);
        while (m.find()) {
            String group = m.group();
            String group0 = m.group(0);
            String group1 = m.group(1);
            String group2 = m.group(2);//13651301931
            System.out.println(group);
            System.out.println(group0);
            System.out.println(group1);
            System.out.println(group2);
        }

        System.out.println("================");
        m = p.matcher(mobile2);
        while (m.find()) {
            String group = m.group();
            String group0 = m.group(0);
            String group1 = m.group(1);
            String group2 = m.group(2);//13651301931
            System.out.println(group);
            System.out.println(group0);
            System.out.println(group1);
            System.out.println(group2);
        }
    }
}
