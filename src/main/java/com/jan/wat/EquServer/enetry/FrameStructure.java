package com.jan.wat.EquServer.enetry;

import com.jan.wat.EquServer.helper.Tools;
import lombok.Data;

import java.util.Arrays;

@Data
public class FrameStructure {

    private byte[] systemIDCode;
    private byte[] address;
    private byte frameType;
    private String id;
    private byte version;
    private byte[] data;
    private byte[] crc={(byte)0x5A, (byte) 0xA5};

    public boolean Load(byte[] buffer, byte[] systemIDCode)
    {
        this.systemIDCode = systemIDCode;
        if (buffer.length < 16) return false;
        //验证系统识别码
        //if (!CheckSystemIDCode(Tools.ByteSubstring(buffer, 0, 4)))
        //    return false;

        //判断数据长度是否正确
        int high = buffer[4];
        int low = buffer[5];
        int length = high * 256 + low;
        if (length != buffer.length)
            return false;

        //数据结束校验
        //if (!CheckCRC(buffer[length - 2], buffer[length - 1]))
        //    return false;

        address = Tools.ByteSubstring(buffer, 6, 6);
        frameType = buffer[12];
        version = buffer[13];
        data = Tools.ByteSubstring(buffer, 14, buffer.length - 16);

        id= String.valueOf(Tools.SixteenToTen_ReturnLong(address));

        return true;
    }


    public byte[] GetBuffer(byte frameType)
    {
        this.frameType = frameType;
        return GetBuffer();
    }


    public byte[] GetBuffer(byte frameType, byte[] data)
    {
        this.frameType = frameType;
        return GetBuffer(data);
    }

    private byte[] GetBuffer(byte[] data)
    {
        //int bufferLength = 16 + data.Length;
        //byte[] buffer = new byte[bufferLength];
        //byte[] length = BitConverter.GetBytes(bufferLength);
        //Buffer.BlockCopy(_systemIDCode, 0, buffer, 0, _systemIDCode.Length);
        //Buffer.BlockCopy(length, 0, buffer, _systemIDCode.Length, 2);
        //Buffer.BlockCopy(_address, 0, buffer, _systemIDCode.Length + 2, _address.Length);
        //buffer[12] = _frameType;
        //buffer[13] = _version;
        //Buffer.BlockCopy(data, 0, buffer, _systemIDCode.Length + 4 + _address.Length, data.Length);
        ////CRC
        //buffer[bufferLength - 2] = 0x00;
        //buffer[bufferLength - 1] = 0x00;
        //return buffer;

        int bufferLength = 16 + data.length;
        byte[] buffer = new byte[bufferLength];
        byte[] length = Tools.int2Bytes(bufferLength);
        System.arraycopy(systemIDCode, 0, buffer, 0, 4);
        //Buffer.BlockCopy(length, 0, buffer, 4, 2);//这种写法是，低位在前，如16长，拷贝后是0x10 0x00
        buffer[4] = length[1];//这样写的目的是高位在前
        buffer[5] = length[0];
        System.arraycopy(address, 0, buffer, 6, 6);
        buffer[12] = frameType;
        buffer[13] = version;
        System.arraycopy(data, 0, buffer, 14, data.length);
        //CRC
        buffer[bufferLength - 2] = crc[0];
        buffer[bufferLength - 1] = crc[1];
        return buffer;
    }

    /**
     * 没有帧内容时，调用这个方法
     * @return
     */
    private byte[] GetBuffer()
    {
        int bufferLength = 16;
        byte[] buffer = new byte[bufferLength];
        byte[] length = Tools.int2Bytes(bufferLength);
        System.arraycopy(systemIDCode, 0, buffer, 0, 4);
        //Buffer.BlockCopy(length, 0, buffer, 4, 2);//这种写法是，低位在前，如16长，拷贝后是0x10 0x00
        buffer[4] = length[1];//这样写的目的是高位在前
        buffer[5] = length[0];
        System.arraycopy(address, 0, buffer, 6, 6);

        buffer[12] = frameType;
        buffer[13] = version;
        //CRC
        buffer[14] = crc[0];
        buffer[15] = crc[1];
        return buffer;
    }

    @Override
    public String toString() {
        return "FrameStructure{" +
                "systemIDCode=" + Arrays.toString(systemIDCode) +
                ", address=" + Arrays.toString(address) +
                ", frameType=" + frameType +
                ", id='" + id + '\'' +
                ", version=" + version +
                ", data=" + Arrays.toString(data) +
                ", crc=" + Arrays.toString(crc) +
                '}';
    }
}
