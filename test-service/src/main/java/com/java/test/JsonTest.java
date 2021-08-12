package com.java.test;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/7/16 5:49 下午
 */
public class JsonTest {

  @Data
  static class A{
    private String aa = "aa";
    private String bb = "bb";
    private String dd = "dd";
  }
  @Data
  static class B{
    private String aa;
    private String bb;
    private String cc;
  }

  public static void main(String[] args) {
    A a = new A();
    String as = JSONObject.toJSONString(a);
    B b = JSONObject.parseObject(as, B.class);
    System.out.println(JSONObject.toJSONString(b));

  }

}
