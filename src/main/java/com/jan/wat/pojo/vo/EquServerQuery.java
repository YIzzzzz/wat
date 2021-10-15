package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquServerQuery {

    @ApiModelProperty(value = "数据类型名称")
    private String datatypename;

    @ApiModelProperty(value = "unitname")
    private String unitname;
}
