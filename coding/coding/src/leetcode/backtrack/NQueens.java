package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    /**
     * n = 4
     * 。Q..
     * ...Q
     * Q...
     * ..Q.
     * 逐行尝试所有情况
     */
    public List<List<String>> solveNQueen(int n) {
        List<List<String>> res = new ArrayList<>();
        List<StringBuilder> track = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            track.add(sb);
        }
        backTrack(res, track, 0);
        return res;
    }

    private void backTrack(List<List<String>> res, List<StringBuilder> track, int row) {
        // 遍历到最后一行
        if (row == track.size()) {
            // 将棋盘结果加入结果集
            List<String> tmpRes = new ArrayList<>();
            for (StringBuilder sb : track) {
                tmpRes.add(sb.toString());
            }
            res.add(tmpRes);
            return;
        }
        // 尝试在每一列防止皇后，进行回溯
        int n = track.get(row).length();
        for (int col = 0; col < n; col++) {
            if (!isValid(track, row, col)) {
                continue;
            }
            track.get(row).setCharAt(col, 'Q');
            backTrack(res, track, row + 1);
            track.get(row).setCharAt(col, '.');
        }
    }

    // 遍历顺序为逐行遍历
    // 当前列，左上，右上如果存在皇后则不合法
    private boolean isValid(List<StringBuilder> track, int row, int col) {
        int n = track.size();
        // 当前列上没有皇后
        for (StringBuilder sb : track) {
            if (sb.charAt(col) == 'Q') {
                return false;
            }
        }
        // 右上没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (track.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 左上角没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (track.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
