package com.java.aaa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/1/9 2:04 下午
 */
public class AATest {

    public static void main(String[] args) {
        int[] arr = {8, 10, 2};
        int[] arr2 = {10, 100};
        int[] arr3 = {10, 110};
        int[] arr4 = {0, 0};

        String str = testArrMaxNum(arr);
    }

    private static String testArrMaxNum(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        list.sort((a, b)->{
            if (a == 0) {
                if (b == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (b == 0) {
                    return 1;
                } else {
                    String ab = a + "" + b;
                    String ba = b + "" + a;
                    return ab.compareTo(ba);
                }
            }
        });
        StringBuffer sb = new StringBuffer();
        sb.append(2);

        StringBuilder sbu = new StringBuilder(12);
        sbu.append(12);

        return null;
    }


}