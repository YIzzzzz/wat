package com.jan.wat.service;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquDatatype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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
    public List<EquDatatype> getEquDataType(String id);
    public JSONObject getRealCurve(String usercode, String equipment_id, String datatype_id, String startTime, String endTime);
    public List<EquDatatype> getMulDataType(List<String> list);
}
