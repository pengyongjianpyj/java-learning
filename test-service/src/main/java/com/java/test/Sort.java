package com.java.test;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr = bubble(new int[]{1,34,12,3,12,623,5,64,23,21});
        System.err.println(Arrays.toString(arr));
    }

    private static int[] bubble(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
