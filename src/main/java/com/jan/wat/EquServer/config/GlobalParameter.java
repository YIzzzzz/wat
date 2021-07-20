package com.jan.wat.EquServer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class GlobalParameter {
    /// <summary>
    /// 用于超级狗获取主服务器系统时间
    /// </summary>
    @Value("${udpServer.port.MainServer}")
    public static Integer udpPort_MainServer;

    /// <summary>
    /// 客户端请求数据时间范围限定，相对当前时间，如最多请求10天前数据
    /// </summary>
    @Value("${udpServer.limit.timeRange}")
    public static Integer timeRangeLimit;


    /// <summary>
    /// 客户端请求数据时间间隔限定，分钟，如10分钟内只能请求一次数据
    /// </summary>
    @Value("${udpServer.limit.downloadInterval}")
    public static Integer downloadIntervalLimit;


    /// <summary>
    /// 服务器与客户端通讯的端口
    /// </summary>
    @Value("${udpServer.port.Client}")
    public static Integer udpPort_Client;


    /// <summary>
    /// 服务器与设备通讯的端口
    /// </summary>
    @Value(value = "${udpServer.port.Equipment1}")
    public static int udpPort_Equipment1;

    @Value(value = "${udpServer.port.Equipment2}")
    public static int udpPort_Equipment2;

    @Value("${udpServer.port.Equipment3}")
    public static Integer udpPort_Equipment3;

    @Value("${udpServer.port.Equipment4}")
    public static Integer udpPort_Equipment4;

    @Value("${udpServer.port.Equipment5}")
    public static Integer udpPort_Equipment5;

    @Value("${udpServer.port.Equipment6}")
    public static Integer udpPort_Equipment6;

    @Value("${udpServer.port.Equipment7}")
    public static Integer udpPort_Equipment7;

    @Value("${udpServer.port.Equipment8}")
    public static Integer udpPort_Equipment8;

    @Value("${udpServer.port.Equipment9}")
    public static Integer udpPort_Equipment9;

    @Value("${udpServer.port.Equipment10}")
    public static Integer udpPort_Equipment10;

    @Value("${udpServer.port.Equipment11}")
    public static Integer udpPort_Equipment11;

    @Value("${udpServer.port.Equipment12}")
    public static Integer udpPort_Equipment12;

    @Value("${udpServer.port.Equipment13}")
    public static Integer udpPort_Equipment13;

    @Value("${udpServer.port.Equipment14}")
    public static Integer udpPort_Equipment14;

    /// <summary>
    /// 服务器与网站通讯的端口
    /// </summary>
    @Value("${udpServer.port.Web}")
    public static Integer udpPort_Web;


    /// <summary>
    /// 系统识别码，与设备通讯用
    /// </summary>
    public static byte[] systemIDCode;


    /// <summary>
    /// 帧版本号，与设备通讯用
    /// </summary>
    public static byte version;


    /// <summary>
    /// 心跳链路周期，单位是毫秒
    /// </summary>
    @Value("${udpServer.limit.keepAlive}")
    public static Integer keepAlive;


    /// <summary>
    /// 设备有效时间，默认是366天
    /// </summary>
    @Value("${udpServer.limit.equipmentValidTime}")
    public static Integer equipmentValidTime;


    /// <summary>
    /// 数据库存放路径
    /// </summary>
    public static String databaseFilePath;


    /// <summary>
    /// 命令下发最多次数
    /// </summary>
    @Value("${udpServer.limit.commandSendMaxNumLimit}")
    public static Integer commandSendMaxNumLimit;


    static
    {
//        udpPort_MainServer = 7001;
//
//        udpPort_Equipment1 = 7003;
//        udpPort_Equipment2 = 8002;
//        udpPort_Equipment3 = 8003;
//        udpPort_Equipment4 = 8010;
        System.out.println(udpPort_Equipment4);
        System.out.println(udpPort_Equipment1);

        udpPort_Equipment5 = 8011;
        udpPort_Equipment6 = 8012;
        udpPort_Equipment7 = 8013;
        udpPort_Equipment8 = 8014;
        udpPort_Equipment9 = 8015;
        udpPort_Equipment10 = 8016;
        udpPort_Equipment11 = 8017;
        udpPort_Equipment12 = 8018;
        udpPort_Equipment13 = 8019;
        udpPort_Equipment14 = 9000;

        udpPort_Web = 7004;
        keepAlive = 60000;

        //设备默认有效时间
        equipmentValidTime = 366;
        databaseFilePath = "D:\"";

        udpPort_Client = 7005;
        timeRangeLimit = 10;
        downloadIntervalLimit = 10;

        commandSendMaxNumLimit = 3;


        //系统识别码，写死
        systemIDCode = new byte[4];
        systemIDCode[0] = 0x53;
        systemIDCode[1] = 0x59;
        systemIDCode[2] = 0x4C;
        systemIDCode[3] = 0x53;

        //系统协议码，写死
        version = 0x20;
    }

}
