package com.java.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-20 09:52
 */
@Configuration
public class ConfigUserDTOHandlerFilter {

    @Bean
    public UserDTOHandlerFilter getUserDTOHandlerFilter() {
        return new UserDTOHandlerFilter();
    }
}
