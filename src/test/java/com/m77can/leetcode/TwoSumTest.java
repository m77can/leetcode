package com.m77can.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {

    @Test
    public void twoSum() {

        Assert.assertArrayEquals(new TwoSum().twoSum(new int[]{1, 2, 3, 4}, 5),new int[]{0,3});
    }
}