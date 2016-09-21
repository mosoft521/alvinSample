package com.gmail.mosoft521.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * Created by Alvin on 2016/9/21 0021.
 */
public class TimeZoneAlvin {
    public static void main(String[] args) {
        Date d = new Date();
        d.setTime(1474453061000L);
        System.out.println(d.toString());
        System.out.println("=============");
        DateTime dateTime = new DateTime();
        dateTime.withZone(DateTimeZone.UTC);
        System.out.println(dateTime);
        DateTime dateTime2 = new DateTime(1474453061000L);
        dateTime2.withZone(DateTimeZone.forID("UTC"));
        System.out.println(dateTime2.toString());
        System.out.println("=============");
        dateTime2.withZone(DateTimeZone.forID("Asia/Shanghai"));
        System.out.println(dateTime2.toString());

//        for (String id : DateTimeZone.getAvailableIDs()) {
//            System.out.println(id);
//        }
    }
}
