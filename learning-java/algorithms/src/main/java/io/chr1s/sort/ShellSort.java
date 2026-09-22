package io.chr1s.sort;

public class ShellSort {

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = 0; i < gap; ++i) {
                insertSort(nums, i, gap);
            }
            gap /= 2;
        }
        return nums;
    }

    /**
     * 局部插入排序
     * @param nums
     * @param startIdx
     * @param gap
     */
    private static void insertSort(int[] nums, int startIdx, int gap) {
        for (int i = startIdx; i + gap <= nums.length; i += gap) {
            for (int j = i; j-gap >= startIdx; j -= gap) {
                if (nums[j-gap] > nums[j]) {
                    int temp = nums[j-gap];
                    nums[j-gap] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        int[] sortedNums = sort(nums);
        for (int num : sortedNums) {
            System.out.print(num + " ");
        }
    }
}
