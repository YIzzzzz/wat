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
public class SysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Code")
    private String code;

    @TableField("Value")
    private String value;

    @TableField("Text")
    private String text;

    @TableField("ParentCode")
    private String parentcode;

    @TableField("Seq")
    private String seq;

    @TableField("IsEnable")
    private Boolean isenable;

    @TableField("IsDefault")
    private Boolean isdefault;

    @TableField("Description")
    private String description;

    @TableField("CodeTypeName")
    private String codetypename;

    @TableField("CodeType")
    private String codetype;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private Date updatedate;


}
