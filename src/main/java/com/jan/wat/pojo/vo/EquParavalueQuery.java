package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquParavalueQuery {

    @ApiModelProperty(value = "参数编码")
    private Integer para_id;

    @ApiModelProperty(value = "参数名称")
    private String para_name;

    @ApiModelProperty(value = "参数值代码")
    private String paravalue_id;

    @ApiModelProperty(value = "参数值")
    private String name;
}
