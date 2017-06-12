package com.gmail.mosoft521.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Alvin on 2016/9/21 0021.
 */
public class TimeZoneAlvin {
    public static void main(String[] args) {
//        Date d = new Date();
//        d.setTime(1477060162000L);//1477031362000 1477060162000
//        System.out.println(d.toString());
//        System.out.println("=============1-1");
//        DateTime dateTime = new DateTime();
//        dateTime.withZone(DateTimeZone.forID("Asia/Shanghai"));//上海时区
//        System.out.println(dateTime);
//        System.out.println("=============1-2");
//        dateTime = new DateTime();
//        dateTime.withZone(DateTimeZone.UTC);
//        System.out.println(dateTime);
//        System.out.println("=============2");
//        DateTime dateTime2 = new DateTime(1477060162000L);
//        dateTime2.withZone(DateTimeZone.forID("UTC"));
//        System.out.println(dateTime2.toString());
//        System.out.println("=============3");
//        dateTime2.withZone(DateTimeZone.forID("Asia/Shanghai"));
//        System.out.println(dateTime2.toString());

//        for (String id : DateTimeZone.getAvailableIDs()) {
//            System.out.println(id);
//        }


        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime dateTime = DateTime.parse("2016-10-21 14:29:22", format);

        System.out.println(dateTime.toString());
        System.out.println(dateTime.getMillis());

        Date date = dateTime.toDate();
        System.out.println(date.getTime());
    }
}
