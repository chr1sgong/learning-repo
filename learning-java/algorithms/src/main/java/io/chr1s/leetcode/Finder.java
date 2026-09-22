package io.chr1s.leetcode;

import java.util.*;

public class Finder {

    public int findKth(int[] a, int n, int K) {
        // write code here
        int res = help(a, 0, n - 1, K);
        while (res != n - K) {
            System.out.println(res);
            if (res < n - K) {
                res = help(a, res + 1, n - 1, K);
            } else {
                res = help(a, 0, res - 1, K);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,5,2,2};
        System.out.println("ans: " +  nums[new Finder().findKth(nums, 5,3)]);
    }

    private int help(int[] a, int start, int end, int K) {
        int pivot = a[start];
        while (start < end) {
            while (start < end && pivot <= a[end]) {
                end--;
            }
            a[start] = a[end];
            while (start < end && pivot > a[start]) {
                start++;
            }
            a[end] = a[start];
        }
        a[start] = pivot;
        return start;
    }
}
