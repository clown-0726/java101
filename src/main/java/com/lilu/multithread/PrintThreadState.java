package com.lilu.multithread;

import java.util.concurrent.locks.LockSupport;

public class PrintThreadState {
    public static void newState() {
        System.out.println(new Thread().getState());
    }

    public static void runnableState() {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });
        thread.start();
        System.out.println(thread.getState());
    }

    public static void blockedState() throws InterruptedException {
        Object MONITOR = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (MONITOR) {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (MONITOR) {
            }
        });

        thread1.start();
        Thread.sleep(100);

        thread2.start();
        Thread.sleep(100);

        System.out.println(thread2.getState());
    }

    public static void waitingState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            while (true) {
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState()); // WAITING

        LockSupport.unpark(thread);
        Thread.sleep(100);
        System.out.println(thread.getState()); // RUNNABLEABLE
    }

    public static void timedwaitingState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        });

        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
    }

    public static void terminatedState() throws InterruptedException {
        Thread thread = new Thread(() -> {
        });
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
    }

    public static void main(String[] args) {
        newState();
    }
}
