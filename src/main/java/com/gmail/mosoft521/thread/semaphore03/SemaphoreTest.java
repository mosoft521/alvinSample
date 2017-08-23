package com.gmail.mosoft521.thread.semaphore03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 10;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(System.currentTimeMillis() + " ThreadName: " + Thread.currentThread().getName() + " save data");
                        Thread.sleep(3000);
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
/*
1503466160559 ThreadName: pool-1-thread-4 save data
1503466160559 ThreadName: pool-1-thread-1 save data
1503466163559 ThreadName: pool-1-thread-2 save data
1503466163559 ThreadName: pool-1-thread-5 save data
1503466166559 ThreadName: pool-1-thread-9 save data
1503466166559 ThreadName: pool-1-thread-6 save data
1503466169559 ThreadName: pool-1-thread-10 save data
1503466169559 ThreadName: pool-1-thread-8 save data
1503466172559 ThreadName: pool-1-thread-7 save data
1503466172559 ThreadName: pool-1-thread-3 save data
-----------------------------
1503466809035 ThreadName: pool-1-thread-2 save data
1503466809035 ThreadName: pool-1-thread-3 save data
1503466812035 ThreadName: pool-1-thread-4 save data
1503466812035 ThreadName: pool-1-thread-6 save data
1503466815035 ThreadName: pool-1-thread-8 save data
1503466815035 ThreadName: pool-1-thread-7 save data
1503466818035 ThreadName: pool-1-thread-1 save data
1503466818035 ThreadName: pool-1-thread-5 save data
1503466821035 ThreadName: pool-1-thread-9 save data
1503466821035 ThreadName: pool-1-thread-10 save data
 */