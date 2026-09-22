package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-03
 */
public class Solution101 {

    private List<List<Integer>> combination(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= nums.length; ++i) {
            backtrack(nums, 0, i, new ArrayList<Integer>(), res);
        }
        return res;

    }
    private void backtrack(int[] nums, int startIdx, int len, List<Integer> curr, List<List<Integer>> res) {
//        if (startIdx + len > nums.length) return;
        if (curr.size() == len) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = startIdx; i < nums.length; ++i) {
            curr.add(nums[i]);
            backtrack(nums, i + 1, len, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution101().combination(new int[]{1,2,3,4});
        System.out.println(result);
    }
}
