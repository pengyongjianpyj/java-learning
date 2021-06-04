package com.java.aaa;

import java.util.Arrays;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/1/9 2:04 下午
 */
public class AATest {

    public static void main(String[] args) {
        Integer[] arr1 = {8, 10, 2};
        Integer[] arr2 = {10, 100};
        Integer[] arr3 = {10, 110};
        Integer[] arr4 = {0, 10};

        System.out.println(testArrMaxNum(arr1));
        System.out.println(testArrMaxNum(arr2));
        System.out.println(testArrMaxNum(arr3));
        System.out.println(testArrMaxNum(arr4));
    }

    private static String testArrMaxNum(Integer[] arr) {
        Arrays.sort(arr, (a, b)->{
            String ab = a + "" + b;
            String ba = b + "" + a;
            return ba.compareTo(ab);
        });
        StringBuilder str = new StringBuilder();
        for (Integer integer : arr) {
            str.append(integer);
        }
        return str.toString();
    }


}
