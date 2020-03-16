package com.java.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yeauty.pojo.Session;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
@RequestMapping
public class WebSocketApplication {

    public static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

    @RequestMapping("/")
    public String hello() throws Exception {
        return "hello world!";
    }
    @RequestMapping("/id/{id}")
    public String sendMsg(@PathVariable String id) throws Exception {
        Session session = map.get(id);
        session.sendText(id);
        return id;
    }
}
