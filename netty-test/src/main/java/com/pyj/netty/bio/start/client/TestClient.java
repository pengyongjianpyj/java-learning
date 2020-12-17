package com.pyj.netty.bio.start.client;

import org.junit.Test;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/17 下午3:26
 */
public class TestClient {
    @Test
    public void testClient() throws Exception{
        new MyRPCClient().start("127.0.0.1", 5566);
    }
}
