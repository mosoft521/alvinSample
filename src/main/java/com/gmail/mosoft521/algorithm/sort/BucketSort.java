package com.gmail.mosoft521.algorithm.sort;

public class BucketSort {
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //常用写法
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] bucket = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            //桶数组此下标有数据，数值就加一
            bucket[arr[i]]++;
        }

        int i = 0;

        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
