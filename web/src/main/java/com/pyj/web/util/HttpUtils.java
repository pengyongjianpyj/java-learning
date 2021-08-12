package com.pyj.web.util;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.CollectionUtils;

public abstract class HttpUtils {

  private static final String DEFAULT_CHARSET = "UTF-8";

  /**
   * HttpGet请求.
   */
  public static ResponseObject doGet(String getUrl, Map<String, String> headers)
      throws IOException {
    HttpGet httpGet = new HttpGet(getUrl);
    if (headers != null && !headers.isEmpty()) {
      headers.forEach(httpGet::addHeader);
    }
    return executeHttp(HttpClients.createDefault(), httpGet);
  }

  /**
   * HttpGet请求.
   */
  public static ResponseObject doHttpGet(String getUrl) throws IOException {
    return executeHttp(HttpClients.createDefault(), new HttpGet(getUrl));
  }

  /**
   * get请求.
   */
  public static ResponseObject doGet(String getUrl) throws Exception {
    if (getUrl.startsWith("https")) {
        return doHttpsGet(getUrl);
    }
    return executeHttp(HttpClients.createDefault(), new HttpGet(getUrl));
  }

  /**
   * HttpsGet请求.
   */
  public static ResponseObject doHttpsGet(String getUrl) throws Exception {
    return executeHttp(new SSLClient(), new HttpGet(getUrl));
  }

  /**
   * HttpGet请求.
   */
  public static String doHttpGet(String httpUrl, Map<String, String> requestProperties)
      throws IOException {
    StringBuilder sb = new StringBuilder();
    URL url = new URL(httpUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    if (requestProperties != null && requestProperties.size() > 0) {
      for (Entry<String, String> entry : requestProperties.entrySet()) {
        connection.setRequestProperty(entry.getKey(), entry.getValue());
      }
    }
    connection.connect();
    InputStream is = connection.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(is, DEFAULT_CHARSET));
    String strRead;
    while ((strRead = reader.readLine()) != null) {
      sb.append(strRead);
      sb.append("\r\n");
    }
    reader.close();
    return sb.toString();
  }

  /**
   * HttpPost请求.
   */
  public static ResponseObject doHttpPost(String url, Map<String, String> params)
      throws IOException {
    return post(HttpClients.createDefault(), url, params, null);
  }

  /**
   * do http post.
   */
  public static ResponseObject doHttpPost(String url, Map<String, String> params,
                                          Map<String, String> headers) throws IOException {
    return post(HttpClients.createDefault(), url, params, headers);
  }

  public static ResponseObject doHttpPost(String url, String json) throws IOException {
    return post(HttpClients.createDefault(), url, json, null);
  }

  public static ResponseObject doHttpPost(String url, String json, Map<String, String> headers)
      throws IOException {
    return post(HttpClients.createDefault(), url, json, headers);
  }

  /**
   * HttpsPost请求
   */
  public static ResponseObject doHttpsPost(String url, Map<String, String> params)
      throws Exception {
    return post(new SSLClient(), url, params, null);
  }

  public static ResponseObject doHttpsPost(String url, Map<String, String> params,
                                           Map<String, String> headers) throws Exception {
    return post(new SSLClient(), url, params, headers);
  }

  public static ResponseObject doHttpsPost(String url, String json) throws Exception {
    return post(new SSLClient(), url, json, null);
  }

  public static ResponseObject doHttpsPost(String url, String json, Map<String, String> headers)
      throws Exception {
    return post(new SSLClient(), url, json, headers);
  }

  private static ResponseObject post(CloseableHttpClient httpClient, String url,
                                     Map<String, String> params, Map<String, String> headers)
      throws IOException {
    if (httpClient == null) {
      return null;
    }

    // 创建HttpPost对象
    HttpPost httpPost = new HttpPost(url);
    String contentType = null;
    if (headers != null && !headers.isEmpty()) {
      contentType = headers.get("Content-Type");
      headers.forEach(httpPost::addHeader);
    }
    if (contentType != null && contentType.contains("application/json")) {
      httpPost.setEntity(new StringEntity(JSONObject.toJSONString(params), DEFAULT_CHARSET));
    } else {
      List<NameValuePair> nameValuePairs = new ArrayList<>();
      if (!CollectionUtils.isEmpty(params)) {
        for (Entry<String, String> entry : params.entrySet()) {
          nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
      }
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
    }

    return executeHttp(httpClient, httpPost);
  }

  private static ResponseObject post(CloseableHttpClient httpClient, String url, String json,
                                     Map<String, String> headers) throws IOException {
    if (httpClient == null) {
      return null;
    }

    // 创建HttpPost对象
    HttpPost httpPost = new HttpPost(url);
    httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    if (headers != null && !headers.isEmpty()) {
      headers.forEach(httpPost::addHeader);
    }
    httpPost.setEntity(new StringEntity(json, DEFAULT_CHARSET));

    return executeHttp(httpClient, httpPost);
  }

  private static ResponseObject executeHttp(CloseableHttpClient httpClient, HttpUriRequest request)
      throws IOException {
    ResponseObject result = new ResponseObject();
    HttpResponse httpResponse = httpClient.execute(request);
    result.setCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
    HttpEntity httpEntity = httpResponse.getEntity();
    if (httpEntity != null) {
      result.setMsg(inputStream2String(httpEntity.getContent()));
    }
    httpClient.close();
    return result;
  }

  private static String inputStream2String(InputStream inputStream) throws IOException {
    StringBuilder result = new StringBuilder();
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    while ((line = reader.readLine()) != null) {
      result.append(line).append("\n");
    }
    return new String(result.toString().getBytes(StandardCharsets.UTF_8));
  }

  /**
   * 获取客户端ip地址
   */
  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (!(ip == null || "".equals(ip.trim())) && !"unKnown".equalsIgnoreCase(ip)) {
      // 多次反向代理后会有多个ip值，第一个ip才是真实ip
      int index = ip.indexOf(",");
      if (index != -1) {
        return ip.substring(0, index);
      } else {
        return ip;
      }
    }
    ip = request.getHeader("X-Real-IP");
    if (!(ip == null || "".equals(ip.trim())) && !"unKnown".equalsIgnoreCase(ip)) {
      return ip;
    }
    return request.getRemoteAddr();
  }

  /**
   * 用于进行Https请求的HttpClient
   */
  @SuppressWarnings("deprecation")
  private static class SSLClient extends DefaultHttpClient {
    SSLClient() throws Exception {
      super();
      SSLContext ctx = SSLContext.getInstance("TLS");
      X509TrustManager tm = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }
      };
      ctx.init(null, new TrustManager[] {tm}, null);
      SSLSocketFactory ssf =
          new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      ClientConnectionManager ccm = this.getConnectionManager();
      SchemeRegistry sr = ccm.getSchemeRegistry();
      sr.register(new Scheme("https", 443, ssf));
    }
  }
}
