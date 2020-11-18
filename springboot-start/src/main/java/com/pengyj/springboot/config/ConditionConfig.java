package com.pengyj.springboot.config;

import com.pengyj.springboot.condition.ConditionTest;
import com.pengyj.springboot.entity.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-26 11:00
 */
@Configuration
public class ConditionConfig {

    @Bean
//    @ConditionalOnClass
    @Conditional(ConditionTest.class)
    public People people(){
        return new People();
    }
}
