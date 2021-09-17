package leetcode.tree;

import java.util.*;

public class TreeBase {
//    public List<Integer> preorderTraversal(TreeNode root) {
//
//    }
//
//    public List<Integer> inorderTraversal(TreeNode root) {
//
//    }
//
//    public List<Integer> postorderTraversal(TreeNode root) {
//
//    }

    /**
     * [3,9,20,null,null,15,7]
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> sub = new ArrayList<>();
            int n = queue.size();
            while (n-- > 0) {
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }
                sub.add(queue.poll().val);
            }
            res.add(sub);
        }
        return res;
    }

    public static TreeNode genTreeFromLevelOrderArray(Integer[] levelOrderList) {
        TreeNode p = new TreeNode(levelOrderList[0]);
        TreeNode head = p;
        Queue<TreeNode> nodes = new LinkedList<>();
        int i = 0, n = levelOrderList.length;
        while (p != null) {
            if (2 * i + 1 < n) {
                Integer val = levelOrderList[2 * i + 1];
                p.left = val == null ? null : new TreeNode(val);
                nodes.add(p.left);
            }
            if (2 * i + 2 < n) {
                Integer val = levelOrderList[2 * i + 2];
                p.right = val == null ? null : new TreeNode(val);
                nodes.add(p.right);
            }
            i++;
            p = nodes.poll();
        }
        return head;
    }

    public static void main(String[] args) {
        // 层序遍历
        TreeNode head = genTreeFromLevelOrderArray(new Integer[]{3,9,20,null,null,15,7});
        List<List<Integer>> levelOrderRes = levelOrder(head);
        System.out.printf(Arrays.toString(levelOrderRes.toArray()));
        // 前序遍历

        // 中序遍历

        // 后续遍历
    }
}
