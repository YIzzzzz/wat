package com.jan.wat.EquServer.handle;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.enetry.DataCell;
import com.jan.wat.EquServer.enetry.DataHandle;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.udpNetty.BootNettyUdpClient;
import com.jan.wat.mapper.EquServerMapper;
import com.jan.wat.pojo.EquCommand;
import com.jan.wat.pojo.EquServer;
import com.jan.wat.service.IEquCommandService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

@Component
public class SendHandle {

    @Autowired
    IEquCommandService iEquCommandService;

    @Autowired
    EquServerMapper equServerMapper;

    @Autowired
    BootNettyUdpClient bootNettyUdpClient;

    public void client(String equipmentId, DataHandle dataHandle){

        List<EquServer> serverList = equServerMapper.getServerList(equipmentId);

        for (EquServer item : serverList)
        {
            String ipAddress = item.getIpaddress();
            String port = String.valueOf(item.getPort());
            if ((ipAddress != null) && (ipAddress != "") && (port != null) && (port != ""))
            {
                InetSocketAddress ip = new InetSocketAddress(ipAddress, Integer.parseInt(port));
                String sendStr = equipmentId + "," + dataHandle.getCollectTime().toString() + ",";
                //按照Position排序发送
                DataCell[] dataCells = dataHandle.getDataCells();

                Arrays.sort(dataCells);

                for (int j = 0; j < dataHandle.getDataCells().length; j++)
                {
                    if (dataHandle.getDataCells()[j].getIfRecord() == 1)
                    {
                        sendStr += dataHandle.getDataCells()[j].getValue() + ",";
                    }
                }
                if (sendStr.charAt(sendStr.length() - 1) == ',') sendStr = sendStr.substring(0,sendStr.length()-1);

                //需要转换ASCII编码
                bootNettyUdpClient.bind(9998,sendStr.getBytes(),ip);
            }
        }
    }

    public void sendCommand(EquCommand model, FrameStructure frame, ChannelHandlerContext ctx, InetSocketAddress sender) {
        //构成要发送的数据
        CommandParameterHandle cph = new CommandParameterHandle();
        byte[] data = cph.GetCommandData(model, frame);

        if (data != null)
        {
            sendData(ctx, data, sender);

            //更新发送记录
            int num = model.getSendnum()+1;
            int status = 2;
            if(num>= GlobalParameter.commandSendMaxNumLimit)
                status = 3;
            UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
            updateCommand.set("SendTime", DateTime.DateNow());
            updateCommand.set("SendNum", num);
            updateCommand.set("Status",2);
            updateCommand.eq("ID",model.getId());
            iEquCommandService.saveOrUpdate(null,updateCommand);
        }
    }


    protected void sendData(ChannelHandlerContext ctx, byte[] buffer, InetSocketAddress sender){
        ctx.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(buffer), sender));
    }

}
