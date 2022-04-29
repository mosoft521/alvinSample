package com.gmail.mosoft521.leetcode;

import java.util.Arrays;
import java.util.HashMap;
//https://leetcode-cn.com/problems/two-sum/
public class TwoSum1 {
    private static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表：元素映射到相应的索引
        for (int i = 0; i < n; i++)
            index.put(nums[i], i);

        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            // 如果 other 存在且不是 nums[i] 本身
            if (index.containsKey(other) && index.get(other) != i)
                return new int[]{i, index.get(other)};
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 3, 6};
        int[] res = twoSum(nums, 6);
        System.out.println(Arrays.toString(res));//[0, 2]
    }
}