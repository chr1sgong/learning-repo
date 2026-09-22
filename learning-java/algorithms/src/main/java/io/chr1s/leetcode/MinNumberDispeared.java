package io.chr1s.leetcode;

public class MinNumberDispeared {

    public int minNumberDisappeared (int[] arr) {
        // write code here

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i+1) continue;
            if (arr[i] < 0 || arr[i] > arr.length) continue;
            int newIdx = arr[i];
            int temp = arr[newIdx];
            arr[newIdx] = arr[i];
            arr[i] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if (arr[i] != i + 1) return i+1;
        }
        return arr.length;
    }

    public static void main(String[] args) {
        System.out.println("res: " + new MinNumberDispeared().minNumberDisappeared(new int[] {1,2,3,5}));
    }
}
