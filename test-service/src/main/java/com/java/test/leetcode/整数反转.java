package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-13 14:02
 */
public class 整数反转 {
    public static void main(String[] args) {



        int reverse = new 整数反转().reverse(-1234231);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        long xx = x;
        StringBuffer stringBuffer = new StringBuffer();
        if(xx<0){
            stringBuffer.append("-");
            xx = 0-xx;
        }
        String s = String.valueOf(xx);
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(chars[length - i - 1]);
        }
        Long aLong = Long.valueOf(stringBuffer.toString());
        if(aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE){
            return 0;
        }
        return aLong.intValue();
    }
}
