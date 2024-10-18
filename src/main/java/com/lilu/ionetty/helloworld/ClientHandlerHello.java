package com.lilu.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * byteBuf 使用了零拷贝技术，也是 netty 高性能的原因之一
 */
@ChannelHandler.Sharable
public class ClientHandlerHello extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Received: " + byteBuf.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); // 出现异常后，关闭连接
    }
}
