package com.lilu.multithread;

import java.util.Map;

public class GetThreadStack {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        // 获取 JVM 中所有线程堆栈
        Map<Thread, StackTraceElement[]> ts = Thread.getAllStackTraces();

        ts.keySet().forEach(thread -> {
            sb.append(thread.getName()).append(':').append(thread.getId()).append("\n");
            for (StackTraceElement ste : ts.get(thread)) {
                sb.append(ste).append("\n");
            }
            // 隔离开每一个线程
            sb.append("--------------------------").append("\n");
        });

        System.out.println(sb.toString());
    }
}
