package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        // 边界条件判断
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // (o1, o2) -> o1[1] - o2[1] = Comparator.comparingInt(o -> o[1])
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int res = 0;
        int preEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] < preEnd) {
                res ++;
                continue;
            }
            preEnd = interval[1];
        }
        return res;
    }

    public static void main(String[] args) {
//        int res = eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}});
//        int res = eraseOverlapIntervals(new int[][]{{1,100},{11,22},{1,11},{2,12}});
        int res = eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{-100,-2},{5,7}});
        System.out.println(res);
    }
}
