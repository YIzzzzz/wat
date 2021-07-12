package com.jan.wat.pojo;

import java.util.Date;
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
public class SysSystemalarm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ReceiveSoftRecordTime")
    private Date receivesoftrecordtime;

    @TableField("FlowSoftRecordTime")
    private Date flowsoftrecordtime;

    @TableField("CDiskSpace")
    private Double cdiskspace;

    @TableField("DataBaseDiskSpace")
    private Double databasediskspace;


}
