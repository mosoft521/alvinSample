package com.gmail.mosoft521.dl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alvin on 2016/3/30.
 */
public class Recipes_Lock {
    static String lock_path = "/curator_recipes_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                        lock.acquire();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("生成的订单号是：" + orderNo);
                    try {
                        lock.release();
                    } catch (Exception e) {

                    }
                }
            }).start();
        }
        down.countDown();
    }
}
/*
生成的订单号是：10:29:49|969
生成的订单号是：10:29:50|015
生成的订单号是：10:29:50|062
生成的订单号是：10:29:50|102
生成的订单号是：10:29:50|131
生成的订单号是：10:29:50|198
生成的订单号是：10:29:50|232
生成的订单号是：10:29:50|262
生成的订单号是：10:29:50|304
生成的订单号是：10:29:50|332
生成的订单号是：10:29:50|357
生成的订单号是：10:29:50|382
生成的订单号是：10:29:50|421
生成的订单号是：10:29:50|458
生成的订单号是：10:29:50|473
生成的订单号是：10:29:50|493
生成的订单号是：10:29:50|503
生成的订单号是：10:29:50|532
生成的订单号是：10:29:50|550
生成的订单号是：10:29:50|564
生成的订单号是：10:29:50|580
生成的订单号是：10:29:50|602
生成的订单号是：10:29:50|632
生成的订单号是：10:29:50|657
生成的订单号是：10:29:50|672
生成的订单号是：10:29:50|689
生成的订单号是：10:29:50|707
生成的订单号是：10:29:50|725
生成的订单号是：10:29:50|738
生成的订单号是：10:29:50|756
 */