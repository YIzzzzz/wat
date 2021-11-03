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
 * @since 2021-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EquEquipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Name")
    private String aname;

    @TableField("EquipmentType_ID")
    private Integer equipmenttypeId;

    @TableField("Manufacturer_ID")
    private Integer manufacturerId;

    @TableField("Status")
    private Boolean s;

    @TableField("UploadNum")
    private Integer uploadnum;

    @TableField("Address")
    private byte[] address;

    @TableField("SetupTime")
    private Date setuptime;

    @TableField("SetupPerson")
    private String setupperson;

    @TableField("Model")
    private String model;

    @TableField("FirstUploadTime")
    private Date firstuploadtime;

    @TableField("LastUploadTime")
    private Date lastuploadDestime;

    @TableField("LastCollectTime")
    private Date lastcollecttime;

    @TableField("IPAddress")
    private String ipaddress;

    @TableField("Port")
    private Integer p;

    @TableField("Longitude")
    private Float longitude;

    @TableField("Latitude")
    private Float latitude;

    @TableField("Version")
    private String version;

    @TableField("Manager")
    private String manager;

    @TableField("Telephone")
    private String telephone;

    @TableField("PowerType")
    private Integer powertype;

    @TableField("PicPath")
    private String picpath;

    @TableField("FilePath")
    private String filepath;

    @TableField("Des")
    private String des;

    @TableField("SetupAddress")
    private String setupaddress;

    @TableField("OffLineTimeLimit")
    private Integer offlinetimelimit;

    @TableField("ConnectServerPort")
    private Integer connectserverport;


    private String manufacturername;

    private String equipmenttypename;
//    private String equipmentType_id;

    @TableField("checked")
    private boolean checked;




}
