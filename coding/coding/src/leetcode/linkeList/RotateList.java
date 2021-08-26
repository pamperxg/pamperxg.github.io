package leetcode.linkeList;

public class RotateList {
    // 链表向右边移动k个位置
    // 构建环，移动k个位置，断开环得到结果
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode iterateNode = head;
        int l = 1;
        while (iterateNode.next != null) {
            iterateNode = iterateNode.next;
            l++;
        }
        // 计算头节点
        int lastPos = l - (k % l);
        if (lastPos == l) {
            return head;
        }
        // 构建环
        iterateNode.next = head;
        while (lastPos -- > 0) {
            iterateNode = iterateNode.next;
        }
        // 断开环
        ListNode res = iterateNode.next;
        iterateNode.next = null;
        return res;
    }
}
