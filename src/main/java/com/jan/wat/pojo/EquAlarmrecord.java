package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquAlarmrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Integer id;

    @TableId("Equipment_ID")
    private String equipmentId;

    @TableField("DataType_ID")
    private Integer datatypeId;

    @TableField("AlarmType")
    private Integer alarmtype;

    @TableField("AlarmTime")
    private Date alarmtime;

    @TableField("ConfirmTime")
    private Date confirmtime;

    @TableField("UserCode")
    private String usercode;

    @TableField("Recovery")
    private Boolean recovery;

    @TableField("RecoveryTime")
    private Date recoverytime;

    @TableField("Reason")
    private String reason;

    @TableField("Des")
    private String des;


}
