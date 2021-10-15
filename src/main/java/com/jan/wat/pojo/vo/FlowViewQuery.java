package com.jan.wat.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FlowViewQuery {
    @ApiModelProperty(value = "设备名称")
    private String equipment_name;
    @ApiModelProperty(value = "水表口径id")
    private Integer caliber_id;
    @ApiModelProperty(value = "用水性质id")
    private Integer classify_id;
    @ApiModelProperty(value = "水表型号")
    private String model;
    @ApiModelProperty(value = "客户名称")
    private String clientname;
    @ApiModelProperty(value = "册本号")
    private String bookno;
    @ApiModelProperty(value = "水价")
    private double price;
    @ApiModelProperty(value = "用水面积")
    private double areas;
    @ApiModelProperty(value = "用水人数")
    private double persons;
    @ApiModelProperty(value = "用水性质")
    private String classify_name;
    @ApiModelProperty(value = "水表口径")
    private String caliber_name;
    @ApiModelProperty(value = "用水类别id")
    private Integer category;
    @ApiModelProperty(value = "报警状态")
    private boolean status;
    @ApiModelProperty(value = "表具型号id")
    private Integer flowtype_id;
    @ApiModelProperty(value = "表具型号")
    private String flowtype_name;
    @ApiModelProperty(value = "备注")
    private String des;
    @ApiModelProperty(value = "GPRS站点号")
    private String siteno;
    @ApiModelProperty(value = "储水方式")
    private String storemode;
    @ApiModelProperty(value = "设备id")
    private String equipment_id;
    @ApiModelProperty(value = "使能")
    private boolean enable;
    @ApiModelProperty(value = "用水类别")
    private String category_name;
}
