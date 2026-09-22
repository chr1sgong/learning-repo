package io.chr1s.sort;

public class InsertSort {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i-1; j >= 0; --j) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
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
