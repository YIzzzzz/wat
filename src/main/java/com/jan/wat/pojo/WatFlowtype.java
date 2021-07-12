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
public class WatFlowtype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("Type")
    private String type;

    @TableField("LLFlow")
    private Double llflow;

    @TableField("LFlow")
    private Double lflow;

    @TableField("HFlow")
    private Double hflow;

    @TableField("HHFlow")
    private Double hhflow;

    @TableField("Des")
    private String des;

    @TableField("PressureLoss")
    private Double pressureloss;

    @TableField("MaxValue")
    private Double maxvalue;

    @TableField("Manufacturer")
    private String manufacturer;


}
