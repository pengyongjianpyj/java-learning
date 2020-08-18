package com.java;

import com.java.spring.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        TestService testService = (TestService) applicationContext.getBean("TestService");
        testService.test();
        int i = 1;
        ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
        threadLocal.set("1234");
        Object o = threadLocal.get();
    }
}
