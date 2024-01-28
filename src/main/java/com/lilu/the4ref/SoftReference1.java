package com.lilu.the4ref;

import java.lang.ref.SoftReference;

public class SoftReference1 {
    public static void main(String[] args) {
        // m 指向一个软引用，而软引用指向一个 10M 的字节数组
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(m.get());

        // 这里分配一个数组，堆内存装不下，这时候系统会垃圾回收，
        // 先回收一次，如果不够，会把软引用回收掉
        byte[] b = new byte[1024 * 1024 * 12];
        System.out.println(m.get());

        // VM Options: -Xms20M -Xmx20M
    }
}
