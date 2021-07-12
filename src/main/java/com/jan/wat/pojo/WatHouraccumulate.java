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
public class WatHouraccumulate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("HourTime")
    private Date hourtime;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("StartTime")
    private Date starttime;

    @TableField("EndTime")
    private Date endtime;

    @TableField("Accumulate")
    private Double accumulate;


}
