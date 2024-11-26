package com.lilu.multithread.trysemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreThreshold {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);

            // 当当前的线程数大于一个阈值的时候，程序会在这里阻塞
            System.out.println("Thread.activeCount: " + Thread.activeCount());
            while (Thread.activeCount() > 100) {
                Thread.sleep(1 * 1000);
            }

            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    semaphore.acquire();

                    System.out.println("T1 running..." + finalI);

                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
            t.join();
            t.start();
        }
    }
}
