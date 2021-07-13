package com.jan.wat.EquServer.udpNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("")
public class BootNettyUdpServer {

    @Autowired
    BootNettyUdpSimpleChannelInboundHandler bootNettyUdpSimpleChannelInboundHandler;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap serverBootstrap;
    private ChannelFuture channelFuture;
    private ChannelFuture channelFuture1;
    private ChannelFuture channelFuture2;
    /**
     * 启动服务
     */
    public void bind(int port, int port2, int port3) {

        eventLoopGroup = new NioEventLoopGroup();
        try {
            //UDP方式使用Bootstrap
            serverBootstrap = new Bootstrap();
            serverBootstrap = serverBootstrap.group(eventLoopGroup);
            serverBootstrap = serverBootstrap.channel(NioDatagramChannel.class);
            serverBootstrap = serverBootstrap.option(ChannelOption.SO_BROADCAST, true);
            //不需要太多其他的东西，直接这样就可以用
            serverBootstrap = serverBootstrap.handler(bootNettyUdpSimpleChannelInboundHandler);

            System.out.println("netty udp start!");

            channelFuture = serverBootstrap.bind(port).sync();
            channelFuture1 = serverBootstrap.bind(port2).sync();
            channelFuture2 = serverBootstrap.bind(port3).sync();

            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("netty udp close!");
            eventLoopGroup.shutdownGracefully();
        }
    }




}