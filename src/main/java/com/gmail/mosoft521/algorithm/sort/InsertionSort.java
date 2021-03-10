package com.gmail.mosoft521.algorithm.sort;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        /*
         * 相当于打牌 抓牌的过程  小的插前面
         * 外层循环 初始化i = 1
         * 内循环 j = i-1  当j>=0并且arr[j]>arr[j+1]
         * 交换位置 j--继续循环，往前找
         * 直到循环到下标0
         * 把每次内循环找的的最小的数放到最前面
         * */
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
