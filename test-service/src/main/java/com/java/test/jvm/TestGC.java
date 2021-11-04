package com.java.test.jvm;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import sun.tools.attach.HotSpotVirtualMachine;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/14 上午9:49
 */
public class TestGC {
    public static void main(String[] args) throws Exception {
//        List<Object> list = new ArrayList<>();
//        while (true){
//            int sleep = new Random().nextInt(100);
//            if (System.currentTimeMillis() % 2 == 0) {
//                list.clear();
//            } else {
//                for (int i = 0; i < 10000; i++) {
//                    Properties properties = new Properties();
//                    properties.put("key_" + i, "value_" + System.currentTimeMillis() + i);
//                    list.add(properties);
//                }
//            }
//            System.out.println("list大小为：" + list.size());
//            Thread.sleep(sleep);
//        }
        test();
    }

    public static void test() throws Exception{
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String name = bean.getName();
        int index = name.indexOf('@');
        String pid = name.substring(0, index);
        //这里要区分操作系统
        HotSpotVirtualMachine machine = (HotSpotVirtualMachine) new sun.tools.attach.BsdAttachProvider().attachVirtualMachine(pid);
        InputStream is = machine.heapHisto("| grep java.long.String");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int readed;
        byte[] buff = new byte[1024];
        while((readed = is.read(buff)) > 0) {
            baos.write(buff, 0, readed);
        }
        is.close();

        machine.detach();
        System.out.println(baos);
    }
}
