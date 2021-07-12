package com.jan.wat.EquServer.config;

public class Command{
    /// <summary>
    /// 电池供电GPRS上传数据帧
    /// </summary>
    public final static byte BatteryPowerUploadData = (byte) 0x01;
    /// <summary>
    /// 电池供电GPRS上传数据帧应答
    /// </summary>
    public final static byte BatteryPowerUploadDataResponse = (byte) 0x81;

//    [DescriptionAttribute("220V供电GPRS设备上传数据帧")]

    public final static byte UploadData = (byte)0x02;

    public final static byte ReadData = (byte)0x83;//主站命令GPRS设备回传数据帧

    //ReadParameter = 0x84,//读取GPRS设备参数帧
    //ReadParameterResponse = 0x04,//读取GPRS设备参数帧应答

    public final static byte WriteParameter = (byte) 0x85;//写GPRS设备参数帧
    public final static byte WriteParameterResponse = (byte) 0x05;//写GPRS设备参数应答帧

    public final static byte ReadNetConfig = (byte)0x86;//读GPRS网络配置帧

    /// <summary>
    /// 读取GPRS配置应答帧
    /// </summary>
    public final static byte ReadNetConfigResponse = (byte)0x06;

    public final static byte WriteNetConfig = (byte)0x87;//写GPRS网络配置帧
    public final static byte WriteNetConfigResponse = (byte)0x07;//写GPRS配置应答帧

    public final static byte KeepAliveResponse = (byte)0x88;//心跳包应答帧
    public final static byte KeepAlive = (byte)0x08;//心跳包帧

    //StopUpload = 0x89,//停止上传数据
    //EnableUpload = 0x8A,//允许上传数据

    //ReadStatus = 0x8B,//读取GPRS设备诊断状态帧
    //ReadStatusResponse = 0x0B,//读取GPRS设备诊断状态帧应答

    /// <summary>
    /// 设备登陆系统
    /// </summary>
    public final static byte Login = (byte)0x0F;
    public final static byte LoginResponse = (byte)0x8F;//登陆应答

    public final static byte ReadParameter= (byte)0x91;//读取设备参数
    public final static byte UpdateParameter= (byte)0x11;//读取设备参数应答

    public final static byte ReadParameterValue= (byte)0x92;//读取参数值
    public final static byte UpdateParameterValue=(byte)0x12;//读取参数值应答

    public final static byte SetParameterValue=(byte)0x93;//设置参数值
    public final static byte SetParameterValueResponse= (byte)0x13;//设置参数值应答

    public final static byte SetTimeParameter=(byte)0x94;//校正时间
    public final static byte SetTimeParameterResponse = (byte)0x14;//校正时间应答

    //UploadParameterValue = 0x95,//设备主动上传设备参数值
    //UploadParameterValueResponse = 0x15,//设备主动上传设备参数值应答

    public final static byte UpdateProgram=(byte)0x8C;//服务器下发更新程序指令
    public final static byte UpdateProgramResponse=(byte)0x0C;

    public final static byte DownloadProgram=(byte)0x0D;//设备提交下载部分程序申请
    public final static byte DownloadProgramResponse=(byte)0x8D;

    public final static byte TimeSynchronizationResponse = (byte)0x95;//时钟同步应答
    public final static byte TimeSynchronization = (byte)0x15;//设备上传时钟同步请求



}
