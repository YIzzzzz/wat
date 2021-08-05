package com.jan.wat.pojo.vo;

import lombok.Data;

/**
 * @hor:ZXJ
 * @time:2021/8/5下午1:19
 * @description
 */
@Data
public class MulEquipparaQuery {
    private Integer para_ID;
    private Integer pType;
    private String para_Name;
    private Double UpLimit;
    private Double DownLimit;
}
