package com.jan.wat.EquServer.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.enetry.DataHandle;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.enetry.ParaCell;
import com.jan.wat.EquServer.helper.Agreement;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.mapper.EquipmentdataMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.netty.channel.ChannelHandlerContext;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DatabaseHandle {

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
    SendHandle sendHandle;
    public static List<String> idListForTransform = new ArrayList<>(Arrays.asList( "5169", "4960", "4962", "4963", "4965", "4966", "4967", "4968", "4970", "4976", "4977", "4978", "4982", "4986", "4987", "4990", "4991", "4992", "4994"));


    public void EquipmentData_Insert(String uploadMonth, DataHandle dh, Date collectTime, String id, String str) {
        Equipmentdata equipmentdata = new Equipmentdata();
        equipmentdata.setCollecttime(dh.getCollectTime());
        equipmentdata.setEquipmentId(id);
        equipmentdata.setUploadtime(DateTime.DateNow());
        equipmentdata.setStr(str);
        equipmentdata.setData(dh.getEquipmentDataXml());
        equipmentdataMapper.insertData("rdasdata"+uploadMonth,equipmentdata);

    }
    public void HandleEquipmentAlarmRecord(DataHandle dh, String id) {
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
    public boolean EquipmentTabUpdateData(int flag, int powerType, DataHandle dh, ChannelHandlerContext ctx, FrameStructure frame, InetSocketAddress sender, int port) {

        //if (!dh.Load(data)) return false;//数据个数不正确，返回
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-222222222222222222");
        //设备表添加数据
        boolean returnValue = Equipment_Update(dh, powerType, sender, frame.getVersion(), frame.getId(),port);

        updateRealData(dh, frame.getId(), returnValue);
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-5555555555555555555555555");
        insertEquData(dh, frame.getId(), port, returnValue);
        if(flag == 1 && returnValue){
            findCommand(sender, ctx, frame);
        }
        return returnValue;
    }

    public boolean Equipment_Update(DataHandle dh, int powerType, InetSocketAddress sender, byte version, String id, int serverPort) {
        boolean returnValue = true;
        EquEquipment model = new EquEquipment();
        model.setId(id);
        model.setIpaddress(sender.getAddress().toString().substring(1));

        model.setP(sender.getPort());
        model.setLastcollecttime(dh.getCollectTime());
        model.setVersion(String.format("%02x", version));
        model.setEquipmenttypeId(dh.getEquipmentTypeId());
        model.setPowertype(powerType);//电池供电
        model.setS(dh.isIfAlarm());
        //model.Data = dh.EquipmentXml;
        model.setLongitude((float) dh.getLongitude());
        model.setLatitude((float) dh.getLatitude());
        model.setConnectserverport(serverPort);

        returnValue = iEquEquipmentService.updateById(model);
        return returnValue;
    }

    @Async
    public void findCommand(InetSocketAddress sender,ChannelHandlerContext ctx, FrameStructure frame){
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
            byte[] sendD = frame.GetBuffer((byte) Command.BatteryPowerUploadDataResponse, Agreement.BatteryPowerResponse(flag));
            sendHandle.sendData(ctx, sendD, sender);
            //发送命令
            for (EquCommand item : list){
                sendHandle.sendCommand(item, frame, ctx, sender);
            }
        }
        else
        {
            byte[] sendD = frame.GetBuffer((byte)Command.BatteryPowerUploadDataResponse, Agreement.BatteryPowerResponse(flag));
            sendHandle.sendData(ctx, sendD, sender);
        }
        //记录应答，临时2017-4-16
        //DataHandle dh = new DataHandle();
        //dh.Load(frame.Data);
        //RecordResponse(dh.CollectTime, frame.Id, 2, (IPEndPoint)epClient, sendData.ToString());
    }

    @Async
    public void insertEquData(DataHandle dh, String id,int port, boolean returnValue){

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
//            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-666666666666666666666666666666");
            //处理报警
            //returnValue = (returnValue & HandleEquipmentAlarmRecord(dh, id));
            HandleEquipmentAlarmRecord(dh, id);
//            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-777777777777777777777777");
            if ( port == GlobalParameter.udpPort_Equipment2)//2018-5-27，只有这一个端口支持数据转发
            {
                //转给客户端
                sendHandle.client(id, dh);
            }
            else if (idListForTransform.contains(id))
            { sendHandle.client(id, dh); }

            //判断设备数据是否超限
            //HandleEquipmentDataError(id, dh);

            //水表数据处理,2018-5-15发掘数据应答缓慢，怀疑性能不行，所以取消这部分功能
            //if (WaterEvent != null)
            //    WaterEvent(id, dh);
        }

    }

    @Async
    public void updateRealData(DataHandle dh,  String id, boolean returnValue){
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-333333333333333333");
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
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"------------------");

                iEquEquipmentrealdataService.updateBatchById(listRealData);
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-==================");

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
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"------------------");

                iEquEquipmentrealdataService.updateBatchById(listRealData);
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-==================");
            }
        }
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+"-44444444444444444444444444");

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


    public boolean UpdateEquipmentParaValue(CommandParameterHandle cph, String equipment_ID) {
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


    public boolean UpdateEquipmentPara(CommandParameterHandle cph, String equipment_ID) {
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


    public void Equipment_Insert(InetSocketAddress sender, InetSocketAddress recipient, byte[] address, byte version, String id, byte[] data) {
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

}
