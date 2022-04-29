package com.gmail.mosoft521.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewYearDay {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2021, Calendar.DECEMBER, 31);
        Date d = c.getTime();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat f2 = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println(f.format(d));//2021-12-31
        System.out.println(f2.format(d));//2022-12-31

        c.set(2020, Calendar.DECEMBER, 31);
        d = c.getTime();
        System.out.println(f.format(d));//2020-12-31
        System.out.println(f2.format(d));//2021-12-31

    }
}
