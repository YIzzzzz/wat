package com.jan.wat.pojo;

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
public class EquEquipmentparavaluerange implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Equipment_ID")
    private String equipmentId;

    @TableField("Para_ID")
    private Integer paraId;

    @TableField("ParaValue_ID")
    private Integer paravalueId;


}
