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
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquUpdateprogramrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("Vision")
    private String vision;

    @TableField("FilePath")
    private String filepath;

    @TableField("FileLength")
    private Integer filelength;

    @TableField("SettingTime")
    private Date settingtime;

    @TableField("UserCode")
    private String usercode;

    @TableField("Status")
    private Integer status;

    @TableField("ResponseTime")
    private Date responsetime;


}
