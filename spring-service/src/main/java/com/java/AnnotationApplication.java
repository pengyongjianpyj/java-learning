package com.java;

import com.java.spring.config.AppConfig;
import com.java.spring.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApplication {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.test();
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }
}