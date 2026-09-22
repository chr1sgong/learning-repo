package io.chr1s.interview;


// 二维数组，每行单调递增，每列单调递增
// 取top n, 可能有重复数据


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 1 2 4
// 2 3 5
public class Solution {

    private int[] topN(int[][] arr, int topN) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] i1, int[] i2) -> {
            return Integer.compare(arr[i2[0]][i2[1]], arr[i1[0]][i1[1]]);
        });
        int[] res = new int[topN];
        int index = 0;
        int m = arr.length;
        int n = arr[0].length;
        pq.offer(new int[]{m - 1, n - 1});
//        boolean[][] added = new boolean[m][n];
        Set<Integer> set = new HashSet<>();
        set.add(arr[m - 1][n - 1]);
//        added[m - 1][n - 1] = true;
        while (!pq.isEmpty() && index < topN) {
            int[] currIndex = pq.poll();
            int row = currIndex[0];
            int col = currIndex[1];
            res[index++] = arr[row][col];
            if (row > 0 && !set.contains(arr[row - 1][col])) {
                pq.offer(new int[]{row - 1, col});
                set.add(arr[row - 1][col]);
            }
            if (col > 0 && !set.contains(arr[row][col - 1])) {
                pq.offer(new int[]{row, col - 1});
                set.add(arr[row][col - 1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = new int[][]{{1, 2, 4}, {2,3,5}};
        int[] ans = solution.topN(arr, 10);
        for (int n : ans) {
            System.out.print(n + ", ");
        }
    }
}
