package com.jan.wat.EquServer.helper;

import com.jan.wat.EquServer.config.DataFormat;

public class DataFormatHelper
{
    public static double Format(long data, byte dataFormat)
    {
        double returnValue = data;
        switch (dataFormat)
        {
            case (byte) DataFormat.DivideOne:
                break;
            case (byte)DataFormat.DivideTen:
                returnValue = returnValue / 10;
                break;
            case (byte)DataFormat.DivideHundred:
                returnValue = returnValue / 100;
                break;
            case (byte)DataFormat.DivideThousand:
                returnValue = returnValue / 1000;
                break;
            case (byte)DataFormat.DivideTenThousand:
                returnValue = returnValue / 10000;
                break;

            case (byte)DataFormat.DivideNegativeOne:
                returnValue = returnValue / -1;
                break;
            case (byte)DataFormat.DivideNegativeTen:
                returnValue = returnValue / -10;
                break;
            case (byte)DataFormat.DivideNegativeHundred:
                returnValue = returnValue / -100;
                break;
            case (byte)DataFormat.DivideNegativeThousand:
                returnValue = returnValue / -1000;
                break;
            case (byte)DataFormat.DivideNegativeTenThousand:
                returnValue = returnValue / -10000;
                break;
            default:
                returnValue = 0;//数据格式错误，返回0
                break;
        }
        return returnValue;
    }
}
