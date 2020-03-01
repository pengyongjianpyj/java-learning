package com.java.test.learn;

import javax.lang.model.element.VariableElement;
import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class NioFile {

    static String pathInput = "E:\\咕泡学院\\01.工程化专题\\04_工程化专题回顾-.mp4";
    static String pathOutput = "E:\\迅雷下载\\joker.mp4";
    static int temp = 0;
    static Selector selector;

    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        for (int i = 0; i < 3; i++) {
            new Runnable() {
                @Override
                public void run() {
                    try {
                        long t1 = System.currentTimeMillis();
//                        nio(new File(pathInput), new File(pathInput.replace("04_工程化专题回顾-", temp + ".")));
                        io(new File(pathInput), new File(pathInput.replace("04_工程化专题回顾-", temp + ".")));
                        long t2 = System.currentTimeMillis();
                        System.err.println(Thread.currentThread().getName() + temp++ + "\t" + (t2 - t1) + "ms");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.run();
        }
    }

    private static void nio(File fileIn, File fileOut) throws IOException, InterruptedException {
        FileInputStream input = new FileInputStream(fileIn);
        FileOutputStream output = new FileOutputStream(fileOut);
        FileChannel inChannel = input.getChannel();
        FileChannel outChannel = output.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        inChannel.read(buffer);
        while (inChannel.read(buffer) != -1) {
            Thread.sleep(1L);
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
    }

    private static void io(File fileIn, File fileOut) throws IOException, InterruptedException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileIn));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileOut));
        byte[] arr = new byte[1024 * 1024];
        int len = 0;
        while ((len = inputStream.read(arr)) != -1){
            Thread.sleep(1L);
            outputStream.write(arr, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
}
