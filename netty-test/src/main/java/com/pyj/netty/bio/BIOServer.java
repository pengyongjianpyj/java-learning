package com.pyj.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengyongjian
 * @Description:
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true){
            System.out.println("等待客户端连接。。。。");
            Socket socket = serverSocket.accept(); //阻塞
            executorService.execute(() -> {
                try {
                    InputStream inputStream = socket.getInputStream(); //阻塞
                    byte[] bytes = new byte[1024];
                    while (true){
                        int length = inputStream.read(bytes);
                        if(length == -1){
                            break; }
                        System.out.println(new String(bytes, 0, length, "UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
