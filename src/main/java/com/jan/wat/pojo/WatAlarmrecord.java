package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WatAlarmrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("AlarmType_ID")
    private Integer alarmtypeId;

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
