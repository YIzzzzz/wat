package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysRegisterQuerry {

    @ApiModelProperty(value = "用户编码")
    private String usercode;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "描述说明")
    private String description;

    @ApiModelProperty(value = "是否启用")
    private boolean isenable;

    @ApiModelProperty(value = "登录次数")
    private String logincount;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastlogindate;

    @ApiModelProperty(value = "创建人")
    private String createperson;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdate;

    @ApiModelProperty(value = "最后更新人")
    private String updateperson;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updatedate;

    @ApiModelProperty(value = "角色编码")
    private String rolecode;

    @ApiModelProperty(value = "角色名称")
    private String rolename;

    @ApiModelProperty(value = "排序")
    private String roleseq;
}
