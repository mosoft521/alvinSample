package com.gmail.mosoft521.string;

public class Subtring {
    public static void main(String[] args) {
        String phoneAll = "+86-18812345678";
        String phone = phoneAll;
        if (phoneAll.startsWith("+86-")) {
            phone = phoneAll.substring("+86-".length());
        }
        System.out.println(phone);
    }
}
