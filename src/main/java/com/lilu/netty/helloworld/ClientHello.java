package com.lilu.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class ClientHello {
    private final String host;
    private final int port;

    public ClientHello(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap(); // 客户端使用 Bootstrap
            bs.group(group)
                    .channel(NioSocketChannel.class) // Channel 初始化
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandlerHello());
                        }
                    });

            // 代表 I/O 操作的执行结果，通过事件机制，获取执行结果。这里等待连接成功后继续往下执行。
            ChannelFuture future = bs.connect().sync();

            // 收到
            future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8));

            // 阻塞。closeFuture 开启了一个 channel 的监听器
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }

    // 主入口
    public static void main(String[] args) {
        new ClientHello("127.0.0.1", 18080).run();
    }
}
