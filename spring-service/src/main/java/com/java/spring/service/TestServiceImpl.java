package com.java.spring.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    public void test() {
        System.out.println("TestServiceImpl.test()");
    }
}
