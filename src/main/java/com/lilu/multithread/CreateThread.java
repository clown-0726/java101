package com.lilu.multithread;

public class CreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello myThread");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello myRun");
        }
    }

    public static void main(String[] args) {
        // 继承 Thread 开启线程
        new MyThread().start();

        // 实现 Runnable 开启线程
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println("Hello Lambda Thread");
        }).start();
    }
}
