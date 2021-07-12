package com.jan.wat.EquServer.config;

public class GlobalParameter {
    /// <summary>
    /// 用于超级狗获取主服务器系统时间
    /// </summary>
    public static int udpPort_MainServer;


    /// <summary>
    /// 客户端请求数据时间范围限定，相对当前时间，如最多请求10天前数据
    /// </summary>
    public static int timeRangeLimit;


    /// <summary>
    /// 客户端请求数据时间间隔限定，分钟，如10分钟内只能请求一次数据
    /// </summary>
    public static int downloadIntervalLimit;


    /// <summary>
    /// 服务器与客户端通讯的端口
    /// </summary>
    public static int udpPort_Client;


    /// <summary>
    /// 服务器与设备通讯的端口
    /// </summary>
    public static int udpPort_Equipment1;


    public static int udpPort_Equipment2;


    public static int udpPort_Equipment3;


    public static int udpPort_Equipment4;


    public static int udpPort_Equipment5;


    public static int udpPort_Equipment6;


    public static int udpPort_Equipment7;


    public static int udpPort_Equipment8;


    public static int udpPort_Equipment9;


    public static int udpPort_Equipment10;


    public static int udpPort_Equipment11;

    public static int udpPort_Equipment12;

    public static int udpPort_Equipment13;

    public static int udpPort_Equipment14;

    /// <summary>
    /// 服务器与网站通讯的端口
    /// </summary>
    public static int udpPort_Web;


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
    public static int keepAlive;


    /// <summary>
    /// 设备有效时间，默认是366天
    /// </summary>
    public static int equipmentValidTime;


    /// <summary>
    /// 数据库存放路径
    /// </summary>
    public static String databaseFilePath;


    /// <summary>
    /// 命令下发最多次数
    /// </summary>
    public static int commandSendMaxNumLimit;


    static
    {
        udpPort_MainServer = 7001;

        udpPort_Equipment1 = 7003;
        udpPort_Equipment2 = 8002;
        udpPort_Equipment3 = 8003;
        udpPort_Equipment4 = 8010;
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
