package com.gmail.mosoft521.interview.date20210318;

public class Student extends Person {
    public static void main(String[] args) {
        Teacher t = new Teacher();
        Student s = new Student();

        if (t instanceof Person) {
//            s = (Student) t;
        }
    }
}
/*
java: 不兼容的类型: com.gmail.mosoft521.interview.date20210318.Teacher无法转换为com.gmail.mosoft521.interview.date20210318.Student
 */