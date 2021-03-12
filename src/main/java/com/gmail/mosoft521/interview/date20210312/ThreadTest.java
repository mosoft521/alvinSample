package com.gmail.mosoft521.interview.date20210312;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        A a = new A();
        a.start();

        synchronized (atomicInteger) {
            try {
                atomicInteger.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicInteger.get());
    }

    static class A extends Thread {
        @Override
        public void run() {
//            synchronized (atomicInteger) {
            atomicInteger.notify();
            atomicInteger.set(1);
//            }
        }
    }
}
/*
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	at java.lang.Object.notify(Native Method)
	at com.gmail.mosoft521.interview.date20210312.ThreadTest$A.run(ThreadTest.java:25)

把注释打开OK了
这题考的是线程的WaitNotify机制
 */