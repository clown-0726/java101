package com.lilu.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        // t1 线程会一直执行
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        // 由于 t1 一直在占有锁，因此 t2 就拿不到锁，会处于一直等待状态
        // lock.lockInterruptibly(); 可以获取可打断的锁
        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly(); // 可以对 interrupt 做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t2.interrupt();
    }
}
