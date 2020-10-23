package com.pengyj.springbootmybatis;

import com.pengyj.springbootmybatis.domain.User;
import com.pengyj.springbootmybatis.domain.mapper.UserMapper;
import com.pengyj.springbootmybatis.domain.mapper.UserXmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserXmlMapper userXmlMapper;

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

    @Test
    void findAllUser() {
        List<User> all = userMapper.findAll();
        System.out.println(all);
    }
    @Test
    void findAllUserXml() {
        List<User> all = userXmlMapper.findAllUserXml();
        System.out.println(all);
    }


}
