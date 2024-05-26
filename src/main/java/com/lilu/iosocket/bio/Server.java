package com.lilu.iosocket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // server socket listen property
    private static final int RECEIVE_BUTTER = 10;
    private static final int SO_TIMEOUT = 0;
    private static final boolean REUSE_ADDR = false;
    private static final int BACK_LOG = 2; // 超过这个阈值的请求会被拒绝

    // client socket listen property on server endpoint;
    private static final boolean CLI_KEEPALIVE = false;
    private static final boolean CLI_OOB = false; // 是否先发一个字符进行试探
    private static final int CLI_REV_BUFFER = 20;
    private static final boolean CLI_REUSE_ADDR = false; // 是否重用地址
    private static final int CLI_SEND_BUFFER = 20;
    private static final boolean CLI_LINGER = true; // 断开连接的速度
    private static final int CLI_LINGER_N = 0;
    private static final int CLI_TIMEOUT = 0; // 客户端读取超时时间
    private static final boolean CLI_NO_DELAY = false; // 是否缓冲发送数据

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(9000), BACK_LOG);
            server.setReceiveBufferSize(RECEIVE_BUTTER);
            server.setReuseAddress(REUSE_ADDR);
            server.setSoTimeout(SO_TIMEOUT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server up with port 9000!");

        while (true) {
            // BIO 慢的原因有两个：
            // 1. 主线程要一直阻塞等待一个连接的进入。
            // 2. 当线程进入的时候，需要进行系统调用创建新的线程进行处理。即便是预先创建出线程池，线程切换也会有很多损耗。
            try {
                System.in.read(); // FIXME: 只是为了测试使用

                Socket clientHandler = server.accept(); // 没有连接会阻塞
                System.out.println("Client port: " + clientHandler.getPort());

                clientHandler.setKeepAlive(CLI_KEEPALIVE);
                clientHandler.setOOBInline(CLI_OOB);
                clientHandler.setReceiveBufferSize(CLI_REV_BUFFER);
                clientHandler.setReuseAddress(CLI_REUSE_ADDR);
                clientHandler.setSendBufferSize(CLI_SEND_BUFFER);
                clientHandler.setSoLinger(CLI_LINGER, CLI_LINGER_N);
                clientHandler.setSoTimeout(CLI_TIMEOUT);
                clientHandler.setTcpNoDelay(CLI_NO_DELAY);

                new Thread(() -> {
                    while (true) {
                        try {
                            InputStream inputStream = clientHandler.getInputStream(); // 询问状态，获取输入流，阻塞
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            char[] data = new char[1024];
                            int num = bufferedReader.read(data); // 读数据会阻塞

                            if (num > 0) {
                                System.out.println("Client read " + "[" + num + "]: " + new String(data, 0, num));
                            } else if (num == 0) {
                                System.out.println("Client read nothing...");
                                continue;
                            } else {
                                System.out.println("Client read -1...");
                                break;
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
