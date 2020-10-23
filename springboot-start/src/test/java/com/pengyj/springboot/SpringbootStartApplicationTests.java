package com.pengyj.springboot;

import com.pengyj.springboot.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootStartApplicationTests {

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoads() {
        System.out.println("SpringbootStartApplicationTests contextLoads");
        helloController.hello();
    }

}
