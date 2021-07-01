package com.java.test;


import java.net.URL;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-07-22 16:56
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
//        bootstrapClassLoader();
//        System.out.println();
//        extensionClassLoader();
//        System.out.println();
//        systemClassLoader();
//        int i = recursion(1, 1, 10, 1000, 1000);
//        i = i /10;
//        System.out.println(i / 3600 / 24);
//        System.out.println(i / 3600 / 24 + ":" + i / 3600 % 24);
    }

    private static int recursion(int i, int k, int n, double l, double ln){
        while (true){
            System.out.println(i + ":" + l + ":" + ln);
            double l1 = l + n;
            double ln1 = (ln - k) * (l1) / l;
            if(ln1 < 0){
                return ++i;
            }
            i++;
            l = l1;
            ln = ln1;
        }
    }

    public static void bootstrapClassLoader() {
        //获取引导类加载器能加载哪些路径下的jar文件:
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL element : urLs) {
            System.out .println(element.toExternalForm());
        }
    }

    public static void extensionClassLoader() {
        //获取扩展类加载器所加载的jar包:
        String extDirs = System. getProperty("java.ext.dirs");//获取一个或多个扩展目录的路径
        for (String path : extDirs.split(":")) {
            System.out.println(path) ;
        }
    }
    public static void systemClassLoader() {
        java.lang.ClassLoader classLoader = ClassLoader.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
    }



}
