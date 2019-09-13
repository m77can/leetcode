package com.m77can.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class SolutionTest {

    private Solution solution = new Solution();

    @Test
    public void twoSum() {

        Assert.assertArrayEquals(new Solution().twoSum(new int[]{1, 2, 3, 4}, 5), new int[]{0, 3});
    }

    @Test
    public void addTwoNumbers() {

        ListNode l1 = generateListNode(new int[]{2, 4, 3});
        ListNode l2 = generateListNode(new int[]{5, 6, 4});


//        Assert.assertArrayEquals(new Solution().addTwoNumbers(l1,l2));
    }


    private ListNode generateListNode(int[] array) {

        if (Objects.isNull(array)) {
            throw new RuntimeException("Array can not be null");
        }

        ListNode root = new ListNode(0);
        ListNode p = root;

        for (int i = 0; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }

        return root.next;
    }

    @Test
    public void lengthOfLongestSubstring() {

        Assert.assertEquals(solution.lengthOfLongestSubstring("abcabcbb"), 3);
    }

    @Test
    public void findMedianSortedArrays() {

        Assert.assertEquals(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2,4}), 2.0,2);
    }
}