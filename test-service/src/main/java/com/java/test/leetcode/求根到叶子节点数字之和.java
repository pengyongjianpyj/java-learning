package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-29 11:07
 */
public class 求根到叶子节点数字之和 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode treeNode = new TreeNode(1);
        System.out.println(solution.sumNumbers(treeNode));

        TreeNode treeNode1 = new TreeNode(1);
        treeNode.left = treeNode1;
        System.out.println(solution.sumNumbers(treeNode));
    }


    static class Solution {
        public int sumNumbers(TreeNode root) {
            if(root != null){
                return recursion(root, 0);
            }
            return 0;
        }

        private int recursion(TreeNode node, int num){
            int count = 0;
            num = num * 10 + node.val;
            if(node.right == null && node.left == null){
                count += num;
            }
            if(node.left != null){
                count += recursion(node.left, num);
            }
            if(node.right != null){
                count += recursion(node.right, num);
            }
            return count;
        }
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
