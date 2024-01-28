package com.lilu.multithread;

public class ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState()); // NEW

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();

        System.out.println(t.getState()); // RUNNABLE

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(t.getState()); // TERMINATED
    }
}
