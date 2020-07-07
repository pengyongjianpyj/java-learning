package com.java.test.learn;


public class StringTest {

    private static int x = 1;
    public static void main(String[] args) {
        string();
        stringBuilder();
        stringBuffer();
        StringTest stringTest = new StringTest();
        stringTest.x++;
        StringTest.x++;
    }

    private static void stringBuffer() {
        StringBuffer stringBuffer = new StringBuffer();

    }

    private static void stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
    }

    private static void string() {
        String s = new String();
    }
}
