package com.lilu.iosocket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<SocketChannel> socketChannels = new LinkedList<>();

        ServerSocketChannel ss = ServerSocketChannel.open(); // 得到 FD
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); // 设置 kernel 为 NONBLOCKING
        //ss.setOption(StandardSocketOptions.TCP_NODELAY, false);

        while (true) {
            Thread.sleep(1000);
            SocketChannel client = ss.accept(); // 不会阻塞，操作系统层面返回 -1，java 返回 null

            if (client == null) {
                System.out.println("null......");
            } else {
                client.configureBlocking(false); // 设置具体的连接线程为 NONBLOCKING
                int port = ss.socket().getLocalPort();
                System.out.println("Client port..." + port);
                socketChannels.add(client);
            }

            // 开始遍历所有 client 获取数据
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096); // 分配堆外内存
            for (SocketChannel sc : socketChannels) {  // 可以使用多线程！
                int num = sc.read(buffer); // -1 0 1 system call
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);

                    String bbb = new String(aaa);
                    System.out.println("recv data..." + bbb);
                    buffer.clear();
                }
            }
        }
    }
}
