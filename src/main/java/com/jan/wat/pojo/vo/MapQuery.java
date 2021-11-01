package com.jan.wat.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @hor:ZXJ
 * @time:2021/10/30下午2:28
 * @description
 */
@Data
public class MapQuery {

    @TableId("ID")
    private String id;

    @TableField("Name")
    private String name;

    @TableField("Longitude")
    private Float longitude;

    @TableField("Latitude")
    private Float latitude;

    @TableField("Status")
    private Boolean s;

    private Boolean flag = false;


}
