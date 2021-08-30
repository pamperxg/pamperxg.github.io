package leetcode.bfs;

import sun.awt.image.ImageWatched;

import java.util.*;

public class OpenLock {
    /**
     * deadends = [0201, 0101, 0102, 1212, 2002]
     * target = 0202
     * 转盘锁每一位数字都可以+/- 1
     */
    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!q.isEmpty()) {
            // 每一层元素数
            int size = q.size();
            // 每一个元素向周边扩散
            for (int i = 0; i < size; i ++) {
                String cur = q.poll();
                // 遍历到目标节点
                if (cur.equals(target)) {
                    return step;
                }
                // 遍历到死亡节点，直接跳过
                if (deadSet.contains(cur)) {
                    continue;
                }
                // 尝试转动锁
                for (int j = 0; j < 4; j++) {
                    String up = moveOne(cur, j, true);
                    // 未转到过的值，加入队列
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = moveOne(cur, j, false);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String moveOne(String s, int i, boolean isPlus) {
        char[] chars = s.toCharArray();
        if (isPlus) {
            if (chars[i] == '9') {
                chars[i] = '0';
            } else {
                chars[i] += 1;
            }
        } else {
            if (chars[i] == '0') {
                chars[i] = '9';
            } else {
                chars[i] -= 1;
            }
        }
        return new String(chars);
    }
}
