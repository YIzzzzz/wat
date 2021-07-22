package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

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
public class EquSimpackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("Term")
    private Integer term;

    @TableField("StartFlag")
    private Boolean startflag;

    @TableField("AlarmValue")
    private Double alarmvalue;

    @TableField("AlarmType")
    private Boolean alarmtype;

    @TableField("Price")
    private Double price;

    @TableField("TotalFlow")
    private Double totalflow;

    @TableField("OverFlowPrice")
    private Double overflowprice;

    @TableField("Des")
    private String des;


}
