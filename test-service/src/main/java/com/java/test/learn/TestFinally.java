package com.java.test.learn;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/5/28 4:18 下午
 */
public class TestFinally {

  public static void main(String[] args) {
    testFinally();
  }

  private static void testFinally() {
    try {
      for (int i = 0; i < 5; i++) {
        System.out.println("for" + i);
      }
      return;
    } finally {
      System.out.println("finally");
    }

  }
}
