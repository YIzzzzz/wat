package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquAlarmQuery {


    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "确认")
    private boolean isconfirm;
    @ApiModelProperty(value = "设备编码")
    private String equipment_id;
    @ApiModelProperty(value = "设备名称")
    private String equipment_name;
    @ApiModelProperty(value = "设备类型")
    private String equipmenttype_name;
    @ApiModelProperty(value = "生产厂家")
    private String manufacturer_name;
    @ApiModelProperty(value = "供电类型")
    private String powertype;
    @ApiModelProperty(value = "报警类型")
    private Integer alarmtype;
    @ApiModelProperty(value = "报警时间")
    private LocalDateTime alarmtime;
    @ApiModelProperty(value = "确认时间")
    private LocalDateTime confirmtime;

    private String usercode;
    @ApiModelProperty(value = "报警原因")
    private String reason;
    @ApiModelProperty(value = "数据类型")
    private String datatype_name;
    @ApiModelProperty(value = "是否恢复")
    private boolean recovery;
    @ApiModelProperty(value = "恢复时间")
    private LocalDateTime recoverytime;
    @ApiModelProperty(value = "型号")
    private String model;
    @ApiModelProperty(value = "安装人")
    private String setupperson;
    @ApiModelProperty(value = "安装时间")
    private LocalDateTime setuptime;
    @ApiModelProperty(value = "确认人")
    private String username;
    @ApiModelProperty(value = "安装地址")
    private String setupaddress;
    @ApiModelProperty(value = "负责人电话")
    private String telephone;
    @ApiModelProperty(value = "数据类型id")
    private Integer datatype_id;

    private Integer alarmrecord_id;
    @ApiModelProperty(value = "确认描述")
    private String des;
    @ApiModelProperty(value = "负责人")
    private String manager;

}
