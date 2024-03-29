package com.jan.wat.EquServer.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.enetry.CommandParameterHandle;
import com.jan.wat.EquServer.enetry.DataHandle;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.netty.channel.ChannelHandlerContext;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

@Component
public class FunctionHandle {

    public List<EquDatatype> dataTypeList = null;

    @Autowired
    DatabaseHandle dbHandle;
    @Autowired
    SendHandle sendHandle;
    @Autowired
    IEquDatatypeService iEquDatatypeService;
    @Autowired
    IEquCommandService iEquCommandService;
    @Autowired
    IEquUpdateprogramrecordService iEquUpdateprogramrecordService;
    public void init(){
        QueryWrapper<EquDatatype> queryDataType = new QueryWrapper<>();
        queryDataType.select("ID,Name,IfAlarm,IfAccumulate");
        queryDataType.eq("IfAccumulate", "1").or().eq("IfAlarm", "1");
        dataTypeList = iEquDatatypeService.list(queryDataType);
    }

    public void funcUploadData(FrameStructure frame, InetSocketAddress sender, InetSocketAddress recipient) {
        DataHandle dh = new DataHandle();

        if (!dh.Load(frame.getData(), dataTypeList)) return;//数据个数不正确，返回
        ChannelHandlerContext ctx = null;
        dbHandle.EquipmentTabUpdateData(0,1, dh, ctx, frame,sender,recipient.getPort());
    }

    public void funcBatteryPowerUploadData(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender, InetSocketAddress recipient) {
        DataHandle dh = new DataHandle();

        if (!dh.Load(frame.getData(), dataTypeList)) return;//数据个数不正确，返回
        dbHandle.EquipmentTabUpdateData(1,0, dh, ctx, frame,sender, recipient.getPort());
    }

    public void funcDownloadProgram(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        if (frame.getData().length != 10) return;
        int index = Tools.SixteenToTen_ReturnInt(Tools.ByteSubstring(frame.getData(), 0, 4));
        if (index < 0) return;
        int len = Tools.SixteenToTen_ReturnInt(Tools.ByteSubstring(frame.getData(), 4, 4));
        if (len < 1) return;

        QueryWrapper<EquCommand> queryCommand = new QueryWrapper<>();
        queryCommand.eq("Equipment_ID", frame.getId());
        queryCommand.select("top 1 *");
        queryCommand.isNotNull("SendTime");
        queryCommand.eq("CommandType", (int)(Command.UpdateProgram));
        queryCommand.orderBy(true,false,"SendTime","SettingTime");
        List<EquCommand> list = iEquCommandService.list(queryCommand);
        //判断没有发送成功的命令的条件：1 设备ID一致 2 Status<=2（没有成功） 3 ResponseTime为空（没有应答） 4 SendNum发送次数小于最大次数限制 5 SendTime is not null
        if (list.size() < 1) return;
        String program = list.get(0).getCommand();
        Iterator<Element> it = Tools.XML2iter(program);
        if(!it.hasNext())
            return;
        Element child = it.next();
        String lenStr = child.element("L").getTextTrim();
        String path = child.element("P").getTextTrim();

        //读取txt文件
        String str = "";
        String line;
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            line = reader.readLine();
            while(line != null){
                str = line;
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((index + len) > str.length()) return;

        String data = str.substring(index, len);

        if (len > 4)
        {
            //判断后5位中是否有寄存器地址，即@04000，寄存器地址不可截断
            String temp = data.substring(data.length() - 5);
            int inde = temp.lastIndexOf("@");
            if (inde >= 0)
            {
                data = data.substring(0, data.length() - 5 + inde);
            }
        }

        byte[] buffer = new byte[4+4+data.length()+2];
        System.arraycopy(frame.getData(), 0, buffer, 0, 4);//index

        //byte[] crc1 = Tools.CRC16(frame.Data);
        //长度
        byte[] lenByte = Tools.strToToHexByteArray(String.format("%08s",data));//左边补0，确保8位,十进制转16进制
        System.arraycopy(lenByte, 0, buffer, 4, 4);//length

        //byte[] crc2 = Tools.CRC16(lenByte);

        byte[] bData= data.getBytes();
        System.arraycopy(bData, 0, buffer, 8, bData.length);//内容

        //CRC校验
        //byte[] buffer_CRC = new byte[4 + 4 + data.Length];
        //Buffer.BlockCopy(buffer, 0, buffer_CRC, 0, buffer_CRC.Length);

        byte[] crc = Tools.CRC16(buffer, 4 + 4 + data.length());
        System.arraycopy(crc, 0, buffer, 8+bData.length, 2);//CRC

        sendHandle.sendData(ctx, frame.GetBuffer((byte)Command.DownloadProgramResponse, buffer),sender);

    }


    public void funcUpdateProgramResponse(FrameStructure frame) {
        if (frame.getData().length != 1) return;
        //int status = 5;
        //int recordStatus = 1;

        UpdateWrapper<EquUpdateprogramrecord> updateProgram = new UpdateWrapper<>();
        updateProgram.set("ResponseTime", DateTime.DateNow());
        updateProgram.eq("Equipment_ID", frame.getId());

        switch (frame.getData()[0])
        {
            case 0x01:
                String responseMessage = "成功";
                //status = 4;
                UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
                updateCommand.set("Status", 4);
                updateCommand.set("ResponseTime", DateTime.DateNow());
                updateCommand.set("ResponseMessage", responseMessage);
                updateCommand.eq("Equipment_ID", frame.getId());
                updateCommand.eq("CommandType", (int)(Command.UpdateProgram));
                updateCommand.le("Status",2);
                updateCommand.isNull("ResponseTime");
                updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquCommandService.update(null,updateCommand);
                //recordStatus = 2;
                //更新程序记录表，开始下载
                updateProgram.isNull("ResponseTime");
                updateProgram.set("Status", 2);
                updateProgram.eq("Status",1);
                iEquUpdateprogramrecordService.update(null,updateProgram);
                break;
            case 0x02:
                //recordStatus = 3;
                //更新程序记录表,成功，更新条件，一天之内开始下载的，status=2表示开始下载
                updateProgram.set("Status", 3);
                updateProgram.eq("Status",2);
                updateProgram.gt("ResponseTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquUpdateprogramrecordService.update(null,updateProgram);
                break;
            case 0x03:
                //recordStatus = 4;
                //更新程序记录表，失败
                updateProgram.set("Status", 4);
                updateProgram.eq("Status",2);
                updateProgram.gt("ResponseTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquUpdateprogramrecordService.update(null,updateProgram);
                break;
            default:
                //recordStatus = 4;
                //更新程序记录表，失败
                updateProgram.set("Status", 3);
                updateProgram.eq("Status",2);
                updateProgram.gt("ResponseTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquUpdateprogramrecordService.update(null,updateProgram);
                break;
        }
    }

    public void funcSetTimeParameterResponse(FrameStructure frame) {
        if (frame.getData().length != 1) return;
        String responseMessage = "";
        int status = 5;
        switch (frame.getData()[0])
        {
            case 0x00:
                responseMessage = "成功";
                status = 4;
                break;
            case 0x01:
                responseMessage = "校验失败";
                break;
            case 0x02:
                responseMessage = "写EEPROM失败";
                break;
            default:
                responseMessage = "失败";
                break;
        }
        UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
        updateCommand.set("Status", status);
        updateCommand.set("ResponseTime", DateTime.DateNow());
        updateCommand.set("ResponseMessage", responseMessage);
        updateCommand.eq("Equipment_ID", frame.getId());
        updateCommand.eq("CommandType", (int)(Command.SetTimeParameter));
        updateCommand.le("Status",2);
        updateCommand.isNull("ResponseTime");
        updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
        iEquCommandService.update(null,updateCommand);

    }

    public void funcSetParameterValueResponse(FrameStructure frame) {
        if (frame.getData().length!=1) return;
        String responseMessage = "";
        int status = 5;
        switch (frame.getData()[0])
        {
            case 0x00:
                responseMessage = "成功";
                status = 4;
                break;
            case 0x01:
                responseMessage = "校验失败";
                break;
            case 0x02:
                responseMessage = "写EEPROM 失败";
                break;
            default:
                responseMessage = "失败";
                break;
        }

        UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
        updateCommand.set("Status", status);
        updateCommand.set("ResponseTime", DateTime.DateNow());
        updateCommand.set("ResponseMessage", responseMessage);
        updateCommand.eq("Equipment_ID", frame.getId());
        updateCommand.eq("CommandType", (int)(Command.SetParameterValue));
        updateCommand.le("Status",2);
        updateCommand.isNull("ResponseTime");
        updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
        iEquCommandService.update(null,updateCommand);
    }

    public void funcUploadParameterValue(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        CommandParameterHandle cph = new CommandParameterHandle();
        if (!cph.load(frame.getData(), 2)) return;//数据个数不正确，返回
        if (dbHandle.UpdateEquipmentParaValue(cph, frame.getId()))
        {
            if (cph.getStatus() == 0x00)//表示点名上传数据，需要更新命令表
            {
                UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
                updateCommand.set("Status", 4);
                updateCommand.set("ResponseTime",DateTime.DateNow());
                updateCommand.eq("Equipment_ID", frame.getId());
                updateCommand.eq("CommandType", (int)(Command.ReadParameterValue));
                updateCommand.lt("Status",2);
                updateCommand.isNull("ResponseTime");
                updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquCommandService.update(null,updateCommand);
            }
            byte[] data = new byte[1];
            data[0] = 0x01;//应答，表示成功
            sendHandle.sendData(ctx, frame.GetBuffer((byte)Command.ReadParameterValue, data),sender);
        }
    }


    public void funcUploadParameter(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {

        CommandParameterHandle cph = new CommandParameterHandle();

        if (!cph.load(frame.getData(),1)) return;//数据个数不正确，返回

        if (dbHandle.UpdateEquipmentPara(cph,frame.getId()))
        {
            if (cph.status == 0x00)//表示点名上传数据，需要更新命令表
            {
                UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
                updateCommand.eq("Equipment_ID",frame.getId());
                updateCommand.eq("CommandType",(int)(Command.ReadParameter));
                updateCommand.le("Status",2);
                updateCommand.isNull("ResponseTime");
                updateCommand.set("Status",4);
                updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquCommandService.update(null,updateCommand);
            }
//            System.out.println(frame);
            byte[] data = new byte[1];
            data[0] = 0x01;//应答，表示成功
            sendHandle.sendData(ctx, frame.GetBuffer((byte)Command.ReadParameter, data),sender);
        }
    }


    public void funcKeepAlive(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        //要求有应答
        if (frame.getData().length == 1 && frame.getData()[0] == 0x01)
        {
            byte[] sendD = frame.GetBuffer((byte)Command.KeepAliveResponse);
            sendHandle.sendData(ctx, sendD,sender);
        }
    }

    public void funcTimeSynchronization(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        //if(frame.Data.Length != 2) return;
        //校正时间
        CommandParameterHandle cph = new CommandParameterHandle();
        byte[] data = cph.GetTimeByte(frame, false, (byte)Command.TimeSynchronizationResponse);
//        byte[] data = {1,2,3,4,5,6};
        if (data != null)
        {
            sendHandle.sendData(ctx, data, sender);
        }
    }

    public void funcLogin(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender, InetSocketAddress recipient)  {
        if (frame.getData().length != 2) return;

        //应答，不管是否插入成功都应答，因为数据库中有这个设备，就插入不成功，但不应答，设备以为没有登录成功呢，所以要应答
        byte[] sendD = frame.GetBuffer(Command.LoginResponse);//0x8F

        sendHandle.sendData(ctx, sendD,sender);
        //设备表添加数据
        try {
            dbHandle.Equipment_Insert(sender, recipient,frame.getAddress(), frame.getVersion(), frame.getId(), frame.getData());
        }catch (Exception e){

        }finally {
            if (recipient.getPort() != GlobalParameter.udpPort_Equipment1)//2018-5-27，老设备不支持时间校正，如果时间校正，会发生错误
            {
                //校正时间
                CommandParameterHandle cph = new CommandParameterHandle();
                byte[] data = cph.GetTimeByte(frame, false, Command.SetTimeParameter);
                if (data != null)
                {
                    sendHandle.sendData(ctx, data, sender);
                }
            }
        }

        //记录应当，临时2017-4-16
        //RecordResponse(DateTime.Now, frame.Id, 1, (IPEndPoint)epClient, sendData.ToString());



    }


}
