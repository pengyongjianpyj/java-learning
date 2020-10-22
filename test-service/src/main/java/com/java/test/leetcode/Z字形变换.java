package com.java.test.leetcode;

import java.util.LinkedList;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-13 11:22
 */
public class Z字形变换 {
    public static void main(String[] args) {
        String convert = new Z字形变换().convert("PAYPALISHIRING", 4);
        System.out.println(convert);
        System.out.println("LDREOEIIECIHNTSG");
        LinkedList<Long> longs = new LinkedList<>();
    }

    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || s.length() < numRows || numRows < 2){
            return s;
        }

        StringBuffer[] arr = new StringBuffer[numRows];

        int index = 0;
        boolean isDown = true;

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if(arr[index] == null){
                arr[index] = new StringBuffer();
            }
            arr[index].append(ch);
            if(isDown){
                if(index == numRows-1){
                    index--;
                    isDown = false;
                } else {
                    index++;
                }
            } else {
                if(index == 0){
                    index++;
                    isDown = true;
                } else {
                    index--;
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString());
        }
        return sb.toString();
    }
}


