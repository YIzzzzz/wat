package com.jan.wat.pojo;

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
public class EquEquipmentparasettingrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("Para")
    private String para;

    @TableField("Des")
    private String des;

    @TableField("SettingTime")
    private Date settingtime;

    @TableField("UserCode")
    private String usercode;

    @TableField("SendTime")
    private Date sendtime;

    @TableField("SendNum")
    private Integer sendnum;

    @TableField("SuccessFlag")
    private Boolean successflag;

    @TableField("ResponseTime")
    private Date responsetime;

    @TableField("ResponseMessage")
    private String responsemessage;

    @TableField("CheckTime")
    private Date checktime;


}
