package com.gmail.mosoft521.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by root on 2017/6/8 0008.
 */
public class JodaAlvin {
    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime dt1 = DateTime.parse("2017-06-06", format);
        DateTime dt2 = DateTime.parse("2017-06-08", format);

        System.out.println(dt1.toString());
        System.out.println(dt1.getMillis());

        System.out.println(dt2.toString());
        System.out.println(dt2.getMillis());

        System.out.println(Days.daysBetween(dt1, dt2).getDays());//2
        System.out.println(Days.daysBetween(dt2, dt1).getDays());//-2
    }
}
