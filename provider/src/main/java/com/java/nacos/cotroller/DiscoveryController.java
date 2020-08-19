package com.java.nacos.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

    @GetMapping(value = "/echo/{string}")
    public String echoProvider(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

}