package com.m77can.leetcode;

public class TwoSum {

    public int[] twoSum(
        int[] nums,
        int target) {

        int size = nums.length;
        int i = 0;
        int j = 0;
        boolean available = false;

        for (; i < size - 1; i++) {
            for (j = i + 1; j < size; j++) {
                if (nums[i] + nums[j] == target) {
                    available = true;
                    break;
                }
            }
            if (available) break;
        }

        return new int[]{i, j};
    }
}
