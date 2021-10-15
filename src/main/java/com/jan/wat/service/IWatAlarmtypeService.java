package com.jan.wat.service;

import com.jan.wat.pojo.WatAlarmtype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IWatAlarmtypeService extends IService<WatAlarmtype> {

    public List<String> getEquipment(Integer alarmtype_id, Integer equipmentgroup_id);
}
