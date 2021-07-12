package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquUnit;
import com.jan.wat.mapper.EquUnitMapper;
import com.jan.wat.service.IEquUnitService;
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
@Service("EquUnitServiceImpl")
public class EquUnitServiceImpl extends ServiceImpl<EquUnitMapper, EquUnit> implements IEquUnitService {

}
