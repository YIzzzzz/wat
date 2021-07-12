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
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("CommandType")
    private Integer commandtype;

    @TableField("SettingTime")
    private Date settingtime;

    @TableField("Command")
    private String command;

    @TableField("UserCode")
    private String usercode;

    @TableField("Status")
    private Integer status;

    @TableField("SendTime")
    private Date sendtime;

    @TableField("SendNum")
    private Integer sendnum;

    @TableField("ResponseTime")
    private Date responsetime;

    @TableField("ResponseMessage")
    private String responsemessage;

    @TableField("CheckTime")
    private Date checktime;

    @TableField("CheckUserCode")
    private String checkusercode;

    @TableField("Des")
    private String des;

    @TableField("CheckDes")
    private String checkdescribe;


}
