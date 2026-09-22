package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution40 {

    private int back = -1;

    public List<List<Integer>> combinationSum2(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(arr, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] arr, int target, int startIdx, List<Integer> curr, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (target < 0 || startIdx >= arr.length) return;

        for (int i = startIdx; i < arr.length; ++i) {
            if (arr[i] == back) continue;
            curr.add(arr[i]);
            backtrack(arr, target - arr[i], i + 1, curr, res);
            back = curr.get(curr.size() - 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
//        List<List<Integer>> res = new Solution40().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        List<List<Integer>> res = new Solution40().combinationSum2(new int[]{2,5,2,1,2}, 5);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
