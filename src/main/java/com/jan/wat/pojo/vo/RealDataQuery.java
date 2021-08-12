package com.jan.wat.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @hor:ZXJ
 * @time:2021/8/9下午2:31
 * @description
 */
@Data
public class RealDataQuery {
    private String id;
    private String n;
    private Date lastcollecttime;
    private String equipmentalarm;
    private String  outLinealarm;
}
