package com.pyj.netty.start.client;

import org.junit.Test;

/**
 * @author pengyongjian
 * @Description:
 */
public class TestClient {
    @Test
    public void testClient() throws Exception{
        new MyRPCClient().start("127.0.0.1", 5566);
    }
}
