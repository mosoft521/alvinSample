package com.gmail.mosoft521.priorityQueue;

import java.util.PriorityQueue;

public class pqTest {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        int[] arr = new int[]{2, 4, 1, 9, 6};
        //乱序插入
        for (int i : arr) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            // 每次取出第一个（最小）元素
            System.out.println(pq.poll());// 输出有序：1,2,4,6,9
        }
    }
}
