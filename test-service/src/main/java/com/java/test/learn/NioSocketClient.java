package com.java.test.learn;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioSocketClient {

    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);

        channel.connect(new InetSocketAddress("127.0.0.1", 8888));

        while (true){
            Thread.sleep(100L);
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                try {
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    if (selectionKey.isConnectable()) {
                        if (channel.finishConnect()) {
                            channel.register(selector, SelectionKey.OP_READ);
                            doWriteRequest((SocketChannel) selectionKey.channel());
                        }
                    }
                    if (selectionKey.isReadable()) {
                        doRead(selectionKey);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    selectionKey.channel().close();
                    selectionKey.cancel();
                }
            }
        }
    }

    private static void doWriteRequest(SocketChannel channel) throws Exception {
        System.err.println("start connect ...");

        //创建ByteBuffer对象，会放入数据
        ByteBuffer byteBuffer = ByteBuffer.allocate("Hello Server!".getBytes().length);
        byteBuffer.put("Hello Server!".getBytes());
        byteBuffer.flip();
        //写数据
        channel.write(byteBuffer);
        if(!byteBuffer.hasRemaining()) {
            System.err.println("Send request success...");
        }
    }

    //读取服务端的响应
    private static void doRead(SelectionKey selectionKey) throws Exception{
        SocketChannel socketChannel = ((SocketChannel) selectionKey.channel());
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        if (read < 0) {
            socketChannel.close();
            selectionKey.cancel();
            return ;
        } else if (read == 0) {
            return ;
        }
        System.out.println("Recv:" + new String(byteBuffer.array(), 0 ,read));
    }
}
