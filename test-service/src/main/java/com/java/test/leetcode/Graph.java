package com.java.test.leetcode;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/2/21 5:26 下午
 */
public class Graph {
  private int v;
  private LinkedList<Integer>[] adj;

  public static void main(String[] args) {
    Pattern compile = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[1,8,9]))\\d{8}$");
    boolean matches = compile.matcher("15373213732").matches();
    System.out.println(matches);
  }



}
