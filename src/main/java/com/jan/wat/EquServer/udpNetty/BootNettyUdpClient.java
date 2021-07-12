package com.jan.wat.EquServer.udpNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Data
@Component
public class BootNettyUdpClient {

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private Channel channel;

    public void bind(int port, byte[] bytes, InetSocketAddress ip) {

        eventLoopGroup = new NioEventLoopGroup();
        try {
            //UDP方式使用Bootstrap
            bootstrap = new Bootstrap();
            bootstrap = bootstrap.group(eventLoopGroup);
            bootstrap = bootstrap.channel(NioDatagramChannel.class);
            bootstrap = bootstrap.option(ChannelOption.SO_BROADCAST, true);
            //不需要太多其他的东西，直接这样就可以用
            bootstrap = bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
                @Override
                protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
                    nioDatagramChannel.pipeline().addLast(new ClientHandler());
                }
            });
            channel = bootstrap.bind(port).sync().channel();

            ByteBuf byteBuf = new UnpooledByteBufAllocator(false).buffer();
            byteBuf.writeBytes(bytes);

            DatagramPacket datagramPacket = new DatagramPacket(byteBuf, ip);
            channel.writeAndFlush(datagramPacket);

            channel.closeFuture().sync();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}

class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        //处理
    }
}
