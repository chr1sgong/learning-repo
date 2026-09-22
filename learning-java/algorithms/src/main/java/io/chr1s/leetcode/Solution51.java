package io.chr1s.leetcode;

import io.chr1s.ds.BIT;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-30
 */
public class Solution51 {

    public int reversePair(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        BIT bit = new BIT(max + 1);
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int currIdx = nums[i] + 1;
            bit.add(currIdx, 1);
            res += bit.rangeSum(currIdx + 1, max + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution51().reversePair(new int[]{1, 2, 3, 4, 5, 6, 0}));
    }
}
