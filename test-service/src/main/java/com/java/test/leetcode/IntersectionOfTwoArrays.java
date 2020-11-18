package com.java.test.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-11-02 09:19
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = {123,21};
        int[] nums2 = {123,2121};
        int[] intersection = new Solution().intersection(nums1, nums2);
        for (int i = 0; i < intersection.length; i++) {
            System.out.print(intersection[i] + ",");
        }
    }
    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0){
                return new int[0];
            }
            Set<Integer> set1 = convertSet(nums1);
            Set<Integer> set2 = convertSet(nums2);
            Set<Integer> set = new HashSet<>();
            for (Integer integer : set1) {
                if(!set2.add(integer)){
                    set.add(integer);
                }
            }
            int[] ints = new int[set.size()];
            int i = 0;
            for (Integer integer : set) {
                ints[i++] = integer;
            }
            return ints;
        }

        private Set<Integer> convertSet(int[] nums){
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }
            return set;
        }
    }
}
