package com.jan.wat.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @hor:ZXJ
 * @time:2021/8/6下午1:57
 * @description
 */
@Data
public class FailureAndHistoryCommandQuery {

    @ApiModelProperty(value = "设备名称")
    private String equipmentName;
    @ApiModelProperty(value = "命令描述")
    private String des;
    @ApiModelProperty(value = "确认描述")
    private String checkdes;
    @ApiModelProperty(value = "设备编码")
    private String equipmentId;
    @ApiModelProperty(value = "编码")
    private Integer id;
    @ApiModelProperty(value = "命令类型")
    private Integer commandtype;
    @ApiModelProperty(value = "发送次数")
    private Integer sendnum;
    @ApiModelProperty(value = "应答消息")
    private String responsemessage;
    @ApiModelProperty(value = "设置时间")
    private LocalDateTime settingtime;
    @ApiModelProperty(value = "最后一次发送时间")
    private LocalDateTime sendtime;
    @ApiModelProperty(value = "应答时间")
    private LocalDateTime responsetime;
    @ApiModelProperty(value = "确认时间")
    private LocalDateTime checktime;
    @ApiModelProperty(value = "状态")
    private String s;
    @ApiModelProperty(value = "设置人")
    private String username;
    @ApiModelProperty(value = "确认人")
    private String checkuserName;
}
