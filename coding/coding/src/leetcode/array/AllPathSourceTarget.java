package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向无环图
 */
public class AllPathSourceTarget {
    /**
     * graph = [[1,2],[3],[3],[]]，第i个节点能够到达的点
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        // 起始节点
        tmp.add(0);
        dfs(graph, 0, res, tmp);
        return res;
    }

    private void dfs(int[][] graph, int i, List<List<Integer>> res, List<Integer> tmp) {
        // 终止条件，到达末尾
        if (i == graph.length - 1) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int x : graph[i]) {
            tmp.add(x);
            dfs(graph, x, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
