package com.java.spring.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-09-01 15:44
 */
@Aspect
@Component
public class Logger {

    @Before("execution(* com.java.spring.service.*.*(..))")
    public void recordBefore(){
        System.out.println("recordBefore");
    }

    @After("execution(* com.java.spring.service.*.*(..))")
    public void recordAfter(){
        System.out.println("recordAfter");
    }

}