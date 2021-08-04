package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReadEquipmentparaQuery {

    @ApiModelProperty(value = "checked")
    private String checked;

    @ApiModelProperty(value = "设备代码")
    private String id;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "设备类型")
    private Integer equipmenttype_id;

    @ApiModelProperty(value = "供电类型")
    private Integer powertype;
}
