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
public class SysOrganize implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("OrganizeCode")
    private String organizecode;

    @TableField("ParentCode")
    private String parentcode;

    @TableField("OrganizeSeq")
    private String organizeseq;

    @TableField("OrganizeName")
    private String organizename;

    @TableField("Description")
    private String description;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private Date updatedate;


}
