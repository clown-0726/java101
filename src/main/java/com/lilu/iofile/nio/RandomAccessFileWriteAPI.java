package com.lilu.iofile.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileWriteAPI {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("xyz.txt", "rw");

        // 堆内存 -------------------------------------------------------------------------------------------------------
        raf.write("hello abc\n".getBytes());
        raf.write("hello xyz\n".getBytes());
        System.in.read(); // 阻塞

        raf.seek(4);
        raf.write("OOO".getBytes());
        System.in.read(); // 阻塞

        // mmap 映射 ---------------------------------------------------------------------------------------------------
        FileChannel fileChannel = raf.getChannel();
        // 只有文件（块设备）才能做 MappedByteBuffer，也就是 mmap 映射
        // 通过系统调用的到 mmap 映射，一个堆外的，且和文件映射的 ByteBuffer，之后对文件的读写不再需要系统调用
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        map.put("@@@".getBytes());
        //map.force(); // flush
        System.in.read(); // 阻塞

        // 堆外内存 -----------------------------------------------------------------------------------------------------
        //ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // 分配堆内的 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024); // 分配堆外的 ByteBuffer
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
    }
}
