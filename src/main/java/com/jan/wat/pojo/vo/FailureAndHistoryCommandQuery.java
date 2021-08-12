package com.jan.wat.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @hor:ZXJ
 * @time:2021/8/6下午1:57
 * @description
 */
@Data
public class FailureAndHistoryCommandQuery {
    private String equipmentName;
    private String des;
    private String checkdes;
    private String equipmentId;
    private Integer id;
    private Integer commandtype;
    private Integer sendnum;
    private String responsemessage;
    private LocalDateTime settingtime;
    private LocalDateTime sendtime;
    private LocalDateTime responsetime;
    private LocalDateTime checktime;
    private String s;
    private String username;
    private String checkuserName;
}
