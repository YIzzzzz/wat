package com.jan.wat.exception;

import com.jan.wat.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 *
 * phm
 * 2021/7/19 上午9:57
 * 1.0
 */

@RestController
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据操作失败！");
        }
        return RespBean.error("数据库异常！");
    }
}
