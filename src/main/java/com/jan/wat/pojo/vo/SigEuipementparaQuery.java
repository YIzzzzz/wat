package com.jan.wat.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @hor:ZXJ
 * @time:2021/8/4下午3:04
 * @description
 */
@Data
public class SigEuipementparaQuery {

    private String unit_Name;
    private String paravalue;
    private String equipment_ID;
    private Integer para_ID;
    private LocalDateTime uploadTime;
    private Double upLimit;
    private Double DownLimit;
    private Integer Type;
    private String para_Name;
    private Boolean ReadOnly;
}
