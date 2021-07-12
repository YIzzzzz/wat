package com.jan.wat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class SysLoginhistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UserCode")
    private String usercode;

    @TableField("UserName")
    private String username;

    @TableField("HostName")
    private String hostname;

    @TableField("HostIP")
    private String hostip;

    @TableField("LoginCity")
    private String logincity;

    @TableField("LoginDate")
    private Date logindate;

    @TableField("LogoutDate")
    private Date logoutdate;


}
