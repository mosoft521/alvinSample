package com.gmail.mosoft521.concurrency.deadlock;

import java.util.concurrent.TimeUnit;

public class DeadLockApp {
    public static void main(String[] args) {
        String first = "LockA";
        String second = "LockB";
        MyThread a = new MyThread("a", first, second);
        MyThread b = new MyThread("b", second, first);
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        private final String first;
        private final String second;

        public MyThread(String name, String first, String second) {
            super(name);
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            synchronized (first) {
                System.out.println(this.getName() + " invoke first:"
                        + first);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (second) {
                        System.out.println(this.getName()
                                + " invoke second:" + second);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
