package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysLog;
import com.jan.wat.mapper.SysLogMapper;
import com.jan.wat.service.ISysLogService;
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
@Service("SysLogServiceImpl")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
