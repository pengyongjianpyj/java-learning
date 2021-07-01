package com.java.test.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/6/30 1:49 下午
 */
public class MainTest {


  public static void main(String[] args) throws Exception {
    long t0 = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      testhttpimport();
    }
    long t1 = System.currentTimeMillis();

    System.err.println("时间===》");
    System.err.println(t1-t0);
  }

  private static void testhttpimport() throws Exception {
//    String url = "http://localhost:8080/info/base/save/batch";
    String url = "https://dev-marketing-api.tctm.life/info/base/save/batch";
    String httpRes = null;
    String localFileName = "/Users/pengyongjian/Downloads/批量上传线索模板-到CRM系统中心.xls";

    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
      // 创建httpget.
      HttpPost httppost = new HttpPost(url);

      Map<String, String> headers = new HashMap<>();
      headers.put("Authorization",
          "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IldlZCBKdW4gMzAgMTE6MTc6MDQgQ1NUIDIwMjEiLCJ1c2VybmFtZSI6InBlbmd5ajEifQ.PleZKPxIQaXfd4puVa9kJRjP0Xz-FZgtrBqvQSeOMeM");

      if (headers != null && !headers.isEmpty()) {
        headers.forEach(httppost::addHeader);
      }

      //setConnectTimeout：设置连接超时时间，单位毫秒。setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
      RequestConfig defaultRequestConfig =
          RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(15000)
              .build();
      httppost.setConfig(defaultRequestConfig);

      System.out.println("executing request " + httppost.getURI());

      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      builder.setCharset(Charset.forName("utf-8"));
      //加上此行代码解决返回中文乱码问题
      builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
      builder.addBinaryBody("multipartFile", new FileInputStream(new File(localFileName)),
          ContentType.MULTIPART_FORM_DATA, "批量上传线索模板-到CRM系统中心 (1).xls");
      // 类似浏览器表单提交，对应input的name和value
      builder.addTextBody("trackUserCode", "pengyj1");
      builder.addTextBody("orgCoreCode", "110018");
      builder.addTextBody("createOrgCoreCode", "110018");
      builder.addTextBody("trackOrgCoreCode", "110018");
      builder.addTextBody("fileName", "批量上传线索模板-到CRM系统中心.xls");
      builder.addTextBody("modelType", "1");
      builder.addTextBody("checkDailyNum", "1");
      HttpEntity reqEntity = builder.build();
      httppost.setEntity(reqEntity);

      // 执行post请求.
      CloseableHttpResponse response = httpclient.execute(httppost);

      System.out.println("got response");

      try {
        // 获取响应实体
        HttpEntity entity = response.getEntity();
        //System.out.println("--------------------------------------");
        // 打印响应状态
        //System.out.println(response.getStatusLine());
        if (entity != null) {
          String res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
          System.out.println(res);
        }
        //System.out.println("------------------------------------");
      } finally {
        response.close();

      }
    } finally {
      // 关闭连接,释放资源
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
