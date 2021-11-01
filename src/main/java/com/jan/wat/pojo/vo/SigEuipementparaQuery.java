package com.jan.wat.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @hor:ZXJ
 * @time:2021/8/4下午3:04
 * @description
 */
@Data
public class SigEuipementparaQuery {

    private String unit_Name;
    private String paravalue;
    private String paravalue_ID;
    private String equipment_ID;
    private Integer para_ID;
    private Date uploadTime;
    private Double upLimit;
    private Double DownLimit;
    private Integer Type;
    private String para_Name;
    private Boolean ReadOnly;
    public SigEuipementparaQuery(MulEquipparaQuery query, String equipment_ID){
        this.para_Name = query.getPara_Name();
        this.paravalue = query.getChangeValue();
        this.upLimit = query.getUpLimit();
        this.DownLimit = query.getDownLimit();
        this.Type = query.getPType();
        this.para_ID = query.getPara_ID();
        this.equipment_ID = equipment_ID;
    }
    public SigEuipementparaQuery(){}
}
