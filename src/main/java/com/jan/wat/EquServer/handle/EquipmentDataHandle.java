package com.jan.wat.EquServer.handle;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.enetry.DataHandle;
import com.jan.wat.EquServer.helper.Agreement;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.enetry.ParaCell;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Send;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.mapper.*;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.var;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

@Component
public class EquipmentDataHandle {


//    EquEquipmentMapper equEquipmentMapper;
    @Autowired
    IEquEquipmentService iEquEquipmentService;
    @Autowired
    IEquEquipmentparaService iEquEquipmentparaService;
    @Autowired
    IEquEquipmentparavaluerangeService iEquEquipmentparavaluerangeService;
    @Autowired
    IEquSimService iEquSimService;
    @Autowired
    IEquCommandService iEquCommandService;
    @Autowired
    IEquUpdateprogramrecordService iEquUpdateprogramrecordService;
    @Autowired
    IEquDatatypeService iEquDatatypeService;
    @Autowired
    IEquEquipmentrealdataService iEquEquipmentrealdataService;
    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;
    @Autowired
    EquipmentdataMapper equipmentdataMapper;

    @Autowired
    Send send;

    private List<EquDatatype> dataTypeList = null;
    private static List<String> idListForTransform = new ArrayList<>(Arrays.asList( "5169", "4960", "4962", "4963", "4965", "4966", "4967", "4968", "4970", "4976", "4977", "4978", "4982", "4986", "4987", "4990", "4991", "4992", "4994"));


    public void init(){
        QueryWrapper<EquDatatype> queryDataType = new QueryWrapper<>();
        queryDataType.select("ID,Name,IfAlarm,IfAccumulate");
        queryDataType.eq("IfAccumulate", "1").or().eq("IfAlarm", "1");
        dataTypeList = iEquDatatypeService.list(queryDataType);
    }

    public void HandleData(byte[] data, int equipmentNumLimit, ChannelHandlerContext ctx, InetSocketAddress sender, InetSocketAddress recipient){
        FrameStructure frame = new FrameStructure();
        if (!frame.Load(data, Tools.systemIDCode))
            return;//数据长度不正确，或者系统识别码不正确，则返回

        //$$//数量超限，直接退出EquipmentDataHandle100行
        if(dataTypeList==null)
            init();

        switch(frame.getFrameType()){
            case Command.TimeSynchronization:
                //用于同步（com.SendData）
                funcTimeSynchronization(ctx, frame, sender);
                break;
            case Command.Login:
                //设备注册信息，向equ_Equipment表中插入数据
                funcLogin(ctx, frame,sender,recipient);
                break;
            case Command.BatteryPowerUploadData:

                funcBatteryPowerUploadData(ctx, frame, sender, recipient);
                break;
            case Command.UploadData:
                funcUploadData(frame,sender,recipient);
                break;
            case Command.KeepAlive:
                funcKeepAlive(ctx, frame, sender);
                break;
            case Command.UpdateParameter:
                funcUploadParameter(ctx,frame,sender);
                break;
            case Command.UpdateParameterValue:
                funcUploadParameterValue(ctx, frame, sender);
                break;
            case Command.SetParameterValueResponse:
                funcSetParameterValueResponse(frame);
                break;
            case Command.SetTimeParameterResponse:
                funcSetTimeParameterResponse(frame);
                break;
            case Command.UpdateProgramResponse:
                funcUpdateProgramResponse(frame);
                break;
            case Command.DownloadProgram:
                funcDownloadProgram( ctx,frame,sender);
                break;
            //case (byte)Command.UploadParameterValue:
            //    //CommandReturn(frame, (byte)Command.ReadNetConfig);
            //    break;
            //使用CommandReturn...............
            default:
                break;
        }

    }

    private void funcUploadData( FrameStructure frame, InetSocketAddress sender,InetSocketAddress recipient) {
        EquipmentTabUpdateData(1, frame.getVersion(), frame.getData(), frame.getId(),sender,recipient);
    }

    private void funcBatteryPowerUploadData(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender, InetSocketAddress recipient) {
        if (EquipmentTabUpdateData(0,  frame.getVersion(), frame.getData(), frame.getId(),sender, recipient)) {
            boolean flag = false;

            if (sender.getPort() != GlobalParameter.udpPort_Equipment1)//2018-5-27，老设备不支持下传命令
            {
                //读数据库，看看是否有对该设备发送的命令
                //判断没有发送成功的命令的条件：1 设备ID一致 2 Status<=2（没有成功） 3 ResponseTime为空（没有应答） 4 SendNum发送次数小于最大次数限制,按照SettingTime排序，也就是先发以前的命令
                QueryWrapper<EquCommand> queryCommand = new QueryWrapper<>();
                queryCommand.eq("Equipment_ID", frame.getId());
                queryCommand.le("Status",2);
                queryCommand.isNull("ResponseTime");
                queryCommand.lt("SendNum", GlobalParameter.commandSendMaxNumLimit);
                queryCommand.orderBy(true,false,"SettingTime");
                List<EquCommand> list = iEquCommandService.list(queryCommand);

                if (list.size() > 0) flag = true;

                //应答
                byte[] sendData = frame.GetBuffer((byte)Command.BatteryPowerUploadDataResponse, Agreement.BatteryPowerResponse(flag));
                sendData(ctx, sendData, sender);
                //发送命令
                for (EquCommand item : list){
                    sendCommand(item, frame, ctx, sender);
                }
            }
            else
            {
                byte[] sendData = frame.GetBuffer((byte)Command.BatteryPowerUploadDataResponse, Agreement.BatteryPowerResponse(flag));
                sendData(ctx, sendData, sender);
            }
            //记录应答，临时2017-4-16
            //DataHandle dh = new DataHandle();
            //dh.Load(frame.Data);
            //RecordResponse(dh.CollectTime, frame.Id, 2, (IPEndPoint)epClient, sendData.ToString());
        }
    }

    private void sendCommand(EquCommand model, FrameStructure frame, ChannelHandlerContext ctx, InetSocketAddress sender) {
        //构成要发送的数据
        CommandParameterHandle cph = new CommandParameterHandle();
        byte[] data = cph.GetCommandData(model, frame);

        if (data != null)
        {
            sendData(ctx, data, sender);

            //更新发送记录
            int num = model.getSendnum()+1;
            int status = 2;
            if(num>=GlobalParameter.commandSendMaxNumLimit)
                status = 3;
            UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
            updateCommand.set("SendTime", DateTime.DateNow());
            updateCommand.set("SendNum", num);
            updateCommand.set("Status",2);
            updateCommand.eq("ID",model.getId());
            iEquCommandService.saveOrUpdate(null,updateCommand);
        }
    }

    private boolean EquipmentTabUpdateData(int powerType, byte version, byte[] data, String id, InetSocketAddress sender, InetSocketAddress recipient) {
        DataHandle dh = new DataHandle();

        if (!dh.Load(data, dataTypeList)) return false;//数据个数不正确，返回
        //if (!dh.Load(data)) return false;//数据个数不正确，返回

        //设备表添加数据
        boolean returnValue = Equipment_Update(dh, powerType, sender, version, id,recipient.getPort());
        if (returnValue)//更新成功再进行以下步骤，也就是数据库中有这个设备
        {
            //判断RDASData******数据库是否存在
            String uploadMonth = DateTime.getMonthStr(dh.getCollectTime());

            //插入历史数据
            //if(id=="6000343" || id=="6000561")
            //    returnValue = (returnValue & EquipmentData_Insert(dh, dh.CollectTime, id, Tools.GetBCDStr(data)));
            //else returnValue = (returnValue & EquipmentData_Insert(dh, dh.CollectTime, id, ""));

            //returnValue = (returnValue & EquipmentData_Insert(dh, dh.CollectTime, id, ""));
            EquipmentData_Insert(uploadMonth, dh, dh.getCollectTime(), id, "");//20180817，不管成不成功都应答

            //处理报警
            //returnValue = (returnValue & HandleEquipmentAlarmRecord(dh, id));
            HandleEquipmentAlarmRecord(dh, id);
            if (recipient.getPort() == GlobalParameter.udpPort_Equipment2)//2018-5-27，只有这一个端口支持数据转发
            {
                //转给客户端
                send.Client(id, dh);
            }
            else if (idListForTransform.contains(id))
            { send.Client(id, dh); }

            //判断设备数据是否超限
            //HandleEquipmentDataError(id, dh);

            //水表数据处理,2018-5-15发掘数据应答缓慢，怀疑性能不行，所以取消这部分功能
            //if (WaterEvent != null)
            //    WaterEvent(id, dh);
        }
        return returnValue;
    }



    private void HandleEquipmentAlarmRecord(DataHandle dh, String id) {
//bool returnValue = true;

        for (var item : dh.getDataCells())
        {
            if (item.getIfAlarm() == 1)
            {
                if (item.getValue() == 1)
                {
                    EquAlarmrecord model = new EquAlarmrecord();
                    model.setEquipmentId(id);
                    model.setAlarmtype(1);//1设备报警， 2 离线报警， 3 数据超限报警
                    model.setAlarmtime(dh.getCollectTime());
                    model.setDatatypeId(item.getDataTypeId());
                    model.setReason("设备上传报警信息");
                    iEquAlarmrecordService.saveOrUpdate(model);
                    //returnValue = returnValue & service.AlarmRecord_Alarm(model);
                }
                else
                {
                    EquAlarmrecord model = new EquAlarmrecord();
                    model.setEquipmentId(id);
                    model.setAlarmtype(1);//1设备报警， 2 离线报警， 3 数据超限报警
                    model.setAlarmtime(dh.getCollectTime());
                    model.setDatatypeId(item.getDataTypeId());
                    iEquAlarmrecordService.saveOrUpdate(model);
                    //returnValue = returnValue & service.AlarmRecord_Recovery(model);
                }
            }
        }
    }

    private void EquipmentData_Insert(String uploadMonth, DataHandle dh, Date collectTime, String id, String str) {
        Equipmentdata equipmentdata = new Equipmentdata();
        equipmentdata.setCollecttime(dh.getCollectTime());
        equipmentdata.setEquipmentId(id);
        equipmentdata.setUploadtime(DateTime.DateNow());
        equipmentdata.setStr(str);
        equipmentdata.setData(dh.getEquipmentDataXml());
        equipmentdataMapper.insertData("rdasdata"+uploadMonth,equipmentdata);

    }

    private boolean Equipment_Update(DataHandle dh, int powerType, InetSocketAddress sender, byte version, String id, int serverPort) {
        boolean returnValue = true;
        EquEquipment model = new EquEquipment();
        model.setId(id);
        model.setIpaddress(sender.getAddress().toString().substring(1));

        model.setP(sender.getPort());
        model.setLastcollecttime(dh.getCollectTime());
        model.setVersion(String.format("%02x",version));
        model.setEquipmenttypeId(dh.getEquipmentTypeId());
        model.setPowertype(powerType);//电池供电
        model.setS(dh.isIfAlarm());
        //model.Data = dh.EquipmentXml;
        model.setLongitude((float) dh.getLongitude());
        model.setLatitude((float) dh.getLatitude());
        model.setConnectserverport(serverPort);
        ;
        returnValue=iEquEquipmentService.updateById(model);
        if (returnValue)//更新成功，再往下进行，也即是数据库中有这个设备，才插入实时数据
        {

            if (dh.getDataCells().length < 7)//这时候不能完全删除数据类型，再插入，如果这么操作，会造成查询历史数据错误，查询不出其他的数据类型
            {
                //20180811，先把所有实时数据清零，再更新真实数据，目的是为了解决变送器与GPRS模块通讯中断时，只上传GPRS信号强度
                clearEquipmentRealData(id, dh.getCollectTime());

                //20180815，只更新这几项，而且不用判断是否存在，直接update
                List<EquEquipmentrealdata> listRealData = new ArrayList<>();
                for (var item : dh.getDataCells())
                {
                    EquEquipmentrealdata dataCell = new EquEquipmentrealdata();
                    dataCell.setEquipmentId(id);
                    dataCell.setDatatypeId(item.getDataTypeId());
                    dataCell.setValue(item.getValue());
                    dataCell.setUnitId(item.getUnitId());
                    dataCell.setPosition(item.getPosition());
                    dataCell.setIfrecord(item.getIfRecord() == 1 ? true : false);
                    dataCell.setIfcurve(item.getIfCurve() == 1 ? true : false);
                    dataCell.setIfalarm(item.getIfAlarm() == 1 ? true : false);
                    dataCell.setIfaccumulate(item.getIfaccumulate() == 1 ? true : false);
                    dataCell.setLastupdatetime(dh.getCollectTime());
                    listRealData.add(dataCell);
                    //returnValue = returnValue & realDataService.InsertEquipmentRealData(dataCell);//插入实时数据
                }
                iEquEquipmentrealdataService.saveBatch(listRealData);

            }
            else
            {
                DeleteEquipmentRealData(id);//先删除，然后批量插入
                List<EquEquipmentrealdata> listRealData = new ArrayList<>();
                for (var item : dh.getDataCells())
                {
                    EquEquipmentrealdata realData = new EquEquipmentrealdata();

                    realData.setEquipmentId(id);
                    realData.setDatatypeId(item.getDataTypeId());
                    realData.setValue(item.getValue());
                    realData.setUnitId(item.getUnitId());
                    realData.setPosition(item.getPosition());
                    realData.setIfrecord(item.getIfRecord() == 1 ? true : false);
                    realData.setIfcurve(item.getIfCurve() == 1 ? true : false);
                    realData.setIfalarm(item.getIfAlarm() == 1 ? true : false);
                    realData.setIfaccumulate(item.getIfaccumulate() == 1 ? true : false);
                    realData.setUplimit((double) 0);
                    realData.setDownlimit((double) 0);
                    listRealData.add(realData);

                }
                iEquEquipmentrealdataService.saveBatch(listRealData);
            }
        }
        return returnValue;
    }

    private void DeleteEquipmentRealData(String equipment_ID) {
        QueryWrapper<EquEquipmentrealdata> deleteRealData = new QueryWrapper<>();
        deleteRealData.eq("Equipment_ID",equipment_ID);
        iEquEquipmentrealdataService.remove(deleteRealData);
    }

    private void clearEquipmentRealData(String equipment_ID, Date collectTime) {

        UpdateWrapper<EquEquipmentrealdata> updateRealData = new UpdateWrapper<>();
        updateRealData.set("Value",0);
        updateRealData.set("LastUpdateTime","\'"+collectTime.toString()+"\'");
        updateRealData.eq("Equipment_ID",equipment_ID);
        iEquEquipmentrealdataService.update(updateRealData);
    }


    private void funcDownloadProgram(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
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

        sendData(ctx, frame.GetBuffer((byte)Command.DownloadProgramResponse, buffer),sender);

    }


    private void funcUpdateProgramResponse(FrameStructure frame) {
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

    private void funcSetTimeParameterResponse(FrameStructure frame) {
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

    private void funcSetParameterValueResponse(FrameStructure frame) {
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

    private void funcUploadParameterValue(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        CommandParameterHandle cph = new CommandParameterHandle();
        if (!cph.Load(frame.getData(), 2)) return;//数据个数不正确，返回

        if (UpdateEquipmentParaValue(cph, frame.getId()))
        {
            if (cph.getStatus() == 0x00)//表示点名上传数据，需要更新命令表
            {
                UpdateWrapper<EquCommand> updateCommand = new UpdateWrapper<>();
                updateCommand.set("Status", 4);
                updateCommand.set("ResponseTime",DateTime.DateNow());
                updateCommand.eq("Equipment_ID", frame.getId());
                updateCommand.eq("CommandType", (int)(Command.ReadParameterValue));
                updateCommand.le("Status",2);
                updateCommand.isNull("ResponseTime");
                updateCommand.gt("SendTime",DateTime.DateNow(System.currentTimeMillis()-86400));
                iEquCommandService.update(null,updateCommand);
            }
            byte[] data = new byte[1];
            data[0] = 0x01;//应答，表示成功
            sendData(ctx, frame.GetBuffer((byte)Command.ReadParameterValue, data),sender);
        }
    }

    private boolean UpdateEquipmentParaValue(CommandParameterHandle cph, String equipment_ID) {
        boolean returnValue = true;
        for (ParaCell item : cph.getParaCells())
        {
            UpdateWrapper<EquEquipmentpara> updateEquPara = new UpdateWrapper<EquEquipmentpara>();
            updateEquPara.eq("Equipment_ID", equipment_ID);
            updateEquPara.eq("Para_ID", item.getParaId());
            updateEquPara.set("ParaValue", item.getValue());
            updateEquPara.set("UploadTime", DateTime.DateNow());
            returnValue = iEquEquipmentparaService.update(null,updateEquPara);

        }
        return returnValue;
    }

    private void funcUploadParameter(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {

        CommandParameterHandle cph = new CommandParameterHandle();

        if (!cph.Load(frame.getData(),1)) return;//数据个数不正确，返回

        if (UpdateEquipmentPara(cph,frame.getId()))
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
            byte[] data = new byte[1];
            data[0] = 0x01;//应答，表示成功
            sendData(ctx, frame.GetBuffer((byte)Command.ReadParameter, data),sender);
        }
    }

    private boolean UpdateEquipmentPara(CommandParameterHandle cph, String equipment_ID) {
        boolean returnValue = true;

        Map<String,Object> paraMap = new HashMap<String, Object>();
        paraMap.put("Equipment_ID", equipment_ID);
        iEquEquipmentparaService.removeByMap(paraMap);
        iEquEquipmentparavaluerangeService.removeByMap(paraMap);

        for (ParaCell item : cph.getParaCells())
        {
            EquEquipmentpara equEquipmentpara = new EquEquipmentpara();
            switch (item.getType())
            {
                case 1://置数型
                    equEquipmentpara.setEquipmentId(equipment_ID);
                    equEquipmentpara.setParaId(item.getParaId());
                    equEquipmentpara.setParavalue(item.getValue());
                    equEquipmentpara.setUploadtime(DateTime.DateNow());
                    equEquipmentpara.setUnitId(item.getUnitId());
                    equEquipmentpara.setUplimit(item.getUpLimit());
                    equEquipmentpara.setDownlimit(item.getDownLimit());
                    returnValue = iEquEquipmentparaService.save(equEquipmentpara);
                    break;
                case 2://选择型
                    equEquipmentpara.setEquipmentId(equipment_ID);
                    equEquipmentpara.setParaId(item.getParaId());
                    equEquipmentpara.setParavalue(item.getValue());
                    equEquipmentpara.setUploadtime(DateTime.DateNow());
                    equEquipmentpara.setUnitId(item.getUnitId());
                    returnValue = iEquEquipmentparaService.save(equEquipmentpara);
                    EquEquipmentparavaluerange equEquipmentparavaluerange = new EquEquipmentparavaluerange();
                    equEquipmentparavaluerange.setEquipmentId(equipment_ID);
                    equEquipmentparavaluerange.setParaId(item.getParaId());
                    for (int i : item.getValueRange()){

                        equEquipmentparavaluerange.setParavalueId(i);
                        iEquEquipmentparavaluerangeService.save(equEquipmentparavaluerange);
                    }
                break;
                case 3://字符型
                    equEquipmentpara.setEquipmentId(equipment_ID);
                    equEquipmentpara.setParaId(item.getParaId());
                    equEquipmentpara.setParavalue(item.getValue());
                    equEquipmentpara.setUploadtime(DateTime.DateNow());
                    returnValue = iEquEquipmentparaService.save(equEquipmentpara);
                    //如果是CCID_SIM参数，就更新equ_SIM表
                    if (String.valueOf(item.getParaId()).trim().equals("15"))
                    {
                        UpdateWrapper<EquSim> updateSIM = new UpdateWrapper<EquSim>();
                        updateSIM.set("Equipment_ID", equipment_ID);
                        updateSIM.eq("ID", item.getValue());
                        iEquSimService.update(null, updateSIM);
                    }
                    break;
                default:
                    returnValue = false;
                    break;
            }
        }
        return returnValue;
    }

    private void funcKeepAlive(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        //要求有应答
        if (frame.getData().length == 1 && frame.getData()[0] == 0x01)
        {
            byte[] sendData = frame.GetBuffer((byte)Command.KeepAliveResponse);
            sendData(ctx, sendData,sender);
        }
    }

    private void funcTimeSynchronization(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender) {
        //if(frame.Data.Length != 2) return;
        //校正时间
        CommandParameterHandle cph = new CommandParameterHandle();
        byte[] data = cph.GetTimeByte(frame, false, (byte)Command.TimeSynchronizationResponse);
        if (data != null)
        {
            sendData(ctx, data, sender);
        }
    }

    private void funcLogin(ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender, InetSocketAddress recipient)  {
        if (frame.getData().length != 2) return;

        //应答，不管是否插入成功都应答，因为数据库中有这个设备，就插入不成功，但不应答，设备以为没有登录成功呢，所以要应答
        byte[] sendData = frame.GetBuffer(Command.LoginResponse);//0x8F

        sendData(ctx, sendData,sender);
        //设备表添加数据
        try {
            Equipment_Insert(sender, recipient,frame.getAddress(), frame.getVersion(), frame.getId(), frame.getData());
        }catch (Exception e){

        }finally {
            if (recipient.getPort() != GlobalParameter.udpPort_Equipment1)//2018-5-27，老设备不支持时间校正，如果时间校正，会发生错误
            {
                //校正时间
                CommandParameterHandle cph = new CommandParameterHandle();
                byte[] data = cph.GetTimeByte(frame, false, Command.SetTimeParameter);
                if (data != null)
                {
                    sendData(ctx, data, sender);
                }
            }
        }

        //记录应当，临时2017-4-16
        //RecordResponse(DateTime.Now, frame.Id, 1, (IPEndPoint)epClient, sendData.ToString());



    }

    private void Equipment_Insert(InetSocketAddress sender, InetSocketAddress recipient, byte[] address, byte version, String id, byte[] data) {
        EquEquipment equEquipment = new EquEquipment();
        equEquipment.setId(id);
        equEquipment.setAddress(address);
        equEquipment.setN(id);
        equEquipment.setIpaddress(sender.getAddress().toString().substring(1));
        equEquipment.setP(sender.getPort());
        equEquipment.setLastcollecttime(DateTime.DateNow());
        equEquipment.setVersion(String.format("%02x", version));
        equEquipment.setEquipmenttypeId(Tools.byte2int(data[0]));
        equEquipment.setPowertype(Tools.byte2int(data[1]));
        equEquipment.setConnectserverport(recipient.getPort());

        iEquEquipmentService.save(equEquipment);

    }

    protected void sendData(ChannelHandlerContext ctx, byte[] buffer, InetSocketAddress sender){
        ctx.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(buffer), sender));
    }

}
