package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WatAlarmQuery {

    @ApiModelProperty(value = "设备编码")
    private String equipment_id;
    @ApiModelProperty(value = "报警记录id")
    private Integer alarmrecord_id;
    @ApiModelProperty(value = "报警类型id")
    private Integer alarmtype_id;
    @ApiModelProperty(value = "报警时间")
    private LocalDateTime alarmtime;
    @ApiModelProperty(value = "确认时间")
    private LocalDateTime confirmtime;
    @ApiModelProperty(value = "是否恢复")
    private boolean recovery;
    @ApiModelProperty(value = "恢复时间")
    private LocalDateTime recoverytime;
    @ApiModelProperty(value = "报警原因")
    private String reason;
    @ApiModelProperty(value = "确认描述")
    private String des;
    @ApiModelProperty(value = "报警类型")
    private String alarmtype_name;
    @ApiModelProperty(value = "设备名称")
    private String equipment_name;
    @ApiModelProperty(value = "客户名称")
    private String clientname;
    @ApiModelProperty(value = "册本号")
    private String bookno;
    @ApiModelProperty(value = "性质")
    private String classify_name;
    @ApiModelProperty(value = "口径")
    private String caliber_name;
    @ApiModelProperty(value = "站点号")
    private String siteno;
    @ApiModelProperty(value = "型号")
    private String model;
    @ApiModelProperty(value = "安装时间")
    private LocalDateTime setuptime;
    @ApiModelProperty(value = "安装地址")
    private String setupaddress;
    @ApiModelProperty(value = "负责人电话")
    private String telephone;
    @ApiModelProperty(value = "负责人")
    private String manager;
    @ApiModelProperty(value = "安装人")
    private String setupperson;
    @ApiModelProperty(value = "类别")
    private String category_name;
    @ApiModelProperty(value = "确认人")
    private String username;
    @ApiModelProperty(value = "")
    private String usercode;
    @ApiModelProperty(value = "")
    private boolean enable;

}
