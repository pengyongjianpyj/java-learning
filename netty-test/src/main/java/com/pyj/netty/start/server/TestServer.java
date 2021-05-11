package com.pyj.netty.start.server;

import org.junit.Test;

/**
 * @author pengyongjian
 * @Description:
 */
public class TestServer {
    @Test
    public void testServer() throws Exception{
        MyRPCServer myRPCServer = new MyRPCServer();
        myRPCServer.start(5566);
    }
}
