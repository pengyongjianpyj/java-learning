package com.java.test.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/1/4 上午9:48
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put(1,1);
        Object o = map.get(1);
    }
}
