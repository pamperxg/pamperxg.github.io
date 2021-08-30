package leetcode.linkeList;

import java.util.PrimitiveIterator;
import java.util.PriorityQueue;

public class MergeLists {
    // 合并两个升序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 任意链表为空，返回另一个链表
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        // 虚拟头节点， 遍历节点， 链表指针
        ListNode head = new ListNode(0);
        ListNode cur = head, aPtr = l1, bPtr = l2;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val > bPtr.val) {
                cur.next = bPtr;
                bPtr = bPtr.next;
            } else {
                cur.next = aPtr;
                aPtr = aPtr.next;
            }
            cur = cur.next;
        }
        cur.next = aPtr == null ? bPtr : aPtr;
        return head.next;
    }

    // 合并k个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    static class PreNode implements Comparable<PreNode> {
        int val;
        ListNode listNode;

        public PreNode(int val, ListNode listNode) {
            this.val = val;
            this.listNode = listNode;
        }

        @Override
        public int compareTo(PreNode o) {
            return this.val - o.val;
        }
    }

    PriorityQueue<PreNode> preNodePriorityQueue = new PriorityQueue<>();

    public ListNode mergeKLists1(ListNode[] listNodes) {
        for (ListNode listNode : listNodes) {
            if (listNode != null) {
                preNodePriorityQueue.offer(new PreNode(listNode.val, listNode));
            }
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (!preNodePriorityQueue.isEmpty()) {
            PreNode preNode = preNodePriorityQueue.poll();
            cur.next = preNode.listNode;
            cur = cur.next;
            if (preNode.listNode.next != null) {
                int nextVal = preNode.listNode.next.val;
                ListNode nextNode = preNode.listNode.next;
                preNodePriorityQueue.offer(new PreNode(nextVal, nextNode));
            }
        }
        return head.next;
    }
}
