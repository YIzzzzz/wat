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
public class EquServerEquipmentMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Server_ID")
    private Integer serverId;

    @TableField("Equipment_ID")
    private String equipmentId;

    @TableField("LastDownloadDataTime")
    private Date lastdownloaddatatime;

    @TableField("DataPacket")
    private String datapacket;


}
