package leetcode.linkeList;

// 2 -> 4 -> 3
// 5 -> 6 -> 4
// => 7 -> 0 -> 8
public class AddTwoNum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 存下头节点
        ListNode dummyNode = new ListNode(-1);
        ListNode p = l1, q = l2, curr = dummyNode;
        // 每位数字相加保存进位
        int carry = 0;
        while (p != null || q != null) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;
            int val = x + y + carry;
            carry = val / 10; // 进位除数
            curr.next = new ListNode(val  % 10);  // 值余数
            // 指针同时向前移动
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        // 最终检查进位是否为0
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return dummyNode.next;
    }
}
