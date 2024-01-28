package com.lilu.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class FromVector2Queue {
    static Vector<String> l1 = new Vector<>();
    static List<String> l2 = new ArrayList<>();
    static Queue<String> l3 = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 100; i++) {
            l1.add("Good" + i);
        }

        for (int i = 0; i < 100; i++) {
            l2.add("Good" + i);
        }

        for (int i = 0; i < 100; i++) {
            l3.add("Good" + i);
        }
    }


    static void run4Vector() {
        // 下面代码中，虽然容器 Vector 是线程安全的，
        // 但是 size 方法和 remove 虽然其本身都是原子性的，到那时一起使用仍然需要保证整体的原子性
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 原子性
                while (l1.size() > 0) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // 原子性
                    System.out.println(l1.remove(0));
                }
            }).start();
        }
    }

    static void run4ArrayList() {
        // ArrayList 本身不是线程安全的，因此下面代码一定是会超售
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (l2.size() > 0) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(l2.remove(0));
                }
            }).start();
        }
    }

    static void run4ConcurrentLinkedDeque() {
        // Queue 是专门为多线程而生的队列
        // 其实现原理基本上是用 CAS 进行实现的
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = l3.poll();
                    if (s == null) break;
                    System.out.println(s);
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        //run4Vector();
        //run4ArrayList();
        run4ConcurrentLinkedDeque();
    }
}
