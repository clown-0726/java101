package com.lilu.the4ref;

import java.lang.ref.WeakReference;

public class WeakReference1 {
    public static void main(String[] args) {
        WeakReference<R> m = new WeakReference<>(new R());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get()); // null
    }
}
