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
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("MenuCode")
    private String menucode;

    @TableField("ParentCode")
    private String parentcode;

    @TableField("MenuName")
    private String menuname;

    @TableField("URL")
    private String url;

    @TableField("IconClass")
    private String iconclass;

    @TableField("IconURL")
    private String iconurl;

    @TableField("MenuSeq")
    private String menuseq;

    @TableField("Description")
    private String description;

    @TableField("IsVisible")
    private Boolean isvisible;

    @TableField("IsEnable")
    private Boolean isenable;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private Date updatedate;


}
