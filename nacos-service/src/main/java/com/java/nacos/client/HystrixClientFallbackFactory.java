package com.java.nacos.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-21 14:10
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable) {

        return null;
    }
}
