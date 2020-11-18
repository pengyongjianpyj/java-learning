package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-29 15:02
 */
public class 字符串转换整数 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi(" 32 mfdreknmt,n"));
        System.out.println(solution.myAtoi(" -234r32 mfdreknmt,n"));
        System.out.println(solution.myAtoi(" 1332 mfdreknmt,n"));
        System.out.println(solution.myAtoi(" -234233345643523232323232323233r32 mfdreknmt,n"));
        System.out.println(solution.myAtoi(" 132333334555567686543245678654332 mfdreknmt,n"));
    }

    static class Solution {
        public int myAtoi(String s) {
            if (s == null || "".equals(s)) {
                return 0;
            }
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                if (' ' == c || c == '0') {
                    continue;
                } else if ('-' == c) {
                    if (++i == length) {
                        break;
                    }
                    return reverse(s, i, false);
                } else if ('+' == c) {
                    if (++i == length) {
                        break;
                    }
                    return reverse(s, i, true);
                } else if (checkNum(c)) {
                    return reverse(s, i, true);
                } else {
                    break;
                }
            }
            return 0;
        }

        private int reverse(String s, int i, boolean b) {
            StringBuilder numStr = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (checkNum(c)) {
                    if(c == '0' && numStr.length() == 0){
                        continue;
                    }
                    numStr.append(c);
                } else {
                    break;
                }
            }

            if (numStr.length() > 0) {
                if(numStr.length() > 11){
                    if(b){
                        return Integer.MAX_VALUE;
                    }else{
                        return Integer.MIN_VALUE;
                    }
                }

                Long along = Long.valueOf(numStr.toString());
                if (b) {
                    if (along > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    return along.intValue();
                } else {
                    if ((0 - along) < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                    return (int) (0 - along);
                }
            }
            return 0;
        }

        private boolean checkNum(char c) {
            if (c <= 57 && c >= 48) {
                return true;
            }
            return false;
        }
    }

}
