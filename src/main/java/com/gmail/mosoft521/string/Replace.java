package com.gmail.mosoft521.string;

/**
 * Created by root on 2017/6/14 0014.
 */
public class Replace {
    public static void main(String[] args) {
//        String all = "aaa中文英文中文123中m文";
//        String a = String.copyValueOf(all.toCharArray());
//        System.out.println(all);
//
////        CharSequence s1 = "中文";
////        CharSequence s2 = "Chinese";
////        String all2 = all.replace(s1, s2);
//
//        String s1 = "中文";
//        String s2 = "Chinese";
//        String all2 = all.replaceAll(s1, s2);
//        System.out.println(all);
//        System.out.println(all2);
//        System.out.println(a);


        String url1 = "https://fasdfjkasjdfkasf.com/afjasdf/jkdjfak/jfaskdfj.docx?dfajskf=afsadf&asdfasf=f123fda&https=1234&https2=ab123";
        int intIndex = url1.indexOf("https");
        System.out.println(intIndex);
        String url2 = url1.replaceFirst("https:","http:");
        System.out.println(url2);
    }
}
