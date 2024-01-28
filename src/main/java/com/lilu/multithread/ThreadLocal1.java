package com.lilu.multithread;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {

    static ThreadLocal<People> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(threadLocal.get().name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            threadLocal.set(new People());
            System.out.println(threadLocal.get().name);
        }).start();

    }

    static class People {
        String name = "Zhangsan";
    }
}
