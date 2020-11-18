package com.java.test.functional;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/16 1:52 下午
 */

@FunctionalInterface
public interface FunctionalInterfaceDemo {

    void c();

    default void a(){
        System.out.println("a");
    }
    default void b(){
        System.out.println("b");
    }
}
