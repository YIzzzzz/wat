package com.jan.wat.EquServer.handle;


import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.service.*;
import io.lettuce.core.ScriptOutputType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EquipmentDataHandle {
    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @Autowired
    FunctionHandle func;


    @Async("doSomethingExecutor")
    public void HandleData(byte[] data, int equipmentNumLimit, ChannelHandlerContext ctx, InetSocketAddress sender, InetSocketAddress recipient){

        FrameStructure frame = new FrameStructure();
        System.out.println(frame);
        if (!frame.Load(data, Tools.systemIDCode))
            return;//数据长度不正确，或者系统识别码不正确，则返回
        //$$//数量超限，直接退出EquipmentDataHandle100行
        if(func.dataTypeList==null)
            func.init();

        switch(frame.getFrameType()){
            case Command.TimeSynchronization:
                //用于同步（com.SendData）
                func.funcTimeSynchronization(ctx, frame, sender);
                break;
            case Command.Login:
                //设备注册信息，向equ_Equipment表中插入数据
                func.funcLogin(ctx, frame,sender,recipient);
                break;
            case Command.BatteryPowerUploadData:
                func.funcBatteryPowerUploadData(ctx, frame, sender, recipient);
                break;
            case Command.UploadData:
                func.funcUploadData(frame,sender,recipient);
                break;
            case Command.KeepAlive:
                func.funcKeepAlive(ctx, frame, sender);
                break;
            case Command.UpdateParameter:
                func.funcUploadParameter(ctx,frame,sender);
                break;
            case Command.UpdateParameterValue:
                func.funcUploadParameterValue(ctx, frame, sender);
                break;
            case Command.SetParameterValueResponse:
                func.funcSetParameterValueResponse(frame);
                break;
            case Command.SetTimeParameterResponse:
                func.funcSetTimeParameterResponse(frame);
                break;
            case Command.UpdateProgramResponse:
                func.funcUpdateProgramResponse(frame);
                break;
            case Command.DownloadProgram:
                func.funcDownloadProgram( ctx,frame,sender);
                break;
            //case (byte)Command.UploadParameterValue:
            //    //CommandReturn(frame, (byte)Command.ReadNetConfig);
            //    break;
            //使用CommandReturn...............
            default:
                break;
        }

    }

}
