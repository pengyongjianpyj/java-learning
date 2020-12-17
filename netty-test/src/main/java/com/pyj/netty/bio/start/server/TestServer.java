package com.pyj.netty.bio.start.server;

import org.junit.Test;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/17 下午3:16
 */
public class TestServer {
    @Test
    public void testServer() throws Exception{
        MyRPCServer myRPCServer = new MyRPCServer();
        myRPCServer.start(5566);
    }
}
