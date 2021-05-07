package com.java.test.file;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File parent = new File("E:");
        File[] files = parent.listFiles();
        String[] chars = {"-", "Ｃ", " ", "１", "５", "３", "０"};
        for (String start : chars) {
            for (File file : files) {
                String startNew = "";
                String nameOld = file.getName();
                if (nameOld.startsWith(start)){
                    System.out.println(nameOld);
                    String replace = nameOld.replace(start, startNew);
                    String pathname = "E:\\" + replace;
                    File newFile = new File(pathname);
                    System.out.println(pathname);
                    boolean b = file.renameTo(newFile);
                    System.out.println(b);

                }
            }
        }


    }
}
