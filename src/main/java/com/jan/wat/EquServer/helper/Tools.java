package com.jan.wat.EquServer.helper;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Data
public class Tools {


    public static byte[] systemIDCode={0x53,0x59,0x4C,0x53};
//    private static byte[] systemIDCode={0x53,0x59,0x4C,0x53};

    public static int GetBit(byte b, int index)
    {
        int value;
        value = b >> index;
            return value &= 1;
    }
    public static List<Byte> strToToHexByte(String hexString)
    {
        hexString = hexString.replace(" ", "");
        if ((hexString.length() % 2) != 0)
            hexString += " ";
        List<Byte> list = new ArrayList<Byte>();

        for (int i = 0; i < hexString.length() / 2; i++) {
            long dec_num = Long.parseLong(hexString.substring(i * 2, i * 2+2), 16);
            list.add(Byte.parseByte(String.valueOf(dec_num)));
        }
        return list;
    }

    public static byte[] strToToHexByteArray(String hexString)
    {
        hexString = hexString.replace(" ", "");
        if ((hexString.length() % 2) != 0)
            hexString += " ";
        byte[] returnBytes = new byte[hexString.length() / 2];
        for (int i = 0; i < returnBytes.length; i++)
            returnBytes[i] = Byte.parseByte(hexString.substring(i * 2, 2), 16);
        return returnBytes;
    }

    public static Iterator<Element> XML2iter(String xml){
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();

        return root.elementIterator();

    }

    /**
     *将int转化为byte[]低位在前
     * @return
     */
    public static byte[] int2Bytes(int integer)
    {
        byte[] bytes=new byte[4];
        bytes[3]= (byte) (integer>>24);
        bytes[2]= (byte) (integer>>16);
        bytes[1]= (byte) (integer>>8);
        bytes[0]= (byte) integer;

        return bytes;
    }

    //将byte[]转换成十六进制字符串
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();

        String hex;

        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);

            if (hex.length() == 1) {
                hex = '0' + hex;

            }

            result.append(hex.toUpperCase());

        }

        return result.toString();

    }

    /**
     * 将byte[]切分
     * @param buffer 源数组
     * @param startIndex 切分开始位置
     * @param num 长度
     * @return 子byte[]
     */
    public static byte[] ByteSubstring(byte[] buffer, int startIndex, int num){
        byte[] b = new byte[num];
        if(buffer.length >= startIndex + num)
            System.arraycopy(buffer, startIndex, b, 0, num);
        return b;
    }

    /**
     * 将array转化为10进制 Long型数据
     * @param array
     * @return
     */
    public static long SixteenToTen_ReturnLong(byte[] array){
        return Long.parseLong(bytes2HexString(array),16);
    }
    public static int SixteenToTen_ReturnInt(byte[] array){
        return Integer.parseInt(bytes2HexString(array),16);
    }

    public static int byte2int(byte b){
        int i = b&0xFF;
        return i;
    }

    public static byte[] CRC16(byte[] data, int len) {
        if (len > 0)
        {
            int crc = 0xFFFF;

            for (int i = 0; i < len; i++)
            {
                crc = (crc ^ (data[i]));
                for (int j = 0; j < 8; j++)
                {
                    crc = (crc & 1) != 0 ? ((crc >> 1) ^ 0xA001) : (crc >> 1);
                }
            }
            byte hi = (byte)((crc & 0xFF00) >> 8);  //高位置
            byte lo = (byte)(crc & 0x00FF);         //低位置
            return new byte[] { hi, lo };
        }
        return new byte[] { 0, 0 };
    }
}
