package com.java.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.java.*")
@EnableAspectJAutoProxy
public class AppConfig {

}
