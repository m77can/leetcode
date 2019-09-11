package com.m77can.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static Logger logger = LoggerFactory.getLogger(Solution.class);

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

    //直接计算
    public ListNode addTwoNumbers(
        ListNode l1,
        ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = result;
        int carry = 0;

        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry = carry + p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                carry = carry + p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(carry % 10);
            p3 = p3.next;
            carry = carry / 10;
        }
        if (carry != 0) {
            p3.next = new ListNode(1);
        }

        return result.next;
    }

    public int lengthOfLongestSubstring(String s) {

        int result = 0;
        int temp = 0;
        Map map = new HashMap<Character, String>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.get(ch) == null) {
                map.put(ch, 0);
            } else {

                i = i - map.size();
                temp = map.size();
                map.clear();
            }
            if (temp > result) {
                result = temp;
            }

        }
        if (map.size() > result) {
            result = map.size();
        }


        return result;
    }
}
