package com.jan.wat.EquServer.udpNetty;

import com.jan.wat.EquServer.handle.EquipmentDataHandle;
import com.jan.wat.mapper.EquEquipmentMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Data
@Component
@ChannelHandler.Sharable
public class BootNettyUdpSimpleChannelInboundHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Autowired
    EquEquipmentMapper equEquipmentMapper;
    @Autowired
    EquipmentDataHandle equipmentDataHandle;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        try {

            ByteBuf strdata = msg.content();
            System.out.println(ctx.channel().localAddress().toString());
            System.out.println(msg.recipient().getPort());
//            String strdata = msg.content().toString(CharsetUtil.ISO_8859_1);
            byte[] data = new byte[strdata.writerIndex()-strdata.readerIndex()];
            strdata.readBytes(data);

            equipmentDataHandle.HandleData(data,0,ctx,msg.sender(),msg.recipient());
            //打印收到的消息
//
//            for(byte by : b){
//                System.out.print(by+" ");
//            }
//            System.out.println();
            //收到udp消息后，可通过此方式原路返回的方式返回消息，例如返回时间戳
//            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("53 59 4C0000000000", CharsetUtil.UTF_8), msg.sender()));
//            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(System.currentTimeMillis()/1000+"", CharsetUtil.UTF_8), msg.sender()));
        } catch (Exception e) {

        }
    }

}