package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @hor:ZXJ
 * @time:2021/8/19下午1:55
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUsermenumap {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("RoleCode")
    private String rolecode;

    @TableField("MenuCode")
    private String menucode;

    @TableId("UserCode")
    private String usercode;

}
