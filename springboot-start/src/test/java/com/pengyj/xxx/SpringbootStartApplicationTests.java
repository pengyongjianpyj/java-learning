package com.pengyj.xxx;

import com.pengyj.springboot.SpringbootStartApplication;
import com.pengyj.springboot.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootStartApplication.class)
@RunWith(SpringRunner.class)
class SpringbootStartApplicationTests {

    @Autowired
    private HelloController helloController;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println("SpringbootStartApplicationTests contextLoads");
        helloController.hello();
    }

    @Test
    void setRedisValue(){
        redisTemplate.opsForValue().set("key2", "value2");
    }
    @Test
    void getRedisValue(){
        Object key2 = redisTemplate.opsForValue().get("key2");
        System.out.println(key2);
    }


}
