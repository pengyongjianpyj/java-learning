package com.java.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
@RefreshScope
public class ConfigController {

    @NacosValue(value = "${aaa:default}", autoRefreshed = true)
    private String aaa;


    @GetMapping(value = "/get")
    @ResponseBody
    public String get() {
        return aaa;
    }
}
