package leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // 根据Integer list构建二叉树
    public static TreeNode convertTreeFromList(Integer[] treeNodeValues) {
        TreeNode[] treeNodes = new TreeNode[treeNodeValues.length];
        for (int i = 0; i < treeNodes.length; i++) {
            if (treeNodeValues[i] != null) {
                treeNodes[i] = new TreeNode(treeNodeValues[i]);
            } else {
                treeNodes[i] = null;
            }
        }
        return convertTreeFromList(treeNodes, 0);
    }

    private static TreeNode convertTreeFromList(TreeNode[] treeNodes, int pos) {
        TreeNode root = treeNodes[pos];
        int leftPos = pos * 2 + 1;
        int rightPos = pos * 2 + 2;
        if (leftPos < treeNodes.length) {
            root.left = convertTreeFromList(treeNodes, leftPos);
        }
        if (rightPos < treeNodes.length) {
            root.right = convertTreeFromList(treeNodes, rightPos);
        }
        return root;
    }

    public static void convertTreeToList(TreeNode treeNode, List<Integer> treeValList) {
        if (treeNode == null) {
            return;
        }
        treeValList.add(treeNode.val);
        convertTreeToList(treeNode.left, treeValList);
        convertTreeToList(treeNode.right, treeValList);
    }
}
