package com.gmail.mosoft521.lambda.stream;

import java.util.Random;

public class QuickSort {
    private final int LEN = 10;

    public int[] prepareForData() {
        Random random = new Random(System.currentTimeMillis());
        int[] originalArray = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            originalArray[i] = random.nextInt(20);
        }
        return originalArray;
    }

    public void print(String literal, int[] array) {
        System.out.println(literal);
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void quickSort(int[] originalArray) {
        if (originalArray.length > 0) {
            sort(originalArray, 0, originalArray.length - 1);
        }
    }

    private void sort(int[] originalArray, int low, int high) {
        if (low < high) {
            int middle = partition(originalArray, low, high);
            sort(originalArray, low, middle - 1);
            sort(originalArray, middle + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int tmp = array[low];
        while (low < high) {
            while (low < high && array[high] >= tmp) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= tmp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = tmp;
        return low;
    }
}