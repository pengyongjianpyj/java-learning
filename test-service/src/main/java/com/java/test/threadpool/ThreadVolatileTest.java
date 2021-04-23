package com.java.test.threadpool;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/1/3 下午9:09
 */
public class ThreadVolatileTest extends Thread {
    private volatile static boolean flag = true;

    @Override
    public void run() {
        while (flag) {

        }
        System.out.println("finish");
    }
    public static void main(String[] args) throws Exception {
        new ThreadVolatileTest().start();
        Thread.sleep(2000);
        flag = false;
    }
}