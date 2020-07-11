package com.java.test.nio;

import javax.websocket.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class NioWebSocketClient {

    private static String msg;
    private static String name;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (name == null || "".equals(name) || name.length() > 20){
            System.err.println("请输入你的昵称(1-20个字符):");
//            System.err.println("请输入你的昵称(1-20个字符):");
            name = scanner.nextLine();
        }

//        WebSocketContainer container = getContainer();
//        Session session = new Session();

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKeyMain = channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress("39.105.76.39", 8089));

        while (true){
            Thread.sleep(100L);
            selector.select(100);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                try {
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    if (selectionKey.isConnectable()) {
                        if (channel.finishConnect()) {
                            channel.register(selector, SelectionKey.OP_READ);
                            doWriteRequest((SocketChannel) selectionKey.channel(), name);
                        }
                    }
                    if (selectionKey.isReadable()) {
                        doRead(selectionKey);
                        if (msg != null) {
                            synchronized (msg) {
                                doWriteRequest(channel, name + "\t" + msg);
                            }
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

    private static WebSocketContainer getContainer() {
        WebSocketContainer webSocketContainer = new WebSocketContainer() {
            @Override
            public long getDefaultAsyncSendTimeout() {
                return 0;
            }

            @Override
            public void setAsyncSendTimeout(long l) {

            }

            @Override
            public Session connectToServer(Object o, URI uri) throws DeploymentException, IOException {
                return null;
            }

            @Override
            public Session connectToServer(Class<?> aClass, URI uri) throws DeploymentException, IOException {
                return null;
            }

            @Override
            public Session connectToServer(Endpoint endpoint, ClientEndpointConfig clientEndpointConfig, URI uri) throws DeploymentException, IOException {
                return null;
            }

            @Override
            public Session connectToServer(Class<? extends Endpoint> aClass, ClientEndpointConfig clientEndpointConfig, URI uri) throws DeploymentException, IOException {
                return null;
            }

            @Override
            public long getDefaultMaxSessionIdleTimeout() {
                return 0;
            }

            @Override
            public void setDefaultMaxSessionIdleTimeout(long l) {

            }

            @Override
            public int getDefaultMaxBinaryMessageBufferSize() {
                return 0;
            }

            @Override
            public void setDefaultMaxBinaryMessageBufferSize(int i) {

            }

            @Override
            public int getDefaultMaxTextMessageBufferSize() {
                return 0;
            }

            @Override
            public void setDefaultMaxTextMessageBufferSize(int i) {

            }

            @Override
            public Set<Extension> getInstalledExtensions() {
                return null;
            }
        };
        return webSocketContainer;
    }

    private static void doWriteRequest(SocketChannel channel, String msg) throws Exception {
        if (msg == null) {
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(msg.getBytes("UTF-8").length);
        byteBuffer.put(msg.getBytes("UTF-8"));
        byteBuffer.flip();

        channel.write(byteBuffer);
        if(!byteBuffer.hasRemaining()) {
            NioWebSocketClient.msg = null;
            System.err.println("Send request success...");
        }
    }

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
        System.out.println(new String(byteBuffer.array(), 0 ,read, "UTF-8"));
    }
}
