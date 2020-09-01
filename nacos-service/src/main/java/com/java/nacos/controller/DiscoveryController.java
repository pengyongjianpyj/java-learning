package com.java.nacos.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.java.filter.ThreadLocalUserDTOUtil;
import com.java.nacos.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RefreshScope
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @NacosInjected
    private ConfigService configService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/getNamingService")
    @ResponseBody
    public List<Instance> getNamingService(@RequestParam String serviceName) throws NacosException {
        List<Instance> allInstances = namingService.getAllInstances(serviceName);
        return allInstances;
    }
    @GetMapping(value = "/getConfigService")
    @ResponseBody
    public String getConfigService(@RequestParam String serviceName) throws NacosException {
        String serverStatus = configService.getServerStatus();
        return serverStatus;
    }


    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str){
        ThreadLocalUserDTOUtil.set("{\"phone\":\"15373213730\",\"name\":\"彭永建\",\"id\":715603553934405632,\"account\":\"\",\"register\":false}");
        String s = clientService.echoProvider(str);
        return s;
//        return restTemplate.getForObject("http://provider/echo/" + str, String.class);
    }

}