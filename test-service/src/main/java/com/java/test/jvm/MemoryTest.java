package com.java.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * MemoryTest
 *
 * @author pengyongjian
 * @date 2021/11/2
 */
public class MemoryTest {

  public static void main(String[] args) throws InterruptedException {
    List<List> lists = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < 1000000; j++) {
        list.add(new Integer(j));
      }
      lists.add(list);
      System.out.println(i);
      Thread.sleep(1000);
    }
    System.out.println("开始gc");
    System.gc();
    while (true) {
      Thread.sleep(2000);
      System.out.println("----");
    }
  }

}
