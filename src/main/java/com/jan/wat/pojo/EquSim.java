package com.jan.wat.pojo;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquSim implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss",timezone = "Asia/Shanghai")
    @TableField("UpdateDate")
    private LocalDateTime updatedate;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss")
    @TableField("UpdatePerson")
    private String updateperson;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss")
    @TableField("CreateDate")
    private LocalDateTime createdate;

    @TableField("CreatePerson")
    private String createperson;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss")
    @TableField("ActivateTime")
    private LocalDateTime activatetime;

    @TableField("Status")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss")
    @TableField("StatusLastUpdateTime")
    private LocalDateTime statuslastupdatetime;

    @TableField("SIMPackage_ID")
    private Integer simpackageId;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("Des")
    private String des;

    @TableField("SIMAPP_ID")
    private String simappId;

    @TableField("Balance")
    private Double balance;

    @JsonFormat(pattern = "yyyy-MM-dd-hh-ss")
    @TableField("BalanceLastUpdateTime")
    private LocalDateTime balancelastupdatetime;

    @TableField("Enable")
    private Boolean enable;

    @TableField("ReadNum")
    private Integer readnum;


}
