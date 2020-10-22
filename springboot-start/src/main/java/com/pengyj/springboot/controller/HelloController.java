package com.pengyj.springboot.controller;

import com.pengyj.springboot.entity.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-21 15:24
 */
@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @Autowired
    private Man man;

    @GetMapping("/hello")
    public String hello(){
        System.out.println(env.getProperty("man.name"));
        System.out.println(env.getProperty("man.age"));
        System.out.println(env.getProperty("test"));
//        Man man = env.getProperty("man", Man.class);
        if(man != null){
            System.out.println(man.toString());
        }

        return "hello world!";
    }
}
