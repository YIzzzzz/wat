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
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdvertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("Title")
    private String title;

    @TableField("Message")
    private String message;

    @TableField("Visible")
    private Boolean visible;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private Date updatedate;


}
