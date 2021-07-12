package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysParameter;
import com.jan.wat.mapper.SysParameterMapper;
import com.jan.wat.service.ISysParameterService;
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
@Service("SysParameterServiceImpl")
public class SysParameterServiceImpl extends ServiceImpl<SysParameterMapper, SysParameter> implements ISysParameterService {

}
