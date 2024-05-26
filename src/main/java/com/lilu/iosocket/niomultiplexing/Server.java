package com.lilu.iosocket.niomultiplexing;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private ServerSocketChannel server = null;
    // kernel 的 select poll epoll 多路复用器在 java 层面都被抽象成了 Selector 类
    // 可以在 java 启动的时候通过参数设置，但是受制于操作系统是否支持（windows 不支持 epoll）
    private Selector selector = null;
    int port = 9000;

    public void initServer() {
        try {
            // 服务端三部曲：socket(), bind(), listen()
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            /**
             * 如果不指定，优先选择三个复用器（epoll > poll > select）
             * 如果是 epoll 模型下，通过系统调用 epoll_create 在 kernel 开辟一个空间，并返回程序一个指向这个空间的 FD
             */
            selector = Selector.open();

            /**
             * server 约定于监听状态的 FD = listen(FD)
             * 1. select/poll 模型下：在 jvm 中开辟数据，存放所有代表连接的 FDs
             * 2. epoll 模型下：将返回的的 FD 注册到 epoll_ctl 系统调用方法（将这些 FDs 传给 kernel）
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        initServer();
        System.out.println("Server start...");
        try {
            while (true) {
                System.out.println("Key size: " + selector.keys().size());

                /**
                 * IO 状态询问，会阻塞
                 * 1. select/poll 模型下：将 jvm 开辟的数组中存放所有代表连接的 FDs 通过系统调用方法 select 一次传递给 kernel
                 *    由 kernel 遍历这些 FDs 指向的 IO 询问状态
                 * 2. epoll 模型下：通过 epoll 的系统调用方法 epoll_ctl 拿到有状态的 IO
                 */
                while (selector.select(500) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 返回有状态的 FD 集合
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    /**
                     * 不管是什么多路复用器，拿到的只是有状态的 FDs，真正读数据的时候仍然需要通过系统调用 recv 方法阻塞读取数据
                     */
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();

                        if (key.isAcceptable()) {
                            /**
                             * 处理 listen 状态的 FD，如果有新连接，则创建一个新的 R/W socket
                             * 1. select/poll 模型下：将这个新的 R/W socket 放到 jvm 的 FD 列表中
                             * 2. epoll 模型下：通过 epoll 的系统调用方法 epoll_ctl 放到开辟的 kernel 空间中
                             */
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            key.cancel(); // 规避事件在单独线程重处理的时候重复调起
                            readHandler(key);
                        } else if (key.isWritable()) {
                            key.cancel();
                            writeHandler(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel client = null;
        try {
            client = ssc.accept();
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
            client.register(selector, SelectionKey.OP_READ, buffer); // 根据不同模型，不同操作！
            System.out.println("--------------");
            System.out.println(client.getRemoteAddress());
            System.out.println("--------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHandler(SelectionKey key) {
        new Thread(() -> { // 在新线程中处理
            System.out.println("Read handler...");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            int read = 0;
            try {
                while (true) {
                    read = client.read(buffer);
                    System.out.println(Thread.currentThread().getName() + "..." + read);
                    if (read > 0) {
                        client.register(key.selector(), SelectionKey.OP_WRITE, buffer);
                    } else if (read == 0) {
                        break;
                    } else {
                        client.close();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void writeHandler(SelectionKey key) {
        new Thread(() -> { // 在新线程中处理
            System.out.println("Write handler...");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.flip();
            while (buffer.hasRemaining()) {
                try {
                    client.write(buffer);
                    Thread.sleep(2000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
