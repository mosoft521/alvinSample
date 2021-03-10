package com.gmail.mosoft521.algorithm.sort;

public class RadixSort {
    public static void radixSort(int[] arr, int digit) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //用在桶每次倒出来的循环
        int k = 0;
        //最大数的位数
        int m = 1;
        //取余数，比如第一次按个位数进桶就先除1，第二次十位数就先除10
        int n = 1;
        //二维数组，10代表余数0~9，第二维代表每个余数最多有多少个数
        int[][] temp = new int[10][arr.length];
        // order下标代表余数，值代表每个余数位置上，有几个数
        int[] order = new int[arr.length];

        while (m <= digit) {
            for (int i = 0; i < arr.length; i++) {
                //取余数
                int rem = (arr[i] / n) % 10;
                //从该余数的第0个开始装
                temp[rem][order[rem]++] = arr[i];
            }

            for (int i = 0; i < arr.length; i++) {
                //i位置上有没有数
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        arr[k++] = temp[i][j];
                    }
                }
                order[i] = 0;
            }

            k = 0;
            m++;
            n *= 10;
        }
    }
}
