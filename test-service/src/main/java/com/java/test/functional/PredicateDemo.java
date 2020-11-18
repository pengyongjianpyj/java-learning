package com.java.test.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/16 2:42 下午
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(142);
        list.add(125);
        list.add(123);
        list.add(122);
        List<Integer> foreach = foreach(list, (i) -> i % 2 == 1);
        System.out.println(foreach);
    }

    static List<Integer> foreach(List<Integer> list, Predicate<Integer> predicate){
        List<Integer> listNew = new ArrayList<>();

        list.forEach(e -> {
            if(predicate.test(e)) listNew.add(e);
        });
        return listNew;
    }

}
