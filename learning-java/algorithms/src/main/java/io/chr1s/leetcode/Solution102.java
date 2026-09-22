package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution102 {

    private int back = -1;

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        Arrays.sort(arr);
        List<int[]> freq = new ArrayList<>();
        for (int num : arr) {
            if (freq.isEmpty() || freq.get(freq.size() - 1)[0] != num) {
                freq.add(new int[]{num, 1});
            } else {
                if (num == freq.get(freq.size() - 1)[0]) {
                    freq.get(freq.size() - 1)[1]++;
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        backtrack(freq, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private List<List<Integer>> combinationSumII(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        backtrack(arr, 0, target, curr, res);
        return res;
    }

    private void backtrack(int[] arr, int idx, int target, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
        }
        if (idx >= arr.length) return;
        for (int i = idx; i < arr.length; ++i) {
            if (arr[i] == back) continue;
            curr.add(arr[i]);
            backtrack(arr, idx + 1, target - arr[i], curr, res);
            back = curr.get(curr.size() - 1);
            curr.remove(curr.size() - 1);
        }
    }


    private void backtrack(List<int[]> freq, int target, int startIdx, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (target < 0 || startIdx >= freq.size()) return;

        backtrack(freq, target, startIdx + 1, curr, res); // 不用当前的数
        int most = Math.min(target / freq.get(startIdx)[0], freq.get(startIdx)[1]);
        for (int i = 1; i <= most; ++i) { // 用1到most个当前的数
            curr.add(freq.get(startIdx)[0]);
            backtrack(freq, target - i * freq.get(startIdx)[0], startIdx + 1, curr, res);
        }
        for (int i = 1; i <= most; ++i) { // 把数退出来
            curr.remove(curr.size() - 1);
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> res = new Solution102().combinationSumII(new int[]{2,5,2,1,2}, 5);
        for (List<Integer> list : res) {
            for (int num : list) {
                System.out.print(num + ", ");
            }
            System.out.println();
        }
    }
}
