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

}
