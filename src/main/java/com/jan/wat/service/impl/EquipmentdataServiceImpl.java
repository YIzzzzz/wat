package com.jan.wat.service.impl;

import com.jan.wat.pojo.Equipmentdata;
import com.jan.wat.mapper.EquipmentdataMapper;
import com.jan.wat.service.IEquipmentdataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquipmentdataServiceImpl")
public class EquipmentdataServiceImpl extends ServiceImpl<EquipmentdataMapper, Equipmentdata> implements IEquipmentdataService {

}
