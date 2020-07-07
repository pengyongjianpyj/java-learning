package com.java;

import com.java.spring.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        TestService testService = (TestService) applicationContext.getBean("TestService");
        testService.test();
        int i = 1;
    }
}
