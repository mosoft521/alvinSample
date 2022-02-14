package com.gmail.mosoft521.leetcode;

public class Solution {
    // 定义：将以 root 为根的树拉平为链表
    public static void flatten(TreeNode root) {
        // base case
        if (root == null) return;
        // 先递归拉平左右子树
        flatten(root.left);
        flatten(root.right);

        //这个简单化了
//        root.left.right = root.right;
//        root.right = root.left;
//        root.left = null;
        //这个简单化了

        /****后序遍历位置****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public static void main(String[] args) {
        //构造demo中的树
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        root.left = left;
        TreeNode right = new TreeNode(7);
        root.right = right;
        TreeNode left1 = new TreeNode(1);
        left.left = left1;
        TreeNode right1 = new TreeNode(3);
        left.right = right1;
        TreeNode left2 = new TreeNode(6);
        right.left = left2;
        TreeNode right2 = new TreeNode(9);
        right.right = right2;

        //调用函数
        flatten(root);
        System.out.println("over");
    }
}

