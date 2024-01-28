package com.lilu.the4ref;

import java.io.IOException;

public class NormalReference {
    public static void main(String[] args) throws IOException {
        R r = new R();
        r = null;
        System.gc();

        // 阻塞住当前线程，不让程序退出，
        // 由于 GC 是运行在别的线程中的，如果程序退出，GC 就不存在了
        System.in.read();
    }
}
