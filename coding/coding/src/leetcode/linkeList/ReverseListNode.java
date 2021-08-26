package leetcode.linkeList;

public class ReverseListNode {
    // null head xx last
    public ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseListNode(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // pre <- cur  next
    public ListNode reverseListNode1(ListNode head) {
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            // 注意先移动pre 再移动cur
            pre = cur;
            cur = next;
        }
        return pre;
    }


}
