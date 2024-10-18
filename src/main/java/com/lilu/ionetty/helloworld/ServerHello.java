package com.lilu.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class ServerHello {
    private final int port;

    public ServerHello(int port) {
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sbs = new ServerBootstrap(); // 服务端使用 ServerBootstrap
            sbs.group(group)
                    .channel(NioServerSocketChannel.class) // 通过工厂化方法实例化一个 channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandlerHello());
                        }
                    });

            // 绑定服务器
            ChannelFuture channelFuture = sbs.bind().sync();
            System.out.println("Listening on port " + port);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new ServerHello(18080).run();
    }
}
