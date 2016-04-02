package com.gmail.mosoft521.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alvin on 2016/3/30.
 */
public class Recipes_NoLock {
    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (Exception e) {

                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = simpleDateFormat.format(new Date());
                    System.err.println("生成的订单号是：" + orderNo);
                }
            }).start();
        }

        countDownLatch.countDown();
    }
}
/*
生成的订单号是：22:34:03|848
生成的订单号是：22:34:03|849
生成的订单号是：22:34:03|849
生成的订单号是：22:34:03|849
生成的订单号是：22:34:03|850
生成的订单号是：22:34:03|850
生成的订单号是：22:34:03|850
生成的订单号是：22:34:03|851
生成的订单号是：22:34:03|851
生成的订单号是：22:34:03|852
 */