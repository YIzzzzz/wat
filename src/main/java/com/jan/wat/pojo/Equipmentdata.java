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
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Equipmentdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CollectTime")
    private Date collecttime;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("UploadTime")
    private Date uploadtime;

    @TableField("Data")
    private String data;

    @TableField("STR")
    private String str;


}
