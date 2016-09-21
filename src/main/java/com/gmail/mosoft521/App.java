package com.gmail.mosoft521;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir"));

        System.out.println();

        System.out.println("Thu Apr 14 10:28:06 CST 2016");
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        System.out.println(dateTime.toString(DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss zzz yyyy")));
        System.out.println(dateTime.toString("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH));
        System.out.println(dateTime.toString("EEE MMM dd HH:mm:ss zzz yyyy", Locale.CHINESE));
        Date date = dateTime.toDate();
        System.out.println(date);


        System.out.println("=====================================================================");
        System.out.println(dateTime.getWeekOfWeekyear());
        DateTime dateTime2 = dateTime.plusWeeks(9);
        System.out.println(dateTime2);
        System.out.println(dateTime2.dayOfWeek().getAsText()); //当天是星期几，例如：星期五
        System.out.println(dateTime2.dayOfMonth().getMaximumValue());//当月day里面最大的值
        System.out.println(dateTime.dayOfMonth().getMaximumValue());//当月day里面最大的值
    }
}
/*
C:\Users\Alvin\AppData\Local\Temp\
 */