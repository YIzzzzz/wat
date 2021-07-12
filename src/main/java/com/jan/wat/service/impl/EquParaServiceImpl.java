package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquPara;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.service.IEquParaService;
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
@Service("EquParaServiceImpl")
public class EquParaServiceImpl extends ServiceImpl<EquParaMapper, EquPara> implements IEquParaService {

}
