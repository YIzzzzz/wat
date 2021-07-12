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
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquUserEquipmentgroupMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("UserCode")
    private String usercode;

    @TableField("EquipmentGroup_ID")
    private Integer equipmentgroupId;


}
