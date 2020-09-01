package com.java.nacos.cotroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.filter.ThreadLocalUserDTOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

    @GetMapping(value = "/echo/{string}")
    public String echoProvider(@PathVariable String string) {
        JSONObject obj = (JSONObject) ThreadLocalUserDTOUtil.get(JSONObject.class);
        return "Hello Nacos Discovery " + string + JSON.toJSONString(obj);
    }

}