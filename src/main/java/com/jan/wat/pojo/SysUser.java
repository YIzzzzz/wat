package com.jan.wat.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("UserCode")
    private String usercode;

    @TableField("UserSeq")
    private String userseq;

    @TableField("UserName")
    private String username;

    @TableField("Description")
    private String description;

    @TableField("Password")
    private String password;

    @TableField("RoleName")
    private String rolename;

    @TableField("OrganizeName")
    private String organizename;

    @TableField("ConfigJSON")
    private String configjson;

    @TableField("IsEnable")
    private Boolean isenable;

    @TableField("LoginCount")
    private Integer logincount;

    @TableField("LastLoginDate")
    private Date lastlogindate;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    private LocalDateTime updatedate;


}
