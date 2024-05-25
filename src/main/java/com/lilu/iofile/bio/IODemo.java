package com.lilu.iofile.bio;

import java.io.*;

public class IODemo {
    public static void main(String[] args) throws IOException {
        testBasicFileIO();
        testBufferedFileIO();
    }

    public static void testBasicFileIO() throws IOException {
        File file = new File("abc.txt");
        FileOutputStream out = new FileOutputStream(file);
        while (true) {
            // 每次写入都会进行 system call 访问 pagecache
            out.write("abcdefg\n".getBytes());
        }
    }

    public static void testBufferedFileIO() throws IOException {
        File file = new File("abc.txt");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while (true) {
            // 每次写入首先缓存到 jvm 开辟的缓存中，缓存满了之后再通过 system call 访问 pagecache
            out.write("abcdefg\n".getBytes());
        }
    }
}
