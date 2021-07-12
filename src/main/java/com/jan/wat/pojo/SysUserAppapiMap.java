package com.jan.wat.pojo;

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
public class SysUserAppapiMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("AppApi_ID")
    private Integer appapiId;

    @TableField("UserCode")
    private String usercode;


}
