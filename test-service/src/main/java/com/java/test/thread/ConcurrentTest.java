package com.java.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConcurrentTest
 *
 * @author pengyongjian
 * @date 2021/10/28
 */
public class ConcurrentTest {
  private static AtomicInteger num = new AtomicInteger(0);

  public ConcurrentTest() {
    System.out.println(num.getAndIncrement());;
//    System.out.println(num.incrementAndGet());;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new ConcurrentTest();
    }
    System.out.println(ConcurrentTest.num);
  }

}
