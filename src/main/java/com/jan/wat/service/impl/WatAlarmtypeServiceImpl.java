package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatAlarmtype;
import com.jan.wat.mapper.WatAlarmtypeMapper;
import com.jan.wat.service.IWatAlarmtypeService;
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
@Service("WatAlarmtypeServiceImpl")
public class WatAlarmtypeServiceImpl extends ServiceImpl<WatAlarmtypeMapper, WatAlarmtype> implements IWatAlarmtypeService {

}
