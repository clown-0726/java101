package com.lilu.multithread;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ConsumerProducer1<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    /**
     * 往队列中生成数据
     *
     * @param t
     */
    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        list.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        ConsumerProducer1<String> c = new ConsumerProducer1<>();

        // 启动消费者线程
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 启动生产者线程，生产的数量一定要大于消费的数量，否则线程等待
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "c" + i).start();
        }
    }
}
