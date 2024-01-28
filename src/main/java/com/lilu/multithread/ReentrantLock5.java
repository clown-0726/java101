package com.lilu.multithread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread {
    // 参数为 true 表示公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "Get Lock...");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 reentrantLock5 = new ReentrantLock5();
        /**
         * 下面两个线程一直在获取锁/释放锁
         * 在公平锁的情况下，线程获取锁这个操作会在一个先进先出的队列中，因此结果可能是交替不规则输出，新的操作需要看队列中是否有其他操作在前面
         * 公平锁：结果可能会交替不规则输出，原因是新得获取操作需要关注队列中是否有新的操作在前
         * 非公平锁：结果有可能线程 1 先执行完，然后线程 2 再执行，原因在于有可能线程 1 一直获取到锁
         */
        new Thread(reentrantLock5).start();
        new Thread(reentrantLock5).start();
    }
}
