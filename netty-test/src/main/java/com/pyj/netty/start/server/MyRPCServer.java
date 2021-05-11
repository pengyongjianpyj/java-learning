package com.pyj.netty.start.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/17 下午2:43
 */
public class MyRPCServer {
    public void start(int port) throws Exception {
        // 主线程池，不处理任何业务逻辑，只是接收客户的连接请求
        NioEventLoopGroup boss = new NioEventLoopGroup(1);

        // 工作线程池，线程数默认是:cpu*2
        NioEventLoopGroup worker = new NioEventLoopGroup();
        // 服务器启动类
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                //设置线程组
                .group(boss, worker)
                //配置server通道
                .channel(NioServerSocketChannel.class)
                //boss线程的处理器
                .handler(new LoggingHandler(LogLevel.DEBUG))
                //worker线程的处理器
                .childHandler(new ChannelInitializer(){
                    @Override
                    protected void initChannel(Channel channel) {
                        channel.pipeline()
                            .addLast(new MyChannelHandler())
                            .addLast(new MyChannelHandlerExt());
                    }
                });
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
