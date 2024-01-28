package com.lilu.container;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteListTest {
    // CopyOnWriteArrayList 实现了读写分离，读不加锁，写加锁
    // 每次写数据的时候，先复制整个容器，然后在新的容器中写入数据，之后将容器指针切换到新的容器
    // 通过这种方式实现了读写分离，但是写的效率比较低
    static List<String> l = new CopyOnWriteArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            l.add("Good" + i);
        }
    }

    static void run4CopyOnWriteList() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (l) {
                    while (l.size() > 0) {
                        try {
                            TimeUnit.MICROSECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(l.remove(0));
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        run4CopyOnWriteList();
    }

}
