package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquHistoryUpdateProgramRecord {

    @ApiModelProperty(value = "编码")
    private String id;

    @ApiModelProperty(value = "设备编码")
    private String equipment_id;

    @ApiModelProperty(value = "设备名称")
    private String equipment_name;

    @ApiModelProperty(value = "设置时间")
    private LocalDateTime settingtime;

    @ApiModelProperty(value = "设备人")
    private String username;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "应答时间")
    private LocalDateTime responsetime;

    @ApiModelProperty(value = "版本号")
    private String vision;

    @ApiModelProperty(value = "文件路径")
    private String filepath;

    @ApiModelProperty(value = "文件长度")
    private Integer filelength;
}
