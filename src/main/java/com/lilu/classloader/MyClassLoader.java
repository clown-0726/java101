package com.lilu.classloader;

import java.io.*;

/**
 * @author crown
 */
public class MyClassLoader extends ClassLoader {
    public String myName = "";

    public MyClassLoader(String myName) {
        this.myName = myName;
    }

    // 推荐覆盖 findClass 方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        // 将二进制流的数据转换成类
        return this.defineClass(name, data, 0, data.length);
    }

    // 通过类型得到字节码
    public byte[] loadClassData(String clsName) {
        byte[] data = null;
        clsName = clsName.replace(".", "/");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;

        try {
            in = new FileInputStream(new File("classes" + clsName + ".class"));
            int a = 0;
            while ((a = in.read()) != -1) {
                out.write(a);
            }
            data = out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
