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
public class EquEquipmentpara implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Equipment_ID")
    private String equipmentId;

    @TableField("Para_ID")
    private Integer paraId;

    @TableField("ParaValue")
    private String paravalue;

    @TableField("UploadTime")
    private Date uploadtime;

    @TableField("Unit_ID")
    private Integer unitId;

    @TableField("UpLimit")
    private Double uplimit;

    @TableField("DownLimit")
    private Double downlimit;


}
