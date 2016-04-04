package com.gmail.mosoft521.curator;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator典型使用场景(五)分布式Barrier：使用JDK自带的CyclicBarrier模拟一场龟兔赛跑
 */
public class Recipes_CyclicBarrier {
    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Thread(new Runner("1号选手")));
        executorService.submit(new Thread(new Runner("2号选手")));
        executorService.submit(new Thread(new Runner("3号选手")));
        executorService.shutdown();
    }

}

class Runner implements Runnable {
    private String name;

    public Runner(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(name + " 准备好了");
        try {
            Recipes_CyclicBarrier.barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name + "起跑！");
    }
}
/*
1号选手 准备好了
2号选手 准备好了
3号选手 准备好了
3号选手起跑！
1号选手起跑！
2号选手起跑！
 */