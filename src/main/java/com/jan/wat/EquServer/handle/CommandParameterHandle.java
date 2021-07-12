package com.jan.wat.EquServer.handle;


import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.config.DataFormat;
import com.jan.wat.EquServer.enetry.FrameStructure;
import com.jan.wat.EquServer.enetry.ParaCell;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Encoding;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.pojo.EquCommand;
import lombok.Data;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CommandParameterHandle
{
    //private DateTime _collectTime;
    ///// <summary>
    /////  设备采集数据时间
    ///// </summary>
    //public DateTime CollectTime
    //{
    //    get { return _collectTime; }
    //    set { _collectTime = value; }
    //}
    /// <summary>
    /// 上传数据状态，为0表示主动上传，为1表示点名上传
    /// </summary>
    public byte status;

    private int equipmentTypeId;
    /// <summary>
    /// 仪表类型id
    /// </summary>


    private int paraCellNum;
    /// <summary>
    /// 参数个数
    /// </summary>

    private ParaCell[] paraCells;
    /// <summary>
    /// 参数包
    /// </summary>

    public CommandParameterHandle()
    {
    }
    /// <summary>
    ///
    /// </summary>
    /// <param name="data"></param>
    /// <param name="paraOrValue">为1表示加载参数，为2表示加载参数值</param>
    /// <returns></returns>


    public byte[] GetTimeByte(FrameStructure frame, boolean response, byte frameType)
    {
        DateTime.Now();
        byte[] data = new byte[7];
        data[0] = (byte)DateTime.getYear();
        data[1] = (byte)DateTime.getMonth();
        data[2] = (byte)DateTime.getDay();
        data[3] = (byte)DateTime.getHour();
        data[4] = (byte)DateTime.getMinute();
        data[5] = (byte)DateTime.getSecond();
        if(response)
            data[6] = 0x00;//表示需要应答
        else data[6] = 0x01;//表示不需要应答
        return frame.GetBuffer(frameType, data);
    }

    public boolean Load(byte[] data, int paraOrValue) {
        status = data[0];
        equipmentTypeId = data[1];
        paraCellNum = data[2];
        //_collectTime = Tools.GetDateTime(Tools.GetDate(Tools.ByteSubstring(data, 0, 6)));
        paraCells = new ParaCell[paraCellNum];
        int index = 3;
        for (int i = 0; i < paraCellNum; i++)
        {
            paraCells[i] = new ParaCell();

            if (data.length <= index) return false;

            if (paraOrValue == 1)
            {
                int increase= paraCells[i].LoadPara(Tools.ByteSubstring(data, index, data.length - index));
                if (increase == 0) return false;
                index += increase;
            }
            else
            {
                int increase = paraCells[i].LoadParaValue(Tools.ByteSubstring(data, index, data.length - index));
                if (increase == 0) return false;
                index += increase;
            }
        }
        return true;
    }

    public byte[] GetCommandData(EquCommand model, FrameStructure frame) {

        byte[] returnValue = null;

        byte[] data = new byte[1];
        data[0] = 0x00;//表示点名，要求上传数据
        switch ((int)model.getCommandtype())
        {
            case (int) Command.ReadParameter:
                returnValue= frame.GetBuffer((byte)Command.ReadParameter, data);
                break;
            case (int)Command.ReadParameterValue:
                returnValue= frame.GetBuffer((byte)Command.ReadParameterValue, data);
                break;
            case (int)Command.SetTimeParameter:
                returnValue = GetTimeByte(frame,true, (byte)Command.SetTimeParameter);
                break;
            case (int)Command.SetParameterValue:
                String commandStr = model.getCommand();
                Iterator<Element> it = Tools.XML2iter(commandStr);

                if(!it.hasNext())
                    return null;
                List<Byte> list = new ArrayList<Byte>();

                while(it.hasNext()){
                    Element child = it.next();
                    int t = Integer.parseInt(child.element("T").getTextTrim());
                    String v = child.element("V").getTextTrim();
                    String i = child.element("I").getTextTrim();

                    switch(t){
                        case 1:
                            //先转double目的是把1.00这种情况改为1
                            double value = Double.parseDouble(v);
                            String[] flag = String.valueOf(Math.abs(value)).split(".");

                            //去除小数点
                            String str = "";
                            for (String s : flag)
                                str += s;

                            //转16进制
                            str = String.format("%08x",Long.parseLong(str));//左边补0，确保8位 Convert.ToString(str);//十进制转16进制
                            list.addAll(Tools.strToToHexByte(str));

                            byte format = 0x00;
                            if (value > 0)
                            {
                                if (flag.length == 1)
                                {
                                    format = (byte) DataFormat.DivideOne;
                                }
                                else
                                {
                                    //判断小数点位置
                                    switch (flag[1].length())
                                    {
                                        case 1:
                                            format = (byte)DataFormat.DivideTen;
                                            break;
                                        case 2:
                                            format = (byte)DataFormat.DivideHundred;
                                            break;
                                        case 3:
                                            format = (byte)DataFormat.DivideThousand;
                                            break;
                                        case 4:
                                            format = (byte)DataFormat.DivideTenThousand;
                                            break;
                                    }
                                }
                            }
                            else
                            {
                                if (flag.length == 1)
                                {
                                    format = (byte)DataFormat.DivideNegativeOne;
                                }
                                else
                                {
                                    //判断小数点位置
                                    switch (flag[1].length())
                                    {
                                        case 1:
                                            format = (byte)DataFormat.DivideNegativeTen;
                                            break;
                                        case 2:
                                            format = (byte)DataFormat.DivideNegativeHundred;
                                            break;
                                        case 3:
                                            format = (byte)DataFormat.DivideNegativeThousand;
                                            break;
                                        case 4:
                                            format = (byte)DataFormat.DivideNegativeTenThousand;
                                            break;
                                    }
                                }
                            }
                            list.add(format);
                            break;
                        case 2:
                            list.add(Byte.parseByte(v));
                            break;
                        case 3:
                            List<Byte> ar = Encoding.GetBytes(v);
                            list.add((byte)(ar.size()));
                            list.addAll(ar);
                            break;
                        default:
                            break;
                    }

                }

                byte[] arr = new byte[list.size()];
                for (int k = 0; k < list.size(); k++)
                    arr[k] = list.get(k);
                returnValue= frame.GetBuffer((byte)Command.SetParameterValue, arr);
                break;

            case (int)Command.UpdateProgram://更新程序
                String program = model.getCommand();
                String commandStr1 = model.getCommand();

                Iterator<Element> iter = Tools.XML2iter(commandStr1);

                if(!iter.hasNext())
                    return null;
                List<Byte> byteList = new ArrayList<Byte>();


                Element child = iter.next();
                String lenStr = String.format("%08x",Long.parseLong(child.element("V").getTextTrim()));
                String l = child.element("L").getTextTrim();

                //长度
                byte[] lenByte = Tools.strToToHexByteArray(lenStr);
                //版本号
                byte[] version = String.format("%16s",l).getBytes();


                byte[] buffer = new byte[22];
                System.arraycopy(lenByte, 0, buffer, 0, 4);
                System.arraycopy(version, 0, buffer, 4, 16);

                //CRC校验
                byte[] crc = Tools.CRC16(buffer,20);
                System.arraycopy(crc, 0, buffer, 20, 2);

                returnValue = frame.GetBuffer((byte)Command.UpdateProgram, buffer);
                break;
            default:
                break;
        }
        return returnValue;
    }
}