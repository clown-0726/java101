package com.lilu.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    // LockSupport 是专门用于线程的支持类
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 线程停止执行
                    LockSupport.park();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("After 8 sed");
        // unpark() 可以先于 park() 调用
        // 开启停止的线程继续执行
        LockSupport.unpark(t);

        // 将主线程阻塞（主线程退出并不会造成子线程的退出！）
        t.join();
        System.out.println("Main thread quit.");
    }
}
