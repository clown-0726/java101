package com.lilu.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        //new QueueTest().arrayBlockingQueue();
        //new QueueTest().linkedBlockingQueue();
        //new QueueTest().syncQueue();
    }

    // 通过数组实现的有界阻塞队列
    public void arrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<Integer>(10);
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    // 通过链表实现的无界阻塞队列
    public void linkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    public void syncQueue() {
        SynchronousQueue queue = new SynchronousQueue<Integer>();

        // 插入值
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // 删除值
        new Thread(() -> {
            try {
                queue.take();
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
