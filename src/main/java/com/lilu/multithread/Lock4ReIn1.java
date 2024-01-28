package com.lilu.multithread;

import java.util.concurrent.TimeUnit;

public class Lock4ReIn1 {
    synchronized void m1() throws InterruptedException {
        System.out.println("m1 start");
        TimeUnit.SECONDS.sleep(1);

        // 调用 m2()，m2 的锁也是 this 锁
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("m2");
    }

    public static void main(String[] args) throws InterruptedException {
        // 可重入指的是"同一个线程"可以多次获得锁
        Lock4ReIn1 lock4ReIn1 = new Lock4ReIn1();
        lock4ReIn1.m1();
    }
}
