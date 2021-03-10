package com.gmail.mosoft521.algorithm.sort;

public class BubbleSort {
    public static void bubbleSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }

        /*每次把最大的数字排到最后一位
         * 数组下标 从0到arr.length-1
         * 当e=0时，说明剩下的数字就是最小的第一个数字 所以不用再排了
         * 内循环，i<e 因为判断条件时 arr[i]和arr[i+1] 比较
         * 当i=e时 i+1越界所以 到 e-1就行了
         * */
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        /*
         * 取巧的交换方法，俗称抖机灵法
         * arr[i] = arr[i] ^ arr[j];
         * arr[j] = arr[i] ^ arr[j];
         * arr[i] = arr[i] ^ arr[j];
         */


        /*
         * 常规交换方法
         * */
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
