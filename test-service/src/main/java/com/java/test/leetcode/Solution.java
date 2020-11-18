package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-07-29 14:06
 */
class Solution {

    public static void main(String[] args) {
//        String a = "wiuefosjd";
//        int length = a.length();
//        for (int i = 0; i <length; i++) {
//            char c = a.charAt(i);
//            Stack<Character> chars = new Stack<Character>();
//            boolean empty = chars.empty();
//            Character peek = chars.peek();
//        }
//
//        int[] nums1 = new int[0];
//        int[] nums2 = new int[]{3};
//        double medianSortedArrays = new Solution().findMedianSortedArrays(nums1, nums2);
//        System.out.println(medianSortedArrays);
//        System.out.println(new Solution().reverse(123134));
    }

    public int reverse(int x) {
        int sign = 1;
        if(x < 0){
            sign = -1;
        }
        int[] nums = new int[11];
        int num;
        int i = 0;
        while ((num = x/10) != 0){
            nums[i++] = x%10;
            x = num;
        }
        long result;
        return 0;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0){
            return findMedianSortedArrays(nums2);
        }
        if(nums2 == null || nums2.length == 0){
            return findMedianSortedArrays(nums1);
        }
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while ((i + j) < ((nums1.length + nums2.length)/2)){
            if(i == nums1.length){
                nums[k] = nums2[j];
                k++;
                j++;
            } else if(j == nums2.length || nums1[i] < nums2[j]){
                nums[k] = nums1[i];
                i++;
                k++;
            }else{
                nums[k] = nums2[j];
                k++;
                j++;
            }
        }

        return findMedianSortedArrays(nums);
    }

    public double findMedianSortedArrays(int[] nums) {
        int length = nums.length;
        int remainder = length % 2;
        if(remainder == 1){
            double v =  nums[length / 2];
            return v;
        }
        int num = nums[length / 2];
        int num1 = nums[length / 2 - 1];
        double v = ((double) (num + num1)) / 2;
        return v;
    }
}