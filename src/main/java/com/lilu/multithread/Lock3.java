package com.lilu.multithread;

public class Lock3 {
    private static int count = 10;

    public static synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        // 每个类加载到内存之后，会生成 Class 这个类的对象，这个锁就是这个对象
        synchronized (Lock3.class) {
            count--;
        }
    }
}
