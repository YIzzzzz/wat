package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleeditQuery {

    @ApiModelProperty(value = "菜单编码")
    private String index;

    @ApiModelProperty(value = "父节点")
    private String parentcode;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "是否显示")
    private String chk;

    @ApiModelProperty(value = "图标")
    private String iconclass;

    @ApiModelProperty(value = "路径")
    private String url;

    public SysRoleeditQuery(){}
    public SysRoleeditQuery(SysRoleeditQuery query){
        index = query.getIndex();
        parentcode = query.getParentcode();
        title = query.getTitle();
        chk = query.getChk();
        iconclass = query.getIconclass();
        url = query.getUrl();
    }

}
