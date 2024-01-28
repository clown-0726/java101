package com.lilu.misc;

import java.util.Properties;

public class JavaSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);

        System.out.println("////////////////////////////////////////");

        System.out.println(System.getProperty("java.home")); // JRE 主目录
        System.out.println(System.getProperty("java.library.path")); // 用于搜索本机的 JRE
        System.out.println(System.getProperty("java.ext.dirs")); // JRE 扩展库路径
        System.out.println(System.getProperty("java.class.path")); // JRE 类路径
        System.out.println(System.getProperty("java.version")); // Java 版本
    }
}
