package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class MeduimOrderTraversal {

    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList();

        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(Integer.parseInt(null));
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        list.add(t1);
//        list.add(t2);
        list.add(t3);
        list.add(t4);

        for (int i = 0; i < list.size(); i++) {
            inorderTraversal(list.get(i));
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> treeList = new ArrayList<>();
        inOrder(root, treeList);

        for (int i = 0; i < treeList.size(); i++) {
            System.out.println(treeList.get(i));

        }
        return treeList;
    }

    public static void inOrder(TreeNode node, List<Integer> treeList) {
        if(node == null) {
            return;
        }
        inOrder(node.left, treeList);
        treeList.add(node.val);
        inOrder(node.right, treeList);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {this.val = x;}
}
