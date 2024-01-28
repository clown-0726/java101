package com.lilu.multithread_pool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Child2ForkJoinPool2 {
    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("123");
        }
    }

    public static void main(String[] args) throws IOException {
        // 每一个线程都会维护一个等待队列，
        // 当一个线程的等待队列中的任务都执行完成，算法会让线程从其他的等待队列中获取任务并执行
        ExecutorService service = Executors.newWorkStealingPool();
        service.execute(new Task());
        service.execute(new Task());
        service.execute(new Task());
        service.execute(new Task());

        System.in.read();
    }
}
