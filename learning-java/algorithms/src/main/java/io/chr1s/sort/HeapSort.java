package io.chr1s.sort;

public class HeapSort {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        // build min heap
        buildMaxHeap(nums);
        while (len > 0) {
            swap(nums, 0, len-1);
            --len;
            maxHeapify(nums, 0, len);
        }
        return nums;
    }

    private static void buildMaxHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; --i) {
            maxHeapify(nums, i, nums.length);
        }
    }

    private static void maxHeapify(int[] nums, int i, int len) {
        int largest = i;
        int l = left(i);
        int r = right(i);
        if (l < len && nums[l] > nums[largest]) {
            largest = l;
        }
        if (r < len && nums[r] > nums[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(nums, largest, i);
            maxHeapify(nums, largest, len);
        }
    }

    private static int left(int i) {
        return 2*i+1;
    }

    private static int right(int i) {
        return 2*i+2;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        int[] sortedNums = sort(nums);
        for (int num : sortedNums) {
            System.out.print(num + " ");
        }
    }
}
