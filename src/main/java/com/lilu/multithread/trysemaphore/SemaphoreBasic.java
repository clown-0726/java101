package com.lilu.multithread.trysemaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreBasic {
    public static void main(String[] args) {
        // 允许一个线程同时执行
        // 线程限流，控制线程运行的最大数量
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(2);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(2);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
