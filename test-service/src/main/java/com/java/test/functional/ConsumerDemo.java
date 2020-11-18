package com.java.test.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/16 2:42 下午
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(142);
        list.add(125);
        list.add(123);
        list.add(122);
        foreach(list, (i) -> System.out.println(i*i));
    }

    static void foreach(List<Integer> list, Consumer<Integer> consumer){
        list.forEach(e -> consumer.accept(e));
    }

}
