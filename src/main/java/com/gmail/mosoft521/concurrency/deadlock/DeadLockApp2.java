package com.gmail.mosoft521.concurrency.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLockApp2 {
    private static final ScheduledExecutorService threadPool
            = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        checkDeadLockByThreadMXBean();
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

    private static void checkDeadLockByThreadMXBean() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadPool.scheduleAtFixedRate(() -> {
            long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
            if (deadlockedThreads != null && deadlockedThreads.length > 0) {
                Arrays.stream(deadlockedThreads)
                        .mapToObj(id -> threadMXBean.getThreadInfo(id))
                        .forEach(threadInfo -> {
                            System.out.println("出现死锁的线程是："
                                    + threadInfo);
                        });
            }
        }, 1, 5, TimeUnit.SECONDS);
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
                System.out.println(this.getName()
                        + " invoke first:" + first);
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
