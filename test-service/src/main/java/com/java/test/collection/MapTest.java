package com.java.test.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/6/2 11:54 上午
 */
public class MapTest {

  public static void main(String[] args) {
    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    String put = map.put("1", "1");
    String s = map.get("1");
  }
}
