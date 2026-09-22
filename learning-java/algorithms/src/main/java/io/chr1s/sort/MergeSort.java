package io.chr1s.sort;

import java.util.Arrays;

public class MergeSort {

    public int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        int mid = len / 2;
        int[] leftSub = Arrays.copyOfRange(nums, 0, mid);
        int[] rightSub = Arrays.copyOfRange(nums, mid, len);
        return merge(sort(leftSub), sort(rightSub));
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length + nums2.length];
        int idx = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[i]) {
                ans[idx] = nums1[i];
                i++;
            } else {
                ans[idx] = nums2[j];
                j++;
            }
            idx++;
        }
        while (i < nums1.length) {
            ans[idx] = nums1[i];
            i++;
            idx++;
        }
        while (j < nums2.length) {
            ans[idx] = nums2[j];
            j++;
            idx++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        int[] sortedNums = mergeSort.sort(nums);
        for (int num : sortedNums) {
            System.out.print(num + " ");
        }
    }
}
