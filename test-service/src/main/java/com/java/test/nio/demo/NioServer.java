package com.java.test.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/4/22 1:34 下午
 */
public class NioServer {

  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(8888), 1024);
    serverSocketChannel.configureBlocking(false);

    Selector selector = Selector.open();
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      Thread.sleep(100L);
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      for (SelectionKey selectionKey : selectionKeys) {
        if (!selectionKey.isValid()) {
          continue;
        }
        try {
          if (selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = channel.accept();
            if (socketChannel != null) {
              socketChannel.configureBlocking(false);
              socketChannel.register(selector, SelectionKey.OP_READ);
              System.out.println("accept connect..." + socketChannel.getRemoteAddress().toString());
            }
          }
          if (selectionKey.isReadable()) {
            SocketChannel channel = soRead(selectionKey);

            doWrite(channel, "\"write\"");
          }
        } catch (IOException e) {
          e.printStackTrace();
          selectionKey.channel().close();
          selectionKey.cancel();
        }
      }
    }
  }

  private static SocketChannel soRead(SelectionKey selectionKey) throws IOException {
    SocketChannel channel = (SocketChannel) selectionKey.channel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int read = channel.read(buffer);
    buffer.flip();
    System.out.println("read..." + new String(buffer.array(), 0, read, "UTF-8"));
    return channel;
  }

  private static void doWrite(SocketChannel channel, String msg) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(msg.getBytes("UTF-8").length);
    buffer.put(msg.getBytes("UTF-8"));
    buffer.flip();
    channel.write(buffer);
    if(!buffer.hasRemaining()) {
      System.err.println("Send request");
    }
  }
}
