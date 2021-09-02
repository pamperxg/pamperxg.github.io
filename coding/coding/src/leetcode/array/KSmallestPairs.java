package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class KSmallestPairs {
    /**
     * nums1=[1,7,11], nums2=[2,4,6], k = 3
     * => [1,2],[1,4],[1,6]
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 大顶堆
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(k,
                ((o1, o2) -> (o2.get(0) + o2.get(1)) - (o1.get(0) + o1.get(1))));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                // 大顶堆中没满，或者还有更小的元素
                if (priorityQueue.size() < k ||
                        nums1[i] + nums2[j] <=
                                priorityQueue.peek().get(0) + priorityQueue.peek().get(1)) {
                    if (priorityQueue.size() == k) {
                        priorityQueue.poll();
                    }
                    List<Integer> list = new ArrayList<>();
                    list.add(nums1[i]);
                    list.add(nums2[j]);
                    priorityQueue.add(list);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < k && !priorityQueue.isEmpty(); i++) {
            res.add(0, priorityQueue.poll());
        }
        return res;
    }
}
