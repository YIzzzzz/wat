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
public class WatWeekaccumulate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("WeekTime")
    private Date weektime;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("StartTime")
    private Date starttime;

    @TableField("EndTime")
    private Date endtime;

    @TableField("LastAccumulate")
    private Double lastaccumulate;

    @TableField("Accumulate")
    private Double accumulate;

    @TableField("LastWeekSameDateAccumulate")
    private Double lastweeksamedateaccumulate;

    @TableField("LastYearAccumulate")
    private Double lastyearaccumulate;

    @TableField("AvgFlow")
    private Double avgflow;

    @TableField("DayMaxAccumulate")
    private Double daymaxaccumulate;

    @TableField("DayMaxTime")
    private Date daymaxtime;

    @TableField("DayMinAccumulate")
    private Double dayminaccumulate;

    @TableField("DayMinTime")
    private Date daymintime;


}
