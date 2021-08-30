package leetcode.linkeList;

public class HasCycle {
    // 快慢指针判断环(1)
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            // 快指针走到null，证明没有环
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 快慢指针判断环(2)
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    // 慢指针走了a入环，入环后又走了b，与fast指针相遇，此时fast指针以及走过了a + n(b + c) + b, slow 指针走过了a + b
    // a + n(b + c) + b = 2(a + b) -> a = c + (n - 1)(b + c)， ptr从head开始，slow继续前进，最终会在入环点相遇
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // 如果有环， ptr和slow继续往前必然相遇
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
