package com.java.test.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/16 2:42 下午
 */
public class FunctionDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(142);
        list.add(125);
        list.add(123);
        list.add(122);
        List<String> foreach = foreach(list, (i) -> String.valueOf(i * i));
        System.out.println(foreach);
    }

    static List<String> foreach(List<Integer> list, Function<Integer, String> function){
        List<String> listNew = new ArrayList<>();
        list.forEach(e -> listNew.add(function.apply(e)));
        return listNew;
    }

}
