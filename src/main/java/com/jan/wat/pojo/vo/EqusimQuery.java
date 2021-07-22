package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * phm
 * 2021/7/21 下午4:52
 * 1.0
 */

@Data
public class EqusimQuery {

    @ApiModelProperty(value = "API")
    private String api;

    @ApiModelProperty(value = "套餐类型")
    private String simpackage;

    @ApiModelProperty(value = "Simkeiccid")
    private String ccid;

}
