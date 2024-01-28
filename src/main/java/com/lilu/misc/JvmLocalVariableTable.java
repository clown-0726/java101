package com.lilu.misc;

public class JvmLocalVariableTable {
    public static void main(String[] args) {
        {
            byte[] bs = new byte[2 * 1024 * 1024];
            //bs = null;
        }

        /**
         * 如果这里没有 int a = 5; 这行代码，GC 是不会真正回首掉 bs 对象的
         * 但是如果加上这段代码，GC 会把 slot 插槽 1 的位置重新赋值给 a
         */
        int a = 5;
        // 下面是 slot 插槽的位置
        // ----- slot
        // 0 -- args
        // 1 -- bs

        System.gc();

        System.out.println("totalMemory=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0);
        System.out.println("freeMemory=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0);
        System.out.println("maxMemory=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0);
    }
}
