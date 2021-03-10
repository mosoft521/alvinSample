package com.gmail.mosoft521.algorithm.sort;

public class QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            //取[l,r]的任意一个数当作等于区的值
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }


    public static int[] partition(int[] arr, int l, int r) {
        //初始的小于区的最大下标
        int less = l - 1;
        //大于区的最小下标
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        //交换大于区的第一个数和r的位置，
        // 交换后more的位置就是等于区的最后一个数的位置
        swap(arr, more, r);
        //返回的是等于区的第一个位置和等于区的最后一个位置
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
