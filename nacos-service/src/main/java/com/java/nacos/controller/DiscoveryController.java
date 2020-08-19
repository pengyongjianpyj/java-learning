package com.java.nacos.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping(value = "/get")
//    @ResponseBody
//    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
//        List<Instance> allInstances = namingService.getAllInstances(serviceName);
//        return allInstances;
//    }


    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str){
        return restTemplate.getForObject("http://provider/echo/" + str, String.class);
    }

}