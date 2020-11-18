package com.pengyj.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-28 17:08
 */
@ConfigurationProperties(prefix = "pengyj.redis")
public class RedisProperties {
    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
