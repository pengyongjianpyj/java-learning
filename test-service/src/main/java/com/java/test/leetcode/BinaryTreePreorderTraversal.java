package com.java.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-27 11:27
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3, new TreeNode(4, new TreeNode(5), new TreeNode(6)), new TreeNode(7));
        List<Integer> integers = new Solution().preorderTraversal(treeNode);
        System.out.println(integers);
    }

    static class Solution {

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> integers = new ArrayList<>();
//            recursion(integers, root);
            return integers;
        }

        public void recursion(List<Integer> integers, TreeNode node){
            if(node != null){
                integers.add(node.val);
                recursion(integers, node.left);
                recursion(integers, node.right);
            }
        }



    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}




