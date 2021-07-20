package com.jan.wat.EquServer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DataFormat
{
    @Value("${udpServer.dataFormat.DivideOne}")
    public static final byte DivideOne = 0x00;
    @Value("${udpServer.dataFormat.DivideTen}")
    public static final byte DivideTen = 0x01;
    @Value("${udpServer.dataFormat.DivideHundred}")
    public static final byte DivideHundred = 0x02;
    @Value("${udpServer.dataFormat.DivideThousand}")
    public static final byte DivideThousand = 0x03;
    @Value("${udpServer.dataFormat.DivideTenThousand}")
    public static final byte DivideTenThousand = 0x04;
    @Value("${udpServer.dataFormat.DivideNegativeOne}")
    public static final byte DivideNegativeOne = 0x05;
    @Value("${udpServer.dataFormat.DivideNegativeTen}")
    public static final byte DivideNegativeTen = 0x06;
    @Value("${udpServer.dataFormat.DivideNegativeHundred}")
    public static final byte DivideNegativeHundred = 0x07;
    @Value("${udpServer.dataFormat.DivideNegativeThousand}")
    public static final byte DivideNegativeThousand = 0x08;
    @Value("${udpServer.dataFormat.DivideNegativeTenThousand}")
    public static final byte DivideNegativeTenThousand = 0x09;
}
