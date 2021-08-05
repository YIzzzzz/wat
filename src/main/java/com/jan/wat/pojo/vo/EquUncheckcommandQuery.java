package com.jan.wat.pojo.vo;


import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquUncheckcommandQuery {

    private String equipment_name;

    private String des;

    private String equipment_id;

    private Integer id;

    private Integer commandtype;

    private Integer sendnum;

    private String responsemessage;

    private LocalDateTime settingtime;

    private LocalDateTime sendtime;

    private LocalDateTime responsetime;

    private String status;

    private String username;
}
