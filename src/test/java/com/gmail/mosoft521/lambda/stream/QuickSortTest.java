package com.gmail.mosoft521.lambda.stream;

import org.junit.Test;

public class QuickSortTest {
    /**
     * original literal
     */
    private final static String ORIGINAL_LITERAL = "====The original array as below====";
    /**
     * the array after sort.
     */
    private final static String SORTED_LITERAL = "====The array after sorted as below====";

    @Test
    public void testQuickSort() {
        QuickSort quickSort = new QuickSort();
        int[] originalArray = quickSort.prepareForData();
        quickSort.print(ORIGINAL_LITERAL, originalArray);
        quickSort.quickSort(originalArray);
        quickSort.print(SORTED_LITERAL, originalArray);
    }
}