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
public class WatDayaccumulate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("DayTime")
    private Date daytime;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("StartTime")
    private Date starttime;

    @TableField("EndTime")
    private Date endtime;

    @TableField("BeforeYesterdayAccmulate")
    private Double beforeyesterdayaccmulate;

    @TableField("LastAccumulate")
    private Double lastaccumulate;

    @TableField("Accumulate")
    private Double accumulate;

    @TableField("SevenDaysAccumulate")
    private Double sevendaysaccumulate;

    @TableField("LastYearAccumulate")
    private Double lastyearaccumulate;

    @TableField("AvgFlow")
    private Double avgflow;

    @TableField("LastDaySameTimeAvgFlow")
    private Double lastdaysametimeavgflow;

    @TableField("RelativeRatio")
    private Double relativeratio;

    @TableField("IncreaseNum")
    private Integer increasenum;

    @TableField("DecreaseNum")
    private Integer decreasenum;

    @TableField("ZeroNum")
    private Integer zeronum;

    @TableField("HourMaxAccumulate")
    private Double hourmaxaccumulate;

    @TableField("HourMaxTime")
    private Date hourmaxtime;

    @TableField("HourMinAccumulate")
    private Double hourminaccumulate;

    @TableField("HourMinTime")
    private Date hourmintime;

    @TableField("PeakAccumulate")
    private Double peakaccumulate;

    @TableField("ValleyAccumulate")
    private Double valleyaccumulate;

    @TableField("NormalAccumulate")
    private Double normalaccumulate;


}
