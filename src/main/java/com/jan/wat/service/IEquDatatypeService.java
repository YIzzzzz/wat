package com.jan.wat.service;

import com.jan.wat.pojo.EquDatatype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IEquDatatypeService extends IService<EquDatatype> {
    public Map<Integer, String> getMap();
}
