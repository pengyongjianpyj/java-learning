package com.java.test.algorithm;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/12/25 上午9:49
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000, 1000);
    }
}
