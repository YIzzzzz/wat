package com.jan.wat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * phm
 * 2021/7/17 下午4:54
 * 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     *
     * 成功返回
     * @param message 正确信息
     */
    public static RespBean success(String message){
        return new RespBean(200, message, null);
    }
    /**
     *
     * 成功返回结果
     * @param message 正确信息
     * @param obj 返回对象
     */
    public static RespBean success(String message, Object obj){
        return new RespBean(200, message, obj);
    }

    /**
     * 失败返回
     * @param message 错误信息
     */
    public static RespBean error(String message){
        return new RespBean(500, message, null);
    }
    /**
     * 失败返回
     * @param message 错误信息
     * @param obj 返回对象
     */
    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj);
    }
}
