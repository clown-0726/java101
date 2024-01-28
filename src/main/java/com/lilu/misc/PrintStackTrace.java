package com.lilu.misc;

public class PrintStackTrace {
    static void a() {
        b();
    }

    static void b() {
        c();
    }

    static void c() {
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        System.out.println(stackTraceElements[0].getMethodName()); // c
    }

    public static void main(String[] args) {
        a();
    }
}
