package com.java.base.utils;

/**
 * @author pengyongjian
 * @Description:
 * @date 2019-12-30 15:17
 */
public class StringUtils {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int i = 100;
                assert i <  1;
                System.out.println("assert i < 1;");
            }
        });
        thread.start();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }

    }
}
