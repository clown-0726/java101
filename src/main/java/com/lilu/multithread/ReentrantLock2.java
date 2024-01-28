package com.lilu.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    /**
     * ReentrantLock 是 CAS
     * Sync 是锁升级的概念
     *
     * 下面代码中 lock 同时锁定了 m1 和 m2 方法中的代码
     * 虽然代码不在同一个 lock 锁定的块中，但是是同一把锁
     */
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 reentrantLock2 = new ReentrantLock2();
        new Thread(reentrantLock2::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 只有上面的线程执行完成之后，这个线程才执行
        new Thread(reentrantLock2::m2).start();
    }
}
