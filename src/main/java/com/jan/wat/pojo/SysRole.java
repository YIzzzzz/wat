package com.jan.wat.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("RoleCode")
    private String rolecode;

    @TableField("RoleSeq")
    private String roleseq;

    @TableField("RoleName")
    private String rolename;

    @TableField("Description")
    private String description;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("CreateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;


    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("UpdateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedate;


}
