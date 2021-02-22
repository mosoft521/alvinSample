package com.gmail.mosoft521.initialize;

public class Derive extends Base {
    private Member m1 = new Member("Member 1");

    {
        System.out.println("Initial Block()");
    }

    public Derive() {
        System.out.println("Derive()");
    }

    private Member m2 = new Member("Member 2");
    private int i = getInt();

    private int getInt() {
        System.out.println("getInt()");
        return 2;
    }

//    public static void main(String[] args) {
//        new Derive();
//    }
}

class Base {
    public Base() {
        System.out.println("Base()");
    }
}

class Member {
    public Member(String m) {
        System.out.println("Member() " + m);
    }
}
/*
Base()
Member() Member 1
Initial Block()
Member() Member 2
getInt()
Derive()
 */