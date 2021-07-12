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
public class EquEquipmenttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("OffLineLimit")
    private Integer offlinelimit;


}
