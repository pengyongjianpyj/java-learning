package com.java.test.threadpool;

import java.util.concurrent.locks.LockSupport;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/30 下午3:28
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception {
//        test1();
        final byte[] lock = new byte[0];
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {

            }
        });
        t1.start();
        t2.start();
        Thread.sleep(100);
        System.out.println(t1.getState());
        System.out.println(t2.getState());

    }

    private static void test1() throws InterruptedException {
        Thread thread = new Thread(()->{
            LockSupport.park();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        System.out.println(thread.getState());

        thread.start();

        Thread.sleep(1000);

        System.out.println(thread.getState());

        LockSupport.unpark(thread);

        Thread.sleep(100);

        System.out.println(thread.getState());

        Thread.sleep(1000);

        System.out.println(thread.getState());
    }
}
