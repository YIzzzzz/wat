package com.jan.wat.EquServer.helper;

import com.jan.wat.EquServer.enetry.DataCell;
import com.jan.wat.EquServer.enetry.DataHandle;
import com.jan.wat.EquServer.udpNetty.BootNettyUdpClient;
import com.jan.wat.mapper.EquServerMapper;
import com.jan.wat.pojo.EquServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

@Component
public class Send {

    @Autowired
    EquServerMapper equServerMapper;

    @Autowired
    BootNettyUdpClient bootNettyUdpClient;

    public void Client(String equipmentId, DataHandle dataHandle){

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

}
