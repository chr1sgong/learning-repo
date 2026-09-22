package io.chr1s.sort;

public class QuickSort {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int idx = partition(nums, start, end);
        sort(nums, start, idx - 1);
        sort(nums, idx + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivot) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] < pivot) {
                start++;
            }
            nums[end] = nums[start];

        }
        nums[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        int[] sortedNums = sort(nums);
        for (int num : sortedNums) {
            System.out.print(num + " ");
        }
    }
}
