package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(nums, res, tmp);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int num : nums) {
            // 不含重复数字，必要过滤条件
            if (tmp.contains(num)) {
                continue;
            }
            tmp.add(num);
            dfs(nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 排列不能包含重复数字
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, res, tmp, visited);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> tmp, boolean[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 去重条件， visited[i - 1] 同 !visited[i - 1]过滤不同索引的重复元素，结果相同
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                continue;
            }
            tmp.add(nums[i]);
            visited[i] = true;
            dfs(nums, res, tmp, visited);
            tmp.remove(tmp.size() - 1);
            visited[i] = false;
        }
    }
}
