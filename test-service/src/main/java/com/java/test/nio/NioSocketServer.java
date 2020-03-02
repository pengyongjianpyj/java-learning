package com.java.test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Set;

public class NioSocketServer {

    static NioQueue nioQueue;

    public static void main(String[] args) throws Exception{
        nioQueue = new NioQueue(100);
        // 创建非阻塞 ServerChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888), 1024);
        serverSocketChannel.configureBlocking(false);

        // serverSocketChannel交给Selector监听
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            Thread.sleep(100L);
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                try {
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    if (selectionKey.isAcceptable()) {
                        acceptConnection(selectionKey, selector);
                    } else if (selectionKey.isReadable()) {
                        String read = readFromSelectionKey(selectionKey);
                        SocketChannel channel = (SocketChannel)selectionKey.channel();
                        String user = channel.getRemoteAddress().toString();
                        Node node = nioQueue.setNode(user, read);
                        if (node != null) {
                            System.out.println(Node.toString(node));
                        }
                        List<Node> nodes = nioQueue.getNodes(user);
                        if (nodes != null) {
                            doWrite(selectionKey, Node.toString(nodes));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    selectionKey.channel().close();
                    selectionKey.cancel();
                }
            }
        }
    }

    private static void doWrite(SelectionKey selectionKey, String send) throws Exception {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(send.getBytes().length);
        buffer.put(send.getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    private static String readFromSelectionKey(SelectionKey selectionKey) throws Exception {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        if (channel == null) {
            return "";
        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if (read < 0) {
            channel.close();
            selectionKey.cancel();
            return "";
        } else if (read == 0) {
            return "";
        }
        buffer.flip();
        return new String(buffer.array(), 0, read);
    }

    private static void acceptConnection(SelectionKey selectionKey, Selector selector) throws Exception {
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel accept = channel.accept();
        if (accept != null) {
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
            System.err.println("accept connection ...");
        }
    }

}
