package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleeditQuery {

    @ApiModelProperty(value = "菜单编码")
    private String menucode;

    @ApiModelProperty(value = "父节点")
    private String parentcode;

    @ApiModelProperty(value = "菜单名称")
    private String menuname;

    @ApiModelProperty(value = "是否显示")
    private String chk;

}
