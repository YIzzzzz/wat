package com.jan.wat.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @hor:ZXJ
 * @time:2021/12/12下午8:02
 * @description
 */
@Data
public class AccumulateDataQuery {
    private LocalDateTime collectTime;
    private String equipment_ID;
    private String equipment_Name;
    private Float positive = 0.0F;
    private Float negative = 0.0F;
    private Float diff = 0.0F;
    private Float heat = 0.0F;
    private Float cold = 0.0F;
}
