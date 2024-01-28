package com.lilu.classloader;

import java.sql.Driver;

public class ClassLoaderView {
    public static void main(String[] args) throws ClassNotFoundException {
        // null，这是 BootstrapClassLoader 这个不允许直接访问
        String string = "Hello Classloader";
        System.out.println(string.getClass().getClassLoader()); // null

        // PlatformClassLoader，加载平台类
        Class<Driver> clz = (Class<Driver>) Class.forName("java.sql.Driver");
        System.out.println(clz.getClassLoader());
        //System.out.println(clz.getClassLoader().getParent());

        // AppClassLoader，加载应用程序类
        System.out.println(ClassLoaderView.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoaderView.class.getClassLoader().getParent()); //sun.misc.Launcher$ExtClassLoader@1d44bcfa

        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader");
        Class cls = myClassLoader.loadClass("com.lilu.inoutputstream.ReadFileLineByLine");
        System.out.println(cls.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
    }
}
