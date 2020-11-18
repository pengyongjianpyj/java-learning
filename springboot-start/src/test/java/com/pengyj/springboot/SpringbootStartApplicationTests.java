package com.pengyj.springboot;

import com.pengyj.springboot.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootStartApplicationTests implements ApplicationContextAware {

    @Autowired
    private HelloController helloController;

    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println("SpringbootStartApplicationTests contextLoads");
        helloController.hello();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void condition() {
        Object bean = applicationContext.getBean("people");
        if(bean == null){
            System.out.println("null");
        } else {
            System.out.println("bean");
        }
    }
    @Test
    public void redisStarter() {
        Jedis jedis = applicationContext.getBean(Jedis.class);
        if(jedis == null){
            System.out.println("null");
        } else {
            String s = jedis.get("key");
            System.out.println(s);

            System.out.println("bean");
        }
    }
}
