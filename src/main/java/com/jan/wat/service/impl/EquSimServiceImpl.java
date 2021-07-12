package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquSim;
import com.jan.wat.mapper.EquSimMapper;
import com.jan.wat.service.IEquSimService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquSimServiceImpl")
public class EquSimServiceImpl extends ServiceImpl<EquSimMapper, EquSim> implements IEquSimService {

}
