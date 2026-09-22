package io.chr1s.sort;

/**
 * 选择排序
 */
public class SelectSort {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        for (int i = 0; i < nums.length; ++i) {
            int minIdx = i;
            for (int j = i+1; j < nums.length; ++j) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        int[] sortedNums = sort(nums);
        for (int num : sortedNums) {
            System.out.print(num + " ");
        }
    }
}
