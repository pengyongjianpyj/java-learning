package com.java.nacos.interceptor;

import com.java.filter.ThreadLocalUserDTOUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-20 09:46
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String str = (String) ThreadLocalUserDTOUtil.get(String.class);
        if(str != null){
            try {
                str = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            requestTemplate.query("userDTO", str);
        }
    }
}
