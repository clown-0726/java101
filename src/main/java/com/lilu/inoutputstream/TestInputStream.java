package com.lilu.inoutputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


// https://blog.csdn.net/qq_72935001/article/details/128664976

public class TestInputStream {
    public static void main(String[] args) {
        System.out.println("I am input stream");

        ClassLoader classLoader = TestInputStream.class.getClassLoader();
        URL url = classLoader.getResource("abc.txt");
        String filePath = url.getPath();

        int readData = 0;
        // byte[] buf = new byte[8]; // 字节数据，一次读取八个
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            while ((readData = fileInputStream.read()) != -1) {
                System.out.println((char) readData + "");
                //System.out.print(new String(buf, 0, readLen));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
