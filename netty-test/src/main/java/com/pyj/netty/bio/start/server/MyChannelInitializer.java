package com.pyj.netty.bio.start.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author pengyongjian
 * @Description: ChannelHandler的初始化
 * @date 2020/12/17 下午3:01
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new MyChannelHandler());
    }
}
