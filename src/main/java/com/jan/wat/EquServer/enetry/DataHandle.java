package com.jan.wat.EquServer.enetry;

import com.jan.wat.EquServer.enetry.DataCell;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.pojo.EquDatatype;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DataHandle {

    private Date collectTime;
    /// <summary>
    ///  设备采集数据时间


    private int status;
    /// <summary>
    /// 上传数据状态，为0表示主动上传，为1表示点名上传
    /// </summary>


    private int equipmentTypeId;
    /// <summary>
    /// 仪表类型id
    /// </summary>


    private int dataCellNum;
    /// <summary>
    /// 数据个数
    /// </summary>


    private DataCell[] dataCells;
    /// <summary>
    /// 数据包
    /// </summary>


    //private string _equipmentXml;
    ///// <summary>
    ///// 设备表中的数据
    ///// </summary>
    //public string EquipmentXml
    //{
    //    get { return _equipmentXml; }
    //    set { _equipmentXml = value; }
    //}

    private String equipmentDataXml;
    /// <summary>
    /// 设备表中的数据
    /// </summary>


    private boolean ifAlarm;
    /// <summary>
    /// 是否报警
    /// </summary>


    //private string _equipmentAlarmDataXml;
    ///// <summary>
    ///// 设备表中的数据
    ///// </summary>
    //public string EquipmentAlarmDataXml
    //{
    //    get { return _equipmentAlarmDataXml; }
    //    set { _equipmentAlarmDataXml = value; }
    //}

    private double longitude;


    private double latitude;


    public DataHandle()
    {
        ifAlarm = false;
        //_equipmentXml = "";
        equipmentDataXml = "";
        //_equipmentAlarmDataXml = "";

        longitude = 0;
        latitude = 0;
    }

    public boolean Load(byte[] data, List<EquDatatype> dataTypeList)
    //public bool Load(byte[] data)
    {
        dataCellNum = data[8];
        //数据个数不正确
        if (data.length != (9 + 9 * dataCellNum)) return false;

       collectTime = DateTime.getDate(data, 0, 6);
        status = data[6];
        equipmentTypeId = data[7];

        //2018 - 5 - 8,数据类型是报警类型还是累积类型由datatype数据表中的设置决定
        //List<Models.equ_DataType> DataTypeList = null;
        //if (_equipmentTypeId == 7 || _equipmentTypeId == 6)//只针对803E这种类型表，做这样的判断
        //{
        //    var dataType_Service = new RDAS.Service.equ_DataTypeService();
        //    DataTypeList = dataType_Service.GetModelList(RDAS.Core.ParamQuery.Instance().Select("ID,Name,IfAlarm,IfAccumulate").AndWhere("IfAlarm", "true").OrWhere("IfAccumulate", "true"));
        //}

        dataCells = new DataCell[dataCellNum];

        for (int i = 0; i < dataCellNum; i++)
        {
            dataCells[i] = new DataCell();
            dataCells[i].Load(Tools.ByteSubstring(data, 9 + i * 9, 9));
            int id = dataCells[i].getDataTypeId();
            int count = 0;

            //2018 - 5 - 8,数据类型是报警类型还是累积类型由datatype数据表中的设置决定
            if (dataTypeList != null)
            {
                List<EquDatatype> dataTypes = dataTypeList.stream()
                        .filter((EquDatatype e) -> dataTypeList.contains(e.getId()))
                        .collect(Collectors.toList());
                if (count == 1)
                {
                    dataCells[i].setIfAlarm(dataTypes.get(0).getIfalarm() == true ? 1 : 0);
                    dataCells[i].setIfaccumulate(dataTypes.get(0).getIfaccumulate() == true ? 1 : 0);
                }
                else
                {
                    dataCells[i].setIfAlarm(0);
                    dataCells[i].setIfaccumulate(0);
                }
            }

            ////获取设备表xml列值
            //_equipmentXml += "<I>";
            //_equipmentXml += "<DT>" + _dataCells[i].DataTypeId.ToString() + "</DT>";
            //_equipmentXml += "<V>" + _dataCells[i].Value.ToString() + "</V>";
            //_equipmentXml += "<U>" + _dataCells[i].UnitId.ToString() + "</U>";
            //_equipmentXml += "<P>" + _dataCells[i].Position.ToString() + "</P>";
            //_equipmentXml += "<R>" + _dataCells[i].IfRecord.ToString() + "</R>";
            //_equipmentXml += "<C>" + _dataCells[i].IfCurve.ToString() + "</C>";
            //_equipmentXml += "<A>" + _dataCells[i].IsAlarm.ToString() + "</A>";
            //_equipmentXml += "<T>" + _dataCells[i].Total.ToString() + "</T>";//2013.6.1,新增加累积类型
            //_equipmentXml += "</I>";

            if (dataCells[i].getIfRecord() == 1)
            {
                if (dataCells[i].getIfAlarm() == 1)
                {
                    if (dataCells[i].getValue() == 1)
                    {
                        ifAlarm = true;

                        //获取报警数据表xml列值
                        //_equipmentAlarmDataXml += "<V i=\"" + _dataCells[i].DataTypeId.ToString() + "\">" + _dataCells[i].Value.ToString() + "</V>";
                    }
                }
                //else 20161129，所有数据都存在一个表中，不管有没有报警信息
                //{
                //获取设备数据表xml列值
                equipmentDataXml += "<V i=\"" + dataCells[i].getDataTypeId() + "\">" + dataCells[i].getValue() + "</V>";
                //}
            }

            //添加经纬度识别
            if (dataCells[i].getDataTypeId() == 101)
                longitude = dataCells[i].getValue();
            if (dataCells[i].getDataTypeId() == 102)
                latitude = dataCells[i].getValue();
        }

        //if (_dataCellNum > 0)
        //    _equipmentXml = "<D>" + _equipmentXml + "</D>";

        if (equipmentDataXml.length() > 0)
            equipmentDataXml = "<D>" + equipmentDataXml + "</D>";

        //if (_ifAlarm)
        //    _equipmentAlarmDataXml = "<D>" + _equipmentAlarmDataXml + "</D>";

        return true;
    }
}
