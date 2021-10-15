package com.jan.wat.pojo.vo;

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
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquipmentQuery {
    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Name")
    private String name;

}
