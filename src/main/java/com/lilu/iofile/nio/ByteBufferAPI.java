package com.lilu.iofile.nio;

import java.nio.ByteBuffer;

public class ByteBufferAPI {
    public static void main(String[] args) {
        // 分配一个 1024 大小的空间
        //ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // 分配堆内的 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024); // 分配堆外的 ByteBuffer

        // 一共三个纬度，position limit capacity
        System.out.println("position: " + byteBuffer.position());
        System.out.println("limit: " + byteBuffer.limit());
        System.out.println("capacity: " + byteBuffer.capacity());
        System.out.println("MARK: " + byteBuffer);


        // 往里面放 123，这时候 position 在放完数据后的位置，limit 保持不变
        byteBuffer.put("123".getBytes());
        System.out.println("MARK put: " + byteBuffer);

        // flip 之后 position 在开始位置，limit 在放完数据后的位置，这时候可以准备输出了
        byteBuffer.flip();
        System.out.println("MARK flip: " + byteBuffer);

        // 将数据都输出出来，每次 get 一个字节
        byteBuffer.get();
        System.out.println("MARK get: " + byteBuffer);

        // position 在当前位置后移一位，limit 回到最后
        byteBuffer.compact();
        System.out.println("MARK compact: " + byteBuffer);

        // 清空数据
        byteBuffer.clear();
        System.out.println("MARK clear: " + byteBuffer);
    }
}
