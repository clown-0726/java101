package com.lilu.iosocket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSimple {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket();  // 通过 system call 申请新的 FD
            server.bind(new InetSocketAddress(9000), 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server up with port 9000!");

        while (true) {
            try {
                Socket clientHandler = server.accept(); // 没有连接会阻塞，使用 system call 的 accept
                System.out.println("Client port: " + clientHandler.getPort());

                // 通过 system call 创建新的线程（clone 创建新的线程）
                new Thread(() -> {
                    while (true) {
                        try {
                            InputStream inputStream = clientHandler.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            char[] data = new char[1024];
                            int num = bufferedReader.read(data); // 读数据会阻塞，使用 system call 的 read

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
