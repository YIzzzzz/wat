package com.jan.wat.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EquParaQuery {

    @ApiModelProperty(value = "参数代码")
    private Integer id;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "参数组id")
    private Integer paragroup_id;

    @ApiModelProperty(value = "参数组名称")
    private String paraname;

    @ApiModelProperty(value = "参数类型")
    private Integer type;

    @ApiModelProperty(value = "是否只读")
    private boolean readonly;

}
