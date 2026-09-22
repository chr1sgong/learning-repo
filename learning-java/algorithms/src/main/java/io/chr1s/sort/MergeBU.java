package io.chr1s.sort;

public class MergeBU {

    private int[] aux;

    public void sort(int[] nums) {
        int N = nums.length;
        aux = new int[N];
        for (int size = 1; size < N; size = size + size) {
            for (int lo = 0; lo < N - size; lo += size + size) {
                merge(nums, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        // 先复制到辅助数组
        for (int k = lo; k <= hi; k++) {
            aux[k] = nums[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) nums[k] = aux[j++];
            else if (j > hi) nums[k] = aux[i++];
            else if (aux[i] > aux[j]) nums[k] = aux[j++];
            else nums[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        MergeBU mergeBU = new MergeBU();
        int[] nums = new int[] {1,3,2,5,7,9,10,3,5,7,20,111,14,12};
        mergeBU.sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
