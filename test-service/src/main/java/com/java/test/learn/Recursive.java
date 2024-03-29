package com.java.test.learn;

import java.io.File;

public class Recursive {


    public static void main(String[] args) {
        String dir = "F:\\七血";
        recursive1(new File(dir));
    }

    private static void recursive1(File file) {
        if (!file.exists()) { // 不存在
            return;
        }
        if (file.isDirectory()) { // 目录
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                file.delete();
                printFile(file);
                return ;
            }
            for (File fileChild : files) { // 遍历目录
                recursive1(fileChild); // 递归file
            }
        } else { // 文件

        }
    }

    private static void printFile(File file) {
        System.err.println(file.getPath() + "\\" + file.getName());
    }

}
