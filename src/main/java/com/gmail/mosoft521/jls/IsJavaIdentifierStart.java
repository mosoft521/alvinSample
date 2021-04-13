package com.gmail.mosoft521.jls;

public class IsJavaIdentifierStart {
    public static void main(String[] args) {
        System.out.println(Character.isJavaIdentifierStart('A'));
        System.out.println(Character.isJavaIdentifierStart('a'));
        System.out.println(Character.isJavaIdentifierStart('α'));
        System.out.println(Character.isJavaIdentifierStart('啊'));
    }
}
