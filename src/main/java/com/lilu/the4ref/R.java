package com.lilu.the4ref;

public class R {
    @Override
    protected void finalize() throws Throwable {
        // 垃圾回收时会调用 finalize 方法，以此来观察垃圾回收的行为
        System.out.println("finalize");
    }
}
