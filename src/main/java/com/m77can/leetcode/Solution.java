package com.m77can.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Solution {

    private static Logger logger = LoggerFactory.getLogger(Solution.class);

    //1
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

    //2

    /**
     * 计算两数之和
     * 可能出现很大的数，所以不能转换成 int 再计算
     *
     * @param l1
     * @param l2
     * @return
     */
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

    //3 计算最长字符串长度
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
            result = temp > result ? temp : result;
        }
        result = map.size() > result ? map.size() : result;


        return result;
    }

    //4 两个数组的中位数（log(m+n)） 实现的是 n
    public double findMedianSortedArrays(
            int[] nums1,
            int[] nums2) {

        int[] all = new int[nums1.length + nums2.length];

        int i, j = 0, k = 0;

        // 排序
        for (i = 0; i < all.length; i++) {

            if (j < nums1.length && k < nums2.length) {

                if (nums1[j] <= nums2[k]) {
                    all[i] = nums1[j];
                    j++;
                } else {
                    all[i] = nums2[k];
                    k++;
                }
                continue;
            }

            if (j >= nums1.length && k < nums2.length) {
                all[i] = nums2[k];
                k++;
            }
            if (j < nums1.length && k >= nums2.length) {
                all[i] = nums1[j];
                j++;
            }
            System.out.println(all[i]);
        }

        for (int q = 0; q < all.length; q++) {
            System.out.println(all[q]);
        }

        // 查找
        if (all.length % 2 == 1) {
            return all[(all.length - 1) / 2];
        } else {
            return (double) (all[(all.length - 1) / 2] + all[all.length / 2]) / 2L;
        }
    }

    public String longestPalindrome(String s) {

        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return s;
        }
        int i, j;
        int length = 0;
        char[] result = {};
        for (i = 0; i < s.length(); i++) {
            for (j = i + 1; j <= s.length(); j++) {
                if (judgePalindrome(chars, i, j)) {
                    if (length <= j - i) {
                        result = Arrays.copyOfRange(chars, i, j);
                        length = result.length;
                    }
                }
            }
        }
        String s1 = String.valueOf(result);
        return s1;
    }

    private boolean judgePalindrome(char[] chars, int i, int j) {

        int length = j - i;
        if (length == 1) return true;

        j = j - 1;
        while (i <= j) {
            if (chars[i] == chars[j]) {
                i++;
                j--;
            } else {
                break;
            }
        }
        return i > j;
    }
}
