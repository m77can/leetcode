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
      if (available) {
        break;
      }
    }

    return new int[]{i, j};
  }

  //2

  /**
   * 计算两数之和 可能出现很大的数，所以不能转换成 int 再计算
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
    if (length == 1) {
      return true;
    }

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

  //
  //效率太低
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    if (s.length() == 1) {
      return s;
    }
    if (numRows == 2) {

    }
    char[] chars = s.toCharArray();
    char[] result = new char[chars.length];
    int unit = numRows * 2 - 2;

    int group = s.length() / unit;
    int mod = s.length() % unit;
    if (mod > 0) {
      group++;
    }
    //fill matrix
    List<Character[][]> list = new ArrayList<>();
    for (int i = 0; i < chars.length; ) {
      for (int j = 0; j < group; j++) {
        int currentGroup = (i + 1) / unit;// from 0
        int currentLocation = i % unit;
        int currentSize = 0;
        if (currentGroup == group - 1) {
          currentSize = mod == 0 ? unit : mod;
        } else {
          currentSize = unit;
        }
        Character[][] characters = new Character[numRows][numRows - 1];
        for (int k = i - unit * currentGroup; k < currentSize; k++, i++) {
          System.out.println(String.format("current k is %s", k));
          if (k >= 0 && k < numRows) {
            characters[k][0] = chars[i];
          }
          if (k >= numRows) {
            characters[numRows * 2 - k - 2][k - numRows + 1] = chars[i];
          }
        }
        list.add(characters);
      }
    }
    // Traversing
    for (int i = 0; i < chars.length; ) {
      for (int j = 0; j < numRows; j++) {
        for (int k = 0; k < group; k++) {
          Character[][] characters = list.get(k);
          for (int z = 0; z < numRows - 1; z++) {
            if (characters[j][z] != null) {
              result[i] = characters[j][z];
              i++;
            }
          }
        }
      }

    }
    return String.valueOf(result);
  }

  public String convertTry(String s, int numRows) {

    if (numRows == 1) {
      return s;
    }
    if (s.length() == 1) {
      return s;
    }

    char[] temp = s.toCharArray();

    char[] result = new char[temp.length];
    int unit = numRows * 2 - 2;
    int group = s.length() / unit;
    int mod = s.length() % unit;
    if (mod > 0) {
      group++;
    }

    for (int i = temp.length - 1; i >= 0; ) {

      for (int j = numRows; j > 0; j--) {
        for (int k = group; k > 0; k--) {
          int z = 0;

          if (k == group) {
            z = mod == 0 ? unit : mod - 1;
          } else {
            z = unit - 1;
          }
          if (j == numRows) {
            if (z < numRows - 1) {
              //do nothing
            } else {
              result[i] = temp[(k - 1) * unit + numRows - 1];
              System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
              i--;
            }

          } else if (j == 1) {
            if (z >= 0) {
              result[i] = temp[(k - 1) * unit];
              System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
              i--;
            }
          } else {
            if (z <= numRows - 1) {
//                             do nothing
              if (z >= j - 1) {
                result[i] = temp[(k - 1) * unit + j - 1];
                System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
                i--;
                continue;
              }

            } else if (z > numRows - 1 && unit - z > j - 1) {
              result[i] = temp[(k - 1) * unit + j - 1];
              System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
              i--;
            } else if (unit - z <= j - 1) {
              result[i] = temp[k * unit - j + 1];
              System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
              i--;
              result[i] = temp[(k - 1) * unit + j - 1];
              System.out.println(String.format("Current i is %s and value is %s", i, result[i]));
              i--;

            }
          }
        }
      }
    }

    return String.valueOf(result);
  }

  //    7 reverse int
  public int reverse(int x) {
    int sum = 0;
    while (x != 0) {
      if ((sum * 10L) > Integer.MAX_VALUE || (sum * 10L) < Integer.MIN_VALUE) {
        return 0;
      }
      sum = sum * 10 + x % 10;
      x = x / 10;
    }
    return sum;
  }

  //8

  public int myAtoi(String str) {
    long sum = 0;

    char last = ' ';
    boolean isNegative = false;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      //' '
      //'-'
      //'[0-9]'
      //'other'
      if (c >= '0' && c <= '9') {
        if (last == ' '
            || last == '+'
            || last == '-'
            || c >= '0' && c <= '9') {

          sum = (sum * 10L + Integer.parseInt(c + ""));
          last = c;

          if (isNegative) {
            if (sum * -1L < Integer.MIN_VALUE) {
              return Integer.MIN_VALUE;
            }
          } else {
            if (sum > Integer.MAX_VALUE) {
              return Integer.MAX_VALUE;
            }
          }


        } else {
          break;
        }

      } else if (c == '+') {
        if (last == ' ') {
          isNegative = false;
          last = c;
        } else {
          break;
        }
      } else if (c == '-') {
        if (last == ' ') {
          isNegative = true;
          last = c;
        } else {
          break;
        }

      } else if (c == ' ') {
        if (last == ' ') {
          last = c;
        } else {
          break;
        }

      } else {
        break;
      }
    }

    return isNegative ? (int) (sum * -1) : (int) sum;

  }

//  9
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    char[] number = Integer.toString(x).toCharArray();

    if (number.length == 1) {
      return true;
    }
    int i = 0;
    int j = number.length - 1;
    while (i <= j) {
      if (number[i] == number[j]) {
        i++;
        j--;
      } else {
        break;
      }

    }
    return i > j;
  }

//  9
  public boolean isPalindrome1(int x) {
    // 负数肯定不是，以及首尾不对称的非0数
    if (x < 0 || (x % 10 == 0 && x != 0)) {
      return false;
    }

    int rev = 0;
    while (x > rev) {
      rev = rev * 10 + x % 10; //将低位一半的数取反。
      x = (x / 10);
    }
    //有rev >= x， 奇数情况下需要除去10
    return x == rev || x == (rev / 10);
  }
}
