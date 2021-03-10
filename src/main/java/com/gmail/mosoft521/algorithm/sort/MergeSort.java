package com.gmail.mosoft521.algorithm.sort;

public class MergeSort {
    public static void mergeSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);

    }

    public static void mergeSort(int arr[], int l, int r) {
        //说明已经分成最小的范围了，得开始合并了
        if (l == r) {
            return;
        }
        //找一个中间元素，当作左半区和右半区的分界线
        int mid = l + ((r - l) >> 1);
        //递归分到最小，依次向上合并
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        //合并 进入一个辅助数组并排序
        merge(arr, mid, l, r);
    }

    public static void merge(int arr[], int m, int l, int r) {
        //辅助数组，长度为传进来数的个数
        int[] help = new int[r - l + 1];
        //p1记录左半区的最小值
        int p1 = l;
        //p2记录右半区的最小值 m是分界线 [l,m]左半区 [m+1,r]右半区
        int p2 = m + 1;
        int i = 0;
        while (p1 <= m && p2 <= r) {
            //开始向辅助数组中按照大小插入数，当左半区或者右半区没数了跳出循环，
            // 插入规则，每一次比较左半区和右半区
            //下标最前面的数的大小，小的进入数组
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            //说明右半区已经全部插入了，左半区就直接插入就行
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            //插入原数组
            arr[l + j] = help[j];
        }
    }
}
