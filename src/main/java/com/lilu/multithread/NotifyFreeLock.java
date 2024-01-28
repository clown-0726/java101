package com.lilu.multithread;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotifyFreeLock {
    List lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) throws InterruptedException {
        NotifyFreeLock c = new NotifyFreeLock();
        final Object lock = new Object();

        new Thread(() -> {
            System.out.println("t2 Start...");
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("t2 end");
                lock.notify();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println("t2 Start...");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                        // 释放锁，让 t2 得以执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "t1").start();

    }
}
