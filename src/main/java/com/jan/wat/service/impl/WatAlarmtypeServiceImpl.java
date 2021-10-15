package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatAlarmtype;
import com.jan.wat.mapper.WatAlarmtypeMapper;
import com.jan.wat.service.IWatAlarmtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("WatAlarmtypeServiceImpl")
public class WatAlarmtypeServiceImpl extends ServiceImpl<WatAlarmtypeMapper, WatAlarmtype> implements IWatAlarmtypeService {

    @Autowired
    WatAlarmtypeMapper watAlarmtypeMapper;

    @Override
    public List<String> getEquipment(Integer alarmtype_id, Integer equipmentgroup_id) {
        return watAlarmtypeMapper.getEquipment(alarmtype_id, equipmentgroup_id);
    }
}
