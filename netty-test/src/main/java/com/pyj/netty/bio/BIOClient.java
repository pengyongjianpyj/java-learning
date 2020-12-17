package com.pyj.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/16 下午9:41
 */
public class BIOClient {
    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            Socket socket = new Socket("localhost", 6666);
            //获取输入流与输出流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));//InputStreamReader第二个参数可选填，默认为GBK
            pw = new PrintWriter(socket.getOutputStream(),true);
            //向服务器发送数据
            pw.println("你好!");
            //接收服务器发回的消息
            String str = null;
            while(true){//如果接收到的消息为空（没有接收到消息），则继续此循环
                str = br.readLine();
                if(str!=null) {
                    break; //如果接收到的消息不为空（接收到了消息），则跳出此循环
                }
            }
            System.out.println(str);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
