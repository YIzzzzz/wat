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
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquSim implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("UpdateDate")
    private Date updatedate;

    @TableField("UpdatePerson")
    private String updateperson;

    @TableField("CreateDate")
    private Date createdate;

    @TableField("CreatePerson")
    private String createperson;

    @TableField("ActivateTime")
    private Date activatetime;

    @TableField("Status")
    private String status;

    @TableField("StatusLastUpdateTime")
    private Date statuslastupdatetime;

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

    @TableField("BalanceLastUpdateTime")
    private Date balancelastupdatetime;

    @TableField("Enable")
    private Boolean enable;

    @TableField("ReadNum")
    private Integer readnum;


}
