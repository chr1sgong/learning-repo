package io.chr1s.sort;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-04-21
 */
public class SortCompilation {

    public int[] heapSort(int[] nums) {
        if (nums == null || nums.length < 2) return nums;
        heapify(nums);
        int len = nums.length - 1;
        while (len >= 0) {
            swap(nums, 0, len);
            len--;
            sink(nums, 0, len);
        }
        return nums;
    }

    private void heapify(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; --i) {
            sink(nums, i, nums.length - 1);
        }
    }

    private void sink(int[] nums, int i, int endIdx) {
        int left = left(i);
        int right = right(i);
        int largest = i;
        if (left <= endIdx && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right <= endIdx && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(nums, i, largest);
            sink(nums, largest, endIdx);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        int[] sortedArr = new SortCompilation().heapSort(new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12});
        for (int n : sortedArr) {
            System.out.print(n + " ");
        }
    }
}
