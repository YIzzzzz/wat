package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUsermenumap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("MenuCode")
    private String menucode;

    @TableField("UserCode")
    private String usercode;


}
