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
public class EquResponserecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CollectTime")
    private Date collecttime;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("ResponseTime")
    private Date responsetime;

    @TableField("ResponesType")
    private String responestype;

    @TableField("IPAddress")
    private String ipaddress;

    @TableField("Port")
    private Integer port;

    @TableField("sendData")
    private String senddata;


}
