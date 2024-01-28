package com.lilu.multithread;

public class Lock1 {
    private int count = 10;
    private final Object o = new Object();

    public void m0() {
        // 任何线程要访问下面的代码，必须获取锁 o
        // 锁的原理是对"任意"的一个对象的 markword 标志位改变
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }

    // 为了方便，我们可以使用 this，也就是当前对象的实例作为锁
    public void m1() {
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }

    // 简化了上面的写法，使用的锁就是当前对象的实例 this
    public synchronized void m2() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }
}
