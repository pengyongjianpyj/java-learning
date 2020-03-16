package com.java.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-03-16 14:07
 */
@Component
@ServerEndpoint(port = 8888, path = "/websocket")
public class MyWebSocket {
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) throws IOException {
        WebSocketApplication.map.put(session.id().toString(), session);
        System.out.println("new connection:" + session.id());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        WebSocketApplication.map.remove(session.id().toString());
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        System.out.println(message + session.channel().remoteAddress());
        session.sendText("Hello Netty!" + session.id());
    }
}
