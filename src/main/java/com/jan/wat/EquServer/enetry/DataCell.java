package com.jan.wat.EquServer.enetry;

import com.jan.wat.EquServer.helper.DataFormatHelper;
import com.jan.wat.EquServer.helper.Tools;
import lombok.Data;

@Data
public class DataCell implements Comparable<DataCell> {

    private int dataTypeId;
    /// <summary>
    /// 数据类型id
    /// </summary>


    private double value;
    /// <summary>
    /// 值
    /// </summary>

    private int unitId;
    /// <summary
    /// 单位id
    /// </summary>


    private int position;
    /// <summary>
    /// 显示位置
    /// </summary>

    private int ifRecord;
    /// <summary>
    /// 是否记录数据
    /// </summary>


    private int ifCurve;
    /// <summary>
    /// 是否曲线显示
    /// </summary>


    private int ifAlarm;
    /// <summary>
    /// 是否报警类型
    /// </summary>


    private int ifaccumulate;
    /// <summary>
    /// 是否是累积类型
    /// </summary>

    public void Load(byte[] data)
    {
        if (data.length != 9)
            return;

        dataTypeId = data[0];
        value = DataFormatHelper.Format(Tools.SixteenToTen_ReturnLong(Tools.ByteSubstring(data, 1, 4)), data[6]);
        unitId = data[5];
        position = data[7];
        ifRecord = Tools.GetBit(data[8], 0);
        ifCurve = Tools.GetBit(data[8], 1);
        ifAlarm = Tools.GetBit(data[8], 2);
        ifaccumulate = Tools.GetBit(data[8], 3);//2013.6.1,新增加累积类型
    }

    @Override
    public int compareTo(DataCell o) {
        return o.getPosition() > position ? -1 : (o.getPosition() == position ? 0 : 1);
    }
}

