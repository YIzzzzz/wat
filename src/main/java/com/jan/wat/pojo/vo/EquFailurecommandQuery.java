package com.jan.wat.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquFailurecommandQuery {

    private String equipment_name;

    private String des;

    private String checkdes;

    private String equipment_id;

    private Integer id;

    private Integer commandtype;

    private Integer sendnum;

    private String responsemessage;

    private LocalDateTime settingtime;

    private LocalDateTime sendtime;

    private LocalDateTime responsetime;

    private LocalDateTime checktime;

    private String status;

    private String username;

    private String checkusername;
}
