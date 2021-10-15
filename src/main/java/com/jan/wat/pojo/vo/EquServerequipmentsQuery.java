package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquServerequipmentsQuery {

    @ApiModelProperty(value = "设备编码")
    private String equipment_id;

    @ApiModelProperty(value = "数据内容")
    private String data;
}
