package com.jan.wat.EquServer.udpNetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BootNettyUdpInitializer extends ChannelInitializer<NioDatagramChannel> {

    private EventLoopGroup group = new NioEventLoopGroup();
    @Autowired
    BootNettyUdpSimpleChannelInboundHandler bootNettyUdpSimpleChannelInboundHandler;

    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();
        pipeline.addLast(group, bootNettyUdpSimpleChannelInboundHandler);
    }
}
