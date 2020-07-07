package com.java;

import com.java.spring.config.AppConfig;
import com.java.spring.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.test();
        int i = 1;
    }
}
