package com.lilu.multithread;

import java.util.concurrent.TimeUnit;

public class ExNoLockExample {
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (count == 5) {
                // 异常会自动释放锁，这样别的线程才可以正常进入
                // 如果不想释放锁，需要用 try...catch 捕获异常后继续执行
                int i = 1 / 0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        ExNoLockExample exNoLockExample = new ExNoLockExample();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                exNoLockExample.m();
            }
        };
        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(r, "t2").start();
    }
}
