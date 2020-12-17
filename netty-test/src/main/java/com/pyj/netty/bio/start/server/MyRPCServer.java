package com.pyj.netty.bio.start.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/17 下午2:43
 */
public class MyRPCServer {
    public void start(int port) throws Exception {
        // 主线程，不处理任何业务逻辑，只是接收客户的连接请求
        NioEventLoopGroup boss = new NioEventLoopGroup(1);

        // 工作线程，线程数默认是:cpu*2
        NioEventLoopGroup worker = new NioEventLoopGroup();
        // 服务器启动类
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    //设置线程组
                    .group(boss, worker)
                    //配置server通道
                    .channel(NioServerSocketChannel.class)
                    //worker线程的处器
                    .childHandler(new MyChannelInitializer());
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("服务器启动完成，端口为:" + port);
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭线程
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
