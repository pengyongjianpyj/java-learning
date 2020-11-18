package com.java.test.leetcode;

import java.util.*;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-28 11:52
 */
public class 独一无二的出现次数 {

    public static void main(String[] args) {
        int[] arr = {1,2,4234,345,2,1,1,345,2,2};
        boolean b = new Solution().uniqueOccurrences(arr);
        System.out.println(b);
    }

    static class Solution {
        public boolean uniqueOccurrences(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                Integer integer = map.get(arr[i]);
                if(integer != null){
                    map.put(arr[i], ++integer);
                } else {
                    map.put(arr[i], 1);
                }
            }
            Set set = new HashSet<Integer>();
            Set<Integer> keySet = map.keySet();
            for (Integer integer : keySet) {
                if(!set.add(map.get(integer))){
                    return false;
                }

            }
//            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                if(!set.add(entry.getValue())){
//                    return false;
//                }
//            }
//            Collection<Integer> values = map.values();
//            Set set = new HashSet<Integer>();
//            for (Integer value : values) {
//                if(!set.add(value)){
//                    return false;
//                }
//            }
            return true;
        }
    }
}
