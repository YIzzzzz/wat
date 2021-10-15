package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquAlarmQueryDes {

    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "确认描述")
    private String des;
}
