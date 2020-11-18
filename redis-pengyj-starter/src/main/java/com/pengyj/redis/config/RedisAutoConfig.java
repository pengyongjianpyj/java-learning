package com.pengyj.redis.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-28 17:04
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfig {

    @Bean
    public Jedis jedis(RedisProperties redisProperties){
        String host = redisProperties.getHost();
        Integer port = redisProperties.getPort();
        return new Jedis(host, port);
    }

}
