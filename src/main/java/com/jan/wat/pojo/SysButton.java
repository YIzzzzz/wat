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
public class SysButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ButtonCode")
    private String buttoncode;

    @TableField("ButtonName")
    private String buttonname;

    @TableField("ButtonSeq")
    private Integer buttonseq;

    @TableField("Description")
    private String description;

    @TableField("ButtonIcon")
    private String buttonicon;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private Date updatedate;


}
