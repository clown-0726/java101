package com.lilu.multithread;

import java.util.concurrent.TimeUnit;

public class Volatile {
    /* volatile */ boolean running = true;

    void c() {
        System.out.println("c start");
        // 主线程改变 running 的值之后，当前线程不一定能获取到最新改变的值，因此循环一直进行
        while (running) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("c end");
    }

    public static void main(String[] args) {
        Volatile v = new Volatile();

        // 开启新的线程
        new Thread(v::c, "t1").start();

        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 在主线程中改变 running 的值
        v.running = false;
    }
}
