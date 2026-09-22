package io.chr1s;

public class Solution {

    private static int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    private int res;

    public int shortestPathBinaryMatrix(int[][] grid) {
        res = Integer.MAX_VALUE;
        int N = grid.length;
        boolean[][] visited = new boolean[N][N];
        dfs(grid, 0, 0, 1, visited);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int[][] grid, int row, int col, int length, boolean[][] visited) {
        if (grid[row][col] == 1 || visited[row][col]) {
            return;
        }
        int N = grid.length;
        if (row == N - 1 && col == N - 1) {
            res = Math.min(res, length);
            return;
        }
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }
            visited[row][col] = true;
            dfs(grid, nextRow, nextCol, length + 1, visited);
            visited[row][col] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestPathBinaryMatrix(new int[][]{{0,1,0,0,1,1,0},{1,0,0,0,0,0,0},{1,0,0,1,1,1,1},{0,1,0,0,0,0,0},{1,0,0,0,0,0,1},{1,0,0,1,0,0,0},{1,0,1,0,0,1,0}}));
    }
}
