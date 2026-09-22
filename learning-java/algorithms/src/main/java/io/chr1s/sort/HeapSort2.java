package io.chr1s.sort;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-15
 */
public class HeapSort2 {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        maxHeapify(nums);
        int len = nums.length - 1;
        while (len > 0) {
            swap(nums, 0, len--);
            sink(nums, 0, len);
        }
        return nums;
    }

    private static void maxHeapify(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            sink(nums, i, nums.length - 1);
        }
    }

    private static void sink(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int largest = start;
        int left = left(start);
        int right = right(start);
        if (left <= end && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right <= end && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != start) {
            swap(nums, start, largest);
            sink(nums, largest, end);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        int[] res = sort(new int[]{1,3,5,7,1,3,1,76});
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
