package com.java.test.learn;

import java.io.*;

public class NioTest {

    static String path = "D:\\fouderwork\\nio.txt";

    public static void main(String[] args) throws IOException {
        io(new File(path));
        nio(new File(path));
    }

    private static void nio(File file) {

    }

    private static void io(File file) throws IOException {
        InputStream input = new FileInputStream(file); // get the InputStream from the client socket

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String nameLine   = reader.readLine();
        String ageLine    = reader.readLine();
        String emailLine  = reader.readLine();
        String phoneLine  = reader.readLine();
        System.err.println(nameLine);
        System.err.println(ageLine);
        System.err.println(emailLine);
        System.err.println(phoneLine);
    }
}
