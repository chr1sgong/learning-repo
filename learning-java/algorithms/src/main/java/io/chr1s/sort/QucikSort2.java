package io.chr1s.sort;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-15
 */
public class QucikSort2 implements Sort {

    @Override
    public int[] sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partion(nums, start, end);
        sort(nums, start, pivot - 1);
        sort(nums, pivot + 1, end);
    }


    private int partion(int[] nums, int start, int end) {
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
        int[] res = new QucikSort2().sort(new int[]{1,4,7,6,3,5,9,14,2,67,43});
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
