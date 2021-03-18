package com.gmail.mosoft521.interview.date20210318;

public class Sub extends Super {
//        public long getLength() {
    public int getLength() {
        return 5;
    }

    public static void main(String[] args) {
        Super sooper = new Super();
        Super sub = new Sub();
        System.out.println(sooper.getLength() + "," + sub.getLength());
    }
}
/*
VER1:
java: com.gmail.mosoft521.interview.date20210318.Sub中的getLength()无法覆盖com.gmail.mosoft521.interview.date20210318.Super中的getLength()
  返回类型long与int不兼容
VER2: 4,5
 */
