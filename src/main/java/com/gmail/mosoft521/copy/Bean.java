package com.gmail.mosoft521.copy;

import java.io.Serializable;

/**
 * Created by root on 2017/6/12 0012.
 */
public class Bean implements Cloneable, Serializable {
    private String name;
    private int age;

    public Bean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CopyBean:: Name=" + this.name + " Age=" + this.age;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }
}
