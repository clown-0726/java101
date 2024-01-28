package com.lilu.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class BQ4DelayQueue {
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();
    static Random r = new Random();

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS))
                return 1;
            return 0;
        }

        public static void main(String[] args) throws InterruptedException {
            long now = System.currentTimeMillis();
            MyTask t1 = new MyTask("t1", 1000);
            MyTask t2 = new MyTask("t2", 8000);
            MyTask t3 = new MyTask("t3", 5000);
            MyTask t4 = new MyTask("t4", 3000);
            MyTask t5 = new MyTask("t5", 7000);
            tasks.put(t1);
            tasks.put(t2);
            tasks.put(t3);
            tasks.put(t4);
            tasks.put(t5);

            for (int i = 0; i < 5; i++) {
                System.out.println(tasks.take());
            }
        }
    }
}
