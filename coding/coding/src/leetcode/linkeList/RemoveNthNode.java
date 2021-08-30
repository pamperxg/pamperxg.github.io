package leetcode.linkeList;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveNthNode {
    // 删除第N个节点， 栈
    public ListNode removeNthNode(ListNode node, int n) {
        ListNode dummy = new ListNode(0, node);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; i++) {
            stack.poll();
        }

        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return dummy.next;
    }

    // 删除N个节点，双指针  first节点先向前移动n， first、second再一起移动到末尾，删除second所在节点
    public ListNode removeNthNode1(ListNode node, int n) {
        ListNode dummyNode = new ListNode(node.val, node);
        ListNode first = node, second = dummyNode;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyNode.next;
    }
}
