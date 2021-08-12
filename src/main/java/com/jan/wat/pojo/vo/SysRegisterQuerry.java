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
}
