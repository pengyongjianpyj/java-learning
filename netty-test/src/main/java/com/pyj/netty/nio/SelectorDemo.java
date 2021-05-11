package com.pyj.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author pengyongjian
 * @Description:
 */
public class SelectorDemo {
    public static void main(String[] args) throws Exception {
        new SelectorDemo().listen();
    }

    private void listen() throws Exception {
        Selector selector = getSelector();

        while (true) {
            //该方法会阻塞，直到至少有一个事件的发生
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                processs(selectionKey, selector);
                iterator.remove();
            }

        }
    }

    private void processs(SelectionKey key, Selector selector) throws Exception {
        if (key.isAcceptable()) {
            //新连接请求
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

        } else if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            System.out.println("form 客户端 " + new String(byteBuffer.array(), 0, byteBuffer.position()));
        }
    }

    private Selector getSelector() throws Exception {
        //获取selector对象
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);

        //获取通道并且绑定端口
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(6666));

        //注册感兴趣的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        return selector;
    }
}
