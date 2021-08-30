package leetcode.backtrack;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands {
    /**
     * [
     * [1,1,1,1,0],
     * [1,1,0,1,0],
     * [0,0,0,0,0]
     * ]
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nc = grid.length;
        int nr = grid[0].length;
        int numIslands = 0;
        for (int i = 0; i < nc; i++) {
            for (int j = 0; j < nr; j++) {
                // 遇到小岛+1
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }

    // 深度优先
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        // 四周扩散
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r , c - 1);
    }

    // 广度优先
    private void bfs(char[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] ele = queue.poll();
            int i = ele[0], j = ele[1];
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '1') {
                continue;
            }
            grid[i][j] = '0';
            queue.offer(new int[]{i + 1, j});
            queue.offer(new int[]{i - 1, j});
            queue.offer(new int[]{i, j - 1});
            queue.offer(new int[]{i, j+ 1});
        }
    }
}
