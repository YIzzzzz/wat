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
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquEquipmentrealdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Equipment_ID")
    private String equipmentId;

    @TableField("DataType_ID")
    private Integer datatypeId;

    @TableField("Value")
    private Double value;

    @TableField("Unit_ID")
    private Integer unitId;

    @TableField("Position")
    private Integer position;

    @TableField("IfRecord")
    private Boolean ifrecord;

    @TableField("IfCurve")
    private Boolean ifcurve;

    @TableField("IfAlarm")
    private Boolean ifalarm;

    @TableField("IfAccumulate")
    private Boolean ifaccumulate;

    @TableField("UpLimit")
    private Double uplimit;

    @TableField("DownLimit")
    private Double downlimit;

    @TableField("LastUpdateTime")
    private Date lastupdatetime;


}
