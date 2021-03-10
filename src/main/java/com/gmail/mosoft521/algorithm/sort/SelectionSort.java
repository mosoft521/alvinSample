package com.gmail.mosoft521.algorithm.sort;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        /*
         * 选择排序的思想： 遍历数组
         * 初始化一个变量，代表每次循环找到的最小的数的下标
         * 内循环，每一次找的都是最小数的下标，
         * 然后每次用当前最小下标的元素和arr[j]比较，
         * 看看当前的下表是否是最小下标 循环结束，
         * minIndex就是这一次循环找到的最小的数的下标
         * 然后交换i和minIndex的下标
         * i代表每次开始的位置也就是每次循环最小的位置
         **/
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
