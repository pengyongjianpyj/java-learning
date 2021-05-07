package com.java.test.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/4/22 1:34 下午
 */
public class NioClient {
  public static void main(String[] args) throws Exception {
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.configureBlocking(false);
    Selector selector = Selector.open();
    socketChannel.register(selector, SelectionKey.OP_CONNECT);
    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
    while (true) {
      Thread.sleep(100L);
      selector.select(1000);
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      for (SelectionKey selectionKey : selectionKeys) {
        if (!selectionKey.isValid()) {
          continue;
        }
        if (selectionKey.isConnectable()) {
          if (socketChannel.finishConnect()) {
            socketChannel.register(selector, SelectionKey.OP_READ);

            doWrite(socketChannel, "\"connect\"");
          }
        }
        if (selectionKey.isReadable()) {
          doRead(socketChannel, selectionKey);
        }
      }
    }
  }

  private static void doWrite(SocketChannel socketChannel, String msg) throws IOException {
    ByteBuffer byteBuffer = ByteBuffer.allocate(msg.getBytes("UTF-8").length);
    byteBuffer.put(msg.getBytes("UTF-8"));
    byteBuffer.flip();
    socketChannel.write(byteBuffer);
    if(!byteBuffer.hasRemaining()) {
      System.err.println("Send request");
    }
  }

  private static void doRead(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    int read = socketChannel.read(byteBuffer);
    if (read < 0) {
      socketChannel.close();
      selectionKey.cancel();
    }
    byteBuffer.flip();
    System.out.println("read..." + new String(byteBuffer.array(), 0, read, "UTF-8"));
  }
}
