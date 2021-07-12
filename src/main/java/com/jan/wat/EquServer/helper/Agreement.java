package com.jan.wat.EquServer.helper;

/**
 * 应答协议
 */
public class Agreement {

    /// <summary>
    /// 心跳包
    /// </summary>
    /// <param name="ifResponse">是否需要应答</param>
    /// <returns></returns>
    public static byte[] KeepAlive(boolean ifResponse)
    {
        byte[] returnValue = new byte[1];
        if (ifResponse) returnValue[0] = 0x01;
        else returnValue[0] = 0x00;
        return returnValue;
    }

    /// <summary>
    /// 应答
    /// </summary>
    public static byte[] BatteryPowerResponse(boolean ifCommand)
    {
        byte[] data = new byte[2];
        data[0] = 0x00;

        if (ifCommand) data[1] = 0x01;//不关闭设备，等待服务器给发命令
        else data[1] = 0x00;//立刻关闭设备
        return data;
    }

}
