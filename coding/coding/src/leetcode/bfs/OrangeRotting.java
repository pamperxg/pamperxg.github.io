package leetcode.bfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class OrangeRotting {
    /**
     * [[2,1,1],[1,1,0],[0,1,1]]
     *
     *  [j, i]
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) fresh++;
                if (grid[i][j] == 2) queue.add(new Pair<>(j, i));
            }
        }
        int[] dirs = new int[]{1, 0, -1, 0, 1};
        int days = 0;
        while (!queue.isEmpty() && fresh != 0) {
            int size = queue.size();
            while (size -- > 0) {
                int x = queue.peek().getKey();
                int y = queue.peek().getValue();
                queue.poll();
                for (int i = 0; i < 4; i++) {
                    int dx = x + dirs[i];
                    int dy = y + dirs[i + 1];
                    if (dx < 0 || dx >= n || dy < 0 || dy >= m || grid[dy][dx] != 1) continue;
                    --fresh;
                    grid[dy][dx] = 2;
                    queue.offer(new Pair<>(dx, dy));
                }
            }
            ++days;
        }
        return fresh > 0 ? -1 : days;
    }
}
