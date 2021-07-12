package com.jan.wat.EquServer.enetry;

import com.jan.wat.EquServer.helper.DataFormatHelper;
import com.jan.wat.EquServer.helper.Encoding;
import com.jan.wat.EquServer.helper.Tools;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParaCell {

    private int paraId;
    /// <summary>
    /// 参数类型id
    /// </summary>
    private int type;

    private String value;
    /// <summary>
    /// 值
    /// </summary>

    private int unitId;
    /// <summary>
    /// 单位id
    /// </summary>

    private double upLimit;
    /// <summary>
    /// 上限，置数型
    /// </summary>


    private double downLimit;
    /// <summary>
    /// 下限，置数型
    /// </summary>


    private List<Integer> valueRange;

    /// <summary>
    /// 可选择参数，选择型
    /// </summary>

    /// <summary>
    /// 读取参数，返回本参数的长度，返回0表示错误
    /// </summary>
    /// <param name="data"></param>
    /// <returns></returns>
    public int LoadPara(byte[] data)
    {
        if (data.length < 4)
            return 0;
        int returnValue = 0;
        paraId = data[0];
        type = data[1];
        switch(type)
        {
            case 1://置数型
                if (data.length < 17)
                    return 0;
                value = String.valueOf(DataFormatHelper.Format(Tools.SixteenToTen_ReturnLong(Tools.ByteSubstring(data, 2, 4)), data[7]));
                unitId = data[6];
                upLimit = DataFormatHelper.Format(Tools.SixteenToTen_ReturnLong(Tools.ByteSubstring(data, 8, 4)), data[16]);
                downLimit = DataFormatHelper.Format(Tools.SixteenToTen_ReturnLong(Tools.ByteSubstring(data, 12, 4)), data[16]);
                returnValue = 17;
                break;
            case 2://选择型
                if (data.length < 6)
                    return 0;
                value = String.valueOf((int)(data[2]));
                unitId = data[3];
                int num = data[4];
                valueRange = new ArrayList<Integer>();
                for (int i = 0; i < num; i++)
                {
                    valueRange.add((int)data[5+i]);
                }
                returnValue = 5+num;
                break;
            case 3://字符型
                if (data.length < 4)
                    return 0;
                int length = data[2];
                value= Encoding.GetString(data,3,length);
                returnValue = 3 + length;
                break;
            default:
                break;
        }
        return returnValue;
    }

    public int LoadParaValue(byte[] data)
    {
        if (data.length < 3)
            return 0;
        int returnValue = 0;
        paraId = data[0];
        type = data[1];
        switch (type)
        {
            case 1://置数型
                if (data.length < 7)
                    return 0;
                value = String.valueOf(DataFormatHelper.Format(Tools.SixteenToTen_ReturnLong(Tools.ByteSubstring(data, 2, 4)), data[6]));
                returnValue = 7;
                break;
            case 2://选择型
                if (data.length < 3)
                    return 0;
                value = String.valueOf((int)(data[2]));
                returnValue = 3;
                break;
            case 3://字符型
                if (data.length < 4)
                    return 0;
                int length = data[2];
                value = Encoding.GetString(data, 3, length);
                returnValue = 3 + length;
                break;
            default:
                break;
        }
        return returnValue;
    }

}