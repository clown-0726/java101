package com.lilu.multithread;

import java.util.ArrayList;
import java.util.List;

public class VolatileNotSync {
    volatile int count = 0;

    /*synchronized*/ void m() {
        // volatile 并不能保证多个线程共同修改 count 变量时所带来的不一致问题
        // 也就说 volatile 不能替代 synchronized
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileNotSync volatileNotSync = new VolatileNotSync();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(volatileNotSync::m, "thread_" + i));
        }

        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(volatileNotSync.count);
    }
}
