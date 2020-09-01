package com.java.nacos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 应用对外提供客户端
 *
 * @author: zf
 * @create: 2019-12-28
 */
//@FeignClient(value = "provider", fallbackFactory = HystrixClientFallbackFactory.class)
@FeignClient(value = "provider", fallback = )
public interface ClientService {

    @GetMapping(value = "/echo/{string}")
    String echoProvider(@PathVariable String string);
}
