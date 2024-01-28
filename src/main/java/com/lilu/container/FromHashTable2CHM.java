package com.lilu.container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class FromHashTable2CHM {
    // 1.0 最古老的 map 容器，内部都使用了 synchronized 锁同步
    static Hashtable<String, String> m1 = new Hashtable<>();
    // HashMap 默认是不加锁的，因此是线程不安全的
    static HashMap<String, String> m2 = new HashMap<>();
    // Collections.synchronizedMap 可以将一个 HashMap 变成线程安全的，即加上 synchronized
    // 其原理是通过 mutex 这把锁对所有方法进行重写实现的（装饰器？）
    // 其效率和 Hashtable 差不多，两者都是使用的是 synchronized 锁
    static Map<String, String> m3 = Collections.synchronizedMap(new HashMap<>());
    // 写：其插入效率还不如 Hashtable/synchronizedMap，原因是其内部各种 CAS 判断，构造红黑树等
    // 读：但是由于 红黑树 数据结构，其读取效率比较高
    static Map<String, String> m4 = new ConcurrentHashMap<>();
    // 正常 Map 常用的结构为 HashMap 和 TreeMap：
    // 一个是无序的，一个是有序的
    // 一个是内部是哈希，一个内部是红黑树
    // 但是由于 ConcurrentHashMap 内部使用的 CAS 保证的线程安全，这在数的结构上实现非常复杂，因此通过跳表进行实现
    static Map<String, String> m5 = new ConcurrentSkipListMap<>();

    static class MyThread4Hashtable extends Thread {
        public MyThread4Hashtable() {
            System.out.println("New MyThread...");
        }

        @Override
        public void run() {
            m1.put("a", "1");
            m1.put("b", "2");

            System.out.println(m1.get("a"));
        }
    }

    static class MyThread4HashMap extends Thread {
        @Override
        public void run() {
            m2.put("a", "1");
            m2.put("b", "2");

            System.out.println(m2.get("a"));
        }
    }

    static class MyThread4SynchronizedMap extends Thread {
        @Override
        public void run() {
            m3.put("a", "1");
            m3.put("b", "2");

            System.out.println(m3.get("a"));
        }
    }

    static class MyThread4ConcurrentHashMap extends Thread {
        @Override
        public void run() {
            m4.put("a", "1");
            m4.put("b", "2");

            System.out.println(m4.get("a"));
        }
    }

    static class MyThread4ConcurrentSkipListMap extends Thread {
        @Override
        public void run() {
            m5.put("a", "1");
            m5.put("b", "2");

            System.out.println(m5.get("a"));
        }
    }


    public static void main(String[] args) {
        new MyThread4Hashtable().start();
        new MyThread4HashMap().start();
        new MyThread4SynchronizedMap().start();
        new MyThread4ConcurrentHashMap().start();
        new MyThread4ConcurrentSkipListMap().start();
    }
}
