package com.gmail.mosoft521.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//全排列
public class LinkedListTest01 {
    private static List<LinkedList<Integer>> res = new ArrayList<>();

    public static void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i]))
                continue;
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            track.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        System.out.println(res);
    }
}
//[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
