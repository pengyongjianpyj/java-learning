package com.java;

import com.java.spring.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-08-04 15:32
 */
@Component
public class SpringApplication {
    @Test
    public void test() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("application.xml"));
        TestService bean = beanFactory.getBean(TestService.class);
        bean.test();
    }

}
