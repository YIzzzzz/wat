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
public class EquBattery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("Model")
    private String model;

    @TableField("ProductDate")
    private Date productdate;

    @TableField("QualityPeriod")
    private String qualityperiod;

    @TableField("InitialPower")
    private String initialpower;

    @TableField("StartTime")
    private Date starttime;

    @TableField("EndTime")
    private Date endtime;

    @TableField("AlarmLimit")
    private Double alarmlimit;

    @TableField("CurrentValue")
    private Double currentvalue;

    @TableField("Unit_ID")
    private Integer unitId;

    @TableField("Company")
    private String company;

    @TableField("Des")
    private String des;


}
