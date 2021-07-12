package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class SysUsersetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UserCode")
    private String usercode;

    @TableField("SettingCode")
    private String settingcode;

    @TableField("SettingName")
    private String settingname;

    @TableField("SettingValue")
    private String settingvalue;

    @TableField("Description")
    private String description;


}
