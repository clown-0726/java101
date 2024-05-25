package com.lilu.iofile.aiofake;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AsyncExample {
    /**
     * 在大多数情况下，它的异步操作也是通过在后台使用一个或多个线程来实现的，而不是通过真正的非阻塞 I/O
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("abc.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());

        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("Read success");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Read failed");
            }
        });

        // 由于读取文件的操作是另一个线程进行的，这里阻塞主进程防止过早退出
        System.in.read();
    }
}
