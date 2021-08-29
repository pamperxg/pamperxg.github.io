package leetcode.tree;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeBase {
    /**
     * [3,9,20,null,null,15,7]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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

    public TreeNode genTreeFromArray(Integer[] levelOrderList) {
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
}
