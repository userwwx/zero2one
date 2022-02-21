package com.wwx.zero2one.util;

import org.springframework.util.StopWatch;

public class StopWatchTest {


    public static void sleep(int millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("test");
        stopWatch.start("task1");
        sleep(1000);
        stopWatch.stop();
        stopWatch.start("task2");
        sleep(2000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }
}
