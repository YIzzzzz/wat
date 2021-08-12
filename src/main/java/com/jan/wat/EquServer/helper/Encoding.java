package com.jan.wat.EquServer.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encoding {
    public static String GetString(byte[] data, int index, int length){
        byte[] buf = new byte[length];
        System.arraycopy(data,index,buf,0,length);
        return new String(buf);
    }

    public static List<Byte> GetBytes(String v) {
        return new ArrayList(Collections.singleton(v.getBytes()));
    }
    public static String printHexString(byte[] data) {
        String res = "";
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(data[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            res += hex+" ";
        }
        return res;
    }
    public static String printHexString(byte data) {
        String res = "";

        String hex = Integer.toHexString(data & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        res += hex+" ";

        return res;
    }
}
