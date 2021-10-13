package leetcode.backtrack;

public class MaxAreaOfIslands {
    int[] directions = new int[]{-1, 0, 1, 0, -1};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid[0] == null || grid.length == 0) return 0;
        int area = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area = Math.max(area, dfs(grid, i, j));
                }
            }
        }
        return area;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        int area = 1, x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            x = r + directions[i];
            y = c + directions[i + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                area += dfs(grid, x, y);
            }
        }
        return area;
    }
}
